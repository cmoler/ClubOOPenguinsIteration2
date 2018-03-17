package View.StatusView;

import Configs.Commons;
import Configs.SkillsSizes;
import Configs.ImagesInfo;
import Model.Entity.Entity;
import Model.Entity.Player;
import Model.Entity.Role.*;
import Model.Entity.Skill.Skill;
import View.Viewport;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SkillsView extends Viewport {

    //TODO - adjust POINTS_COLUMN_X and BUTTON_COLUMN_X

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
        int pointsAvailable = player.getSkillPointsAvailable();
        int SKILL_COLUMN_X = (int) ((Commons.SCREEN_WIDTH  * 564.0/765.0));
        int POINTS_COLUMN_X = (int) ((Commons.SCREEN_WIDTH  * 564.0/765.0));
        int BUTTON_COLUMN_X = (int) ((Commons.SCREEN_WIDTH  * 564.0/765.0));
        int SKILL_Y = ((int) (Commons.SCREEN_HEIGHT  * 221.0/765.0) + 170);
        int rowNumber = 0;

        graphics2D.drawString("Points:", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
        graphics2D.drawString("" + pointsAvailable, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
        ++rowNumber;

        graphics2D.drawString("Bind Wounds", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
        graphics2D.drawString("" + role.getBindWounds(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
        graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
        ++rowNumber;

        graphics2D.drawString("Bargain", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
        graphics2D.drawString("" + role.getBargain(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT) );
        graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
        ++rowNumber;

        graphics2D.drawString("Observation", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
        graphics2D.drawString("" + role.getObservation(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
        graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
        ++rowNumber;

        switch (role.getRoleType()) {
            case Smasher:
                graphics2D.drawString("One Handed Weapon", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Smasher) role).getOneHandedWeapon(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Two Handed Weapon", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Smasher) role).getTwoHandedWeapon(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Brawl", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Smasher) role).getBrawl(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;
                break;
            case Sneak:
                graphics2D.drawString("Pick Pocket", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Sneak) role).getPickPocket(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Detect Traps", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Sneak) role).getDetectAndRemoveTrap(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Creep", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Sneak) role).getCreep(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Ranged Weapon", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Sneak) role).getRangedWeapon(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;
                break;
            case Summoner:
                graphics2D.drawString("Enchantment", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Summoner) role).getEnchantment(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Boon", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Summoner) role).getBoon(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Bane", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Summoner) role).getBane(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;

                graphics2D.drawString("Staff", SKILL_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawString("" + ((Summoner) role).getStaff(), POINTS_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT));
                graphics2D.drawImage(increaseSkillImage, BUTTON_COLUMN_X, SKILL_Y + (rowNumber * SkillsSizes.SKILL_ROW_HEIGHT), SkillsSizes.SKILL_BUTTON_SIZE, SkillsSizes.SKILL_BUTTON_SIZE, this);
                ++rowNumber;
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
