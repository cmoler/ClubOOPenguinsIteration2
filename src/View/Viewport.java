package View;

import Model.Map.Direction;
import View.AreaView.MapView;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Viewport extends JPanel {

    protected Viewport parent;
    protected List<Viewport> children = new ArrayList<Viewport>();

    public Viewport(){

        setFocusable(true);
        setDoubleBuffered(true);
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
    public void paintComponent(Graphics g){
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

    public void draw(Graphics2D graphics2D, int x, int y){
        for(Viewport child: children){
            child.draw(graphics2D, x, y);
        }
    }

    public void addToFront(Viewport viewport){
        viewport.parent = this;
        children.add(0, viewport);
    }

    public void add(Viewport viewport){
        viewport.parent = this;
        children.add(viewport);
    }

    public void remove(Viewport viewport){
        for(int i = 0; i < children.size(); ++i){
            viewport.parent = null;
            if(children.get(i) == viewport) children.remove(i);
        }
    }

    public List<Viewport> getChildren(){

        return children;
    }

    public void update(){
        repaint();
    }

    public void updateMap(MapView lastMapView, MapView currentMapView){
        this.remove(lastMapView);
        this.add(currentMapView);
    }

    public static void LoopMusic() {
        InputStream is;
        try {
            is = new FileInputStream("../resources/music/music.mp3");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        try {
            AudioStream as = new AudioStream(is);
            AudioData ad = as.getData();
            ContinuousAudioDataStream cs = new ContinuousAudioDataStream(ad);
            AudioPlayer.player.start(cs);
        } catch (IOException e) {
            System.out.println("Audio data exception");
        }
    }

}
