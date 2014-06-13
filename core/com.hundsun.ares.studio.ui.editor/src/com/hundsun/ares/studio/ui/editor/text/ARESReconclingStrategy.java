package com.hundsun.ares.studio.ui.editor.text;

import java.util.ArrayList;
import java.util.Stack;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.reconciler.DirtyRegion;
import org.eclipse.jface.text.reconciler.IReconcilingStrategy;
import org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension;
import org.eclipse.swt.widgets.Display;

/**
 * <p>CreatedDate: 2008-2-18</p>
 * @author sundl
 */
public class ARESReconclingStrategy implements IReconcilingStrategy,
		IReconcilingStrategyExtension {

	private GeneralSourceEditor editor;
	protected IDocument document;

    /** holds the calculated positions */
    protected final ArrayList fPositions = new ArrayList();

    /** The offset of the next character to be read */
    protected int fOffset;

    /** The end offset of the range to be scanned */
    protected int fRangeEnd;
    
    /**
     * next character position - used locally and only valid while
     * {@link #calculatePositions()} is in progress.
     */
    protected int cNextPos = 0;
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#reconcile(org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(IRegion partition) {
		initialReconcile();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#reconcile(org.eclipse.jface.text.reconciler.DirtyRegion, org.eclipse.jface.text.IRegion)
	 */
	public void reconcile(DirtyRegion dirtyRegion, IRegion subRegion) {
		initialReconcile();
	}
	
	/**
	 * @return the editor
	 */
	public GeneralSourceEditor getEditor() {
		return editor;
	}

	/**
	 * @param editor the editor to set
	 */
	public void setEditor(GeneralSourceEditor editor) {
		this.editor = editor;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategy#setDocument(org.eclipse.jface.text.IDocument)
	 */
	public void setDocument(IDocument document) {
		this.document = document;
//		try {
////			int l2 = document.getLineInformation(2).getLength();
////			int l4 = document.getLineInformation(4).getLength();
//			int l7 = document.getLineLength(7);
//			int l10 = document.getLineLength(8);
//			//fPositions.add(new Position(0, /*document.getLength()*/ i + j));
//			fPositions.add(new Position(document.getLineOffset(2), document.getLineOffset(8) + l10 - document.getLineOffset(2)));
//			//fPositions.add(new Position(document.getLineOffset(4), document.getLineOffset(7) + l7 - document.getLineOffset(4)));
//		} catch (BadLocationException e) {
//			e.printStackTrace();
//		}

	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension#initialReconcile()
	 */
	public void initialReconcile() {
		fOffset = 0;
		fRangeEnd = document.getLength();
		calculatePositions();

	}

	  /**
     * uses {@link #fDocument}, {@link #fOffset} and {@link #fRangeEnd} to
     * calculate {@link #fPositions}. About syntax errors: this method is not a
     * validator, it is useful.
     */
    protected void calculatePositions() {
            fPositions.clear();
            cNextPos = fOffset;

//            try {
//                   // recursiveTokens(0);
//            } catch (BadLocationException e) {
//                    e.printStackTrace();
//            }
            // Collections.sort(fPositions, new RangeTokenComparator());  
            long start = System.currentTimeMillis();
            Stack<Integer> starts = new Stack<Integer>();
            while(cNextPos < fRangeEnd) {
            	try {
					char ch = document.getChar(cNextPos++);
					if(ch == '{') {
						starts.push(new Integer(cNextPos -1));
					} else if(ch == '}') {
						if(starts.empty()) {
							
						} else {
							int offset = starts.pop().intValue();
							if(fPositions.size() != 0) {
								Position lastPosition = (Position)fPositions.get(fPositions.size() - 1);
								int preLine = document.getLineOfOffset(lastPosition.getOffset());
								int startLine = document.getLineOfOffset(offset + 1);
								if(preLine == startLine) continue;
								int startOffset = document.getLineOffset(startLine);
								int curLine = document.getLineOfOffset(cNextPos -1);
								if(curLine == startLine) continue;
								int curLineLength = document.getLineLength(curLine);
								int curLineOffset = document.getLineOffset(curLine);
								fPositions.add(new Position(startOffset, curLineOffset + curLineLength - startOffset));								
							} else {
								int startLine = document.getLineOfOffset(offset + 1);
								int startOffset = document.getLineOffset(startLine);
								int curLine = document.getLineOfOffset(cNextPos -1);
								if(curLine == startLine) continue;
								int curLineLength = document.getLineLength(curLine);
								int curLineOffset = document.getLineOffset(curLine);
								fPositions.add(new Position(startOffset, curLineOffset + curLineLength - startOffset));								
							}
							continue;
						}
					} else {
						continue;
					}
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
            }
            long end = System.currentTimeMillis();
            

            Display.getDefault().asyncExec(new Runnable() {
                    public void run() {
                            editor.updateFoldingStructure(fPositions);
                    }

            });
    }

	/* (non-Javadoc)
	 * @see org.eclipse.jface.text.reconciler.IReconcilingStrategyExtension#setProgressMonitor(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void setProgressMonitor(IProgressMonitor monitor) {

	}

}
