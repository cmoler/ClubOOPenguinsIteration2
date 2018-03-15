package View.MenuView;

import View.Viewport;
import javax.swing.*;
import java.awt.*;

public abstract class MenuViewPort extends Viewport {

    protected int selectedX;
    protected int selectedY;

    public void setSelectedX(int selectedX) {
        this.selectedX = selectedX;
    }

    public void setSelectedY(int selectedY) {
        this.selectedY = selectedY;
    }
}

//    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;
//    private int selectedX = Configs.Commons.SCREEN_WIDTH/2;
//    private int selectedY = Commons.SCREEN_HEIGHT/4;
//
//    int currentSubMenu = -1;
//
//    public MenuViewPort(){
//        add(new NewGameView());
//        add(new SaveGameView());
//        add(new LoadGameView());
//        add(new OptionsView());
//        add(new ExitGameView());
//
//        //Display numbers for testing
//        JButton ngBtn = new JButton("1");
//        JButton sgBtn = new JButton("2");
//        JButton lgBtn = new JButton("3");
//        JButton opBtn = new JButton("4");
//        JButton egBtn = new JButton("5");
//
//        //Styling to make button invisible
//        ngBtn.setBorderPainted(false);
//        ngBtn.setContentAreaFilled(false);
//        ngBtn.setToolTipText("Click");
//
//        sgBtn.setBorderPainted(false);
//        sgBtn.setContentAreaFilled(false);
//        sgBtn.setToolTipText("Click");
//
//        lgBtn.setBorderPainted(false);
//        lgBtn.setContentAreaFilled(false);
//        lgBtn.setToolTipText("Click");
//
//        opBtn.setBorderPainted(false);
//        opBtn.setContentAreaFilled(false);
//        opBtn.setToolTipText("Click");
//
//        egBtn.setBorderPainted(false);
//        egBtn.setContentAreaFilled(false);
//        egBtn.setToolTipText("Click");
//
//        add(ngBtn);
//        add(sgBtn);
//        add(lgBtn);
//        add(opBtn);
//        add(egBtn);
//
//        setVisible(true);
//
//}
//
//    @Override
//    public void draw(Graphics2D graphics2D) {
//        if(currentSubMenu == -1) drawMainMenu(graphics2D);
//        else children.get(currentSubMenu).draw(graphics2D);
//
//    }
