package mindmapApplication;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JLabel;

public class JNode extends JLabel
{
	private JNode parent;
	private ArrayList<JNode> children;
	
	public JNode(String label, int x, int y, int width, int height, Color color, JNode parent)
	{
		super(label);
		setBounds(x, y, width, height);
		setBackground(color);
		setOpaque(true);
		setHorizontalAlignment(JLabel.CENTER);
		
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
	
	public String toString()
	{
		return "mindmapApplication.JNode[text="+getText()+",x="+getX()+",y="+getY()+",width="+getWidth()+",height="+getHeight()+",color="+getBackground()+"]";
	}
}
