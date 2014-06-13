/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.internal.core;

import java.io.InputStream;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;

import com.hundsun.ares.studio.core.ARESModelException;
import com.hundsun.ares.studio.core.ARESModelStatus;
import com.hundsun.ares.studio.core.IARESElement;
import com.hundsun.ares.studio.core.IARESModelStatus;
import com.hundsun.ares.studio.core.IARESModelStatusConstants;

/**
 * ARES模型操作的Operation,实现了IWorkspaceRunnable接口，对底层资源操作进行封装，
 * 多个资源操作最终形成了一个IWorkspaceRunnable运行，避免大量资源改变事件。
 * @author sundl
 */
public abstract class ARESModelOperation implements IWorkspaceRunnable {

	/**
	 * The progress monitor passed into this operation
	 */
	public IProgressMonitor progressMonitor= null;
	
	/**
	 * The elements this operation operates on,
	 * or <code>null</code> if this operation
	 * does not operate on specific elements.
	 */
	protected IARESElement[] elementsToProcess;	
	/**
	 * The parent elements this operation operates with
	 * or <code>null</code> if this operation
	 * does not operate with specific parent elements.
	 */
	protected IARESElement[] parentElements;
	/**
	 * An empty collection of <code>IJavaElement</code>s - the common
	 * empty result if no elements are created, or if this
	 * operation is not actually executed.
	 */
	protected static final IARESElement[] NO_ELEMENTS= new IARESElement[] {};
	/**
	 * The elements created by this operation - empty
	 * until the operation actually creates elements.
	 */
	protected IARESElement[] resultElements = NO_ELEMENTS;
	/**
	 * A flag indicating whether this operation is nested.
	 */
	protected boolean isNested = false;
	/**
	 * Conflict resolution policy - by default do not force (fail on a conflict).
	 */
	protected boolean force = false;
	
	public ARESModelOperation(IARESElement[] elementsToProElements, IARESElement[] parentElements, boolean force) {
		this.elementsToProcess = elementsToProElements;
		this.parentElements = parentElements;
		this.force = force;
	}
	
	public void beginTask(String name, int totalWork) {
		if (this.progressMonitor != null)
			this.progressMonitor.beginTask(name, totalWork);
	} 
	
	protected void checkCanceled() {
		if (isCanceled()) {
			throw new OperationCanceledException();
		}
	}
	
	/**
	 * @see IProgressMonitor
	 */
	public boolean isCanceled() {
		if (this.progressMonitor != null) {
			return this.progressMonitor.isCanceled();
		}
		return false;
	}
	
	/**
	 * @see IProgressMonitor
	 */
	public void worked(int work) {
		if (this.progressMonitor != null) {
			this.progressMonitor.worked(work);
			checkCanceled();
		}
	}
	
	/**
	 * @see IProgressMonitor
	 */
	public void done() {
		if (this.progressMonitor != null) {
			this.progressMonitor.done();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.resources.IWorkspaceRunnable#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws CoreException {
		try {
			excuteOperation();
		} finally {
			
		}
	}

	public void runOperation(IProgressMonitor monitor) throws ARESModelException{
		IARESModelStatus status = verify();
		if (!status.isOK()) {
			throw new ARESModelException(status);
		}
		
		try {
			if (isReadOnly()) {
				run(monitor);
			} else {
				ResourcesPlugin.getWorkspace().run(this, getSchedulingRule(), IWorkspace.AVOID_UPDATE, monitor);
			}
		} catch (CoreException e) {
			if (e instanceof ARESModelException) {
				throw (ARESModelException)e;
			} else {
				if (e.getStatus().getCode() == IResourceStatus.OPERATION_FAILED) {
					Throwable ee = e.getStatus().getException();
					if (ee instanceof ARESModelException) {
						throw (ARESModelException)ee;
					}
				}
				throw new ARESModelException(e);
			}
		}
	}
	
	/**
	 * @throws ARESModelException
	 */
	protected abstract void excuteOperation() throws ARESModelException;
	
	/**
	 * Common code used to verify the elements this operation is processing.
	 * @see ARESModelOperation#verify()
	 */
	protected IARESModelStatus commonVerify() {
		if (this.elementsToProcess == null || this.elementsToProcess.length == 0) {
			return new ARESModelStatus(IARESModelStatusConstants.NO_ELEMENTS_TO_PROCESS);
		}
		for (int i = 0; i < this.elementsToProcess.length; i++) {
			if (this.elementsToProcess[i] == null) {
				return new ARESModelStatus(IARESModelStatusConstants.NO_ELEMENTS_TO_PROCESS);
			}
		}
		return ARESModelStatus.VERIFIED_OK;
	}
	
	/**
	 * Convenience method to copy resources
	 */
	protected void copyResources(IResource[] resources, IPath container) throws ARESModelException {
		IProgressMonitor subProgressMonitor = getSubProgressMonitor(resources.length);
		IWorkspaceRoot root =  ResourcesPlugin.getWorkspace().getRoot();
		try {
			for (int i = 0, length = resources.length; i < length; i++) {
				IResource resource = resources[i];
				IPath destination = container.append(resource.getName());
				if (root.findMember(destination) == null) {
					resource.copy(destination, false, subProgressMonitor);
				}
			}
			//setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
	}
	
	/**
	 * Convenience method to create a file
	 */
	protected void createFile(IContainer folder, String name, InputStream contents, boolean forceFlag) throws ARESModelException {
		IFile file= folder.getFile(new Path(name));
		try {
			file.create(
				contents,
				forceFlag ? IResource.FORCE | IResource.KEEP_HISTORY : IResource.KEEP_HISTORY,
				getSubProgressMonitor(1));
				//setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
	}
	
	/**
	 * Convenience method to create a folder
	 */
	protected void createFolder(IContainer parentFolder, String name, boolean forceFlag) throws ARESModelException {
		IFolder folder= parentFolder.getFolder(new Path(name));
		try {
			// we should use true to create the file locally. Only VCM should use tru/false
			folder.create(
				forceFlag ? IResource.FORCE | IResource.KEEP_HISTORY : IResource.KEEP_HISTORY,
				true, // local
				getSubProgressMonitor(1));
			//setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
	}
	
	/**
	 * Convenience method to delete a resource
	 */
	protected void deleteResource(IResource resource,int flags) throws ARESModelException {
		try {
			resource.delete(flags, getSubProgressMonitor(1));
			//setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
	}
	/**
	 * Convenience method to delete resources
	 */
	protected void deleteResources(IResource[] resources, boolean forceFlag) throws ARESModelException {
		if (resources == null || resources.length == 0) return;
		IProgressMonitor subProgressMonitor = getSubProgressMonitor(resources.length);
		IWorkspace workspace = resources[0].getWorkspace();
		try {
			workspace.delete(
				resources,
				forceFlag ? IResource.FORCE | IResource.KEEP_HISTORY : IResource.KEEP_HISTORY,
				subProgressMonitor);
				//setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
	}
	
	/**
	 * Convenience method to move resources
	 */
	protected void moveResources(IResource[] resources, IPath container) throws ARESModelException {
		IProgressMonitor subProgressMonitor = null;
		if (this.progressMonitor != null) {
			subProgressMonitor = new SubProgressMonitor(this.progressMonitor, resources.length, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		}
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		try {
			for (int i = 0, length = resources.length; i < length; i++) {
				IResource resource = resources[i];
				IPath destination = container.append(resource.getName());
				if (root.findMember(destination) == null) {
					resource.move(destination, false, subProgressMonitor);
				}
			}
			//setAttribute(HAS_MODIFIED_RESOURCE_ATTR, TRUE);
		} catch (CoreException e) {
			throw new ARESModelException(e);
		}
	}
		
	/**
	 * Creates and returns a subprogress monitor if appropriate.
	 */
	protected IProgressMonitor getSubProgressMonitor(int workAmount) {
		IProgressMonitor sub = null;
		if (this.progressMonitor != null) {
			sub = new SubProgressMonitor(this.progressMonitor, workAmount, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK);
		}
		return sub;
	}
	
	/*
	 * Returns the scheduling rule for this operation (i.e. the resource that needs to be locked
	 * while this operation is running.
	 * Subclasses can override.
	 */
	protected ISchedulingRule getSchedulingRule() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}
	
	/**
	 * Returns <code>true</code> if this operation performs no resource modifications,
	 * otherwise <code>false</code>. Subclasses must override.
	 */
	public boolean isReadOnly() {
		return false;
	}
	
	/**
	 * Returns a status indicating if there is any known reason
	 * this operation will fail.  Operations are verified before they
	 * are run.
	 *
	 * Subclasses must override if they have any conditions to verify
	 * before this operation executes.
	 *
	 * @see IARESModelStatus
	 */
	protected IARESModelStatus verify() {
		return commonVerify();
	}
}
