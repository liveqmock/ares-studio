/**
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: 恒生电子股份有限公司</p>
 */
package com.hundsun.ares.studio.core.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * Zip, Unzip相关工具方法
 * @author sundl
 */
public class ZipUtil {
	
	private static final String SEP = "/";

	/**
	 * 解压缩流对应的压缩文件，到指定的位置。
	 * @param is
	 * @param target
	 * @param filePattern 文件名（路径）匹配正则表达式，传入null代表全部解压
	 * @param overwrite 遇到已经存在的文件是否覆盖
	 * @param pm
	 */
	public static void Unzip(InputStream is, IContainer target, String filePattern, boolean overwrite, IProgressMonitor monitor) throws CoreException, IOException{
		ZipArchiveInputStream zip = new ZipArchiveInputStream(is, null, true);
		ArchiveEntry entry;
		monitor = Util.monitorFor(monitor);
		monitor.beginTask("", IProgressMonitor.UNKNOWN);
		entry = zip.getNextEntry();
		while (entry != null) {
			boolean canProcess = true;

			if (filePattern != null) {
				if (!entry.getName().matches(filePattern)) {
					canProcess = false;
				}
			}
			
			if (canProcess) {
				if (entry.isDirectory()) {
					IFolder folder = target.getFolder(new Path(entry.getName()));
					ResourcesUtil.safelyCreateFolder(folder);
				} else {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len;
					while ((len = zip.read(buffer)) > 0) {
						bos.write(buffer, 0, len);
					}
					IFile file = target.getFile(new Path(entry.getName()));
					ResourcesUtil.safelyCreateFile(file, new ByteArrayInputStream(bos.toByteArray()), overwrite, null);
				}
			}
			
//			if (!entry.isDirectory() && canProcess) {
//				ByteArrayOutputStream bos = new ByteArrayOutputStream();
//				byte[] buffer = new byte[1024];
//				int len;
//				while ((len = zip.read(buffer)) > 0) {
//					bos.write(buffer, 0, len);
//				}
//				IFile file = target.getFile(new Path(entry.getName()));
//				ResourcesUtil.safelyCreateFile(file, new ByteArrayInputStream(bos.toByteArray()), overwrite, null);
//			}

			monitor.worked(1);
			entry = zip.getNextEntry();
		}
		monitor.done();
	}

	/**
	 * 解压缩流对应的压缩文件，到指定的位置。
	 * @param is
	 * @param target
	 * @param overwrite 遇到已经存在的文件是否覆盖
	 * @param pm
	 */
	public static void Unzip(InputStream is, IContainer target, boolean overwrite, IProgressMonitor monitor) throws CoreException, IOException{
		Unzip(is, target, null, overwrite, monitor);
	}
	
	/**
	 * 解压zip中的某个文件夹的内容到指定的位置。
	 * 解压后会忽略这个父文件夹路径例如:
	 * zip结构如下:
	 * 		xxx/aaa/bbb
	 * 假如解压xxx到一个目标目录target的话，结果将为：
	 * 		target/aaa/bbb
	 * @param is
	 * @param parentName
	 * @param target
	 * @param filePattern
	 * @param overwrite
	 * @param monitor
	 * @throws IOException 
	 * @throws CoreException 
	 */
	public static void unzipSubEntries(InputStream is, String parentName, IContainer target, String filePattern, boolean overwrite, IProgressMonitor monitor) throws IOException, CoreException {
		ZipArchiveInputStream zip = new ZipArchiveInputStream(is, null, true);
		ArchiveEntry entry;
		monitor = Util.monitorFor(monitor);
		monitor.beginTask("", IProgressMonitor.UNKNOWN);
		entry = zip.getNextEntry();
		while (entry != null) {
			boolean canProcess = true;
			// pattern
			if (filePattern != null) {
				if (!entry.getName().matches(filePattern)) {
					canProcess = false;
				}
			}
			
			// prefix
			if (!entry.getName().startsWith(parentName + SEP)) {
				canProcess = false;
			}
			
			if (canProcess) {
				if (entry.isDirectory()) {
					IFolder folder = target.getFolder(new Path(StringUtils.substringAfter(entry.getName(), SEP)));
					ResourcesUtil.safelyCreateFolder(folder);
				} else {
					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len;
					while ((len = zip.read(buffer)) > 0) {
						bos.write(buffer, 0, len);
					}
					IFile file = target.getFile(new Path(StringUtils.substringAfter(entry.getName(), SEP)));
					ResourcesUtil.safelyCreateFile(file, new ByteArrayInputStream(bos.toByteArray()), overwrite, null);
				}
			}

			monitor.worked(1);
			entry = zip.getNextEntry();
		}
		monitor.done();
	}
	
	/**
	 * 从某个Bundle中解压缩某个entry代表的zip文件到指定的位置。
	 * @param bundleName
	 * @param entry
	 * @param target
	 * @param filePattern zip文件中需要解压的文件名正则表达式
	 * @param overwrite
	 * @param monitor
	 * @throws CoreException
	 * @throws IOException
	 */
	public static void UnzipFromBundle(String bundleName, String entry, IContainer target, String filePattern, boolean overwrite, IProgressMonitor monitor) throws CoreException, IOException{
		Bundle bundle = Platform.getBundle(bundleName);
		if (bundle == null)
			return;
		URL url = bundle.getEntry(entry);
		InputStream is = url.openStream();
		Unzip(is, target, filePattern, overwrite, monitor);
	}
	
	/**
	 * 从某个Bundle中解压缩某个entry代表的zip文件到指定的位置。
	 * @param bundleName
	 * @param entry
	 * @param target
	 * @param overwrite
	 * @param monitor
	 * @throws CoreException
	 * @throws IOException
	 */
	public static void UnzipFromBundle(String bundleName, String entry, IContainer target, boolean overwrite, IProgressMonitor monitor) throws CoreException, IOException{
		UnzipFromBundle(bundleName, entry, target, null, overwrite, monitor);
	}
	
	public static void UnzipSubEntriesFromBundle(String bundleName, String entry, String parent, IContainer target, boolean overwrite, IProgressMonitor monitor) throws IOException, CoreException {
		Bundle bundle = Platform.getBundle(bundleName);
		if (bundle == null)
			return;
		URL url = bundle.getEntry(entry);
		InputStream is = url.openStream();
		unzipSubEntries(is, parent, target, null, overwrite, monitor);
	}

	/**
	 * 将指定的zip entry以及对应的内容写到输出流中去，本方法不会关闭流，调用者需要自己处理流的关闭
	 * @param entry
	 * @param content
	 * @param output
	 * @throws IOException
	 */
	public static void writeZipEntry(ArchiveEntry entry, InputStream content, ZipArchiveOutputStream output) throws IOException {
		output.putArchiveEntry(entry);
		byte[] buffer = new byte[2048];
		int length;
		while ((length = content.read(buffer)) != -1) {
			output.write(buffer, 0, length);
		}
		output.closeArchiveEntry();
	}
	
	/**
	 * @param entry
	 * @param content
	 * @param output
	 * @throws IOException
	 * @see {@link ZipUtil#writeZipEntry(ZipEntry, InputStream, ZipOutputStream)}
	 */
	public static void writeZipEntry(ArchiveEntry entry, byte[] content, ZipArchiveOutputStream output) throws IOException {
		output.putArchiveEntry(entry);
		output.write(content);
		output.closeArchiveEntry();
	}
	
	public static void addFolder(final IContainer container, final ZipArchiveOutputStream output) {
		try {
			final IPath basePath = new Path(container.getName());
			container.accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					String name = resource.getName();
					switch (resource.getType()) {
					case IResource.FOLDER:
						if (name.equals(".svn"))
							return false;
						return true;
					case IResource.FILE:
						IFile file = (IFile) resource;
						IPath rPath = resource.getFullPath().removeFirstSegments(container.getFullPath().segmentCount());
						IPath path = basePath.append(rPath);
						ZipArchiveEntry entry = new ZipArchiveEntry(path.toString());
						try {
							InputStream is = file.getContents();
							writeZipEntry(entry, is, output);
						} catch (IOException e) {
							e.printStackTrace();
						}
						return false;
					}
					return false;
				}
			});
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * 导出工程为引用资源包时，将<code>iproject<code>工程整个目录结构也压缩到ares.<br>
	 * 工程压缩到<code>moduleName<code>目录下，压缩目录过程中，忽略掉<code>ingoreDirs<code>中目录
	 * @param iproject
	 * @param zipStream
	 * @param moduleName
	 * @param ingoreDirs
	 * 
	 * @author xuzhen
	 * 2011-09-16
	 */
	public static void addProject(final IProject iproject, final ZipArchiveOutputStream zipStream,final String moduleName, final List<String> ingoreDirs) {
		final IPath basePath = new Path(moduleName);
		try {
			iproject.getProject().accept(new IResourceVisitor() {
				public boolean visit(IResource resource) throws CoreException {
					String name = resource.getName();
					switch (resource.getType()) {
					case IResource.FOLDER:
						if ( ingoreDirs.contains(name) ) {
							return false;
						}
						{
							IPath rPath = resource.getFullPath().removeFirstSegments(iproject.getFullPath().segmentCount());
							IPath path = basePath.append(rPath);
							ZipArchiveEntry entry = new ZipArchiveEntry(path+"/");
							try {
								zipStream.putArchiveEntry(entry);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						return true;
					case IResource.FILE:
						{
							IFile file = (IFile) resource;
							IPath rPath = resource.getFullPath().removeFirstSegments(iproject.getFullPath().segmentCount());
							IPath path = basePath.append(rPath);
							ZipArchiveEntry entry = new ZipArchiveEntry(path.toString());
							try {
								InputStream is = file.getContents();
								ZipUtil.writeZipEntry(entry, is, zipStream);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						return false;
					case IResource.PROJECT:
						return true;
					}
					return false;
				}
			});
		} catch (CoreException e) {
			e.printStackTrace();
		}		
	}
	
}
