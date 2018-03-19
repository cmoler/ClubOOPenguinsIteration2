package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Map.AreaEffect.AreaEffectType;
import Model.Map.Direction;
import Model.Map.Location;

import java.util.*;

public class EnemyState implements NPCState {

    @Override
    public void move(NPC npc, Player player) {


        if (playerInRange(npc, player)) {
            npc.setNpcState(new AggroState());
            return;
        }

    }

    @Override
    public String getType() {
        return "enemy";
    }

    private boolean playerInRange(NPC npc, Player player) {

        ArrayList<Direction> path = bfs(npc, player);
        return path.size() <= npc.getVisibleRange();
    }

    private ArrayList<Direction> bfs(NPC npc, Player player){

        System.out.println("\n\n\n");

        System.out.println("DOING BFS");
        System.out.println("LOCATION OF NPC:");
        System.out.println("COLUMN: " + npc.getLocation().getxCoordinate());
        System.out.println("ROW: " + npc.getLocation().getyCoordinate());

        System.out.println("\n\n\n");

        System.out.println("DOING BFS");
        System.out.println("LOCATION OF player:");
        System.out.println("COLUMN: " + player.getLocation().getxCoordinate());
        System.out.println("ROW: " + player.getLocation().getyCoordinate());

        System.out.println("\n\n\n");


        Location start = npc.getLocation();
        Location goal = player.getLocation();

        ArrayList<Direction> solution = new ArrayList<>();
        PathNode startNode = new PathNode(start, null, null);
        PathNode endNode = new PathNode(start, null, null);

        Set<PathNode> visited = new HashSet<>();
        Queue<PathNode> queue = new LinkedList<>();
        queue.add(startNode);

        boolean pathFound = false;
        while(!queue.isEmpty()){

            PathNode currPathNode = queue.poll();
            visited.add(currPathNode);

            for(Direction currDirection : Direction.values()){
                Location adjLocation = currPathNode.location.getAdjacentAt(currDirection);
                PathNode adjPathNode = new PathNode(adjLocation, currPathNode, currDirection);
                if(adjLocation != null && adjLocation.equals(goal)){

                    pathFound = true;
                    endNode = adjPathNode;
                    break;
                }

                if (adjLocation != null && !visited.contains(adjPathNode) && adjLocation.moveAllowed(npc) ){
                    if(adjLocation.getAreaEffect() != null && adjLocation.getAreaEffect().getAreaEffectType() != AreaEffectType.TELEPORT){
                        queue.add(adjPathNode);
                    }
                    else if(adjLocation.getAreaEffect() == null){
                        queue.add(adjPathNode);
                    }
                }
            }
            if(pathFound){
                break;
            }
        }

        if(pathFound){
            System.out.println("FOUND PATH BRO");
            PathNode current = endNode;
            while(current.parent != null){
                solution.add( 0, current.parentToThis);
                current = current.parent;
            }
        }

        System.out.println("PATH LENGTH: " + solution.size());

        return solution;
    }

    private class PathNode{
        public Location location;
        public PathNode parent;
        public Direction parentToThis;

        public PathNode(Location location, PathNode parent, Direction parentToThis){
            this.location = location;
            this.parent = parent;
            this.parentToThis = parentToThis;
        }

        @Override
        public int hashCode() {

            int hashCode = new Integer(location.getyCoordinate()).hashCode() + new Integer(location.getyCoordinate()).hashCode();
            return hashCode ;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof PathNode)) return false;
            PathNode pathNodeOther = (PathNode) obj;
            return (pathNodeOther.location.getxCoordinate() == this.location.getxCoordinate() && pathNodeOther.location.getyCoordinate() == this.location.getyCoordinate());
        }
    }

    private class DistanceNode {
        public Location location;
        public int distance;

        public DistanceNode(Location location, int distance) {
            this.location = location;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof DistanceNode) {
                DistanceNode other = (DistanceNode) o;
                return other.location == this.location;
            }
            return false;
        }

    }
}
