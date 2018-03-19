package View.AreaView;

import Model.Entity.Player;
import View.Viewport;
import Configs.AreaSizes;
import Configs.Commons;
import Model.Map.Location;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class AreaViewPort extends Viewport {

    private final List<JButton> btnList = new ArrayList<>();
    private int rowCount = Commons.SCREEN_HEIGHT / AreaSizes.TERRAIN_HEIGHT;
    private int colCount = Commons.SCREEN_WIDTH / AreaSizes.TERRAIN_WIDTH;

    public AreaViewPort(Player player) {
        ButtonGrid(player);
    }

    private JButton getButtonIndex(int x, int y) {
        return btnList.get(x * rowCount + y);
    }

    public void ButtonGrid(Player player) {
        JPanel buttonGrid = new JPanel(new GridLayout(rowCount, colCount, 0, 0));

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                JButton btn = createButton(i, j, player);
                btnList.add(btn);
                buttonGrid.add(btn);
            }
        }

//        buttonGrid.setOpaque(false);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        add(buttonGrid);
        setVisible(true);
    }

    private JButton createButton(int x, int y, Player player) {
        JButton btn = new JButton(String.valueOf(x) + ", " + String.valueOf(y));

        //Printing x and y values for testing purposes
        System.out.println("Creating button at " + String.valueOf(x) + ", " + String.valueOf(y));

        //Styling to make button invisible
//        btn.setBorderPainted(false);
//        btn.setContentAreaFilled(false);
//        btn.setOpaque(false);
//        btn.setPreferredSize(new Dimension(AreaSizes.TERRAIN_WIDTH, AreaSizes.TERRAIN_HEIGHT));
        btn.setToolTipText("Click to observe");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Observing " + String.valueOf(x) + ", " + String.valueOf(y));

                int moveX = rowCount / 2 + 1 - x;
                int moveY = colCount / 2 + 1 - y;
                JButton getIndex = getButtonIndex(x, y);

                JList<JLabel> entityInfo = new JList<>();

                //Get player location
                Location location = player.getLocation();
                JLabel type = new JLabel("Type: " + player.getRole().ObservationSkill().getEntityType(location, moveX, moveY));
                JLabel maxHealth = new JLabel("Maximum Health: " + Integer.toString(player.getRole().ObservationSkill().getEntityMaxHealth(location, moveX, moveY)));
                JLabel health = new JLabel("Health: " + Integer.toString(player.getRole().ObservationSkill().getEntityHealth(location, moveX, moveY)));
                JLabel level = new JLabel("Level: " + Integer.toString(player.getRole().ObservationSkill().getEntityLevel(location, moveX, moveY)));

                entityInfo.add(type);
                entityInfo.add(maxHealth);
                entityInfo.add(health);
                entityInfo.add(level);

                entityInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 2, 2));

                add(entityInfo);

                setVisible(true);

                getTopLevelAncestor().requestFocus();
            }
        });

        return btn;
    }


}