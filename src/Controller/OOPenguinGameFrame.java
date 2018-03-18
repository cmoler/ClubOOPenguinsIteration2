package Controller;

import Configs.Commons;

import javax.swing.JFrame;

public class OOPenguinGameFrame extends JFrame {

    public OOPenguinGameFrame() {
        setTitle("OOPenguin");
        setSize(Commons.SCREEN_WIDTH, Commons.SCREEN_HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
