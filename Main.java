package mindmapApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame
{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	public Main()
	{
		setTitle("MindMapApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		mb.setBackground(Color.GRAY);
		
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
		
		mb.add(fileMenu);
		mb.add(editMenu);
		mb.add(toolsMenu);
		mb.add(viewMenu);
		mb.add(helpMenu);
		setJMenuBar(mb);
		
		///////////////////
		
		JToolBar toolBar = new JToolBar("Kitae Menu");
		toolBar.setBackground(Color.GRAY);
		
		toolBar.setFloatable(false);
		
		String [] toolBarTitle = {"New", "Open File", "Save", "Save As", "Exit"};
		JButton [] toolBarItem = new JButton[toolBarTitle.length];
		
		for(int i = 0; i < toolBarItem.length; i++)
		{
			toolBarItem[i] = new JButton(toolBarTitle[i]);
			toolBar.add(toolBarItem[i]);
		}
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//////////////////
		
		Split splitComponent = new Split();
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitComponent.getLeft(),splitComponent.getMiddle());
		split.setDividerLocation(250);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split,splitComponent.getRight());
		split2.setDividerLocation(650);
		
		getContentPane().add(split2);
		
		//////////////////
		try {
	    	
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    	SwingUtilities.updateComponentTreeUI(this);
	    }
	    catch(Exception q) {
	    	
	    }
		/////
		setSize(1000, 600);
		int x = screenSize.width/2 - this.getWidth()/2;
		int y = screenSize.height/2 - this.getHeight()/2;
		this.setLocation(x, y);
		setVisible(true);
	}
	
	class MenuActionListener implements ActionListener { 
		public void actionPerformed(ActionEvent e) {
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
	
	public static void main(String[] args)
	{
		Main frame = new Main();
	}
}
