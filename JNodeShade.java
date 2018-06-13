package mindmapApplication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;

public class JNodeShade extends JLabel
{
	private final static int INITIAL_ALPHA = 100;
	
	public JNodeShade(int x, int y, int width, int height, Color color)
	{
		setBounds(x, y, width, height);
		setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), INITIAL_ALPHA));
		setBorder(new RoundedBorder(new Color(70,70,70,INITIAL_ALPHA), 2, 20));
		this.setVisible(false);
	}
	
	private class RoundedBorder extends AbstractBorder
	{
		private Color color;
		private int thickness;
		private int round;
		private BasicStroke stroke;
		private int strokePad;
	    private Insets insets;

		public RoundedBorder(Color color, int thickness, int round)
		{
			this.color = color;
			this.thickness = thickness;
			this.round = round;
			
			stroke = new BasicStroke(thickness);
			strokePad = thickness+1;

	        int pad = round + strokePad;
	        int bottomPad = pad + strokePad;
	        insets = new Insets(pad, pad, bottomPad, pad);
		}

		@Override
		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
		{
			Graphics2D g2 = (Graphics2D)g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			Shape border = getBorderShape(x+thickness/2, y+thickness/2, width-strokePad, height-strokePad);
			g2.setPaint(new Color(0x0, true));
			Area area = new Area(new RoundRectangle2D.Double(thickness/2, thickness/2, width-thickness, height-thickness, round, round));
			area.subtract(new Area(border));
			g2.fill(area);
			g2.setPaint(color);
			g2.setStroke(stroke);
			g2.draw(border);
			g2.dispose();
		}
		
		public Shape getBorderShape(int x, int y, int width, int height)
		{
			return new RoundRectangle2D.Double(x, y, width, height, round, round);
		}
		
		@Override
		public Insets getBorderInsets(Component c)
		{
			return insets;
		}

		@Override
		public Insets getBorderInsets(Component c, Insets insets)
		{
			return getBorderInsets(c);
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		if(!isOpaque() && getBorder() instanceof RoundedBorder)
		{
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(getBackground());
			g2.fill(((RoundedBorder)getBorder()).getBorderShape(0, 0, getWidth()-1, getHeight()-1));
			g2.dispose();
		}
		super.paintComponent(g);
	}
	
	@Override
	public void setBackground(Color color)
	{
		super.setBackground(new Color(color.getRed(), color.getGreen(), color.getBlue(), INITIAL_ALPHA));
	}
	
	@Override
	public String toString()
	{
		return "JNodeShade[x="+getX()+",y="+getY()+",width="+getWidth()+",height="+getHeight()+",color="+getBackground()+"]";
	}
}
