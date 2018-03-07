package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.EntityController;
import Controller.SavingLoading.GameLoader;
import Model.Entity.Entity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

// needed key bindings:
/*
move n, NE, E, etc
attack
open inventory
open equipment
open skills
use item 1
use item 2
... for however many we want
 */

public class EntityState implements ControllerState {

    ControllerMediator controllerMediator;
    EntityController entityController;

    Entity entity;

    Map<Integer, Consumer<Entity>> keyBinding = new HashMap();

    public EntityState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.entityController = new EntityController(gameLoader);
    }

    @Override
    public void process(KeyEvent keyEvent) {
        keyBinding.get(keyEvent).accept(this.entity);
    }

    @Override
    public void loadKeyBindings() {
        File entityKeyBindings = new File("resources/Keybindings/entity");

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(entityKeyBindings);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("Bind");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    switch (eElement.getAttribute("Bind")){
                        case "moveN":
                            keyBinding.put(Integer.parseInt(eElement.
                                                            getElementsByTagName("Key").
                                                            item(0).
                                                            getTextContent()), (Entity entity) -> moveN(entity));

                    }
                }

            }

            } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void moveN(Entity entity) {

    }
}
