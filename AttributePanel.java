package mindmapApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AttributePanel extends JPanel
{
	private static JNode focus;
	
	private static JPanel mainPanel = new JPanel();
	
	private JButton change = new JButton("변경");
	
	private static JTextField textData = new JTextField(10);
	private static JTextField widthData = new JTextField(10);
	private static JTextField heightData = new JTextField(10);
	private static JTextField xData = new JTextField(10);
	private static JTextField yData = new JTextField(10);
	private static JTextField colorData = new JTextField(10);
	public AttributePanel()
	{
		super();
		
		JLabel textLabel = new JLabel("이름");
		JLabel size = new JLabel("크기");
		JLabel width = new JLabel("너비");
		JLabel height = new JLabel("높이");
		JLabel location = new JLabel("위치");
		JLabel x = new JLabel("X");
		JLabel y = new JLabel("Y");
		JLabel color = new JLabel("색");
		JLabel hex = new JLabel("HEX");
		
		setLayout(new BorderLayout());
		mainPanel.setLayout(null);
		
		textLabel.setBounds(20,20,50,30);
		textData.setBounds(90,20,80,30);
		textData.setEditable(false);
		
		size.setBounds(20, 100, 30, 30);
		width.setBounds(115, 120, 30, 30);
		height.setBounds(225, 120, 30, 30);
		heightData.setBounds(200, 100, 80, 30);
		widthData.setBounds(90, 100, 80, 30);
		
		location.setBounds(20, 180, 30, 30);
		x.setBounds(127, 200, 30, 30);
		y.setBounds(237, 200, 30, 30);
		xData.setBounds(90, 180, 80, 30);
		yData.setBounds(200, 180, 80, 30);
		
		color.setBounds(20, 260, 30, 30);
		hex.setBounds(115, 280, 30, 30);
		colorData.setBounds(90, 260, 80, 30);
		
		change.setBounds(125, 500, 300, 30);
		change.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				focus.setLocation(Integer.parseInt(xData.getText()), Integer.parseInt(yData.getText()));
				focus.setSize(Integer.parseInt(widthData.getText()), Integer.parseInt(heightData.getText()));
			}
			
		});
		
		mainPanel.add(textLabel);
		mainPanel.add(textData);
		mainPanel.add(hex);
		mainPanel.add(x);
		mainPanel.add(y);
		mainPanel.add(height);
		mainPanel.add(width);
		mainPanel.add(colorData);
		mainPanel.add(color);
		mainPanel.add(xData);
		mainPanel.add(yData);
		mainPanel.add(location);
		mainPanel.add(heightData);
		mainPanel.add(widthData);
		mainPanel.add(size);
		mainPanel.setBackground(Color.WHITE);
		add(change, BorderLayout.SOUTH);
		add(mainPanel, BorderLayout.CENTER);
		setBackground(Color.WHITE);
		setMinimumSize(new Dimension(300,600));
		setMaximumSize(new Dimension(300,600));
	}
	public static String rgbToHex(String color)
	{
		String [] rgb;
		color = color.replace("java.awt.Color[r=", "");
		color = color.replace("g=", "");
		color = color.replace("b=", "");
		color = color.replace("]", "");
		
		
		
		return color;
	}
	
	public static void setMainPanel(JNode data)
	{
		String color = ""+data.getBackground();
		textData.setText(data.getText());
		widthData.setText(""+data.getWidth());
		heightData.setText(""+data.getHeight());
		xData.setText(""+data.getX());
		yData.setText(""+data.getY());
		
		
		color = rgbToHex(color);
		
		colorData.setText(color);
		
		mainPanel.repaint();
	}
	
	public static void setFocus(JNode data)
	{
		focus = data;
	}
	
	
}
