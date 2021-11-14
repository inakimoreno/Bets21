package gui;

import domain.Bezeroa;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Apustua;

public class BezeroaAdapter extends AbstractTableModel {
	
	private List<Apustua> apustuak;
	private Bezeroa bezeroa;
	private String[] colNames = new String[] {"Event", "Question", "Event Date", "Bet(€)"};
	
	public BezeroaAdapter(Bezeroa b) {
		this.bezeroa = b;
		this.apustuak = new ArrayList<>(b.getApustuak());
	}
	
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		switch(colIndex) {
		case 0:
			return (Object) apustuak.get(rowIndex).getPronostikoak().get(0).getQuestion().getEvent().getDescription();
		case 1:
			return (Object) apustuak.get(rowIndex).getPronostikoak().get(0).getQuestion().getQuestion();
		case 2:
			return (Object) apustuak.get(rowIndex).getPronostikoak().get(0).getQuestion().getEvent().getEventDate();
		case 3:
			return (Object) apustuak.get(rowIndex).getKopurua();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int col) {
		return colNames[col];
	}
	
	@Override
	public int getColumnCount() {
		return 4;
	}
	
	@Override
	public int getRowCount() {
		return apustuak.size();
	}

}