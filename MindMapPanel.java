package mindmapApplication;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class MindMapPanel
{
	private static MindMapPanel instance;
	private JPanel panel;
	
	private MindMapPanel()
	{
		panel = new JPanel();
		panel.setLayout(null);
		panel.setMinimumSize(new Dimension(400,600));

		panel.add(new JNode("Å©±â", 100, 100, 100, 50, Color.CYAN));
		panel.add(new JNode("asdf", 100, 200, 100, 50, Color.YELLOW));
		panel.add(new JNode("hello", 100, 300, 100, 50, Color.GRAY));
		panel.add(new JNode("world", 100, 400, 100, 50, Color.GREEN));
	}
	
	public JPanel getPanel()
	{
		return panel;
	}
	
	public static MindMapPanel getInstance() 
	{
		if(instance == null)
			instance = new MindMapPanel();
		return instance;
	}
}
