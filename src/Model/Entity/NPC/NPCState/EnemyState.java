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

    private boolean playerInRange(NPC npc, Player player) {
        Location start = npc.getLocation();
        Location goal = player.getLocation();
        LinkedList<LocationDistanceNode> queue = new LinkedList<>();

        LocationDistanceNode initialNode = new LocationDistanceNode(start,  0);
        queue.add(initialNode);
        Set<LocationDistanceNode> visited = new HashSet<LocationDistanceNode>();

        while (!queue.isEmpty()) {
            LocationDistanceNode currentNode = queue.poll();
            visited.add(currentNode);

            //if the player has been found in the visible range
            if (currentNode.location == goal) {
                return true;

            }

            for (Direction direction : Direction.values()) {
                Location nextLocation = currentNode.location.getAdjacentAt(direction);
                if (nextLocation != null && nextLocation.moveAllowed(npc)) {
                    //increment distance for new node
                    LocationDistanceNode toVisit = new LocationDistanceNode(nextLocation,currentNode.distance + 1);

                    //if unvisited and within range, add to queue
                    if (!queue.contains(toVisit) && !visited.contains(toVisit) && toVisit.distance <= npc.getVisibleRange()) {
                        queue.add(toVisit);
                    }

                }
            }

        }

        return false;
    }

    private class LocationDistanceNode {
        public Location location;
        public int distance;

        public LocationDistanceNode(Location location, int distance) {
            this.location = location;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof LocationDistanceNode) {
                LocationDistanceNode other = (LocationDistanceNode) o;
                return other.location == this.location;
            }
            return false;
        }

    }
}
