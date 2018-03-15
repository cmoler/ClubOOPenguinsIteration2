package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UpdateList {

    private static UpdateList instance = null;

    private UpdateList(){
        updateables = new ArrayList<Updateable>();
    }

    private List<Updateable> updateables;

    public static UpdateList getInstance(){
        if (instance == null)
        {
            instance = new UpdateList();
        }
        return instance;
    }

    public void add(Updateable updateable){
        updateables.add(updateable);
    }

    public void remove(Updateable updateable){
        updateables.remove(updateable);
    }

    public void update(){
        for(Iterator<Updateable> iterator = updateables.iterator(); iterator.hasNext();){
            Updateable current = iterator.next();
            if(!current.isDone()){
                current.update();
            }
            else
                iterator.remove();
        }
    }
}
