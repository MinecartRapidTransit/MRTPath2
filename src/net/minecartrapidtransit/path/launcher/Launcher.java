package net.minecartrapidtransit.path.launcher;

import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

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
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		
		label = new JLabel("Working out whether update is needed");
		add(label);
		
		pb =  new JProgressBar();
		pb.setValue(0);
		pb.setStringPainted(true);
		pb.setVisible(true);
		add(pb);
		setVisible(true);

		updateFiles();
	}
	
	private void updateFiles(){
		try{
			// For test
			Downloader.guiDownload("http://wordpress.org/latest.zip", System.getProperty("user.home") + "/Downloads/wp.zip", pb);
		}catch(IOException e){
			JOptionPane.showConfirmDialog(null,
					"There was a problem downloading the latest release. Please check your internet connection.",
					"Error",
					JOptionPane.DEFAULT_OPTION);
			System.exit(1);
		}
	}
	
	private boolean filesUpToDate(){
		
		return false;
	}
}
