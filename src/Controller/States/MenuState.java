package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.MenuController.MainMenuController;
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

public class MenuState implements ControllerState {

    ControllerMediator controllerMediator;
    MainMenuController menuController;

    Map<Integer, Runnable> keyBinding = new HashMap();

    public MenuState(GameBuilder gameBuilder, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        menuController = new MainMenuController(gameBuilder, controllerMediator);
        loadKeyBindings();
    }

    @Override
    public void process(KeyEvent keyEvent) {

        System.out.println("SIZE OF KEYBINDING:" + keyBinding.size());

        if(keyBinding.get(keyEvent.getKeyCode()) != null) keyBinding.get(keyEvent.getKeyCode()).run();
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

                    switch (eElement.getAttribute("type")){
                        case "exitMenu":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> exitMenu());
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
                        case "select":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> select());
                            break;
                        case "goToEntity":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), () -> goToEntity());
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

    private void goToEntity() {
        controllerMediator.changeToEntityState();
    }

    @Override
    public void setActive() {
        menuController.setActive();
    }

    private void exitMenu() {
        menuController.exitSubMenu();
    }

    private void scrollLeft() {
        menuController.scrollLeft();
    }

    private void scrollRight() {
        menuController.scrollRight();
    }

    private void scrollUp() {
        System.out.println("calling scrollUp in menustate");
        menuController.scrollUp();
    }

    private void scrollDown() {
        System.out.println("calling scrollDown in menustate");
        menuController.scrollDown();
    }

    private void select() {
        menuController.select();
    }

}
