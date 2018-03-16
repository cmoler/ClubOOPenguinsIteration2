package Model.Entity.NPC.NPCState;

import Model.Entity.NPC.NPC;
import Model.Entity.Player;

public interface NPCState {

    public void move(NPC npc, Player player);

}
