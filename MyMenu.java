package mindmapApplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyMenu extends JMenuBar
{
	public MyMenu()
	{
		super();
		setBackground(Color.GRAY);
		
		JMenu fileMenu = new JMenu("File");
		String [] fileTitle = {"New", "Open File", "Save", "Save As", "Exit"};
		JMenuItem [] fileItem = new JMenuItem[fileTitle.length];
		
		MenuActionListener listener = new MenuActionListener();
		for(int i = 0; i < fileItem.length; i++)
		{
			fileItem[i] = new JMenuItem(fileTitle[i]);
			fileItem[i].addActionListener(listener);
			fileMenu.add(fileItem[i]);
		}
		
		JMenu editMenu = new JMenu("Edit");
		JMenu toolsMenu = new JMenu("Tools");
		JMenu viewMenu = new JMenu("View");
		JMenu helpMenu = new JMenu("Help");
		
		add(fileMenu);
		add(editMenu);
		add(toolsMenu);
		add(viewMenu);
		add(helpMenu);
	}
	
	class MenuActionListener implements ActionListener 
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
			}
		}
	}
}
