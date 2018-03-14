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

    Map<Integer, Runnable> keyBinding = new HashMap();

    public EntityState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.entityController = new EntityController(gameLoader);
        loadKeyBindings();
    }

    @Override
    public void process(KeyEvent keyEvent) {
        if(keyBinding.get(keyEvent.getKeyCode()) != null) keyBinding.get(keyEvent.getKeyCode()).run();
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

                    switch (eElement.getAttribute("type")){
                        case "moveN":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveN());
                            break;
                        case "moveNE":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveNE());
                            break;
                        case "moveE":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveE());
                            break;
                        case "moveSE":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveSE());
                            break;
                        case "moveS":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveS());
                            break;
                        case "moveSW":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveSW());
                            break;
                        case "moveW":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveW());
                            break;
                        case "moveNW":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> moveNW());
                            break;
                        case "openMenu":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openMenu());
                            break;
                        case "openInventory":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openInventory());
                            break;
                        case "openEquipment":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openEquipment());
                            break;
                        case "openSkills":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openSkills());
                            break;
                        case "interact":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> interact());
                            break;
                        case "slot1":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> slot1());
                            break;
                        case "slot2":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> slot2());
                            break;
                        case "slot3":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> slot3());
                            break;
                        case "slot4":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> slot4());
                            break;
                        case "slot5":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> slot5());
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

    @Override
    public void setActive() {
        entityController.setActive();
    }


    private void moveN() {

    }

    private void moveNE() {

    }

    private void moveE() {

    }

    private void moveSE() {

    }

    private void moveS() {

    }

    private void moveSW() {

    }

    private void moveW() {

    }

    private void moveNW() {

    }

    private void openMenu() {

    }

    private void openInventory() {

    }

    private void openEquipment() {

    }

    private void openSkills() {

    }

    private void interact(){

    }

    private void slot1() {

    }

    private void slot2() {

    }

    private void slot3() {

    }

    private void slot4() {

    }

    private void slot5() {

    }
}
