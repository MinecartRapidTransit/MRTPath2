package net.minecartrapidtransit.path.gui.networkEditor;

import javax.swing.JFrame;

import net.minecartrapidtransit.path.gui.S;

public class NetworkEditor extends JFrame {
	public NetworkEditor(){
		super(S.networkEditor);
		initUi();
	}
	
	private void initUi(){
		setSize(400, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
