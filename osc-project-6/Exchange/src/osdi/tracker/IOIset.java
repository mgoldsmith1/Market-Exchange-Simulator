/*
 * File     : IOIset.java
 *
 * Author   : Zoltan Feledy
 * 
 * Contents : This class is a Set of IOI objects with a utility
 *            methods to access the individual iois.
 * 
 */

package osdi.tracker;

import java.util.ArrayList;
import java.util.Iterator;
import osdi.trackerui.OrderBookTableModel;

public class IOIset {
    private ArrayList<IOI> iois = new ArrayList<IOI>();
    private OrderBookTableModel ioiTableModel = null;

    public IOIset() {}
    
    public void add ( IOI ioi ) {
        iois.add( ioi );
        int limit = 50;
        try {
            limit = (int)FIXTracker.getApplication().getSettings()
                    .getLong("FIXimulatorCachedObjects");
        } catch ( Exception e ) {}
        while ( iois.size() > limit ) {
            iois.remove(0);
        }
        ioiTableModel.update();
    }
    
    public void addCallback(OrderBookTableModel ioiTableModel){
        this.ioiTableModel = ioiTableModel;
    }
        
    public int getCount() {
        return iois.size();
    }

    public IOI getIOI( int i ) {
        return iois.get( i );
    }

    public IOI getIOI( String id ) {
        Iterator<IOI> iterator = iois.iterator();
        while ( iterator.hasNext() ){
            IOI ioi = iterator.next();
            if ( ioi.getID().equals(id) )
                return ioi;
        }
        return null;
    }
}
