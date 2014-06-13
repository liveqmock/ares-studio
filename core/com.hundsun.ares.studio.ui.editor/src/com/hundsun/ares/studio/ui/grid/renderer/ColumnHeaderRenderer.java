/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.renderer;

import org.eclipse.nebula.widgets.grid.GridColumn;
import org.eclipse.nebula.widgets.grid.internal.DefaultColumnHeaderRenderer;
import org.eclipse.nebula.widgets.grid.internal.SortArrowRenderer;
import org.eclipse.nebula.widgets.grid.internal.TextUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Display;

/**
 * grid行头样式控制
 * @author maxh
 *
 */
public class ColumnHeaderRenderer extends DefaultColumnHeaderRenderer {

    int leftMargin = 6;

    int rightMargin = 6;

    int topMargin = 3;

    int bottomMargin = 3;

    int arrowMargin = 6;

    int imageSpacing = 3;

    private SortArrowRenderer arrowRenderer = new SortArrowRenderer();

    private TextLayout textLayout;

    /**
     * {@inheritDoc}
     */
    public Point computeSize(GC gc, int wHint, int hHint, Object value)
    {
        GridColumn column = (GridColumn)value;

        gc.setFont(column.getHeaderFont());

        int x = leftMargin;
        int y = topMargin + gc.getFontMetrics().getHeight() + bottomMargin;


        if (column.getImage() != null)
        {
            x += column.getImage().getBounds().width + imageSpacing;

            y = Math.max(y, topMargin + column.getImage().getBounds().height + bottomMargin);
        }
        if (!isWordWrap())
        {
          x += gc.stringExtent(column.getText()).x + rightMargin;
        }
        else
        {
          int plainTextWidth;
          if (wHint == SWT.DEFAULT)
            plainTextWidth = getBounds().width - x - rightMargin;
          else
            plainTextWidth = wHint - x - rightMargin;

          getTextLayout(gc, column);
            textLayout.setText(column.getText());
            textLayout.setWidth(plainTextWidth < 1 ? 1 : plainTextWidth);

            x += plainTextWidth + rightMargin;

            int textHeight = topMargin;
            textHeight += textLayout.getBounds().height;
            textHeight += bottomMargin;

            y = Math.max(y, textHeight);
        }


		y += computeControlSize(column).y;

		return new Point(x, y);
	}

    Color color;
    
    public void setColor(Color color) {
		this.color = color;
	}
    
    Color lineColor;
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
    /**
     * {@inheritDoc}
     */
    public void paint(GC gc, Object value)
    {
    	if(color == null){
    		color = getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND);
    	}
    	if(lineColor == null){
    		lineColor = getDisplay().getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW);
    	}
    	
        GridColumn column = (GridColumn)value;

        gc.setFont(column.getHeaderFont());

        gc.setBackground(color);

        gc.fillRectangle(getBounds().x, getBounds().y, getBounds().width,
                         getBounds().height);

        int pushedDrawingOffset = 0;
        int x = leftMargin;

        int width = getBounds().width - x;
        int y = bottomMargin;

        gc.setForeground(getDisplay().getSystemColor(SWT.COLOR_BLACK));
        String text = column.getText();

        if (!isWordWrap())
        {
          text = TextUtils.getShortString(gc, text, width);
        }

        if (column.getAlignment() == SWT.RIGHT)
        {
            int len = gc.stringExtent(text).x;
            if (len < width)
            {
                x += width - len;
            }
        }
        else if (column.getAlignment() == SWT.CENTER)
        {
            int len = gc.stringExtent(text).x;
            if (len < width)
            {
                x += (width - len) / 2;
            }
        }


        if (!isWordWrap()) {
        	gc.drawString(text, getBounds().x + x + pushedDrawingOffset,
        			y + pushedDrawingOffset,true);
        }
        else
        {
        	getTextLayout(gc, column);
        	textLayout.setWidth(width < 1 ? 1 : width);
        	textLayout.setText(text);
        	y -= textLayout.getBounds().height;

        	if (column.getParent().isAutoHeight())
        	{
        		column.getParent().recalculateHeader();
        	}

        	textLayout.draw(gc, getBounds().x + x + pushedDrawingOffset, y + pushedDrawingOffset);
        }
        
        gc.setForeground(lineColor);
        
        gc.drawLine(getBounds().x + getBounds().width - 1, getBounds().y, getBounds().x
                                                                          + getBounds().width - 1,
                    getBounds().y + getBounds().height - 1);
        gc.drawLine(getBounds().x, getBounds().y + getBounds().height - 1, getBounds().x
                                                                           + getBounds().width - 1,
                    getBounds().y + getBounds().height - 1);
    }

    /**
     * {@inheritDoc}
     */
    public void setDisplay(Display display)
    {
        super.setDisplay(display);
        arrowRenderer.setDisplay(display);
    }

    /**
     * {@inheritDoc}
     */
    public boolean notify(int event, Point point, Object value)
    {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    public Rectangle getTextBounds(Object value, boolean preferred)
    {
        GridColumn column = (GridColumn)value;

        int x = leftMargin;

        if (column.getImage() != null)
        {
            x += column.getImage().getBounds().width + imageSpacing;
        }



        GC gc = new GC(column.getParent());
        gc.setFont(column.getParent().getFont());
        int y = getBounds().height - bottomMargin - gc.getFontMetrics().getHeight();

        Rectangle bounds = new Rectangle(x,y,0,0);

        Point p = gc.stringExtent(column.getText());

        bounds.height = p.y;

        if (preferred)
        {
            bounds.width = p.x;
        }
        else
        {
            int width = getBounds().width - x;
            if (column.getSort() == SWT.NONE)
            {
                width -= rightMargin;
            }
            else
            {
                width -= arrowMargin + arrowRenderer.getSize().x + arrowMargin;
            }
            bounds.width = width;
        }


        gc.dispose();

        return bounds;
    }

	/**
	 * @return the bounds reserved for the control
	 */
	protected Rectangle getControlBounds(Object value, boolean preferred) {
		Rectangle bounds = getBounds();
		GridColumn column = (GridColumn) value;
		Point controlSize = computeControlSize(column);

		int y = getBounds().y + getBounds().height - bottomMargin - controlSize.y;

		return new Rectangle(bounds.x+3,y,bounds.width-6,controlSize.y);
	}

	private Point computeControlSize(GridColumn column) {
		if( column.getHeaderControl() != null ) {
			return column.getHeaderControl().computeSize(SWT.DEFAULT, SWT.DEFAULT);
		}
		return new Point(0,0);
	}

	 private void getTextLayout(GC gc, GridColumn column)
   {
       if (textLayout == null)
       {
           textLayout = new TextLayout(gc.getDevice());
           textLayout.setFont(gc.getFont());
           column.getParent().addDisposeListener(new DisposeListener()
           {
               public void widgetDisposed(DisposeEvent e)
               {
                   textLayout.dispose();
               }
           });
       }
       textLayout.setAlignment(column.getAlignment());
   }
}
