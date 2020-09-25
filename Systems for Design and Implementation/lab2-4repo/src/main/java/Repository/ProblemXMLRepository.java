package Repository;

import Container.Pair;
import Domain.Problem;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ProblemXMLRepository extends XMLRepository<Long, Problem> {

    public ProblemXMLRepository(String fileName) {
        super(fileName);
    }

    @Override
    protected Pair<Long, Problem> createEntityFromElement(Element element) {
        Long id=Long.parseLong(getTextFromTagName(element,"id"));
        int number=Integer.parseInt(getTextFromTagName(element,"number"));
        String chapter=getTextFromTagName(element,"chapter");
        String text=getTextFromTagName(element,"text");
        Problem problem=new Problem(number,chapter,text,id);
        return new Pair(id,problem);
    }

    @Override
    protected Node createNodeFromEntity(Problem entity) {
        Element problemElement=document.createElement("problem");
        appendChildWithTextToNode(problemElement,"id",entity.getId().toString());
        appendChildWithTextToNode(problemElement,"number",String.valueOf(entity.getNumber()));
        appendChildWithTextToNode(problemElement,"chapter",entity.getChapter());
        appendChildWithTextToNode(problemElement,"text",entity.getText());
        return problemElement;
    }

    @Override
    protected String getTag() {
        return "problem";
    }
}
