package mindmapApplication;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JToolBar;

public class MyToolBar extends JToolBar
{
	public MyToolBar()
	{
		super("Kitae Menu");
		setBackground(Color.GRAY);
		setFloatable(false);
		
		String [] toolBarTitle = {"New", "Open File", "Save", "Save As", "Exit"};
		JButton [] toolBarItem = new JButton[toolBarTitle.length];
		
		for(int i = 0; i < toolBarItem.length; i++)
		{
			toolBarItem[i] = new JButton(toolBarTitle[i]);
			add(toolBarItem[i]);
		}
		
	}
}
