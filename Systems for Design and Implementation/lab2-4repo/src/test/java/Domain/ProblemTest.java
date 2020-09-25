package Domain;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProblemTest {

    @Test
    public void getText() {
        String text="ana";
        int number=1;
        String chapter="AI";
        Problem prb=new Problem(number,chapter,text,1);
        Assert.assertEquals("ana",prb.getText());
        Problem prob2=new Problem(2,"AI","bb",2);
        Assert.assertEquals("bb",prob2.getText());
    }

    @Test
    public void getNumber() {
        String text="ana";
        int number=1;
        String chapter="AI";
        Problem prb=new Problem(number,chapter,text,1);
        Assert.assertEquals(1,prb.getNumber());
        Problem prob2=new Problem(2,"AI","bb",2);
        Assert.assertEquals(2,prob2.getNumber());
    }

    @Test
    public void getChapter() {
        String text="ana";
        int number=1;
        String chapter="AI";
        Problem prb=new Problem(number,chapter,text,1);
        Assert.assertEquals("AI",prb.getChapter());
        Problem prob2=new Problem(2,"AI","bb",2);
        Assert.assertEquals("AI",prob2.getChapter());
    }

    @Test
    public void setText() {
        String text="ana";
        int number=1;
        String chapter="AI";
        Problem prb=new Problem(number,chapter,text,1);
        prb.setText("aa");
        Assert.assertEquals("aa",prb.getText());
        Problem prob2=new Problem(2,"AI","bb",2);
        prob2.setText("gg");
        Assert.assertEquals("gg",prob2.getText());
    }

    @Test
    public void setNumber() {
        String text="ana";
        int number=1;
        String chapter="AI";
        Problem prb=new Problem(number,chapter,text,1);
        prb.setNumber(8);
        Assert.assertEquals(8,prb.getNumber());
        Problem prob2=new Problem(2,"AI","bb",2);
        prob2.setNumber(6);
        Assert.assertEquals(6,prob2.getNumber());
    }

    @Test
    public void setChapter() {
        String text="ana";
        int number=1;
        String chapter="AI";
        Problem prb=new Problem(number,chapter,text,1);
        prb.setChapter("kk");
        Assert.assertEquals("kk",prb.getChapter());
        Problem prob2=new Problem(2,"AI","bb",2);
        prob2.setChapter("jj");
        Assert.assertEquals("jj",prob2.getChapter());
    }
}