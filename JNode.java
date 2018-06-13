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
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;

public class JNode extends JLabel
{
	private final static int SMALLBOX_SIZE = 6;
	
	private int code;
	private int parentCode;
	private MindMapPanel mindMapPanel;
	private JNode parentNode;
	private JNode thisOne;
	private ArrayList<JLabel> selection;
	
	public JNode(int code, MindMapPanel mindMapPanel, String label, int x, int y, int width, int height, Color color, JNode parentNode)
	{
		super(label);
		setBounds(x, y, width, height);
		setBackground(color);
		setHorizontalAlignment(JLabel.CENTER);
		setBorder(new RoundedBorder(new Color(70,70,70), 2, 20));
		
		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);

		this.code = code;
		this.mindMapPanel = mindMapPanel;
		this.parentNode = parentNode;
		this.parentCode = parentNode==null? -1 : parentNode.getCode();
		this.thisOne = this;
		
		selection = new ArrayList<>();
		JLabel box = new JLabel();
		box.setBounds(x, y, width, height);
		box.setBorder(BorderFactory.createLineBorder(new Color(156, 179, 252)));
		selection.add(box);
		
		for(int i=0; i<3; ++i)
			for(int j=0; j<3; ++j)
			{
				if(i==1 && j==1)
					continue;

				int tx = x+i*width/2-SMALLBOX_SIZE/2;
				int ty = y+j*height/2-SMALLBOX_SIZE/2;
				selection.add(new JSizeBox(thisOne, i, j, tx, ty, SMALLBOX_SIZE));
			}
		
		setFocus(false);
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
			mindMapPanel.focusRemove();
			setFocus(true);
		}
		
		@Override
		public void mouseDragged(MouseEvent e)
		{
			int x = start.x+e.getX()-mouse.x;
			int y = start.y+e.getY()-mouse.y;
			setLocation(x, y);
			start.move(x, y);
			AttributePanel.setMainPanel(thisOne);
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
		selection.get(0).setLocation(x, y);
		for(int i=1; i<9; ++i)
		{
			int idx = i<5? i-1 : i;
			int tx = x+idx/3*getWidth()/2-SMALLBOX_SIZE/2;
			int ty = y+idx%3*getHeight()/2-SMALLBOX_SIZE/2;
			selection.get(i).setLocation(tx, ty);
		}
		mindMapPanel.repaint();
	}
	
	@Override
	public void setSize(int width, int height)
	{
		int minimumSize = 20;
		if(width < minimumSize)
			width = minimumSize;
		if(height < minimumSize)
			height = minimumSize;
		super.setSize(width, height);
		selection.get(0).setSize(width, height);
		for(int i=1; i<9; ++i)
		{
			int idx = i<5? i-1 : i;
			int tx = getX()+idx/3*width/2-SMALLBOX_SIZE/2;
			int ty = getY()+idx%3*height/2-SMALLBOX_SIZE/2;
			selection.get(i).setLocation(tx, ty);
		}
		mindMapPanel.repaint();
	}
	
	public void setFocus(boolean flag)
	{
		if(flag)
			AttributePanel.setFocus(thisOne);
		for(JLabel label : selection)
			label.setVisible(flag);
	}
	
	public int getCode()
	{
		return code;
	}
	
	public JNode getParentNode()
	{
		return parentNode;
	}
	
	public ArrayList<JLabel> getSelection()
	{
		return selection;
	}
	
	public ArrayList<Object> getData()
	{
		ArrayList<Object> data = new ArrayList<>();

		data.add(code);
		data.add(parentCode);
		data.add(getText());
		data.add(getX());
		data.add(getY());
		data.add(getWidth());
		data.add(getHeight());
		data.add(getBackground().getRed());
		data.add(getBackground().getGreen());
		data.add(getBackground().getBlue());
		
		return data;
	}
	
	@Override
	public String toString()
	{
		return "JNode[code="+code+",text="+getText()+",x="+getX()+",y="+getY()+",width="+getWidth()+",height="+getHeight()+",color="+getBackground()+",parentCode="+parentCode+"]";
	}
}