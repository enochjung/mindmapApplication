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
		ToolBarActionListener listener = new ToolBarActionListener();
		
		String [] toolBarTitle = {"New", "Open File", "Save", "Save As", "Apply", "Change","Exit"};
		JButton [] toolBarItem = new JButton[toolBarTitle.length];
		
		for(int i = 0; i < toolBarItem.length; i++)
		{
			toolBarItem[i] = new JButton(toolBarTitle[i]);
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
					if(FileManager.getPath() == null && !TextEditorPanel.sendText().equals(""))
						FileManager.saveNewFile();
					FileManager.resetPath();
					FileManager.clearMiddle();
					TextEditorPanel.setdata("");
					AttributePanel.clearPanel();
					break;
				case "Open File" :
					FileManager.openFile();
					break;
				case "Save" :
					if(FileManager.getPath() != null)
						FileManager.saveFile();
					break;
				case "Save As" :
					FileManager.saveNewFile();
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
