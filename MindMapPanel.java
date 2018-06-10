package mindmapApplication;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MindMapPanel extends JPanel
{
	ArrayList<JNode> nodes;
	
	public MindMapPanel(int width, int height)
	{
		super();
		setLayout(null);
		setMinimumSize(new Dimension(width, height));
		
		nodes = new ArrayList<>();
	}
	
	public void makeNodes(String query)
	{
		ArrayList<JNode> tmpNodes = new ArrayList<>();
		ArrayList<Integer> indent = new ArrayList<>();
		ArrayList<Integer> depth = new ArrayList<>();
		ArrayList<Color> color = new ArrayList<>();
		
		nodes.clear();
		removeAll();
		updateUI();
		
		String[] names = query.split("\n");		
		for(String name : names)
		{			
			if(name.trim().equals(""))
			{
				tmpNodes.add(null);
				indent.add(999);
				depth.add(999);
				continue;
			}
			
			JNode parent = null;
			int cnt = 0;
			
			while(cnt<name.length() && name.charAt(cnt)=='\t')
				++cnt;
			
			boolean found = false;
			for(int i=indent.size()-1; i>=0; --i)
				if(indent.get(i) < cnt)
				{
					found = true;
					parent = tmpNodes.get(i);
					depth.add(depth.get(i)+1);
					break;
				}
			if(!found)
				depth.add(0);
			
			name = name.trim();
			int x = getRandom(60, 300);
			int y = getRandom(100,500);
			int width = 70+name.length()*10;
			int height = 40;
			if(color.size() <= depth.get(depth.size()-1))
				color.add(new Color(getRandom(150,255), getRandom(150,255), getRandom(150,255)));
			tmpNodes.add(new JNode(this, name, x, y, width, height, color.get(depth.get(depth.size()-1)), parent));
			indent.add(cnt);
		}

		for(JNode node : tmpNodes)
			if(node != null)
			{
				add(node);
				nodes.add(node);
			}
		
		repaint();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(new Color(150, 150, 150));
		
		g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

		for(JNode node : nodes)
			if(node.getParentNode() != null)
				node.draw(g2);
	}
	
	private int getRandom(int a, int b)
	{
		return (int)(Math.random()*(b-a+1)+a);
	}
}
