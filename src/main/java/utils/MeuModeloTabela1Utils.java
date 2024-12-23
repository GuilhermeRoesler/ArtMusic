package utils;

import javax.swing.table.AbstractTableModel;

public class MeuModeloTabela1Utils extends AbstractTableModel {
	private static final long serialVersionUID = 1L;

	private String[] colunas = { "Nome", "Data", "Local" };
	private Object[][] dados;
//	private Object[][] dados = { { "Alice", 30, "São Paulo" }, { "Bob", 25, "Rio de Janeiro" },
//			{ "Carlos", 35, "Belo Horizonte" } };
	
	public MeuModeloTabela1Utils(Object[][] dados) {
		this.dados = dados;
	}

	@Override
	public int getRowCount() {
		return dados.length;
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return dados[rowIndex][columnIndex];
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}
}
