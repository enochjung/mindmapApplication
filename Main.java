package mindmapApplication;

import java.awt.*;

import javax.swing.*;

public class Main extends JFrame
{
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension screenSize = tk.getScreenSize();
	
	private MyMenu mb;
	private MyToolBar toolBar;
	private TextEditorPanel left;
	private MindMapPanel middle;
	private AttributePanel right;
	
	public Main()
	{
		setTitle("MindMapApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mb = new MyMenu();
		setJMenuBar(mb);
		
		toolBar = new MyToolBar();
		getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//////////////////

		middle = new MindMapPanel(500, 600);
		left = new TextEditorPanel(middle);
		right = new AttributePanel();

		//////////////////
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, middle);

		split.setDividerLocation(250);
		split.setDividerSize(0);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,split, right);
		split2.setDividerLocation(780);
		split2.setDividerSize(0);
		getContentPane().add(split2);
		
		//////////////////
		
		try 
		{
		   	//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		   	//SwingUtilities.updateComponentTreeUI(this);
	    }
	    catch(Exception e) 
		{	
	    }

		setSize(1100, 600);
		int x = screenSize.width/2 - this.getWidth()/2;
		int y = screenSize.height/2 - this.getHeight()/2;
		this.setLocation(x, y);
		setVisible(true);
	}
	
	public static void main(String[] args)
	{
		new Main();
	}
}
