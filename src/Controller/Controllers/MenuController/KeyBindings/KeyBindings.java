package Controller.Controllers.MenuController.KeyBindings;

import Configs.Commons;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class KeyBindings {

    private LinkedHashMap<String, List<String>> bindings;

    public KeyBindings() {

        File keyBindingsDirectory = new File(Commons.KEYBINDINGS_FOLDER);

        File[] listOfKeyBindingFiles = keyBindingsDirectory.listFiles();

        if (listOfKeyBindingFiles == null) return;

        for (int i = 0; i < listOfKeyBindingFiles.length; ++i) {
            if (listOfKeyBindingFiles[i].isFile()) {
                String binding = listOfKeyBindingFiles[i].getName();

                if(bindings.containsKey(binding)) bindings.put(binding, new ArrayList<>());

                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = null;
                try {
                    dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(listOfKeyBindingFiles[i]);
                    doc.getDocumentElement().normalize();
                    NodeList nList = doc.getElementsByTagName("Bind");

                    for (int j = 0; j < nList.getLength(); j++) {
                        Node nNode = nList.item(j);

                        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement = (Element) nNode;

                            String function = eElement.getAttribute("type");
                            bindings.get(binding).add(function);
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
        }
    }

    public int getNumberOfBindings(){
        return bindings.size();
    }

    public int getNumberOfKeysForBinding(int i){

        int entry = 0;

        for(String key: bindings.keySet()){
            if(entry == i) return bindings.get(key).size();
            entry++;
        }

        return -1;
    }

    public String getBinding(int i){

        int entry = 0;

        for(String key: bindings.keySet()){
            if(entry == i) return key;
            entry++;
        }

        return null;
    }

    public String getKey(String binding,  int i){
        return bindings.get(binding).get(i);
    }
}
