package View.StatusView;

import Configs.Commons;
import Configs.SkillsSizes;
import Configs.ImagesInfo;
import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Role.Role;
import Model.Entity.Role.RoleType;
import View.Viewport;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SkillsView extends Viewport {

    private Player player;

    public SkillsView(Role role){
        this.player = player;
        player.attach(this);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Role role = player.getRole();

        ImageIcon imageIcon = new ImageIcon(ImagesInfo.INCREASE_SKILL_ICON);
        Image increaseSkillImage = imageIcon.getImage();

        graphics2D.drawString("Bind Wounds", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
        graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
        graphics2D.drawString("Bargain", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
        graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
        graphics2D.drawString("Observation", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
        graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);

        switch (role.getRoleType()) {
            case Smasher:
                graphics2D.drawString("One Handed Weapon", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Two Handed Weapon", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Brawl", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                break;
            case Sneak:
                graphics2D.drawString("Pick Pocket", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Detect Traps", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Creep", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Ranged Weapon", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                break;
            case Summoner:
                graphics2D.drawString("Enchantment", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Boon", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Bane", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                graphics2D.drawString("Staff", ((int) (Commons.SCREEN_WIDTH  * 564.0/765.0)), 0);
                graphics2D.drawImage(increaseSkillImage, 0, 0, SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                break;
            default:
                break;
        }

    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    @Override
    public void update(){
        repaint();
    }
}
