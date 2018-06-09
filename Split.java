package mindmapApplication;

import java.awt.*;
import javax.swing.*;

public class Split extends JPanel
{
	private JPanel left;
	private JPanel middle;
	private JPanel right;
	
	public Split()
	{
		JTextArea text = new JTextArea();
		left = new JPanel();
		JButton apply = new JButton("적용");
		left.setLayout(new BorderLayout(10,10));
		left.add(new JScrollPane(text), BorderLayout.CENTER);
		left.add(apply, BorderLayout.SOUTH);
		
		text.setBackground(Color.LIGHT_GRAY);
		left.setMinimumSize(new Dimension(200,600));
		
		middle = new JPanel();
		middle.setMinimumSize(new Dimension(300,600));
		
		right = new JPanel();
		right.setLayout(null);
		
		JButton change = new JButton("변경");
		change.setBounds(125, 450, 100, 30);
		
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
		
		right.add(change);
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
	}
	
	public JPanel getLeft()
	{
		return left;
	}
	
	public JPanel getMiddle()
	{
		return middle;
	}
	
	public JPanel getRight()
	{
		return right;
	}
	
}
