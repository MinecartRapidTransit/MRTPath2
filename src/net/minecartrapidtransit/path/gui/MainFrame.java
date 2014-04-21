package net.minecartrapidtransit.path.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import net.minecartrapidtransit.path.gui.networkEditor.NetworkEditor;

public class MainFrame extends JFrame implements ActionListener {
	public MainFrame(){
		super(String.format("%s %s", S.name, S.version));
		initUi();
	}
	
	private void initUi(){
		setSize(400, 600);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		setVisible(true);
	}
	
	private void initMenu(){
		JMenuBar menubar = new JMenuBar();
		JMenu tools = new JMenu(S.mnuTools);
		tools.setMnemonic(S.mnuToolsShortcut);
		menubar.add(tools);
		
		JMenuItem networkEditor = new JMenuItem(S.networkEditor, S.mniNetworkEditorShourtcut);
		networkEditor.addActionListener(this);
		tools.add(networkEditor);
		
		setJMenuBar(menubar);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() instanceof JMenuItem){
			JMenuItem source = (JMenuItem) e.getSource();
			if(source.getText().equals(S.networkEditor)){
		        new NetworkEditor();
			}
		}
		
	}
}
