package net.minecartrapidtransit.path.launcher;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

@SuppressWarnings("serial")
public class Launcher extends JFrame {
	public static void main(String[] args) throws IOException{
		new Launcher();
	}
	
	private JLabel label;
	private JProgressBar pb;
	private FileStoreUtils fsu;
	
	public Launcher() throws IOException{
		super("MRTPath Launcher");
		setSize(400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		label = new JLabel("Loading");
		label.setHorizontalAlignment(JLabel.CENTER);
		add(label, BorderLayout.PAGE_START);
		
		pb =  new JProgressBar();
		pb.setValue(0);
		pb.setStringPainted(true);
		pb.setVisible(true);
		add(pb, BorderLayout.CENTER);
		setVisible(true);

		updateFiles();
		pb.setValue(0);
		label.setText("Attempting launch...");
		try{
			launchJar();
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "Could not launch the program. Check that you are connected to the internet otherwise please tell the developers.",
					"Catastrophic Failure", JOptionPane.DEFAULT_OPTION);
			System.exit(1);
		}	
	}
	
	private void updateFiles(){
		try{ // First let's get the version file
			downloadFile("version");
			fsu = new FileStoreUtils("version");
			updateFile("api", "jar");
			updateFile("gui", "jar");
			downloadFile("mrtnetwork.yml");
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "There was a problem downloading the file. This may have been caused by us or by"
					+ " your internet connection. We will try to launch regardless.", "Error", JOptionPane.DEFAULT_OPTION);
		}

	}
	
	private void launchJar() throws IOException, InterruptedException {
		String classpath = fsu.getVersionedDataFilePath("api", "jar") + File.pathSeparator + fsu.getVersionedDataFilePath("gui", "jar");
		Process i = Runtime.getRuntime().exec(new String[]{"java", "-cp", classpath, S.main_class});
		setVisible(false);
		String line;
		BufferedReader in = new BufferedReader(
				new InputStreamReader(i.getInputStream()));
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		in.close();
		i.waitFor();
		if(i.exitValue() != 0) throw new IOException("Failed launch");
		System.exit(0);

	}
	
	private void downloadFile(String file) throws IOException{
		label.setText(String.format("Downloading file: %s", file));
		Downloader.guiDownload(S.filesLocation + file, FileStoreUtils.getDataFilePath(file), pb);
	}
	
	private void updateFile(String file, String type) throws IOException {
		downloadFile(fsu.getVersionedFile(file, type));
	}
	
}
