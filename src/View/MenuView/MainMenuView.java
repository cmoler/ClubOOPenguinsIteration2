package View.MenuView;

import Configs.Commons;
import Configs.ImagesInfo;
import Configs.TextBoxInfo;
import com.sun.prism.image.ViewPort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenuView extends MenuViewPort {

    private Image selected = ImagesInfo.AREAEFFECT_LEVELUP_IMAGE;

    private int currentSubMenu = -1;

    private int startX = Configs.Commons.SCREEN_WIDTH/2 - TextBoxInfo.TEXTBOX_WIDTH + 35;
    private int startY = Commons.SCREEN_HEIGHT/4;


    public MainMenuView(){
        add(new NewGameView());
        add(new SaveGameView());
        add(new LoadGameView());
        add(new OptionsView());
        add(new ExitGameView());

        ClickableMenu();
    }

    public void ClickableMenu() {
        JPanel buttonGrid = new JPanel(new GridLayout(5, 1, 0, 0));
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

        buttonGrid.add(ngBtn);
        buttonGrid.add(sgBtn);
        buttonGrid.add(lgBtn);
        buttonGrid.add(opBtn);
        buttonGrid.add(egBtn);


        buttonGrid.setOpaque(false);
        buttonGrid.setBounds(100, 100, TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT);
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 190));

        this.add(buttonGrid);

//        add(ngBtn);
//        add(sgBtn);
//        add(lgBtn);
//        add(opBtn);
//        add(egBtn);
        
        setVisible(true);

    }

    public JButton ConfigureButton(JButton btn, String btnFn) {
        //Styling to make button invisible
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT));
        btn.setToolTipText(btnFn);
        return btn;
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
