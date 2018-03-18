package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;


public class MainMenuView extends MenuViewPort {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;

    private int currentSubMenu = -1;

    JPanel mainButtonGrid;
    JPanel ngButtonGrid;
    JPanel sgButtonGrid;
    JPanel lgButtonGrid;
    JPanel opButtonGrid;
    JPanel egButtonGrid;


    public MainMenuView(){
        add(new NewGameView());
        add(new SaveGameView());
        add(new LoadGameView());
        add(new OptionsView());
        add(new ExitGameView());

        mainClickableMenu();
    }

    private void killButtons(JPanel buttons) {
        this.remove(buttons);
    }

    private void mainClickableMenu() {
        mainButtonGrid = new JPanel(new GridLayout(5, 1, 0, 0));

        //Display numbers for testing
        JButton ngBtn = ConfigureButton(new JButton(" "), "New Game");
        JButton sgBtn = ConfigureButton(new JButton(" "), "Save Game");
        JButton lgBtn = ConfigureButton(new JButton(" "), "Load Game");
        JButton opBtn = ConfigureButton(new JButton(" "), "Options");
        JButton egBtn = ConfigureButton(new JButton(" "), "Exit Game");

        ngBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enterNewGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        sgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enterSaveGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        lgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enterLoadGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        opBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enterOptionsView();
                getTopLevelAncestor().requestFocus();
            }
        });

        egBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                enterExitGameView();
                getTopLevelAncestor().requestFocus();
            }
        });

        mainButtonGrid.add(ngBtn);
        mainButtonGrid.add(sgBtn);
        mainButtonGrid.add(lgBtn);
        mainButtonGrid.add(opBtn);
        mainButtonGrid.add(egBtn);


        mainButtonGrid.setOpaque(false);
//        buttonGrid.setBounds(100, 100, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 193));

        this.add(mainButtonGrid);

        setVisible(true);
    }

    private void ngClickableMenu() {

    }

    private void sgClickableMenu() {

    }

    private void lgClickableMenu() {

    }

    private void opClickableMenu() {

    }

    private void egClickableMenu() {

    }


    @Override
    public void draw(Graphics2D graphics2D) {
        if(currentSubMenu == -1) drawMainMenu(graphics2D);
        else children.get(currentSubMenu).draw(graphics2D);

    }

    private void drawMainMenu(Graphics2D graphics2D){
        BufferedImage background = null;

        try {
            background = ImageIO.read(new File("resources/images/menu_back.png"));
        } catch (IOException e) {
            System.out.println("Exception while reading background: " + e);
        }

        graphics2D.drawImage(background, 0, 0, Commons.SCREEN_WIDTH, Commons.SCREEN_WIDTH, this);

        int startX = Configs.Commons.SCREEN_WIDTH/2 - TextBoxInfo.TEXTBOX_WIDTH + 28;
        int startY = Commons.SCREEN_HEIGHT/4;

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
        mainClickableMenu();
    }

    public NewGameView getNewGameView(){
        return (NewGameView)children.get(0);
    }

    public void enterNewGameView(){
        currentSubMenu = 0;
        killButtons(mainButtonGrid);
    }


    public SaveGameView getSaveGameView(){
        return (SaveGameView)children.get(1);
    }

    public void enterSaveGameView(){
        currentSubMenu = 1;
        killButtons(mainButtonGrid);
    }


    public LoadGameView getLoadGameView(){
        return (LoadGameView)children.get(2);

    }

    public void enterLoadGameView(){
        currentSubMenu = 2;
        killButtons(mainButtonGrid);
    }


    public OptionsView getOptionsView(){
        return (OptionsView)children.get(3);
    }

    public void enterOptionsView(){
        currentSubMenu = 3;
        killButtons(mainButtonGrid);
    }


    public ExitGameView getExitGameView(){
        return (ExitGameView)children.get(4);
    }

    public void enterExitGameView(){
        currentSubMenu = 4;
        killButtons(mainButtonGrid);
    }
}
