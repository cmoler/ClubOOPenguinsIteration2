package View.StatusView;

import Configs.Commons;
import Configs.ImagesInfo;
import Model.Entity.Entity;
import Model.Entity.Role.Role;
import View.Viewport;

import java.awt.*;
import java.util.List;

public class SkillsView extends Viewport {

    private Entity entity;

    public SkillsView(Role role){
        this.entity = entity;
        entity.attach(this);
    }

    @Override
    public void draw(Graphics2D graphics2D) {

    }

    @Override
    public List<Viewport> getChildren(){
        return null;
    }

    @Override
    public void update(){
        repaint();
    }
}
