package mindmapApplication;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JButton;

public class MyButton extends JButton
{
	public MyButton()
	{
		super();
		decorate();
	}
	public MyButton(String text)
	{
		super(text);
		decorate();
	}
	protected void decorate() 
	{
        setBorderPainted(false);
        setOpaque(false);
    }
	protected void paintComponent(Graphics g) 
	{
	    int width = getWidth();
	    int height = getHeight();
	    Graphics2D graphics = (Graphics2D) g;
	    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    if (getModel().isArmed()) {
	        graphics.setColor(Color.GRAY.darker());
	    } else if (getModel().isRollover()) {
	        graphics.setColor(Color.GRAY.brighter());
	    } else {
	        graphics.setColor(Color.GRAY);
	    }
	    graphics.fillRoundRect(0, 0, width, height, 10, 10);

	    FontMetrics fontMetrics = graphics.getFontMetrics();
	    Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

	    int textX = (width - stringBounds.width) / 2;
	    int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 

	    graphics.setColor(Color.WHITE);
	    graphics.setFont(getFont());
	    graphics.drawString(getText(), textX, textY);
	    graphics.dispose(); 
	    super.paintComponent(g);
	}
}
