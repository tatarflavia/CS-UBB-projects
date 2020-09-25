package Repository;

import Container.Pair;
import Domain.Grade;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import Exception.*;

import java.util.stream.IntStream;

public class AssignmentXMLRepository extends XMLRepository<Pair<Long,Long>, Grade> {
    public AssignmentXMLRepository(String fileName) {
        super(fileName);
    }

    @Override
    protected Pair<Pair<Long, Long>, Grade> createEntityFromElement(Element element) {
        Long idOfStudent=Long.parseLong(getTextFromTagName(element,"idOfStudent"));
        Long idOfProblem=Long.parseLong(getTextFromTagName(element,"idOfProblem"));
        int value=Integer.parseInt(getTextFromTagName(element,"value"));
        Grade grade=new Grade(idOfStudent,idOfProblem,value);
        //Pair<Long,Long> id=new Pair<>(idOfStudent,idOfProblem);
        return new Pair<Pair<Long, Long>, Grade>(new Pair<>(idOfStudent,idOfProblem),grade);
    }

    @Override
    protected Node createNodeFromEntity(Grade entity) {
        Element gradeElement=document.createElement("grade");
        appendChildWithTextToNode(gradeElement,"idOfStudent",entity.getIdOfTheStudent().toString());
        appendChildWithTextToNode(gradeElement,"idOfProblem",entity.getIdOfTheProblem().toString());
        appendChildWithTextToNode(gradeElement,"value",String.valueOf(entity.getGradeGivenByTeacher()));
        return gradeElement;
    }

    @Override
    protected void deleteElementByID(Pair<Long, Long> longLongPair) throws FileException {
        NodeList nodes=document.getElementsByTagName(getTag());
        IntStream.range(0, nodes.getLength())
                .mapToObj(index -> nodes.item(index))
                .filter(node -> node instanceof Element)
                .filter(node -> ((Element) node).getElementsByTagName("idOfStudent").item(0).getTextContent().equals(longLongPair.first.toString()))
                .filter(node -> ((Element) node).getElementsByTagName("idOfProblem").item(0).getTextContent().equals(longLongPair.second.toString()))
                .forEach(element -> element.getParentNode().removeChild(element));
    }

    @Override
    protected String getTag() {
        return "grade";
    }
}
