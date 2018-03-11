package Controller.Controllers.MenuController;

import Controller.SavingLoading.GameLoader;
import View.MenuView.OptionsView;

public class OptionsController extends MenuController {

    private int currentlySelectedBinding = 0;
    private int currentlySelectedKey = 0;

    private OptionsView optionsView;

    public OptionsController(GameLoader gameLoader, MenuController parent) {
        setParent(parent);
        optionsView = gameLoader.getMenuViewport().getOptionsView();
    }

    public void scrollRight(){
        if(currentlySelectedBinding < 4) currentlySelectedBinding++;
        correctKeySelectionParameters();
        optionsView.setSelectedBindingView(1);
    }

    public void scrollLeft(){
        if(currentlySelectedBinding > 0) currentlySelectedBinding--;
        correctKeySelectionParameters();
        optionsView.setSelectedBindingView(-1);
    }

    public void scrollUp(){
        currentlySelectedKey -= 1;
        correctKeySelectionParameters();
        optionsView.setSelectedKeyView(1);
    }

    public void scrollDown(){
        currentlySelectedKey += 1;
        correctKeySelectionParameters();
        optionsView.setSelectedKeyView(-1);
    }

    private void correctKeySelectionParameters(){
        switch (currentlySelectedBinding){
            case 0:
                if(currentlySelectedKey > 16) currentlySelectedKey = 16;
                if(currentlySelectedKey < 0) currentlySelectedKey = 0;
                break;
            case 1:
                if(currentlySelectedKey > 6) currentlySelectedKey = 6;
                if(currentlySelectedKey < 0) currentlySelectedKey = 0;
                break;
            case 2:
                if(currentlySelectedKey > 8) currentlySelectedKey = 8;
                if(currentlySelectedKey < 0) currentlySelectedKey = 0;
                break;
            case 3:
                if(currentlySelectedKey > 5) currentlySelectedKey = 5;
                if(currentlySelectedKey < 0) currentlySelectedKey = 0;
                break;
            case 4:
                if(currentlySelectedKey > 8) currentlySelectedKey = 8;
                if(currentlySelectedKey < 0) currentlySelectedKey = 0;
                break;
        }
    }

    public void select(){
        switch (currentlySelectedBinding){
            case 0:
                changeEntityBindings();
                break;
            case 1:
                changeEquipmentBindings();
                break;
            case 2:
                changeInventoryBindings();
                break;
            case 3:
                changeMenuBindings();
                break;
            case 4:
                changeSkillsBindings();
                break;
        }
    }

    private void changeEntityBindings(){
        switch (currentlySelectedKey){
            case 0:
                changeKeyBindings("entity", "moveN");
                break;
            case 1:
                changeKeyBindings("entity", "moveNE");
                break;
            case 2:
                changeKeyBindings("entity", "moveE");
                break;
            case 3:
                changeKeyBindings("entity", "moveSE");
                break;
            case 4:
                changeKeyBindings("entity", "moveS");
                break;
            case 5:
                changeKeyBindings("entity", "moveSW");
                break;
            case 6:
                changeKeyBindings("entity", "moveW");
                break;
            case 7:
                changeKeyBindings("entity", "moveNW");
                break;
            case 8:
                changeKeyBindings("entity", "openMenu");
                break;
            case 9:
                changeKeyBindings("entity", "openInventory");
                break;
            case 10:
                changeKeyBindings("entity", "openEquipment");
                break;
            case 11:
                changeKeyBindings("entity", "openSkills");
                break;
            case 12:
                changeKeyBindings("entity", "slot1");
                break;
            case 13:
                changeKeyBindings("entity", "slot2");
                break;
            case 14:
                changeKeyBindings("entity", "slot3");
                break;
            case 15:
                changeKeyBindings("entity", "slot4");
                break;
            case 16:
                changeKeyBindings("entity", "slot5");
                break;
        }
    }

    private void changeEquipmentBindings(){
        switch (currentlySelectedKey){
            case 0:
                changeKeyBindings("equipment", "openMenu");
                break;
            case 1:
                changeKeyBindings("equipment", "openInventory");
                break;
            case 2:
                changeKeyBindings("equipment", "exitEquipment");
                break;
            case 3:
                changeKeyBindings("equipment", "openSkills");
                break;
            case 4:
                changeKeyBindings("equipment", "unEquipItem");
                break;
            case 5:
                changeKeyBindings("equipment", "scrollLeft");
                break;
            case 6:
                changeKeyBindings("equipment", "scrollRight");
                break;
        }
    }

    private void changeInventoryBindings(){
        switch (currentlySelectedKey){
            case 0:
                changeKeyBindings("inventory", "openMenu");
                break;
            case 1:
                changeKeyBindings("inventory", "openEquipment");
                break;
            case 2:
                changeKeyBindings("inventory", "exitInventory");
                break;
            case 3:
                changeKeyBindings("inventory", "openSkills");
                break;
            case 4:
                changeKeyBindings("inventory", "equipItem");
                break;
            case 5:
                changeKeyBindings("inventory", "scrollLeft");
                break;
            case 6:
                changeKeyBindings("inventory", "scrollRight");
                break;
            case 7:
                changeKeyBindings("inventory", "scrollUp");
                break;
            case 8:
                changeKeyBindings("inventory", "scrollDown");
                break;
        }
    }

    private void changeMenuBindings(){
        switch (currentlySelectedKey){
            case 0:
                changeKeyBindings("menu", "exitMenu");
                break;
            case 1:
                changeKeyBindings("menu", "scrollLeft");
                break;
            case 2:
                changeKeyBindings("menu", "scrollRight");
                break;
            case 3:
                changeKeyBindings("menu", "scrollUp");
                break;
            case 4:
                changeKeyBindings("menu", "scrollDown");
                break;
            case 5:
                changeKeyBindings("menu", "select");
                break;
        }
    }

    private void changeSkillsBindings(){
        switch (currentlySelectedKey){
            case 0:
                changeKeyBindings("skills", "openMenu");
                break;
            case 1:
                changeKeyBindings("skills", "openEquipment");
                break;
            case 2:
                changeKeyBindings("skills", "openInventory");
                break;
            case 3:
                changeKeyBindings("skills", "exitSkills");
                break;
            case 4:
                changeKeyBindings("skills", "increaseCurrent");
                break;
            case 5:
                changeKeyBindings("skills", "scrollLeft");
                break;
            case 6:
                changeKeyBindings("skills", "scrollRight");
                break;
            case 7:
                changeKeyBindings("skills", "scrollUp");
                break;
            case 8:
                changeKeyBindings("skills", "scrollDown");
                break;
        }
    }
}
