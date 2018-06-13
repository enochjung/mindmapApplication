package mindmapApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class TextEditorPanel extends JPanel
{
	private JButton apply;
	private static JTextArea text;
	private static MindMapPanel smmp;
	
	public TextEditorPanel(MindMapPanel mmp)
	{	
		smmp = mmp;
		text = new JTextArea();
		apply = new JButton("적용");
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				createMindMap();
			
			}
		});
		
		setLayout(new BorderLayout());
		setBackground(Color.LIGHT_GRAY);
		add(new JScrollPane(text), BorderLayout.CENTER);
		add(apply, BorderLayout.SOUTH);
		text.setBackground(Color.LIGHT_GRAY);
		text.setTabSize(2);
		setMinimumSize(new Dimension(200,600));
	}
	public static String sendText()
	{
		return text.getText();
	}
	
	public static void setdata(String data)
	{
		text.setText(data);
	}
	
	public static void createMindMap()
	{
		if(smmp != null)
			smmp.makeNodes(text.getText());
	}
}
