package net.minecartrapidtransit.path.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.minecartrapidtransit.path.core.Network;
import net.minecartrapidtransit.path.core.Place;
import net.minecartrapidtransit.path.data.YamlDataStore;
import net.minecartrapidtransit.path.gui.networkEditor.NetworkEditor;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener {
	private JLabel lblNewLabel;
	private JTextField textField;
	private JList<String> list1;
	private JList<String> list2;
	private DefaultListModel<String> lm1;
	private DefaultListModel<String> lm2;
	private JTextField textField_1;
	private YamlDataStore yaml;
	private Network n;

	public MainFrame(){
		super(String.format("%s %s", S.name, S.version));
		initUi();
	}
	
	private void initUi(){
		setSize(500, 600);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		initMenu();
		setVisible(true);
		
		lm1 = new DefaultListModel<String>();
		lm2 = new DefaultListModel<String>();
		
		yaml = new YamlDataStore();
		
		try {
			n = yaml.decodeNetwork(YamlDataStore.readFile(S.networkPath, Charset.defaultCharset()));
		} catch (IOException e) {
			n = null;
			JOptionPane.showMessageDialog(getContentPane(), "No valid .yml found in '"+ S.networkPath + "'!", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(1);
		}
		
		lblNewLabel = new JLabel(S.lblStn1);
		lblNewLabel.setBounds(10, 11, 364, 14);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 352, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		list1 = new JList<String>(lm1);
		list1.setBounds(10, 67, 352, 84);
		getContentPane().add(list1);
		
		JScrollPane scrollPane = new JScrollPane(list1);
		scrollPane.setBounds(10, 67, 352, 84);
		getContentPane().add(scrollPane);
		
		JButton btnNewButton = new JButton(S.btnSearch);
		btnNewButton.setBounds(372, 35, 102, 23);
		getContentPane().add(btnNewButton);
		
		JLabel label = new JLabel(S.lblStn2);
		label.setBounds(10, 171, 364, 14);
		getContentPane().add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 196, 352, 20);
		getContentPane().add(textField_1);
		
		list2 = new JList<String>(lm2);
		list2.setBounds(10, 227, 352, 84);
		getContentPane().add(list2);
		
		JScrollPane scrollPane_1 = new JScrollPane(list2);
		scrollPane_1.setBounds(10, 227, 352, 84);
		getContentPane().add(scrollPane_1);
		
		JButton button = new JButton(S.btnSearch);
		button.setBounds(372, 195, 102, 23);
		getContentPane().add(button);
		
		addNetworkToLists();
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
		getContentPane().setLayout(null);
		
		
	}

	private void addNetworkToLists() {
		List<Place> places = n.getPlacesList();
		Collections.sort(places, new PlaceComparatorByName());
		for (Place p : places) {
			lm1.addElement(p.getName());
			lm2.addElement(p.getName());
		}
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
	
	private class PlaceComparatorByName implements Comparator<Place>{

		@Override
		public int compare(Place o1, Place o2) {
			return o1.getName().compareTo(o2.getName());
		}
		
	}
}
