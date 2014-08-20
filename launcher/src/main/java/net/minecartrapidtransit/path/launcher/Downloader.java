package net.minecartrapidtransit.path.launcher;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JProgressBar;

public class Downloader {

	public static void download(String iNetLoc, String saveLoc) throws IOException {
		guiDownload(iNetLoc, saveLoc, null);
	}
	
	public static void guiDownload(String iNetLoc, String saveLoc, JProgressBar pb) throws IOException{
		URL url = null;
		HttpURLConnection con = null;
		int i;
		try {
			url = new URL(iNetLoc);
			con = (HttpURLConnection) url.openConnection();
			File file = new File(saveLoc);
			file.getParentFile().mkdirs();
			BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
			int filesize = con.getContentLength();
			float totalDataRead = 0;
			byte[] data = new byte[1024];
			while ((i = bis.read(data, 0, 1024)) != -1) {
				totalDataRead += i;
				bos.write(data, 0, i);
				if(pb != null){
					pb.setValue((int) ((totalDataRead*100)/filesize));
				}
			}
			bos.flush();
			bis.close();
			bos.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IOException("No Connection!");
		}
	}
	

}
