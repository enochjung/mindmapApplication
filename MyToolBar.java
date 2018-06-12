package mindmapApplication;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

public class MyToolBar extends JToolBar
{
	public MyToolBar()
	{
		super("Kitae Menu");
		setBackground(Color.GRAY);
		setFloatable(false);
		setMargin(new Insets(0,10,0,10));
		ToolBarActionListener listener = new ToolBarActionListener();
		
		String [] toolBarTitle = {"New", "Open File", "Save", "Save As", "Apply", "Change","Exit"};
		JButton [] toolBarItem = new MyButton[toolBarTitle.length];
		
		for(int i = 0; i < toolBarItem.length; i++)
		{
			toolBarItem[i] = new MyButton(toolBarTitle[i]);
			toolBarItem[i].addActionListener(listener);
			add(toolBarItem[i]);
		}
		
	}
	class ToolBarActionListener implements ActionListener 
	{ 
		public void actionPerformed(ActionEvent e) 
		{
			String cmd = e.getActionCommand(); 
			switch(cmd) {
				case "New" :
					
					break;
				case "Open File" :
					
					break;
				case "Save" :
					
					break;
				case "Save As" :
					
					break;
				case "Exit" :
					System.exit(0); 
					break;
				case "Apply" :
					TextEditorPanel.createMindMap();
					break;
				case "Change" :
					AttributePanel.setJNode();
					break;
			}
		}
	}
}
