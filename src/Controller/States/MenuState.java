package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.MenuController;
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

public class MenuState implements ControllerState {

    ControllerMediator controllerMediator;
    MenuController menuController;

    //... idk i needed something for the lambdas
    Integer selected;

    Map<Integer, Consumer<Integer>> keyBinding = new HashMap();

    public MenuState(ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
    }

    @Override
    public void process(KeyEvent keyEvent) {
        keyBinding.get(keyEvent).accept(this.selected);
    }

    @Override
    public void loadKeyBindings() {
        File entityKeyBindings = new File("resources/KeyBindings/menu");

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
                        case "exitMenu":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Integer selected) -> exitMenu(selected));
                            break;
                        case "scrollLeft":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Integer selected) -> scrollLeft(selected));
                            break;
                        case "scrollRight":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Integer selected) -> scrollRight(selected));
                            break;
                        case "scrollUp":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Integer selected) -> scrollUp(selected));
                            break;
                        case "scrollDown":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("Key").
                                    item(0).
                                    getTextContent()), (Integer selected) -> scrollDown(selected));
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

    private void exitMenu(Integer selected) {

    }

    private void scrollLeft(Integer selected) {

    }

    private void scrollRight(Integer selected) {

    }

    private void scrollUp(Integer selected) {

    }

    private void scrollDown(Integer selected) {

    }

}
