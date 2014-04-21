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
	
	public Launcher() throws IOException{
		super("MRTPath Launcher");
		setSize(400, 100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null);
		
		label = new JLabel("Updating hashes file");
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
		try{ // First let's get the hashes file
			updateFile(S.filesLocation, S.files);
			// Now we can check if each file is up to date
			FileStoreUtils fsu = new FileStoreUtils(S.files);
			updateFile(fsu, S.jar);
			updateFile(fsu, S.gui);
		}catch(Exception e){
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null, "There was a problem downloading the file. This may have been caused by us or by"
					+ " your internet connection. We will try to launch regardless.", "Error", JOptionPane.DEFAULT_OPTION);
		}

	}
	
	private void launchJar() throws IOException, InterruptedException {
		String classpath = FileStoreUtils.getDataFilePath(S.jar) + File.pathSeparator + FileStoreUtils.getDataFilePath(S.gui);
		Process i = Runtime.getRuntime().exec(new String[]{"java", "-cp", classpath, S.main_class});
		setVisible(false);
		String line;
		BufferedReader in = new BufferedReader(
				new InputStreamReader(i.getInputStream()));
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
		i.waitFor();
		in.close();
		if(i.exitValue() != 0) throw new IOException("Failed launch");
	}
	
	private void updateFile(String inet, String file) throws IOException {
		label.setText(String.format("Downloading file \"%s\"", file));
		pb.setValue(0);
		Downloader.guiDownload(inet, FileStoreUtils.getDataFilePath(file), pb);
	}
	private void updateFile(FileStoreUtils fsu, String file) throws IOException {
		if(fsu.fileNeedsUpdating(file)) updateFile(fsu.getInetPath(file), file);
	}
	
	
}
