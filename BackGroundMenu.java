package mindmapApplication;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JMenuBar;

public class BackGroundMenu extends JMenuBar
{
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.GRAY);
		g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
	}
	
}
