package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.EquipmentController;
import Controller.SavingLoading.GameLoader;
import Model.Entity.Entity;
import Model.Entity.Equipment;
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
import java.util.function.Consumer;

public class EquipmentState implements ControllerState {

    ControllerMediator controllerMediator;
    EquipmentController equipmentController;

    Equipment equipment;

    Map<Integer, Consumer<Equipment>> keyBinding = new HashMap();

    public EquipmentState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.equipmentController = new EquipmentController(gameLoader);
    }

    @Override
    public void process(KeyEvent keyEvent) {
        keyBinding.get(keyEvent).accept(this.equipment);
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

                    switch (eElement.getAttribute("Bind")){
                        case "openMenu":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Equipment equipment) -> openMenu(equipment));
                            break;
                        case "openInventory":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Equipment equipment) -> openInventory(equipment));
                            break;
                        case "exitEquipment":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Equipment equipment) -> exitEquipment(equipment));
                            break;
                        case "openSkills":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Equipment equipment) -> openSkills(equipment));
                            break;
                        case "unEquipItem":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Equipment equipment) -> unEquipItem(equipment));
                            break;
                        case "scrollLeft":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Equipment equipment) -> scrollLeft(equipment));
                            break;
                        case "scrollRight":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Equipment equipment) -> scrollRight(equipment));
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



    private void openMenu(Equipment equipment) {
    }


    private void openInventory(Equipment equipment) {

    }

    private void exitEquipment(Equipment equipment) {

    }

    private void openSkills(Equipment equipment) {

    }

    private void unEquipItem(Equipment equipment) {

    }

    private void scrollLeft(Equipment equipment) {

    }

    private void scrollRight(Equipment equipment) {

    }



}
