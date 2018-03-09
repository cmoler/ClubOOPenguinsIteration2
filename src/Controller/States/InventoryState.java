package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.InventoryController;
import Controller.SavingLoading.GameLoader;
import Model.Entity.Entity;
import Model.Entity.Inventory;
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

public class InventoryState implements ControllerState {

    ControllerMediator controllerMediator;
    InventoryController inventoryController;

    Inventory inventory;

    Map<Integer, Consumer<Inventory>> keyBinding = new HashMap();

    public InventoryState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.inventoryController = new InventoryController(gameLoader);
    }


    @Override
    public void process(KeyEvent keyEvent) {
        keyBinding.get(keyEvent).accept(this.inventory);
    }

    @Override
    public void loadKeyBindings() {
        File entityKeyBindings = new File("resources/KeyBindings/inventory");

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
                                    getTextContent()), (Inventory inventory) -> openMenu(inventory));
                            break;
                        case "openEquipment":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> openEquipment(inventory));
                            break;
                        case "exitInventory":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> exitInventory(inventory));
                            break;
                        case "openSkills":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> openSkills(inventory));
                            break;
                        case "equipItem":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> equipItem(inventory));
                            break;
                        case "scrollLeft":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> scrollLeft(inventory));
                            break;
                        case "scrollRight":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> scrollRight(inventory));
                            break;
                        case "scrollUp":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> scrollUp(inventory));
                            break;
                        case "scrollDown":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Inventory inventory) -> scrollDown(inventory));
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

    private void openMenu(Inventory inventory) {

    }

    private void openEquipment(Inventory inventory) {

    }

    private void exitInventory(Inventory inventory) {

    }

    private void openSkills(Inventory inventory) {

    }

    private void equipItem(Inventory inventory) {

    }

    private void scrollLeft(Inventory inventory) {

    }

    private void scrollRight(Inventory inventory) {

    }

    private void scrollUp(Inventory inventory) {

    }

    private void scrollDown(Inventory inventory) {

    }
}
