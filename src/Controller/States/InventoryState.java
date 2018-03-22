package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.InventoryController;
import Controller.SavingLoading.GameBuilder;
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
import java.util.HashMap;
import java.util.Map;

public class InventoryState implements ControllerState {

    ControllerMediator controllerMediator;
    InventoryController inventoryController;

    Map<Integer, Runnable> keyBinding = new HashMap();

    public InventoryState(GameBuilder gameBuilder, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.inventoryController = new InventoryController(gameBuilder);
        loadKeyBindings();
    }


    @Override
    public void process(KeyEvent keyEvent) {
        if(keyBinding.get(keyEvent.getKeyCode()) != null) keyBinding.get(keyEvent.getKeyCode()).run();
    }

    @Override
    public void loadKeyBindings() {
        File entityKeyBindings = new File("resources/KeyBindings/inventory.xml");

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
                        case "openMenu":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openMenu());
                            break;
                        case "openEquipment":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openEquipment());
                            break;
                        case "exitInventory":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> exitInventory());
                            break;
                        case "openSkills":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openSkills());
                            break;
                        case "equipItem":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> equipItem());
                            break;
                        case "scrollLeft":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> scrollLeft());
                            break;
                        case "scrollRight":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> scrollRight());
                            break;
                        case "scrollUp":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> scrollUp());
                            break;
                        case "scrollDown":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> scrollDown());
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
        inventoryController.setActive();
    }

    private void openMenu() {
        controllerMediator.changeToMenuState();
    }

    private void openEquipment() {
        controllerMediator.changeToEquipmentState();
    }

    private void exitInventory() {
        controllerMediator.changeToEntityState();
    }

    private void openSkills() {
        controllerMediator.changeToSkillsState();
    }

    private void equipItem() {
        // TODO
        inventoryController.equipItem();
    }

    private void scrollLeft() {
        // TODO
        inventoryController.scrollLeft();
    }

    private void scrollRight() {
        // TODO
        inventoryController.scrollRight();
    }

    private void scrollUp() {
        // TODO
        inventoryController.scrollUp();
    }

    private void scrollDown() {
        // TODO
        inventoryController.scrollDown();
    }
}
