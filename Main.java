package mindmapApplication;

import java.awt.*;
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
		JMenuItem [] fileItem = new JMenuItem[6];
		String [] fileTitle = {"New", "Open File", "Save", "Save As","Refresh", "Exit"};
		
		//MenuActionListener listener = new MenuActionListener();
		for(int i = 0; i < fileItem.length; i++)
		{
			fileItem[i] = new JMenuItem(fileTitle[i]);
			//menuItem[i].addActionListener(listener);
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
		
		JButton [] toolBarItem = new JButton[6];
		String [] toolBarTitle = {"New", "Open File", "Save", "Save As","Refresh", "Exit"};
		
		for(int i = 0; i < fileItem.length; i++)
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
	    	//"javax.swing.plaf.nimbus.NimbusLookAndFeel"
	    	//"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"
	    	
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
	
	public static void main(String[] args)
	{
		Main frame = new Main();
	}
}
