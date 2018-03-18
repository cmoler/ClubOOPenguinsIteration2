package Controller.Controllers;

import Controller.SavingLoading.GameBuilder;
import Model.Entity.Role.Role;

public class SkillsController implements Controller{

    private Role role;

    public SkillsController(GameBuilder gameBuilder){
        role = gameBuilder.getPlayerRole();
    }

    @Override
    public void setActive() {

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
