package mindmapApplication;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Main extends JFrame
{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	public Main()
	{
		setTitle("MindMapApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyMenu mb = new MyMenu();
		setJMenuBar(mb);
		
		MyToolBar toolBar = new MyToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//////////////////
		
		TextEditorPanel left = new TextEditorPanel();
		JPanel middle = new JPanel(); // mindMapPanel
		AttributePanel right = new AttributePanel();
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, middle);
		split.setDividerLocation(250);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split, right);
		split2.setDividerLocation(750);
		
		getContentPane().add(split2);
		
		//////////////////
		try {
	    	
	    	UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
	    	SwingUtilities.updateComponentTreeUI(this);
	    }
	    catch(Exception q) {
	    	
	    }
		/////
		setSize(1100, 600);
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
