package mindmapApplication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;

public class JNode extends JLabel
{
	private JNode parent;
	private ArrayList<JNode> children;
	
	public JNode(String label, int x, int y, int width, int height, Color color, JNode parent)
	{
		super(label);
		setBounds(x, y, width, height);
		setBackground(color);
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(new RoundedBorder(new Color(70,70,70), 2, 20));
		
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
		
		this.parent = parent;
	}
	
	public void addChild(JNode child)
	{
		children.add(child);
	}
	
	private class MyMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener
	{
		private Point start, mouse;
		
		public MyMouseListener()
		{
			start = new Point();
			mouse = new Point();
		}
		
		@Override
		public void mousePressed(MouseEvent e)
		{
			start.move(getX(), getY());
			mouse.move(e.getX(), e.getY());
		}
		
		@Override
		public void mouseDragged(MouseEvent e)
		{
			int x = start.x+e.getX()-mouse.x;
			int y = start.y+e.getY()-mouse.y;
			setLocation(x, y);
			start.move(x, y);
		}
	}
	
	// https://java-swing-tips.blogspot.com/2012/03/rounded-border-for-jtextfield.html
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

		@Override public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
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
	
	@Override protected void paintComponent(Graphics g)
	{
		if (!isOpaque() && getBorder() instanceof RoundedBorder)
		{
			Graphics2D g2 = (Graphics2D) g.create();
			g2.setPaint(getBackground());
			g2.fill(((RoundedBorder)getBorder()).getBorderShape(0, 0, getWidth()-1, getHeight()-1));
			g2.dispose();
		}
		super.paintComponent(g);
	}
	
	public String toString()
	{
		return "JNode[text="+getText()+",x="+getX()+",y="+getY()+",width="+getWidth()+",height="+getHeight()+",color="+getBackground()+",parent="+parent+"]";
	}
}
