package View.AreaView;

import Model.Entity.Player;
import View.Viewport;
import Configs.AreaSizes;
import Configs.Commons;
import Model.Map.Location;
import Model.Entity.Skill.Observation;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class AreaViewPort extends Viewport {

    private final List<JButton> btnList = new ArrayList<>();
    private int rowCount = Commons.SCREEN_HEIGHT / AreaSizes.TERRAIN_HEIGHT;
    private int colCount = Commons.SCREEN_WIDTH / AreaSizes.TERRAIN_WIDTH;

    private Player player;

    public AreaViewPort(Player player) {
        this.player = player;
    }

    private JButton getButtonIndex(int x, int y) {
        return btnList.get(x * rowCount + y);
    }

    public void ButtonGrid() {
        JPanel buttonGrid = new JPanel(new GridLayout(rowCount, colCount, 0, 0));

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                JButton btn = createButton(i, j);
                btnList.add(btn);
                buttonGrid.add(btn);
            }
        }

        buttonGrid.setOpaque(false);

        this.setLayout(new BorderLayout());

        add(buttonGrid);
        setVisible(true);
    }

    private JButton createButton(int x, int y) {
        JButton btn = new JButton(String.valueOf(x) + ", " + String.valueOf(y));

        //Printing x and y values for testing purposes
        System.out.println("Observing " + String.valueOf(x) + ", " + String.valueOf(y));

        //Styling to make button invisible
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        btn.setOpaque(false);
        btn.setPreferredSize(new Dimension(AreaSizes.TERRAIN_WIDTH, AreaSizes.TERRAIN_HEIGHT));
        btn.setToolTipText("Click to observe");

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int moveX = rowCount / 2 + 1 - x;
                int moveY = colCount / 2 + 1 - y;

                //Need a way to pass current observation skill
                Observation ob = new Observation(5);

                JButton getIndex = getButtonIndex(x, y);

                //Get player location
                Location location = player.getLocation();
                String type = ob.getEntityType(location, moveX, moveY);
                String maxHealth = Integer.toString(ob.getEntityMaxHealth(location, moveX, moveY));
                String health = Integer.toString(ob.getEntityHealth(location, moveX, moveY));
                String level = Integer.toString(ob.getEntityLevel(location, moveX, moveY));


                getTopLevelAncestor().requestFocus();
            }
        });

        return btn;
    }


}