package mindmapApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FileManager
{
	private static MindMapPanel mmp;
	private static JSONObject obj;
	private static JFileChooser chooser = new JFileChooser();
	private static FileNameExtensionFilter filter = new FileNameExtensionFilter("json", "json");
	private static String pathName;
	
	public static void setMindMapPanel(MindMapPanel temp)
	{
		mmp = temp;
		chooser.setFileFilter(filter);
	}
	
	public static void openFile()
	{
		JSONParser parser = new JSONParser();
		int ret = chooser.showOpenDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION)
		{
			pathName = chooser.getSelectedFile().getPath();	
			try {
				Object obj = parser.parse(new FileReader(pathName));
				
				JSONObject jsonObject = (JSONObject) obj;
				
				mmp.readData((ArrayList<ArrayList<Object>>)jsonObject.get("data"));
				TextEditorPanel.setdata((String)jsonObject.get("text"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}		
		}

	}
	
	public static void saveNewFile()
	{
		obj = new JSONObject();
		int ret = chooser.showSaveDialog(null);
		if(ret == JFileChooser.APPROVE_OPTION)
		{
			pathName = chooser.getSelectedFile().toString()+"."+chooser.getFileFilter().getDescription();
			obj.put("data", mmp.getData());
			obj.put("text", TextEditorPanel.sendText());
			System.out.println(pathName);
			try {
				FileWriter file = new FileWriter(pathName);
				file.write(obj.toJSONString());
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void saveFile()
	{
		obj = new JSONObject();
		obj.put("data", mmp.getData());
		obj.put("text", TextEditorPanel.sendText());
		try
		{
			FileWriter file = new FileWriter(pathName);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static void resetPath()
	{
		pathName = null;
	}
	
	public static String getPath()
	{
		return pathName;
	}
	
	public static void clearMiddle()
	{
		mmp.makeNodes("");
	}
}
