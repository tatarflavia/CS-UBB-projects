package Domain;

/**
 * Represents problems that can be assigned to students.
 */
public class Problem extends BaseEntity<Long> {
    private String text;
    private int number;
    private String chapter;

    /**
     * Creates a problem that can be assigned to students.
     * @param problemNumber the order of a problem from a chapter
     * @param problemChapter a chapter title
     * @param problemText the description of the problem
     * @param id unique identifier of the problem
     */
    public Problem(int problemNumber, String problemChapter,String problemText,long id) {
        this.text = problemText;
        this.number = problemNumber;
        this.chapter = problemChapter;
        super.setId(id);
    }

    /**
     * @return text description of the problem
     */
    public String getText() {
        return text;
    }

    /**
     * @return number of the problem from the corresponding chapter
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return chapter title
     */
    public String getChapter() {
        return chapter;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    /**
     * @return String representation of problem
     */
    @Override
    public String toString() {
        return "Problem{" +
                "problemText='" + text + "'" +
                ", problemNumber=" + number +
                ", problemChapter='" + chapter + "'" +
                ", id="+getId()+
                "}";
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return problemNumber == problem.problemNumber &&
                Objects.equals(problemChapter, problem.problemChapter);
    }*/

}
