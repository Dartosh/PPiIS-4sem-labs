package serverside.model.parsers;

import serverside.model.datamodel.Student;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

import java.util.ArrayList;

public class DOMWriter {
    Document writtenDocument;
    Element rootElement;
    String nodeTag;

    public DOMWriter(String rootTag, String nodeTag) {
        this.nodeTag = nodeTag;

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
            writtenDocument = documentBuilder.newDocument();

            rootElement = writtenDocument.createElement(rootTag);
            writtenDocument.appendChild(rootElement);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    public void parseAndWrite(ArrayList<Student> parsedModelArray, String writePath) {
        for (Student currentEntry : parsedModelArray) {
            String fullName = currentEntry.getFullName();
            String year = String.valueOf(currentEntry.getYear());
            String groupId = String.valueOf(currentEntry.getGroupId());
            String assignmentsAmount = String.valueOf(currentEntry.getAssignmentsAmount());
            String passedAmount = String.valueOf(currentEntry.getPassedAmount());
            String progLang = currentEntry.getProgrammingLanguage();

            rootElement.appendChild(getEntry(fullName, year, groupId, assignmentsAmount, passedAmount, progLang));
        }

        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource writtenSource = new DOMSource(writtenDocument);

            StreamResult streamResult = new StreamResult(new File(writePath));
            transformer.transform(writtenSource, streamResult);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    private Node getEntry(String fullName, String year, String groupId,
                          String assignAmount, String passedAmount, String progLang) {

        Element newEntry = writtenDocument.createElement(nodeTag);

        newEntry.appendChild(getEntryElements(newEntry, "fullName", fullName));
        newEntry.appendChild(getEntryElements(newEntry, "year", year));
        newEntry.appendChild(getEntryElements(newEntry, "groupId", groupId));
        newEntry.appendChild(getEntryElements(newEntry, "assignmentsAmount", assignAmount));
        newEntry.appendChild(getEntryElements(newEntry, "passedAmount", passedAmount));
        newEntry.appendChild(getEntryElements(newEntry, "progLang", progLang));

        return newEntry;
    }

    private Node getEntryElements(Element element, String name, String value) {
        Element node = writtenDocument.createElement(name);
        node.appendChild(writtenDocument.createTextNode(value));

        return node;
    }
}
