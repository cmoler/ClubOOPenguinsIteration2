package View;

import Model.Map.Direction;
import View.AreaView.MapView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Viewport extends JPanel {

    protected Viewport parent;
    protected List<Viewport> children = new ArrayList<Viewport>();

    public Viewport() {
        setFocusable(true);
        setDoubleBuffered(true);
        //LoopMusic();
    }

    public int getLocationX() {
        return -1;
    }

    public int getLocationY() {
        return -1;
    }

    @Override
    public void addNotify() {

        super.addNotify();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D graphics2D = (Graphics2D) g;

        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        draw(graphics2D);

        Toolkit.getDefaultToolkit().sync();
    }

    public void draw(Graphics2D graphics2D) {
        for (Viewport child : children) {
            child.draw(graphics2D);
        }
    }

    public void draw(Graphics2D graphics2D, int x, int y) {
        for (Viewport child : children) {
            child.draw(graphics2D, x, y);
        }
    }

    public void addToFront(Viewport viewport) {
        viewport.parent = this;
        children.add(0, viewport);
    }

    public void add(Viewport viewport) {
        if(viewport != null) {
            viewport.parent = this;
            children.add(viewport);
        }
    }

    public void remove(Viewport viewport) {
        for (int i = 0; i < children.size(); ++i) {
            viewport.parent = null;
            if (children.get(i) == viewport) children.remove(i);
        }
    }

    public List<Viewport> getChildren() {

        return children;
    }

    public void update() {
        repaint();
    }

    public void updateMap(MapView lastMapView, MapView currentMapView) {
        this.remove(lastMapView);
        this.add(currentMapView);
    }

/*    private void LoopMusic() {
        Media hit = new Media(new File("resources/music/music.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
    }*/
}