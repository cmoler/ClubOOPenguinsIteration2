package Controller.States;

import Controller.ControllerMediator;
import Controller.Controllers.SkillsController;
import Controller.SavingLoading.GameLoader;
import Model.Entity.Inventory;
import Model.Entity.Role.Role;
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

public class SkillsState implements ControllerState {

    ControllerMediator controllerMediator;
    SkillsController skillsController;

    Role role;

    Map<Integer, Consumer<Role>> keyBinding = new HashMap();

    public SkillsState(GameLoader gameLoader, ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
        this.skillsController = new SkillsController(gameLoader);
    }

    @Override
    public void process(KeyEvent keyEvent) {
        keyBinding.get(keyEvent).accept(this.role);
    }


    @Override
    public void loadKeyBindings() {
        File entityKeyBindings = new File("resources/KeyBindings/skills");

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
                                    getTextContent()), (Role role) -> openMenu(role));
                            break;
                        case "openEquipment":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> openEquipment(role));
                            break;
                        case "openInventory":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> exitInventory(role));
                            break;
                        case "exitSkills":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> openSkills(role));
                            break;
                        case "increaseCurrent":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> increaseCurrent(role));
                            break;
                        case "scrollLeft":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> scrollLeft(role));
                            break;
                        case "scrollRight":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> scrollRight(role));
                            break;
                        case "scrollUp":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> scrollUp(role));
                            break;
                        case "scrollDown":
                            keyBinding.put(Integer.parseInt(eElement.
                                    getElementsByTagName("key").
                                    item(0).
                                    getTextContent()), (Role role) -> scrollDown(role));
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

    private void openMenu(Role role) {

    }

    private void openEquipment(Role role) {

    }

    private void exitInventory(Role role) {

    }

    private void openSkills(Role role) {

    }

    private void increaseCurrent(Role role) {

    }

    private void scrollLeft(Role role) {

    }

    private void scrollRight(Role role) {

    }

    private void scrollUp(Role role) {

    }

    private void scrollDown(Role selected) {

    }

}
