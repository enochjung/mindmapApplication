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
		ArrayList<Integer> childNum = new ArrayList<>();
		ArrayList<Integer> count = new ArrayList<>();
		ArrayList<Integer> parentNum = new ArrayList<>();
		ArrayList<Double> angle = new ArrayList<>();
		ArrayList<Integer> depth = new ArrayList<>();
		ArrayList<Color> color = new ArrayList<>();
		
		nodes.clear();
		removeAll();
		updateUI();
		
		int nodeNum = 0;
		int code = 0;
		
		String[] names = query.split("\n");		
		for(String name : names)
		{			
			if(name.trim().equals(""))
			{
				tmpNodes.add(null);
				indent.add(999);
				childNum.add(999);
				count.add(999);
				parentNum.add(999);
				angle.add(999D);
				depth.add(999);
				color.add(null);
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
					childNum.set(i, childNum.get(i)+1);
					parentNum.add(i);
					depth.add(depth.get(i)+1);
					break;
				}
			if(!found)
			{
				parentNum.add(999);
				depth.add(0);
			}
			
			childNum.add(0);
			count.add(0);
			angle.add(Math.random()*Math.PI*2);
			color.add(new Color(getRandom(150,255), getRandom(150,255), getRandom(150,255)));
			
			name = name.trim();
			int width = 70+name.length()*10;
			int height = 40;
			tmpNodes.add(new JNode(code++, this, name, 0, 0, width, height, Color.BLACK, parent));
			indent.add(cnt);
			++nodeNum;
		}
		
		for(int i=0; i<tmpNodes.size(); ++i)
		{
			JNode node = tmpNodes.get(i);
			if(node != null)
			{
				int x, y;
				int dep = depth.get(i);

				if(dep == 0)
				{
					int width = this.getSize().width;
					int height = this.getSize().height;
					
					x = getRandom(width*4/10, width*6/10) - node.getWidth()/2;
					y = getRandom(height*4/10, height*6/10) - node.getHeight()/2;
					node.setLocation(x, y);
					node.setBackground(new Color(getRandom(150,255), getRandom(150,255), getRandom(150,255)));
				}
				else if(dep == 1)
				{
					int length = (int)(130*Math.log10(nodeNum*50+1)/(depth.get(i)+1));
					int parentX = node.getParentNode().getX()+node.getParentNode().getWidth()/2;
					int parentY = node.getParentNode().getY()+node.getParentNode().getHeight()/2;
					int idx = parentNum.get(i);
					int cnt = count.get(idx);
					int num = childNum.get(idx);
					double initialAngle = angle.get(idx);
					
					x = parentX - node.getWidth()/2 + (int)(length*Math.cos(initialAngle+Math.PI*2*cnt/num));
					y = parentY - node.getHeight()/2 + (int)(length*Math.sin(initialAngle+Math.PI*2*cnt/num));
					node.setLocation(x, y);
					node.setBackground(color.get(idx));
					count.set(idx, cnt+1);
				}
				else
				{
					int length = (int)(130*Math.log10(nodeNum*50+1)/(depth.get(i)+1));
					int parentX = node.getParentNode().getX()+node.getParentNode().getWidth()/2;
					int parentY = node.getParentNode().getY()+node.getParentNode().getHeight()/2;
					JNode granpa = node.getParentNode().getParentNode();
					int granpaX = granpa.getX()+granpa.getWidth()/2;
					int granpaY = granpa.getY()+granpa.getHeight()/2;
					int idx = parentNum.get(i);
					int cnt = count.get(idx);
					int num = childNum.get(idx);
					double initialAngle = Math.atan2(granpaY-parentY, granpaX-parentX);
					
					x = parentX - node.getWidth()/2 + (int)(length*Math.cos(initialAngle+Math.PI*2*(cnt+1)/(num+1)));
					y = parentY - node.getHeight()/2 + (int)(length*Math.sin(initialAngle+Math.PI*2*(cnt+1)/(num+1)));
					node.setLocation(x, y);
					node.setBackground(color.get(idx));
					count.set(idx, cnt+1);
				}
			}
		}

		for(JNode node : tmpNodes)
			if(node != null)
			{
				for(int i=1; i<9; ++i)
					add(node.getSelection().get(i));
				add(node);
				add(node.getSelection().get(0));
				nodes.add(node);
			}
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
	
	public ArrayList<ArrayList<Object>> getData()
	{
		ArrayList<ArrayList<Object>> data = new ArrayList<>();
		
		for(JNode node : nodes)
			data.add(node.getData());
		
		return data;
	}
	
	public void readData(ArrayList<ArrayList<Object>> data)
	{
		nodes.clear();
		removeAll();
		updateUI();
		
		for(ArrayList<Object> datus : data)
		{
			int code = ((Long)datus.get(0)).intValue();
			int parentCode = ((Long)datus.get(1)).intValue();
			String label = (String)datus.get(2);
			int x = ((Long)datus.get(3)).intValue();
			int y = ((Long)datus.get(4)).intValue();
			int width = ((Long)datus.get(5)).intValue();
			int height = ((Long)datus.get(6)).intValue();
			int r = ((Long)datus.get(7)).intValue();
			int g = ((Long)datus.get(8)).intValue();
			int b = ((Long)datus.get(9)).intValue();
			Color color = new Color(r, g, b);
			JNode parent = parentCode==-1? null : nodes.get(parentCode);

			nodes.add(new JNode(code, this, label, x, y, width, height, color, parent));
		}
		
		for(JNode node : nodes)
			add(node);
	}
	
	private int getRandom(int a, int b)
	{
		return (int)(Math.random()*(b-a+1)+a);
	}
}