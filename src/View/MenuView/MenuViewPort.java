package View.MenuView;

import Configs.TextBoxInfo;
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

    public JButton ConfigureButton(JButton btn, String btnFn) {
        //Styling to make button invisible
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(TextBoxInfo.TEXTBOX_WIDTH, TextBoxInfo.TEXTBOX_HEIGHT));
        btn.setToolTipText(btnFn);
        return btn;
    }
}