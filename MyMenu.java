package mindmapApplication;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyMenu extends JMenuBar
{
	public MyMenu()
	{
		super();
		
		//setBackground(Color.GRAY);
		//setMargin(new Insets(0,10,0,10));
		setBorderPainted(false);
		setOpaque(false);
		
		MenuActionListener listener = new MenuActionListener();
		
		JMenu fileMenu = new JMenu("File");
		String [] fileTitle = {"New", "Open File", "Save", "Save As", "Exit"};
		String [] editTitle = {"Apply", "Change"};
		JMenuItem [] fileItem = new JMenuItem[fileTitle.length];
		JMenuItem [] editItem = new JMenuItem[editTitle.length];
		
		
		for(int i = 0; i < fileItem.length; i++)
		{
			fileItem[i] = new JMenuItem(fileTitle[i]);
			fileItem[i].addActionListener(listener);
			//fileItem[i].setForeground(Color.WHITE);
			fileMenu.add(fileItem[i]);
		}
		
		JMenu editMenu = new JMenu("Edit");
		for(int i = 0; i < editItem.length; i++)
		{
			editItem[i] = new JMenuItem(editTitle[i]);
			editItem[i].addActionListener(listener);
			editMenu.add(editItem[i]);
		}
		
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
