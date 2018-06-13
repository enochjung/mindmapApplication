package mindmapApplication;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class FileManager
{
	private static MindMapPanel mmp;
	private static JSONObject obj;
	private static JFileChooser chooser = new JFileChooser();
	private static FileNameExtensionFilter filter = new FileNameExtensionFilter("Json Files", "json");
	public FileManager()
	{
		chooser.setFileFilter(filter);	
	}
	
	public static void setMindMapPanel(MindMapPanel temp)
	{
		mmp = temp;
	}
	
	public static void openFile()
	{
		String pathName;
		int ret = chooser.showOpenDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION)
		{
			pathName = chooser.getSelectedFile().getPath();
			
		}	
	}
	
	public static void saveFile()
	{
		String pathName;
		obj = new JSONObject();
		int ret = chooser.showSaveDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION)
		{
			pathName = chooser.getSelectedFile().toString()+"."+chooser.getFileFilter().getDescription();
			//obj.put("data", mmp.getData);
		}
	}
}
