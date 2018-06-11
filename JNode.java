package mindmapApplication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;

public class JNode extends JLabel
{
	private MindMapPanel mindMapPanel;
	private JNode parentNode;
	
	public JNode(MindMapPanel mindMapPanel, String label, int x, int y, int width, int height, Color color, JNode parentNode)
	{
		super(label);
		setBounds(x, y, width, height);
		setBackground(color);
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(new RoundedBorder(new Color(70,70,70), 2, 20));
		
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);

		this.mindMapPanel = mindMapPanel;
		this.parentNode = parentNode;
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

	public Point getPoint(int position)
	{
		switch(position)
		{
		case 0: // above
			return new Point(getX()+getWidth()/2, getY());
		case 1: // right
			return new Point(getX()+getWidth(), getY()+getHeight()/2);
		case 2: // below
			return new Point(getX()+getWidth()/2, getY()+getHeight());
		case 3: // left
			return new Point(getX(), getY()+getHeight()/2);
		default:
			throw new IllegalArgumentException("wrong position");
		}
	}
	
	private int distanceSq(Point p1, Point p2)
	{
		return (p1.x-p2.x)*(p1.x-p2.x) + (p1.y-p2.y)*(p1.y-p2.y);
	}
	
	public void draw(Graphics2D g2)
	{
		if(parentNode == null)
			throw new UnsupportedOperationException(this+" doesn't have parent node");
		
		int thisPosition = 0;
		int parentPosition = 0;
		int distance = distanceSq(this.getPoint(thisPosition), parentNode.getPoint(parentPosition));
		for(int i=0; i<4; ++i)
			for(int j=0; j<4; ++j)
				if(distance > distanceSq(this.getPoint(i), parentNode.getPoint(j)))
				{
					distance = distanceSq(this.getPoint(i), parentNode.getPoint(j));
					thisPosition = i;
					parentPosition = j;
				}

		Point p1 = this.getPoint(thisPosition);
		Point p2 = parentNode.getPoint(parentPosition);
		
		int length = (Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y)) / 3;
		int[] dx = {0, 1, 0, -1};
		int[] dy = {-1, 0, 1, 0};

		Point p1to = new Point(p1.x+dx[thisPosition]*length, p1.y+dy[thisPosition]*length);
		Point p2from = new Point(p2.x+dx[parentPosition]*length, p2.y+dy[parentPosition]*length);

		g2.setStroke(new BasicStroke(1));
		g2.draw(new CubicCurve2D.Float(p1.x, p1.y, p1to.x, p1to.y, p2from.x, p2from.y, p2.x, p2.y));

		Polygon p = new Polygon();
		p.addPoint(p2.x, p2.y);
		p.addPoint(p2.x+dx[parentPosition]*5+dx[(parentPosition+1)%4]*2, p2.y+dy[parentPosition]*5+dy[(parentPosition+1)%4]*2);
		p.addPoint(p2.x+dx[parentPosition]*5+dx[(parentPosition+3)%4]*2, p2.y+dy[parentPosition]*5+dy[(parentPosition+3)%4]*2);
		
		g2.setStroke(new BasicStroke(5));
		g2.draw(p);
	}
	
	@Override
	public void setLocation(int x, int y)
	{
		super.setLocation(x, y);
		mindMapPanel.repaint();
	}
	
	@Override
	public void setSize(int width, int height)
	{
		super.setSize(width, height);
		mindMapPanel.repaint();
	}
	
	public JNode getParentNode()
	{
		return parentNode;
	}
	
	@Override
	public String toString()
	{
		return "JNode[text="+getText()+",x="+getX()+",y="+getY()+",width="+getWidth()+",height="+getHeight()+",color="+getBackground()+",parent="+parentNode+"]";
	}
}
