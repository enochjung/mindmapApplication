package mindmapApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class TextEditorPanel extends JPanel
{
	private JTextArea text;
	private JButton apply;
	
	public TextEditorPanel(MindMapPanel mmp)
	{	
		text = new JTextArea();
		apply = new JButton("적용");
		apply.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				mmp.makeNodes(text.getText());
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
}
