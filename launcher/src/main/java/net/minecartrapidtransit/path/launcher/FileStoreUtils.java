package net.minecartrapidtransit.path.launcher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;



public class FileStoreUtils {
	
	private String version;
	public FileStoreUtils(String versionFile) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(new File(getDataFilePath(versionFile))));
		version = br.readLine();
		br.close();
	}
	
	public String getVersionedFile(String filename, String type){
		return String.format("%s-%s.%s", filename, version, type);
	}
	
	public boolean fileNeedsUpdating(String filename, String type) {
		return(new File(getVersionedFile(filename, type)).exists());
	}
	
	public String getVersionedDataFilePath(String filename, String type){
		return getDataFilePath(getVersionedFile(filename, type));
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
