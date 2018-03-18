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
    private Viewport areaViewport;
    private MainMenuView menuViewPort;
    private StatusViewPort statusViewPort;
    private InventoryView inventoryView;
    private SkillsView skillsView;
    private EquipmentView equipmentView;


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

    public void setInventoryView(InventoryView inventoryView){
        this.inventoryView = inventoryView;
    }

    public void setSkillsView(SkillsView skillsView){
        this.skillsView = skillsView;
    }

    public void setEquipmentView(EquipmentView equipmentView){
        this.equipmentView = equipmentView;
    }

    public void setStatusViewPort(StatusViewPort statusViewPort){
        this.statusViewPort = statusViewPort;
    }

    public MemorySlots getMemorySlots(){return memorySlots;}

    public OOPenguinGameFrame getGameFrame() { return gameFrame; }

    public AreaViewPort getAreaViewport(){
        return null;
    }

    public EquipmentView getEquipmentView() { return null; }

    public InventoryView getInventoryView() { return null; }

    public MainMenuView getMainMenuViewport() { return menuViewPort; }

    public SkillsView getSkillsView() { return null; }

    public Player getPlayer(){
        return null;
    }

    public World getWorld() {
        return World.getWorld();
    }

    public Inventory getInventory(){
        return null;
    }

    public Equipment getEquipment(){
        return null;
    }

    public Role getSkills() { return null; }

    public KeyBindings getKeyBindings() {
        return keyBindings;
    }

    public List<Updateable> getUpdateables() {
        return updateables;
    }
}
