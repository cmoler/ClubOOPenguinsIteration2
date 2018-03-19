package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.EquipmentController;
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

public class EquipmentState implements ControllerState {

    ControllerMediator controllerMediator;
    EquipmentController equipmentController;

    Map<Integer, Runnable> keyBinding = new HashMap();

    public EquipmentState(GameBuilder gameBuilder, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.equipmentController = new EquipmentController(gameBuilder);
        loadKeyBindings();
    }

    @Override
    public void process(KeyEvent keyEvent) {
        if(keyBinding.get(keyEvent.getKeyCode()) != null) keyBinding.get(keyEvent.getKeyCode()).run();
    }

    @Override
    public void loadKeyBindings() {
        File entityKeyBindings = new File("resources/KeyBindings/equipment");

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
                        case "openInventory":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openInventory());
                            break;
                        case "exitEquipment":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> exitEquipment());
                            break;
                        case "openSkills":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> openSkills());
                            break;
                        case "unEquipItem":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> unEquip());
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
        equipmentController.setActive();
    }


    private void openMenu() {
        controllerMediator.changeToMenuState();
    }


    private void openInventory() {
        controllerMediator.changeToInventoryState();
    }

    private void exitEquipment() {
        controllerMediator.changeToEntityState();
    }

    private void openSkills() {
        controllerMediator.changeToSkillsState();
    }

    private void scrollLeft() {
        equipmentController.scrollLeft();
    }

    private void scrollRight() {
        equipmentController.scrollRight();
    }

    private void unEquip(){
        equipmentController.unEquip();
    }

}
