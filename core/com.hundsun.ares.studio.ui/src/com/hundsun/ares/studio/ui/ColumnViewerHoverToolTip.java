package com.hundsun.ares.studio.ui;

import org.apache.commons.lang.StringUtils;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class ColumnViewerHoverToolTip{
	
	/**表格*/
	ColumnViewer viewer;
	/**当前鼠标所在的单元格*/
	ViewerCell cell;
	String text = "";
	
	/**tooltip显示框*/
	Shell currentShell;
	/**tooltip显示的信息框*/
	StyledText txtInfo;
	
	Listener treeViewerListener = new TreeViewerListener();
	Listener toolTipListener = new ToolTipListener();
	Control control;

	protected ColumnViewerHoverToolTip(ColumnViewer viewer) {
		this.viewer = viewer;
		this.control = viewer.getControl();
		
		this.control.addDisposeListener(new DisposeListener() {

			public void widgetDisposed(DisposeEvent e) {
				deactivate();
			}

		});
		
		activate();
	}	
	public static void enableFor(ColumnViewer viewer) {
		new ColumnViewerHoverToolTip(viewer);
	}

	protected Composite createToolTipContentArea(Event event, Composite parent) {
		control.forceFocus();
		int textStyle=SWT.V_SCROLL | SWT.WRAP;

		final Composite composite= new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		String text = getText();
		
		txtInfo = new StyledText(composite, textStyle);
		txtInfo.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		txtInfo.setFont(new Font(txtInfo.getDisplay(),"宋体",9,SWT.NORMAL));
		if (text != null) {
			txtInfo.setText(text);
			txtInfo.setCaret(null);
			txtInfo.setEditable(false);
		}
		
		//滚动条只有在按下F2或者鼠标停靠在tooltip上的时候才显示
		setScrollBarVisible(false);
		Label separator = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		CLabel label = new CLabel(composite, SWT.NONE);
		
		label.setText("Press F2 for focus");
		label.setFont(new Font(label.getDisplay(),"宋体",7,SWT.NORMAL));
		label.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_INFO_BACKGROUND));
		composite.setLayout(new GridLayout(1, false));
		GridDataFactory.fillDefaults().grab(true, true).applyTo(txtInfo);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, false).applyTo(separator);
		GridDataFactory.fillDefaults().hint(-1,7).align(SWT.END, SWT.FILL).grab(true, false).applyTo(label);
		
		toolTipActivate(composite);
		composite.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				toolTipDeactivate(composite);
			}
		});
		
		return txtInfo;
		
	}
	
	
	private void toolTipShow(Shell tip, Event event) {
		if (!tip.isDisposed()) {
			tip.pack();
			Point size = tip.getSize();
			//tooltip的最大宽度设置为200
			if(size.x > 200){
				size = new Point(200, size.y + 36);
				tip.setSize(size);
			}
			Rectangle cellBound = cell.getBounds();
			Point location = fixupDisplayBounds(size, getLocation(cellBound.x,cellBound.y + cellBound.height));

			tip.setLocation(location);
			tip.setVisible(true);
			control.forceFocus();
			txtInfo.setFocus();
			txtInfo.setText(getText());
			if(!txtInfo.getVerticalBar().isVisible()){
				//默认状态下显示4行，总共约110个字节。超过的话后面加...
				if(getText().getBytes().length>110){
					int end = 54;
					String tmp = StringUtils.substring(getText(), 0, end);
					while(tmp.getBytes().length<=105){
						end += (110-tmp.getBytes().length)/2;
						String addStr = StringUtils.substring(getText(), tmp.length(),end);
						if(addStr.length() == 0){
							break;
						}
						tmp += addStr;
					}
					txtInfo.setText(tmp + "...");
				}
			}
		}
	}
	/**获取相对于table/tree的相对坐标*/
	public Point getLocation(int x, int y) {
		return control.toDisplay(x , y);
	}
	
	//tipSize：tooltip的宽高       location:tooltip的初始显示坐标（单元格的左下位置）
	private Point fixupDisplayBounds(Point tipSize, Point location) {
			
		//按照初始显示位置和宽高获取到的tooltip的右下坐标
			Point rightBounds = new Point(tipSize.x + location.x, tipSize.y
					+ location.y);
			
			Rectangle clientArea = ((Composite)control).getClientArea();
			Point pt = control.toDisplay(clientArea.x,clientArea.y);
			Rectangle bounds = new Rectangle(pt.x, pt.y, control.getSize().x,  control.getSize().y);
			
			//如果tooltip的范围超出了表格，则需要进行调整
			if (!(bounds.contains(location) && bounds.contains(rightBounds))) {
				//如果tootilp的右边超出了表格右边
				if (rightBounds.x > bounds.x + bounds.width) {
					//初始位置左移，直到tootip的右边和表格的右边一样
					location.x -= rightBounds.x - (bounds.x + bounds.width);
				}
				
				//如果tootip的下边超出了表格的下边
				if (rightBounds.y > bounds.y + bounds.height) {
					//初始位置则上移，直到tooltip的下边和单元格的上边一样
					location.y = location.y - cell.getBounds().height - tipSize.y;//-= rightBounds.y - (bounds.y + bounds.height);
				}
				
				//保证左移或者上移后初始xy坐标没有超出表格左上边
				if (location.x < bounds.x) {
					location.x = bounds.x;
				}

				if (location.y < bounds.y) {
					location.y = bounds.y;
				}
			}

		return location;
	}
	
	protected boolean shouldCreateToolTip(Event event) {
		boolean rv = false;
		//判断前看tooltip是否存在，如果当前tootip不存在了，将cell置为null
		if(currentShell == null || currentShell.isDisposed()){
			cell = null;
		}
		try{
			Point point = new Point(event.x, event.y);
			
			ViewerCell curCell = viewer.getCell(point);
			//如果和当前显示tooltip的单元格是同一个
			if(cell != null && cell.equals(curCell)){
				return false;
			}
			//如果当前鼠标所在单元格不存在
			cell = curCell;
			if(curCell == null){
				return false;
			}
			
			Object element = cell.getElement();
			
			CellLabelProvider labelProvider = viewer.getLabelProvider(cell.getColumnIndex());
			
			String text = labelProvider.getToolTipText(element);
			setText(text);
			rv = StringUtils.isNotBlank(getText());
			
		}catch(Exception e){
			return false;
		}

		return rv;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	private void toolTipHide(){
		if(currentShell != null && !currentShell.isDisposed()){
			currentShell.close();
			currentShell.dispose();
		}
	};
	
	private void setScrollBarVisible(boolean visible){
		if(txtInfo != null && !txtInfo.isDisposed() && txtInfo.getVerticalBar().getVisible() == !visible){
			txtInfo.getVerticalBar().setVisible(visible);
			if(visible){
				txtInfo.setFocus();
				txtInfo.setText(getText());
				currentShell.setSize(currentShell.getSize().x + txtInfo.getVerticalBar().getSize().x, currentShell.getSize().y);
			}
		}
	}
	public void toolTipActivate(Control control) {
		toolTipDeactivate(control);
		control.addListener(SWT.MouseHover,toolTipListener);
		control.addListener(SWT.MouseDown,toolTipListener);
		control.addListener(SWT.FocusOut,toolTipListener);
		control.addListener(SWT.KeyDown,toolTipListener);
		if (control instanceof Composite) {
			for(Control child : ((Composite)control).getChildren()){
				toolTipActivate(child);
			}
		}
	}

	/**
	 * Deactivate tooltip support for the underlying control
	 */
	public void toolTipDeactivate(Control control) {
		control.removeListener(SWT.MouseHover,toolTipListener);
		control.removeListener(SWT.MouseDown,toolTipListener);
		control.removeListener(SWT.FocusOut,toolTipListener);
		control.removeListener(SWT.KeyDown,toolTipListener);
		if (control instanceof Composite) {
			for(Control child : ((Composite)control).getChildren()){
				toolTipDeactivate(child);
			}
		}
	}
	
	public void activate() {
		deactivate();
		control.addListener(SWT.MouseHover, treeViewerListener);
		control.addListener(SWT.KeyDown, treeViewerListener);
		control.addListener(SWT.MouseMove, treeViewerListener);
	}

	/**
	 * Deactivate tooltip support for the underlying control
	 */
	public void deactivate() {
		control.removeListener(SWT.MouseHover, treeViewerListener);
		control.removeListener(SWT.KeyDown, treeViewerListener);
		control.removeListener(SWT.MouseMove, treeViewerListener);
	}
	class TreeViewerListener implements Listener{

		@Override
		public void handleEvent(Event event) {
			if (event.widget instanceof Control) {

				Control c = (Control) event.widget;
				switch (event.type) {
				//鼠标停靠
				case SWT.MouseHover:
					if(shouldCreateToolTip(event)){
						//先把当前已经打开的tooltip销毁再重新生成一个新的tooltip并显示出来
						toolTipHide();
						currentShell = new Shell(c.getShell(), SWT.ON_TOP | SWT.TOOL
								| SWT.NO_FOCUS | SWT.RESIZE);
						currentShell.setLayout(new FillLayout());
						createToolTipContentArea(event, currentShell);
						toolTipShow(currentShell, event);
						currentShell.addControlListener(new ControlAdapter() {
							@Override
							public void controlResized(ControlEvent e) {
								setScrollBarVisible(true);
							}
						});
					}
					break;
				case SWT.KeyDown:
					//esc则销毁当前打开的tooltip
					if(event.keyCode == SWT.ESC){
						toolTipHide();
					}else if(event.keyCode == SWT.F2){
						//F2当前tooltip获取焦点，滚动条显示
						setScrollBarVisible(true);
					}
					break;
				case SWT.MouseMove:
					Point point = new Point(event.x, event.y);
					if(currentShell != null && !currentShell.isDisposed() && cell != null){
						if(!currentShell.getBounds().contains(point) && !txtInfo.getVerticalBar().isVisible() && !cell.getBounds().contains(point)){
							//如果鼠标位置不在单元格和tooltip范围内，且tooltip没有获取焦点，则销毁tooltip
							toolTipHide();
						}
					}
					break;
				}
			}
		}
		
	}
	class ToolTipListener implements Listener{
		@Override
		public void handleEvent(Event event) {
			switch (event.type) {
			case SWT.MouseHover:
			case SWT.MouseDown:
				setScrollBarVisible(true);
				break;
			case SWT.KeyDown:
				if(event.keyCode == SWT.ESC){
					toolTipHide();
				}else if(event.keyCode == SWT.F2){
					setScrollBarVisible(true);
				}
				break;
			case SWT.FocusOut:
				//tooltip失去焦点则销毁
				toolTipHide();
				break;
			default:
				break;
			}
		}
		
	}
}
