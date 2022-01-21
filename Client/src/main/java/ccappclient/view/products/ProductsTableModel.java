package ccappclient.view.products;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ProductsTableModel extends AbstractTableModel {

    private int column = 2;
    private int rownum;

    private String[] colNames = {"ID", "Название"};

    private ArrayList<String[]> resultSets;

    public ProductsTableModel(ArrayList<String[]> resultSets) {
        this.resultSets = new ArrayList<>(resultSets);
    }

    @Override
    public int getRowCount() {
        return resultSets.size();
    }

    @Override
    public int getColumnCount() {
        return column;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = resultSets.get(rowIndex);
        return row[columnIndex];
    }

    @Override
    public String getColumnName(int param) {
        return colNames[param];
    }

}
