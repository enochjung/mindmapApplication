package mindmapApplication;

import java.awt.*;
import javax.swing.*;
public class TextEditorPanel extends JPanel
{
	private JTextArea text;
	private JButton apply;
	public TextEditorPanel()
	{
		text = new JTextArea();
		apply = new JButton("Àû¿ë");
		
		setLayout(new BorderLayout(10,10));
		add(new JScrollPane(text), BorderLayout.CENTER);
		add(apply, BorderLayout.SOUTH);
		text.setBackground(Color.LIGHT_GRAY);
		text.setTabSize(2);
		setMinimumSize(new Dimension(200,600));
	}
}
