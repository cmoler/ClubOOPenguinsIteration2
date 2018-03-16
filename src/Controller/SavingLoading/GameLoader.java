package Controller.SavingLoading;

import Controller.OOPenguinGameFrame;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import View.AreaView.AreaViewPort;
import View.MenuView.MainMenuView;
import View.StatusView.EquipmentView;
import View.StatusView.InventoryView;
import View.StatusView.SkillsView;
import View.Viewport;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class GameLoader {

    private String saveGameDirectory;

    private OOPenguinGameFrame gameFrame;
    private Viewport areaViewport;
    private MainMenuView menuViewPort;
    private MemorySlots memorySlots;

    private KeyBindings keyBindings;

    public GameLoader(){
        menuViewPort = new MainMenuView();
        gameFrame = new OOPenguinGameFrame();
        gameFrame.add(menuViewPort);
        menuViewPort.setVisible(true);
        memorySlots = new MemorySlots(this);

        keyBindings = new KeyBindings();
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
}
