package hotel.management.system.dao;

import java.util.*;

/**
 * 数据集封装
 * @author leozhi
 */
public class DataTable {
    /**
     * 列字段、行值、行数、列数
     */
    public String[] column;
    public String[][] row;
    public int rowCount = 0;
    public int columnCount = 0;

    public DataTable() {
        super();
    }

    public DataTable(String[] column, String[][] row, int rowCount, int columnCount) {
        super();
        this.column = column;
        this.row = row;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
    }

    public void setDataTable(ArrayList<LinkedHashMap<String, String>> list) {
        if (list.size() == 0) {
            return;
        }
        rowCount = list.size();
        columnCount = list.get(0).size();
        column = new String[columnCount];
        row = new String[rowCount][columnCount];
        for (int i = 0; i < rowCount; i++) {
            Set<Map.Entry<String, String>> set = list.get(i).entrySet();
            int j = 0;
            for (Map.Entry<String, String> entry : set) {
                row[i][j] = entry.getValue();
                if (i == rowCount - 1) {
                    column[j] = entry.getKey();
                }
                j++;
            }
        }
    }

    public String[] getColumn() {
        return column;
    }

    public void setColumn(String[] column) {
        this.column = column;
    }

    public String[][] getRow() {
        return row;
    }

    public void setRow(String[][] row) {
        this.row = row;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }
}
