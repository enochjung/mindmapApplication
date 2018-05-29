package mindmapApplication;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class Main extends JFrame
{
	public Main()
	{
		JMenuBar mb = new JMenuBar();
		JMenu screenMenu = new JMenu("File");

		screenMenu.add(new JMenuItem("Save"));
		screenMenu.add(new JMenuItem("Save As"));
		screenMenu.add(new JMenuItem("Load"));
		
		mb.add(screenMenu);
		mb.add(new JMenu("Edit"));
		
		setJMenuBar(mb);
		
		///////////////////
		
		JToolBar toolBar = new JToolBar("Kitae Menu");
		toolBar.setBackground(Color.LIGHT_GRAY);
		
		toolBar.setFloatable(false);
		toolBar.add(new JButton("New"));
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//////////////////
		
		JSplitPane split = new JSplitPane();
		split.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		split.setLeftComponent(new JButton("LEFT"));
		split.setRightComponent(new JButton("Right"));
		
		getContentPane().add(split);
		
		//////////////////
		
		setTitle("마인드맵");
		setSize(1000, 600);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		Main frame = new Main();
	}
}
