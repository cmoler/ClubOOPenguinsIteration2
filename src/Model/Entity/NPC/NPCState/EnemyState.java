package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;
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
        Location start = npc.getLocation();
        Location goal = player.getLocation();
        LinkedList<DistanceNode> queue = new LinkedList<>();

        DistanceNode initialNode = new DistanceNode(start,  0);
        queue.add(initialNode);
        Set<DistanceNode> visited = new HashSet<DistanceNode>();

        while (!queue.isEmpty()) {
            DistanceNode currentNode = queue.poll();
            visited.add(currentNode);

            //if the player has been found in the visible range
            if (currentNode.location == goal) {
                return true;

            }

            for (Direction direction : Direction.values()) {
                Location nextLocation = currentNode.location.getAdjacentAt(direction);
                if (nextLocation != null && nextLocation.moveAllowed(npc)) {
                    //increment distance for new node
                    DistanceNode toVisit = new DistanceNode(nextLocation,currentNode.distance + 1);

                    //if unvisited and within range, add to queue
                    if (!queue.contains(toVisit) && !visited.contains(toVisit) && toVisit.distance <= npc.getVisibleRange()) {
                        queue.add(toVisit);
                    }

                }
            }

        }

        return false;
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
