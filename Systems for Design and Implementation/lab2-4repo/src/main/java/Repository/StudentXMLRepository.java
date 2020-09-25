package Repository;

import Container.Pair;
import Domain.Student;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class StudentXMLRepository extends XMLRepository<Long, Student> {

    public StudentXMLRepository(String fileName) {
        super(fileName);
    }

    @Override
    protected Pair<Long, Student> createEntityFromElement(Element element) {
        Long id=Long.parseLong(getTextFromTagName(element,"id"));
        String name=getTextFromTagName(element,"name");
        Student student=new Student(name,id);
        return new Pair(id,student);
    }

    @Override
    protected Node createNodeFromEntity(Student entity) {
        Element studentElement=document.createElement("student");
        appendChildWithTextToNode(studentElement,"id",entity.getId().toString());
        appendChildWithTextToNode(studentElement,"name",entity.getName());
        return studentElement;
    }

    @Override
    protected String getTag() {
        return "student";
    }
}
