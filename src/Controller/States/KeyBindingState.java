package Controller.States;

import Controller.ControllerMediator;
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

public class KeyBindingState implements ControllerState {

    private ControllerMediator controllerMediator;
    private String bindingToChange;
    private String keyToChange;

    public KeyBindingState(ControllerMediator controllerMediator){
        this.controllerMediator = controllerMediator;
    }

    @Override
    public void process(KeyEvent keyEvent) {
        File entityKeyBindings = new File("resources/KeyBindings/"+bindingToChange);

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

                    if(eElement.getAttribute("type") == keyToChange){

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

        controllerMediator.reloadKeyBindings();
        controllerMediator.changeToMenuState();
    }

    @Override
    public void loadKeyBindings() {

    }

    @Override
    public void setActive() {

    }

    public void setBindingToChange(String bindingToChange){
        this.bindingToChange = bindingToChange;
    }

    public void setKeyToChange(String keyToChange){
        this.keyToChange = keyToChange;
    }
}
