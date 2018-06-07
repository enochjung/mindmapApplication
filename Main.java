package mindmapApplication;

import java.awt.*;
import javax.swing.*;

public class Main extends JFrame
{
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
		JTextArea text = new JTextArea();
		JPanel left = new JPanel();
		JButton apply = new JButton("적용");
		left.setLayout(new BorderLayout(10,10));
		left.add(new JScrollPane(text), BorderLayout.CENTER);
		left.add(apply, BorderLayout.SOUTH);
		
		text.setBackground(Color.LIGHT_GRAY);
		left.setMinimumSize(new Dimension(200,600));
		
		JPanel middle = new JPanel();
		middle.setMinimumSize(new Dimension(300,600));
		
		JPanel right = new JPanel();
		right.setLayout(null);
		
		JLabel size = new JLabel("크기");
		size.setBounds(20, 20, 30, 30);
		JLabel width = new JLabel("너비");
		width.setBounds(100, 40, 30, 30);
		JLabel height = new JLabel("높이");
		height.setBounds(210, 40, 30, 30);
		
		JLabel location = new JLabel("위치");
		location.setBounds(20, 100, 30, 30);
		JLabel x = new JLabel("x");
		x.setBounds(100, 120, 30, 30);
		JLabel y = new JLabel("y");
		y.setBounds(210, 120, 30, 30);
		
		JLabel color = new JLabel("색");
		color.setBounds(20, 180, 30, 30);
		JLabel hex = new JLabel("HEX");
		hex.setBounds(100, 200, 30, 30);
		
		JTextField colorData = new JTextField();
		colorData.setBounds(90, 180, 80, 30);
		
		JTextField xData = new JTextField();
		xData.setBounds(90, 100, 80, 30);
		JTextField yData = new JTextField();
		yData.setBounds(200, 100, 80, 30);
		
		JTextField widthData = new JTextField();
		widthData.setBounds(90, 20, 80, 30);
		JTextField heightData = new JTextField();
		heightData.setBounds(200, 20, 80, 30);
		
		right.add(hex);
		right.add(x);
		right.add(y);
		right.add(height);
		right.add(width);
		right.add(colorData);
		right.add(color);
		right.add(xData);
		right.add(yData);
		right.add(location);
		right.add(heightData);
		right.add(widthData);
		right.add(size);
		right.setBackground(Color.WHITE);
		right.setMinimumSize(new Dimension(300,600));
		
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,left,middle);
		split.setDividerLocation(250);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split,right);
		split2.setDividerLocation(650);
		
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
