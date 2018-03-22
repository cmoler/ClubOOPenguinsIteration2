package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Map.AreaEffect.AreaEffectType;
import Model.Map.AreaEffect.TeleportAreaEffect;
import Model.Map.Direction;
import Model.Map.Location;

import java.nio.file.Path;
import java.util.*;

public class AggroState implements NPCState {

    @Override
    public void move(NPC npc, Player player) {

        ArrayList<Direction> path = bfs(npc, player);

        System.out.println("PATH SIZE IN MOVE AGGROSTATE" + path.size());

        if (playerInRange(npc, player)){
            if (!path.isEmpty()) {
                npc.move(path.get(0));
                System.out.println(path.get(0));
            }
        }
        else {
            npc.setNpcState(new EnemyState());
        }



    }

    private boolean playerInRange(NPC npc, Player player) {

        ArrayList<Direction> path = bfs(npc, player);
        return path.size() <= npc.getVisibleRange();
    }

    @Override
    public String getType() {
        return "aggro";
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

    private ArrayList<Direction> uniformCostSearch(NPC npc, Player player) {
        Location start = npc.getLocation();
        Location goal = player.getLocation();
        ArrayList<Direction> solution = new ArrayList<>();
        PriorityQueue<PathCostNode> queue = new PriorityQueue<PathCostNode>(10, new Comparator<PathCostNode>() {
            @Override
            public int compare(PathCostNode node1, PathCostNode node2) {
                if(node1.cost < node2.cost){
                    return 1;
                }

                else if (node1.cost > node2.cost){
                    return -1;
                }
                return 0;
            }
        });

        PathCostNode initialNode = new PathCostNode(start, new ArrayList<>(), 0);
        queue.add(initialNode);
        Set<PathCostNode> visited = new HashSet<PathCostNode>();

        while (!queue.isEmpty()) {
            PathCostNode currentNode = queue.poll();
            visited.add(currentNode);

            //if the player has been found in the visible range, return the optimal path
            if (currentNode.location.getxCoordinate() == goal.getxCoordinate() && currentNode.location.getyCoordinate() == goal.getyCoordinate()) {
                solution = currentNode.path;
                return solution;
            }

            for (Direction direction : Direction.values()) {
                Location nextLocation = currentNode.location.getAdjacentAt(direction);
                if (nextLocation != null && nextLocation.moveAllowed(npc)) {

                    //increment path and cost for new node
                    PathCostNode toVisit = new PathCostNode(nextLocation, currentNode.path, currentNode.cost + 1);
                    toVisit.path.add(direction);

                    //if unvisited and within range, add to queue
                    if (!queue.contains(toVisit) && !visited.contains(toVisit) && toVisit.cost <= npc.getVisibleRange()) {
                        if( toVisit.location.getAreaEffect() != null && toVisit.location.getAreaEffect().getAreaEffectType() != AreaEffectType.TELEPORT) {
                            queue.add(toVisit);
                        }
                    }
                    // if in queue and current path cost is cheaper, update path and cost in existing node
                    else if (queue.contains(toVisit)) {
                        Iterator<PathCostNode> iter = queue.iterator();
                        PathCostNode existing;
                        while (iter.hasNext()) {
                            existing = iter.next();
                            if (existing.equals(toVisit) && existing.cost > currentNode.cost) {
                                queue.remove(existing);
                                toVisit.cost = currentNode.cost + existing.cost;
                                queue.add(toVisit);
                                break;
                            }
                            iter.remove();
                        }

                    }
                }
            }

        }

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

    private class PathCostNode {
        public Location location;
        public ArrayList<Direction> path;
        public int cost;

        public PathCostNode(Location location, ArrayList<Direction> path, int cost) {
            this.location = location;
            this.path = path;
            this.cost = cost;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof PathCostNode) {
                PathCostNode other = (PathCostNode) o;
                return other.location == this.location;
            }
            return false;
        }

    }

}


