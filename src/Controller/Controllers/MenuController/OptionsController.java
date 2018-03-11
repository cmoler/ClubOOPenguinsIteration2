package Controller.Controllers.MenuController;

import Controller.ControllerMediator;
import Controller.SavingLoading.GameLoader;
import View.MenuView.OptionsView;

public class OptionsController extends MenuController {

    private OptionsView optionsView;

    private ControllerMediator controllerMediator;

    public OptionsController(GameLoader gameLoader, ControllerMediator controllerMediator) {
        optionsView = gameLoader.getMainMenuViewport().getOptionsView();
        setMenuViewPort(optionsView);
        this.controllerMediator = controllerMediator;
    }

    public void select(){
        switch (selectedRightLeft){
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

    @Override
    protected void correctUpDownParameters() {
        switch (selectedRightLeft){
            case 0:
                if(selectedUpDown > 16) selectedUpDown = 16;
                if(selectedUpDown < 0) selectedUpDown = 0;
                break;
            case 1:
                if(selectedUpDown > 6) selectedUpDown = 6;
                if(selectedUpDown < 0) selectedUpDown = 0;
                break;
            case 2:
                if(selectedUpDown > 8) selectedUpDown = 8;
                if(selectedUpDown < 0) selectedUpDown = 0;
                break;
            case 3:
                if(selectedUpDown > 5) selectedUpDown = 5;
                if(selectedUpDown < 0) selectedUpDown = 0;
                break;
            case 4:
                if(selectedUpDown > 8) selectedUpDown = 8;
                if(selectedUpDown < 0) selectedUpDown = 0;
                break;
        }
    }

    @Override
    protected void correctLeftRightParameters() {
        if(selectedRightLeft > 4) selectedRightLeft = 4;
        if(selectedRightLeft < 0) selectedRightLeft = 0;

    }

    private void changeEntityBindings(){
        switch (selectedUpDown){
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
        switch (selectedUpDown){
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
        switch (selectedUpDown){
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
        switch (selectedUpDown){
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
        switch (selectedUpDown){
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

    private void changeKeyBindings(String bindingToChange, String keyToChange){
        controllerMediator.primeKeyBindingState(bindingToChange, keyToChange);
        controllerMediator.changeToKeyBindingState();
    }
}
