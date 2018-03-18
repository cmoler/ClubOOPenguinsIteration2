package Controller.SavingLoading;

import Controller.OOPenguinGameFrame;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Skill.Skill;
import Model.Map.World;
import Model.Updateable;
import View.AreaView.AreaViewPort;
import View.MenuView.MainMenuView;
import View.StatusView.EquipmentView;
import View.StatusView.InventoryView;
import View.StatusView.SkillsView;
import View.StatusView.StatusViewPort;
import View.Viewport;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public class GameBuilder {

    //GAMEFRAME
    private OOPenguinGameFrame gameFrame;

    //KEYBINDINGS & SAVESLOTS
    private MemorySlots memorySlots;
    private KeyBindings keyBindings;

    //VIEWS
    private Viewport viewport;
    private AreaViewPort areaViewport;
    private MainMenuView menuViewPort;
    private StatusViewPort statusViewPort;

    private Player player;

    public GameBuilder(){
        menuViewPort = new MainMenuView();
        gameFrame = new OOPenguinGameFrame();
        gameFrame.add(menuViewPort);
        menuViewPort.setVisible(true);
        memorySlots = new MemorySlots(this);

        keyBindings = new KeyBindings();
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setStatusViewPort(StatusViewPort statusViewPort){
        this.statusViewPort = statusViewPort;
    }

    public MemorySlots getMemorySlots(){return memorySlots;}

    public OOPenguinGameFrame getGameFrame() { return gameFrame; }

    public AreaViewPort getAreaViewport(){
        return areaViewport;
    }

    public void setAreaViewport(AreaViewPort areaViewport){
        this.areaViewport = areaViewport;
    }

    public MainMenuView getMainMenuViewport() { return menuViewPort; }

    public Player getPlayer(){
        return player;
    }

    public Role getPlayerRole() {
        if(player != null) player.getRole();
        return null;
    }

    public World getWorld() {
        return World.getWorld();
    }

    public Inventory getInventory(){
        if(player != null) player.getInventory();
        return null;
    }

    public Equipment getEquipment(){
        if(player != null) player.getEquipment();
        return null;
    }

    public KeyBindings getKeyBindings() {
        return keyBindings;
    }

    public StatusViewPort getStatusViewPort() {
        return statusViewPort;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
    }
}
