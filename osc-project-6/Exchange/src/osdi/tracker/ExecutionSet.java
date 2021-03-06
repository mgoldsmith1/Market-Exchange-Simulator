/*
 * File     : ExecutionSet.java
 *
 * Author   : Zoltan Feledy
 * 
 * Contents : This class is a Set of Execution objects with a utility 
 *            methods toto access the individual executions.
 *         
 */

package osdi.tracker;


import java.util.ArrayList;
import java.util.Iterator;
import osdi.trackerui.PositionTableModel;


public class ExecutionSet {
    private ArrayList<Execution> executions = new ArrayList<Execution>();
    private PositionTableModel executionTableModel = null;

    public ExecutionSet() {}
    
    public void add ( Execution execution ) {
        executions.add( execution );
        int limit = 50;
        try {
            limit = (int)FIXTracker.getApplication().getSettings()
                    .getLong("FIXimulatorCachedObjects");
        } catch ( Exception e ) {}
        while ( executions.size() > limit ) {
            executions.remove(0);
        }
        executionTableModel.update();
    }

    public void update () {
        executionTableModel.update();
    }
    
    public void addCallback(PositionTableModel executionTableModel){
        this.executionTableModel = executionTableModel;
    }
        
    public int getCount() {
        return executions.size();
    }

    public Execution getExecution( int i ) {
        return executions.get( i );
    }

    public Execution getExecution( String id ) {
        Iterator<Execution> iterator = executions.iterator();
        while ( iterator.hasNext() ){
            Execution execution = iterator.next();
            if ( execution.getID().equals(id) )
                return execution;
        }
        return null;
    }
}
