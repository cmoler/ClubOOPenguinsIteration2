package View.StatusView;


import Configs.Commons;
import Configs.ImagesInfo;
import Model.Entity.Entity;
import Model.Entity.Player;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class StatsView extends Viewport {

    private final int ENTITY_HEALTH_X = (int) (Commons.SCREEN_WIDTH * 20.0/765.0);
    private final int ENTITY_HEALTH_Y = (int) (Commons.SCREEN_HEIGHT * 360.0/501.0);
    private final int ENTITY_HEALTH_HEIGHT = 20;
    private final int ENTITY_HEALTH_WIDTH = 500;

    private final int ENTITY_MANA_X = (int) (Commons.SCREEN_WIDTH * 20.0/765.0);
    private final int ENTITY_MANA_Y = (int) (Commons.SCREEN_HEIGHT * 390.0/501.0);
    private final int ENTITY_MANA_HEIGHT = 20;
    private final int ENTITY_MANA_WIDTH = 500;

    private final int ENTITY_EXP_X = (int) (Commons.SCREEN_WIDTH * 560/765.0);
    private final int ENTITY_EXP_Y = (int) (Commons.SCREEN_HEIGHT * 0/765.0);
    private final int ENTITY_EXP_HEIGHT = 250;
    private final int ENTITY_EXP_WIDTH = 300;

    private final int ENTITY_LEVEL_X =    (int) (Commons.SCREEN_WIDTH * 625.0/765.0);
    private final int ENTITY_LEVEL_Y =    (int) (Commons.SCREEN_HEIGHT * 80.0/501.0);
    private final int ENTITY_LEVEL_WIDTH = 200;
    private final int ENTITY_LEVEL_HEIGHT = 200;

    private final int ENTITY_GOLD_X = (int) (Commons.SCREEN_WIDTH * 380.0/765.0);
    private final int ENTITY_GOLD_Y = (int) (Commons.SCREEN_HEIGHT * 370.0/501.0);

    private Player player;

    public StatsView(Entity entity){
        this.player = (Player) entity;
        player.attach(this);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        //Portion of HP
        int dynamicHealthWidth = (ENTITY_HEALTH_WIDTH * player.getMaxHealth()) / 100;
        double percentHP = 1 - player.getHealth()/(double) player.getMaxHealth();
        int hprectSize = (int) (percentHP * dynamicHealthWidth);

        int dynamicManaWidth = (ENTITY_MANA_WIDTH * player.getMaxMana()) / player.getMaxMana();
        double percentMANA = 1 - player.getMana() / (double) player.getMaxMana();
        int manaRectSize = (int) (percentMANA * dynamicManaWidth);

        //Needed XP vars
        double expNeeded = (double) player.getExperienceForNextLevel();
        double prevExp = (double) player.getExperienceForCurrentLevel();
        double exp = (double) player.getExperience();
        int getLevel = player.getLevel();

        //Portion of XP
        double percentEXP;
        if(exp-prevExp > 0.1){
             percentEXP = 1 - (exp-prevExp)/(expNeeded-prevExp);
        }
        else {
            percentEXP = 1;
        }

        int xprectsize = (int) (percentEXP * (double) ENTITY_EXP_HEIGHT);


        //EXPBAR
        graphics2D.setColor(Color.YELLOW);
        graphics2D.fillRect(ENTITY_EXP_X, ENTITY_EXP_Y, ENTITY_EXP_WIDTH, ENTITY_EXP_HEIGHT);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(ENTITY_EXP_X, ENTITY_EXP_Y, ENTITY_EXP_WIDTH, xprectsize);
//        graphics2D.fillRect(ENTITY_EXP_X + (ENTITY_EXP_WIDTH-xprectsize), ENTITY_EXP_Y, xprectsize, ENTITY_EXP_HEIGHT);

        //Load Interfrace
        graphics2D.drawImage(ImagesInfo.RUNESCAPE_GUI,0,0, Commons.SCREEN_WIDTH, Commons.SCREEN_HEIGHT, this);

        //HPBAR
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.fillRect(ENTITY_HEALTH_X, ENTITY_HEALTH_Y, dynamicHealthWidth, ENTITY_HEALTH_HEIGHT);
        graphics2D.setColor(new Color(233, 3, 3));
        graphics2D.fillRect(ENTITY_HEALTH_X, ENTITY_HEALTH_Y, dynamicHealthWidth - hprectSize, ENTITY_HEALTH_HEIGHT );
        graphics2D.setColor(new Color(255, 255, 255));
        graphics2D.drawString("" + player.getHealth() + " / " + player.getMaxHealth(), ENTITY_HEALTH_X + (ENTITY_HEALTH_WIDTH/2), ENTITY_HEALTH_Y +( ENTITY_HEALTH_HEIGHT/2));

        //MANABAR
        graphics2D.setColor(new Color(0,0,0));
        graphics2D.fillRect(ENTITY_MANA_X, ENTITY_MANA_Y, dynamicManaWidth, ENTITY_MANA_HEIGHT);
        graphics2D.setColor(new Color(16,90,209));
        graphics2D.fillRect(ENTITY_MANA_X, ENTITY_MANA_Y, dynamicManaWidth - manaRectSize, ENTITY_MANA_HEIGHT);
        graphics2D.setColor(new Color(255, 255, 255));
        graphics2D.drawString("" + player.getMana() + " / " + player.getMaxMana(), ENTITY_MANA_X + (ENTITY_MANA_WIDTH/2), ENTITY_MANA_Y + (ENTITY_MANA_HEIGHT/2));

        //GOLD
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.setFont(new Font("Calibri",2,35));
        graphics2D.drawString("GOLD", ENTITY_GOLD_X, ENTITY_GOLD_Y);
        graphics2D.setColor(new Color(180, 160, 0));
        graphics2D.drawString("" + player.getGold(), ENTITY_GOLD_X, ENTITY_GOLD_Y + 40);

        //level indicator
        //graphics2D.setColor(new Color(200, 200, 200));
        //graphics2D.drawOval(ENTITY_LEVEL_X, ENTITY_LEVEL_Y, ENTITY_LEVEL_WIDTH, ENTITY_LEVEL_HEIGHT);
        graphics2D.setColor(new Color(0, 0, 0));
        graphics2D.setFont(new Font("Calibri",2,70));
        graphics2D.drawString("" + getLevel,  ENTITY_LEVEL_X , ENTITY_LEVEL_Y );
//      graphics2D.drawString("player.getHealth()", (ENTITY_HEALTH_X + ENTITY_HEALTH_HEIGHT)/2, (ENTITY_HEALTH_Y + ENTITY_HEALTH_WIDTH)/2);
//      graphics2D.drawString("player.getEXP()", (ENTITY_EXP_X + ENTITY_EXP_HEIGHT)/2, (ENTITY_EXP_Y + ENTITY_EXP_WIDTH)/2);


        if(player.getHealth() == 0){
            graphics2D.setColor(new Color(233, 3, 3));
            graphics2D.setFont(new Font("Calibri",2,150));
            graphics2D.drawString("Game Over Dude", (int)(Commons.SCREEN_WIDTH * 100.0/765.0), (int) (Commons.SCREEN_HEIGHT * 250.0/501.0));

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
