package View.AreaView;

import View.Viewport;
import Configs.AreaSizes;
import Configs.Commons;
import Configs.AreaSizes;
import Model.Entity.Entity;
import Model.Map.MapIterator;
import Model.Map.World;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class AreaViewPort extends Viewport {

    private final List<JButton> btnList = new ArrayList<JButton>();
    private int rowCount = Commons.SCREEN_HEIGHT / AreaSizes.TERRAIN_HEIGHT;
    private int colCount = Commons.SCREEN_WIDTH / AreaSizes.TERRAIN_WIDTH;

    public AreaViewPort() {

    }

    private JButton getButtonIndex(int row, int column) {
        return btnList.get(row * rowCount + column);
    }

    public void ButtonGrid() {
        JPanel buttonGrid = new JPanel(new GridLayout(rowCount, colCount, 0, 0));

        for(int i = 0; i < rowCount; i++) {
            for(int j = 0; j < colCount; j++) {
                //Printing row and column number for testing purposes
                JButton btn = createButton(i, j);
/*
//Styling to make button invisible
btn.setBorderPainted(false);
btn.setContentAreaFilled(false);
btn.setToolTipText("Click to move");
*/
                btnList.add(btn);
                buttonGrid.add(btn);
            }
        }
        add(buttonGrid);
        setVisible(true);
    }

    private JButton createButton(int x, int y) {
        JButton btn = new JButton(String.valueOf(x) + ", " + String.valueOf(y));

        //Styling to make button invisible
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setToolTipText("Click to move");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JButton getIndex = getButtonIndex(x, y);
            }
        });
        return btn;
    }

}

