package domain;

import container.Pair;

/**
 * Represents grades that can be assigned to students in order to evaluate them.
 */
public class Grade extends BaseEntity<Pair<Long,Long>> {
    private int gradeByTeacher;
    private long student_id;
    private long problem_id;

    /**
     * Creates a grade that can be assigned later to students for assignments.
     * @param idOfTheStudent id of the student that will be given a grade
     * @param idOfTheProblem id of the problem for which the students will receive a grade
     */
    public Grade(Long idOfTheStudent, Long idOfTheProblem) {
        super.setId(new Pair(idOfTheStudent,idOfTheProblem));
        student_id=idOfTheStudent;
        problem_id=idOfTheProblem;
        gradeByTeacher=-1;
    }

    /**
     * Creates an assignment that is already graded.
     * @param idOfTheStudent id of the student that received the grade
     * @param idOfTheProblem id of the problem for which the grade is given
     * @param gradeGivenByTeacher grade value
     */
    public Grade(Long idOfTheStudent,Long idOfTheProblem,int gradeGivenByTeacher) {
        super.setId(new Pair(idOfTheStudent,idOfTheProblem));
        student_id=idOfTheStudent;
        problem_id=idOfTheProblem;
        this.gradeByTeacher=gradeGivenByTeacher;
    }

    /**
     * @return String representation of the grade entity
     */
    @Override
    public String toString() {
        return "Grade{" +
                "idOfTheStudent=" + super.getId().first +
                ", idOftheProblem=" + super.getId().second +
                ", gradeGivenByTeacher=" + gradeByTeacher +'}';
    }

    /**
     * @return id of the student that receives the grade
     */
    public Long getIdOfTheStudent() {
        return super.getId().first;
    }

    /**
     *
     * @return id of the problem that is graded
     */
    public Long getIdOfTheProblem() {
        return super.getId().second;
    }

    /**
     *
     * @return the actual value of the grade given by the teacher
     */
    public int getGradeGivenByTeacher() {
        return gradeByTeacher;
    }

    /**
     *
     * @param gradeGivenByTeacher actual value of the grade that will be given to the student for that assignment
     */
    public void setGradeGivenByTeacher(int gradeGivenByTeacher) {
        this.gradeByTeacher = gradeGivenByTeacher;
    }

    @Override
    public boolean equals(Object o) {
        Grade grade=(Grade) o;
        return grade.getId().equals(getId());
    }
}
