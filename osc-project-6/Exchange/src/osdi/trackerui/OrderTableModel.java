package osdi.trackerui;

import javax.swing.table.AbstractTableModel;

import osdi.tracker.ApplicationImpl;
import osdi.tracker.MarketExchangeSim;
import osdi.tracker.Order;
import osdi.tracker.OrderSet;

public class OrderTableModel extends AbstractTableModel {
    private static OrderSet orders = MarketExchangeSim.getApplication().getOrders();
    private static String[] columns = 
        {"ID", "Status", "Side", "Quantity", "Symbol", "Type", "Limit", "TIF", 
         "Executed", "Open", "AvgPx", "ClOrdID", "OrigClOrdID"}; 
    

    public OrderTableModel(){
    	MarketExchangeSim.getApplication().getOrders().addCallback(this);
    }
    
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class getColumnClass(int column) {
        if (column == 3) return Double.class;
        if (column == 6) return Double.class;
        if (column == 8) return Double.class;
        if (column == 9) return Double.class;
        if (column == 10) return Double.class;
        return String.class;
    }
        
    public int getRowCount() {
        return orders.getCount();
    }

    public Object getValueAt(int row, int column) {
        Order order = orders.getOrder(row);
        if (column == 0) return order.getID();        
        if (column == 1) return order.getStatus();
        if (column == 2) return order.getSide();
        if (column == 3) return order.getQuantity();
        if (column == 4) return order.getSymbol();
        if (column == 5) return order.getType();
        if (column == 6) return order.getLimit();
        if (column == 7) return order.getTif();
        if (column == 8) return order.getExecuted();
        if (column == 9) return order.getOpen();
        if (column == 10) return order.getAvgPx();
        if (column == 11) return order.getClientID();
        if (column == 12) return order.getOrigClientID();
        return new Object();
    }
    
    public void update() {
        fireTableDataChanged();
    }
}
