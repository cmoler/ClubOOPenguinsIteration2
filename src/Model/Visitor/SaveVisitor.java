package Model.Visitor;

import Model.Entity.Entity;
import Model.Map.Map;

public class SaveVisitor implements Visitor{

    private String filename;

    public SaveVisitor(String filename){

    }

    public void visitEntity(Entity entity) {
        // TODO: save entity
    }

    public void visitMap(Map map) {
        // TODO: save map
    }
}
