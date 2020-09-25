package Domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    public void getName() {
        Student std=new Student("alina",9);
        Assert.assertEquals("alina",std.getName());
        Student std2=new Student("razv",2);
        Assert.assertEquals("razv",std2.getName());
    }
}