/**
* <p>Copyright: Copyright   2010</p>
* <p>Company: 恒生电子股份有限公司</p>
*/
package com.hundsun.ares.studio.ui.grid.renderer;

import org.eclipse.nebula.widgets.grid.AbstractRenderer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;

/**
 * 行列交叉的地方的样式控制
 * @author maxh
 *
 */
public class TopLeftRenderer  extends AbstractRenderer
{

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
    public Point computeSize(GC gc, int wHint, int hHint, Object value)
    {
        return new Point(wHint, hHint);
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
    	
        gc.setBackground(color);

        gc.fillRectangle(getBounds().x, getBounds().y, getBounds().width - 1,
                         getBounds().height + 1);

        gc.setForeground(lineColor);

        
        gc.drawLine(getBounds().x + getBounds().width - 1, getBounds().y, getBounds().x
                                                                          + getBounds().width - 1,
                    getBounds().y + getBounds().height);

        gc.drawLine(getBounds().x, getBounds().y + getBounds().height - 1, getBounds().x
                                                                           + getBounds().width,
                    getBounds().y + getBounds().height - 1);

    }

}
