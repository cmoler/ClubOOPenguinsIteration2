package Controller.SavingLoading;

import Controller.Input.Input;
import Controller.OOPenguinGameFrame;
import Model.Entity.Equipment;
import Model.Entity.Inventory;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import View.AreaView.AreaViewPort;
import View.MenuView.MenuViewPort;
import View.StatusView.EquipmentView;
import View.StatusView.InventoryView;
import View.StatusView.SkillsView;
import View.Viewport;

public class GameLoader {

    private String saveGameDirectory;

    private OOPenguinGameFrame gameFrame;
    private Viewport areaViewport;
    private MenuViewPort menuViewPort;

    public GameLoader(){
        menuViewPort = new MenuViewPort();

        gameFrame = new OOPenguinGameFrame();
        gameFrame.add(menuViewPort);
        menuViewPort.setVisible(true);

    }

    public void loadGame(String fileName){

    }

    public OOPenguinGameFrame getGameFrame() { return null; }

    public AreaViewPort getAreaViewport(){
        return null;
    }

    public EquipmentView getEquipmentView() { return null; }

    public InventoryView getInventoryView() { return null; }

    public MenuViewPort getMenuViewport() { return null; }

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
}
