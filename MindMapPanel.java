package mindmapApplication;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;

public class MindMapPanel extends JPanel
{
	public MindMapPanel(int width, int height)
	{
		super();
		setLayout(null);
		setMinimumSize(new Dimension(width, height));
	}
	
	public void makeNodes(String query)
	{
		ArrayList<JNode> nodes = new ArrayList<>();
		ArrayList<Integer> indent = new ArrayList<>();

		removeAll();
		String[] names = query.split("\n");
		for(String name : names)
		{
			if(name.trim().equals(""))
			{
				nodes.add(null);
				indent.add(999);
				continue;
			}
			
			JNode parent = null;
			int cnt = 0;
			
			while(cnt<name.length() && name.charAt(cnt)=='\t')
				++cnt;
			for(int i=indent.size()-1; i>=-1; --i)
			{
				if(i == -1)
					parent = null;
				else if(indent.get(i) < cnt)
				{
					parent = nodes.get(i);
					break;
				}
			}
			
			int x = getRandom(60, 300);
			int y = getRandom(100,500);
			int width = 70;
			int height = 40;
			Color color = new Color(getRandom(150,255), getRandom(150,255), getRandom(150,255));
			nodes.add(new JNode(name.trim(), x, y, width, height, color, parent));
		}
		
		for(JNode node : nodes)
			if(node != null)
				add(node);
	}
	
	private int getRandom(int a, int b)
	{
		return (int)(Math.random()*(b-a+1)+a);
	}
}
