package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.PlayerController;
import Controller.SavingLoading.GameBuilder;
import Controller.SavingLoading.GameLoader;
import Model.Map.Direction;
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

public class PlayerState implements ControllerState {

    ControllerMediator controllerMediator;
    PlayerController playerController;

    Map<Integer, Runnable> keyBinding = new HashMap();

    public PlayerState(GameBuilder gameBuilder, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.playerController = new PlayerController(gameBuilder);
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
                        case "bindWounds":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> bindWounds());
                            break;
                        case "removeTrap":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> removeTrap());
                            break;
                        case "creep":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> creep());
                            break;
                        case "pickPocket":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> pickPocket());
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

    private void bindWounds() {
        playerController.bindWounds();
    }

    private void removeTrap() {
        playerController.removeTrap();
    }

    private void creep() {
        playerController.creep();
    }

    private void pickPocket() {
        playerController.pickPocket();
    }

    @Override
    public void setActive() {
        playerController.setActive();
    }


    private void moveN() {
        playerController.move(Direction.N);
    }

    private void moveNE() {
        playerController.move(Direction.NE);
    }

    private void moveE() {
        playerController.move(Direction.E);
    }

    private void moveSE() {
        playerController.move(Direction.SE);
    }

    private void moveS() {
        playerController.move(Direction.S);
    }

    private void moveSW() {
        playerController.move(Direction.SW);
    }

    private void moveW() {
        playerController.move(Direction.W);
    }

    private void moveNW() {
        playerController.move(Direction.NW);
    }

    private void openMenu() {
        controllerMediator.changeToMenuState();
    }

    private void openInventory() {
        controllerMediator.changeToInventoryState();
    }

    private void openEquipment() {
        controllerMediator.changeToEquipmentState();
    }

    private void openSkills() {
        controllerMediator.changeToSkillsState();
    }

    private void interact(){
        // TODO
    }

    private void slot1() {
        playerController.useItem(0);
    }

    private void slot2() {
        playerController.useItem(1);
    }

    private void slot3() {
        playerController.useItem(2);
    }

    private void slot4() {
        playerController.useItem(3);
    }

    private void slot5() {
        playerController.useItem(4);
    }
}
