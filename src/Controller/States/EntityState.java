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
import java.io.IOException;
import java.util.*;
import java.util.function.Consumer;

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
        File entityKeyBindings = new File("resources/KeyBindings/entity");

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
                            break;
                        case "moveNE":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> moveNE(entity));
                            break;
                        case "moveE":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> moveE(entity));
                            break;
                        case "moveSE":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> moveSE(entity));
                            break;
                        case "moveS":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> moveS(entity));
                            break;
                        case "moveSW":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> moveSW(entity));
                            break;
                        case "moveW":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> moveW(entity));
                            break;
                        case "moveNW":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> moveNW(entity));
                            break;
                        case "openMenu":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> openMenu(entity));
                            break;
                        case "openInventory":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> openInventory(entity));
                            break;
                        case "openEquipment":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> openEquipment(entity));
                            break;
                        case "openSkills":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> openSkills(entity));
                            break;
                        case "slot1":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> slot1(entity));
                            break;
                        case "slot2":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> slot2(entity));
                            break;
                        case "slot3":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> slot3(entity));
                            break;
                        case "slot4":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> slot4(entity));
                            break;
                        case "slot5":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Entity entity) -> slot5(entity));
                            break;
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

    private void moveNE(Entity entity) {

    }

    private void moveE(Entity entity) {

    }

    private void moveSE(Entity entity) {

    }

    private void moveS(Entity entity) {

    }

    private void moveSW(Entity entity) {

    }

    private void moveW(Entity entity) {

    }

    private void moveNW(Entity entity) {

    }

    private void openMenu(Entity entity) {

    }

    private void openInventory(Entity entity) {

    }

    private void openEquipment(Entity entity) {

    }

    private void openSkills(Entity entity) {

    }

    private void slot1(Entity entity) {

    }

    private void slot2(Entity entity) {

    }

    private void slot3(Entity entity) {

    }

    private void slot4(Entity entity) {

    }

    private void slot5(Entity entity) {

    }
}
