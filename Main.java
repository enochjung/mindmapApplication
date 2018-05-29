package mindmapApplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class Main extends JFrame
{
	public Main()
	{
		setTitle("MindMapApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		fileMenu.add(new JMenuItem("Save"));
		fileMenu.add(new JMenuItem("Save As"));
		fileMenu.add(new JMenuItem("Load"));
		
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
		toolBar.setBackground(Color.LIGHT_GRAY);
		
		toolBar.setFloatable(false);
		toolBar.add(new JButton("New"));
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//////////////////
		JTextField left = new JTextField();
		left.setMinimumSize(new Dimension(250,600));
		left.setPreferredSize(new Dimension(300,600));
		JPanel middle = new JPanel();
		middle.setMinimumSize(new Dimension(400,600));
		middle.setPreferredSize(new Dimension(400,600));
		JPanel right = new JPanel();
		right.setMinimumSize(new Dimension(250,600));
		right.setPreferredSize(new Dimension(300,600));
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,middle);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split,right);
		
		getContentPane().add(split2);
		
		//////////////////
		
		setSize(1000, 600);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Main frame = new Main();
	}
}
