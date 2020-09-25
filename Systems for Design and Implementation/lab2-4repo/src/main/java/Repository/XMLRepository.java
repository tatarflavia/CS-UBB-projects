package Repository;

import Container.Pair;
import Domain.BaseEntity;
import Exception.FileException;
import Exception.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Stores the entities in a file
 * @param <ID> unique identifier of the entity
 * @param <T> entity identified by ID
 */
abstract public class XMLRepository<ID, T extends BaseEntity<ID>> implements Repository<ID,T> {

    protected Path path;
    protected String fileName;
    protected Map<ID,T> entities;
    protected Document document;

    /**
     * Creates a new repository.
     * @param fileName name of the file stored in the project folder
     */
    public XMLRepository(String fileName) {
        this.fileName=fileName;
        path= Paths.get(new File("."+File.separator+fileName).getAbsolutePath());
        entities=readXML();
    }

    /**
     * Returns the text from the node with the tag name
     * @param parentElement element from which we want to take information from
     * @param tagName tag name of the node looked for
     * @return a string with the text looked for
     */
    protected static String getTextFromTagName(Element parentElement, String tagName) {
        Node node = parentElement.getElementsByTagName(tagName).item(0);
        return node.getTextContent();
    }

    /**
     *Adds a child to the parent node given
     * @param parentNode parent node that wil be given a child
     * @param tagName tag name of the child node that is created
     * @param textContent text content of the child node that is created
     */
    protected void appendChildWithTextToNode(Node parentNode,
                                                  String tagName,
                                                  String textContent) {
        Element element = document.createElement(tagName);
        element.setTextContent(textContent);
        parentNode.appendChild(element);
    }

    abstract protected Pair<ID,T> createEntityFromElement(Element element);

    abstract protected Node createNodeFromEntity(T entity);

    abstract protected String getTag();

    /**
     * Reads from the xml document and saves the elements as entities in the repository
     * @return a list of entities
     * @throws FileException if the file is poorly written or not in the folder
     */
    public Map<ID,T> readXML() throws FileException{
        entities=new HashMap<>();
        document = null;
        try {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            document = factory
                    .newDocumentBuilder()
                    .parse(path.toString());
            Element root = document.getDocumentElement();
            NodeList children = root.getChildNodes();
            IntStream.range(0, children.getLength())
                    .mapToObj(index -> children.item(index))
                    .filter(node -> node instanceof Element)
                    .map(node -> createEntityFromElement((Element)node))
                    .forEach(pair -> entities.put(pair.first,pair.second));
            return entities;
        } catch (SAXException e) {
            throw new FileException(fileName+" is missing or poorly formatted!");
        } catch (IOException e) {
            throw new FileException(fileName+" is missing or poorly formatted!");
        } catch (ParserConfigurationException e) {
            throw new FileException(fileName+" is missing or poorly formatted!");
        }
    }

    /**
     * Writes all the elements in the xml document
     * @throws FileException if the file is poorly written or not in the folder
     */
    private void saveDocument() throws FileException {
        document.normalizeDocument();
        Transformer transformer = null;
        try {
            XPath xp = XPathFactory.newInstance().newXPath();
            NodeList emptyLines = (NodeList) xp.evaluate("//text()[normalize-space(.)='']", document, XPathConstants.NODESET);
            for (int i=0; i < emptyLines.getLength(); ++i) {
                Node node = emptyLines.item(i);
                node.getParentNode().removeChild(node);
            }
            TransformerFactory factory=TransformerFactory.newInstance();
            factory.setAttribute("indent-number",2);
            transformer = factory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(new DOMSource(document),
                    new StreamResult(new File(path.toString())));
        } catch (TransformerConfigurationException e) {
            throw new FileException(fileName+" does not exist!");
        } catch (TransformerException e) {
            throw new FileException(fileName+" does not exist!");
        } catch (XPathExpressionException e) {
            throw new FileException(fileName+" does not exist!");
        }
    }

    /**
     * Deletes the element that has the id given from the xml document
     * @param id id of the element that needs to be deleted
     * @throws FileException if the file is poorly written or not in the folder
     */
    protected void deleteElementByID(ID id) throws FileException {
        NodeList nodes=document.getElementsByTagName(getTag());
        IntStream.range(0, nodes.getLength())
                .mapToObj(index -> nodes.item(index))
                .filter(node -> node instanceof Element)
                .filter(node -> ((Element) node).getElementsByTagName("id").item(0).getTextContent().equals(id.toString()))
                .forEach(element -> element.getParentNode().removeChild(element));
    }

    /**
     * Adds an element to the xml document
     * @param entity entity object that needs to be added to the xml doc
     * @throws FileException if the file is poorly written or not in the folder
     */
    private void addElement(T entity) throws FileException {
        Element root = document.getDocumentElement();
        Node bookNode = createNodeFromEntity(entity);
        root.appendChild(bookNode);
    }

    @Override
    public Optional<T> findOne(ID id) {
        return  Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        T existingValue=entities.putIfAbsent(entity.getId(),entity);
        Optional existingValueOptional=Optional.ofNullable(existingValue);
        existingValueOptional.ifPresentOrElse(value -> {},
                () -> { addElement(entity);
                    saveDocument();
        });
        return existingValueOptional;
    }

    @Override
    public Optional<T> delete(ID id) {
        T existingValue=entities.remove(id);
        Optional existingValueOptional=Optional.ofNullable(existingValue);
        existingValueOptional.ifPresent(value -> {
            deleteElementByID(id);
            saveDocument();
        });
        return existingValueOptional;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        T savedValue=entities.computeIfPresent(entity.getId(), (k, v) -> entity);
        Optional savedValueOptional=Optional.ofNullable(savedValue);
        savedValueOptional.ifPresent(value -> {
            deleteElementByID(((T)value).getId());
            addElement((T)value);
            saveDocument();
        });
        return savedValueOptional;
    }
}
