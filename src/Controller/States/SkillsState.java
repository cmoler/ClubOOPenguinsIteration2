package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.SkillsController;
import Controller.SavingLoading.GameLoader;

import java.awt.event.KeyEvent;

public class SkillsState implements ControllerState {

    ControllerMediator controllerMediator;
    SkillsController skillsController;

    public SkillsState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.skillsController = new SkillsController(gameLoader);
    }

    @Override
    public void process(KeyEvent keyEvent) {

    }

    @Override
    public void loadKeyBindings() {

    }

}
