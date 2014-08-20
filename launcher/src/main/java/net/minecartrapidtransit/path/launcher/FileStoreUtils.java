package net.minecartrapidtransit.path.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;



public class FileStoreUtils {
	
	private String filesPath;
	private Map<String, List<String>> fileData;
	public FileStoreUtils(String filesPath) throws FileNotFoundException, IOException{
		this.filesPath = FileStoreUtils.getDataFilePath(filesPath);
		fileData = new HashMap<String, List<String>>();
		readFilesTxt();
	}
	
	private void readFilesTxt() throws FileNotFoundException, IOException{
		try(BufferedReader br = new BufferedReader(new FileReader(new File(filesPath)))){ // New java7 feature
			String line = br.readLine();
			Pattern p = Pattern.compile("(.+)#(.+)#(.+)");
	
			while(line != null){
				if(!line.isEmpty() && line.charAt(0) != '#'){
					Matcher m = p.matcher(line);
					m.find();
					fileData.put(m.group(1), new ArrayList<String>(Arrays.asList(m.group(2), m.group(3))));
				}
				line = br.readLine();
			}
		}
	  
	}
	
	public String getInetPath(String file) throws NullPointerException {
		return fileData.get(file).get(0);
	}
	
	public boolean fileNeedsUpdating(String filename) throws FileNotFoundException, IOException{
		File file = new File(FileStoreUtils.getDataFilePath(filename));
		if(!file.exists()) return true;
		String hash = DigestUtils.md5Hex(new FileInputStream(file));
		String shouldBeHash = fileData.get(filename).get(1);
		return !hash.equals(shouldBeHash);
	}
	
	public static String getDataFolderPath(){
		String os = System.getProperty("os.name").toLowerCase();
		boolean windows = os.contains("windows");
		boolean mac = os.contains("macosx");
		if(windows){
			return System.getenv("APPDATA") + "\\MRTPath2";
		}else{
			String home = System.getProperty("user.home");
			if(mac){
				return home + "/Library/Application Support/MRTPath2";
			}else{ // Linux
				return home + "/.mrtpath2";
			}
		}
	}
	
	public static String getDataFilePath(String file){
		return getDataFolderPath() + File.separator + file.replace('/', File.separatorChar);
	}
}
