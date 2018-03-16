package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenuView extends MenuViewPort {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;

    private int currentSubMenu = -1;

    private int startX = Configs.Commons.SCREEN_WIDTH/2 - TextBoxInfo.TEXTBOX_WIDTH;
    private int startY = Commons.SCREEN_HEIGHT/4;


    public MainMenuView(){
        JPanel buttonGrid = new JPanel(new GridLayout(5, 1, 0, 0));

        add(new NewGameView());
        add(new SaveGameView());
        add(new LoadGameView());
        add(new OptionsView());
        add(new ExitGameView());

        //Display numbers for testing
        JButton ngBtn = new JButton("1");
        JButton sgBtn = new JButton("2");
        JButton lgBtn = new JButton("3");
        JButton opBtn = new JButton("4");
        JButton egBtn = new JButton("5");

        //Styling to make button invisible
        ngBtn.setBorderPainted(false);
        ngBtn.setContentAreaFilled(false);
        ngBtn.setPreferredSize(new Dimension(30, 30));
        ngBtn.setToolTipText("New Game");
        ngBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Start new game");
                enterNewGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        sgBtn.setBorderPainted(false);
        sgBtn.setContentAreaFilled(false);
        sgBtn.setFocusable(false);
        sgBtn.setSize(new Dimension(80, 80));
        sgBtn.setToolTipText("Save Game");
        sgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Saving");
                enterSaveGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        lgBtn.setBorderPainted(false);
        lgBtn.setContentAreaFilled(false);
        lgBtn.setFocusable(false);
        lgBtn.setSize(new Dimension(80, 80));
        lgBtn.setToolTipText("Load Game");
        lgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Loading");
                enterLoadGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        opBtn.setBorderPainted(false);
        opBtn.setContentAreaFilled(false);
        opBtn.setFocusable(false);
        opBtn.setSize(new Dimension(80, 80));
        opBtn.setToolTipText("Options");
        opBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Options");
                enterOptionsView();
                getTopLevelAncestor().requestFocus();
            }
        });

        egBtn.setBorderPainted(false);
        egBtn.setContentAreaFilled(false);
        egBtn.setFocusable(false);
        egBtn.setSize(new Dimension(80, 80));
        egBtn.setToolTipText("Exit Game");
        egBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Exit");
                enterExitGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        buttonGrid.add(ngBtn);
        buttonGrid.add(sgBtn);
        buttonGrid.add(lgBtn);
        buttonGrid.add(opBtn);
        buttonGrid.add(egBtn);

        add(buttonGrid, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if(currentSubMenu == -1) drawMainMenu(graphics2D);
        else children.get(currentSubMenu).draw(graphics2D);

    }

    private void drawMainMenu(Graphics2D graphics2D){
        graphics2D.drawRect(startX, startY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("New Game", (startX), (startY+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Save Game", (startX), (startY + TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + 2*TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Load Game", (startX), (startY + 2*TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + 3*TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Options", (startX), (startY + 3*TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        graphics2D.drawRect(startX, startY + 4*TextBoxInfo.TEXTBOX_HEIGHT, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        graphics2D.drawString("Exit Game", (startX), (startY + 4*TextBoxInfo.TEXTBOX_HEIGHT+TextBoxInfo.TEXTBOX_HEIGHT/4));

        int selectionBoxX = startX;
        int selectionBoxY = startY + selectedY*TextBoxInfo.TEXTBOX_HEIGHT;

        graphics2D.drawImage(selected, selectionBoxX, selectionBoxY, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT, this);
    }

    public void returnToMenu(){
        currentSubMenu = -1;
    }

    public NewGameView getNewGameView(){
        return (NewGameView)children.get(0);
    }

    public void enterNewGameView(){
        currentSubMenu = 0;
    }


    public SaveGameView getSaveGameView(){
        return (SaveGameView)children.get(1);
    }

    public void enterSaveGameView(){
        currentSubMenu = 1;
    }


    public LoadGameView getLoadGameView(){
        return (LoadGameView)children.get(2);

    }

    public void enterLoadGameView(){
        currentSubMenu = 2;
    }


    public OptionsView getOptionsView(){
        return (OptionsView)children.get(3);
    }

    public void enterOptionsView(){
        currentSubMenu = 3;
    }


    public ExitGameView getExitGameView(){
        return (ExitGameView)children.get(4);
    }

    public void enterExitGameView(){
        currentSubMenu = 4;
    }
}
