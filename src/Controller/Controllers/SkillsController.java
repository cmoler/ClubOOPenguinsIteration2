package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Role.Role;
import View.AreaView.AreaViewPort;

public class SkillsController implements Controller{

    private Role role;
    private AreaViewPort areaViewPort;

    public SkillsController(GameBuilder gameBuilder){
        this.areaViewPort = gameBuilder.getAreaViewport();
        role = gameBuilder.getPlayerRole();
    }

    @Override
    public void setActive() {
        areaViewPort.setVisible(true);
        areaViewPort.requestFocus();
    }

    public void incrementSkill() {
        role.increaseSkill(1);
    }

    public void scrollDown() {
        role.scroll(1);
    }

    public void scrollUp() {
        role.scroll(-1);
    }
}
