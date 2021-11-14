package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Bezeroa;

public class ApustuakIkusiGUI extends JFrame {

	private JPanel contentPane;

	private Bezeroa bezeroa;
	BezeroaGUI aurrekoa;

	public ApustuakIkusiGUI(BezeroaGUI aurrekoa, Bezeroa bezeroa) {
		try {
			this.bezeroa = bezeroa;
			this.aurrekoa = aurrekoa;
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jbInit() {
		BLFacade facade = MainGUI.getBusinessLogic();
		BezeroaAdapter model = new BezeroaAdapter(facade.getBezeroa(bezeroa.getErabiltzaileIzena()));

		JFrame j = new JFrame();
		JTable table = new JTable(model);
		j.getContentPane().add(new JScrollPane(table));

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.pack();
		j.setVisible(true);
	}

}
