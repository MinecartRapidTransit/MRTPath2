package net.minecartrapidtransit.path.launcher;

import java.io.File;


public class FileStoreUtils {
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
		return getDataFolderPath() + File.pathSeparator + file.replace('/', File.pathSeparatorChar);
	}
}
