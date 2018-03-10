package Model.Visitor;

import Model.Entity.Entity;
import Model.Map.Map;

public interface Visitor {

    void visitEntity(Entity entity);

    void visitMap(Map map);
}
