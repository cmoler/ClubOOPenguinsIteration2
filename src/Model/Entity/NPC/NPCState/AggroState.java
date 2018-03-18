package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;
import Model.Map.AreaEffect.AreaEffectType;
import Model.Map.AreaEffect.TeleportAreaEffect;
import Model.Map.Direction;
import Model.Map.Location;

import java.util.*;

public class AggroState implements NPCState {

    @Override
    public void move(NPC npc, Player player) {

        ArrayList<Direction> path = uniformCostSearch(npc, player);
        if (!path.isEmpty()) {
            npc.move(path.get(0));
        }
        else {
            npc.setNpcState(new EnemyState());
        }

    }

    private ArrayList<Direction> uniformCostSearch(NPC npc, Player player) {
        Location start = npc.getLocation();
        Location goal = player.getLocation();
        ArrayList<Direction> solution = new ArrayList<>();
        PriorityQueue<PathCostNode> queue = new PriorityQueue<PathCostNode>(10, new Comparator<PathCostNode>() {
            @Override
            public int compare(PathCostNode node1, PathCostNode node2) {
                if(node1.cost > node2.cost){
                    return 1;
                }

                else if (node1.cost < node2.cost){
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
            if (currentNode.location == goal) {
                solution = queue.peek().path;
                return solution;

            }

            for (Direction direction : Direction.values()) {
                Location nextLocation = currentNode.location.getAdjacentAt(direction);
                if (nextLocation != null && nextLocation.moveAllowed(npc)) {
                    //increment path and cost for new node
                    PathCostNode toVisit = new PathCostNode(nextLocation, currentNode.path, currentNode.cost + 1);
                    toVisit.path.add(direction);

                    //if unvisited and within range, add to queue
                    if (!queue.contains(toVisit) && !visited.contains(toVisit) && toVisit.cost <= npc.getVisibleRange()
                            && toVisit.location.getAreaEffect().getAreaEffectType() != AreaEffectType.TELEPORT) {
                        queue.add(toVisit);
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


