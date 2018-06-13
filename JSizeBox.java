package mindmapApplication;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class JSizeBox extends JLabel
{
	private JNode node;
	private int i;
	private int j;
	
	public JSizeBox(JNode node, int i, int j, int x, int y, int size) 
	{
		this.node = node;
		this.i = i;
		this.j = j;
		
		setBounds(x, y, size, size);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setBackground(Color.WHITE);
		setOpaque(true);

		MyMouseListener listener = new MyMouseListener();
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	
	private class MyMouseListener extends MouseAdapter implements MouseListener, MouseMotionListener
	{
		private Point start;
		private int x;
		private int y;
		private int width;
		private int height;
		
		public MyMouseListener()
		{
			start = new Point();
		}
		
		@Override
		public void mousePressed(MouseEvent e)
		{
			start.move(e.getXOnScreen(), e.getYOnScreen());
			x = node.getX();
			y = node.getY();
			width = node.getWidth();
			height = node.getHeight();
			AttributePanel.setMainPanel(node);
		}
		
		@Override
		public void mouseDragged(MouseEvent e)
		{
			int mx = e.getXOnScreen();
			int my = e.getYOnScreen();

			int toX = x;
			int toY = y;
			int toWidth = width;
			int toHeight = height;
			
			if(i == 0)
			{
				toX = x+mx-start.x;
				toWidth = width-mx+start.x;
			}
			else if(i == 2)
				toWidth = width+mx-start.x;

			if(j == 0)
			{
				toY = y+my-start.y;
				toHeight = height-my+start.y;
			}
			else if(j == 2)
				toHeight = height+my-start.y;
			
			node.setLocation(toX, toY);
			node.setSize(toWidth, toHeight);
			AttributePanel.setMainPanel(node);
		}
	}
}
