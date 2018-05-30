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
		toolBar.setBackground(Color.GRAY);
		
		toolBar.setFloatable(false);
		toolBar.add(new JButton("New"));
		
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//////////////////
		JTextArea text = new JTextArea();
		JScrollPane left = new JScrollPane(text);
		
		text.setBackground(Color.LIGHT_GRAY);
		left.setMinimumSize(new Dimension(250,600));
		//left.setPreferredSize(new Dimension(300,600));
		JPanel middle = new JPanel();
		middle.setMinimumSize(new Dimension(400,600));
		//middle.setPreferredSize(new Dimension(400,600));	
		JPanel right = new JPanel();
		right.setLayout(null);
		
		JLabel size = new JLabel("ũ��");
		size.setBounds(20, 20, 30, 30);
		JLabel width = new JLabel("�ʺ�");
		width.setBounds(100, 40, 30, 30);
		JLabel height = new JLabel("����");
		height.setBounds(210, 40, 30, 30);
		
		JLabel location = new JLabel("��ġ");
		location.setBounds(20, 100, 30, 30);
		JLabel x = new JLabel("x");
		x.setBounds(100, 120, 30, 30);
		JLabel y = new JLabel("y");
		y.setBounds(210, 120, 30, 30);
		
		JLabel color = new JLabel("��");
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
		right.setMinimumSize(new Dimension(280,600));
		//right.setPreferredSize(new Dimension(300,600));
		
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
