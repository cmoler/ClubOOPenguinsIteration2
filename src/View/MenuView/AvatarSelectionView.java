package View.MenuView;

import View.Viewport;

import java.awt.*;
import java.util.List;

public class AvatarSelectionView extends MenuViewPort {
    private int x;
    private int y;

    public AvatarSelectionView(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D graphics2D) {


    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

}
