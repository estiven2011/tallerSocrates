package co.edu.poli.ces3.socrates.dao;

public class Enrollment {
    private int id;
    private int studentId;
    private int subjectId;
    private String semester;
    private String status;

    public Enrollment() {}

    public Enrollment(int id, int studentId, int subjectId, String semester, String status) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.semester = semester;
        this.status = status;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getSubjectId() { return subjectId; }
    public void setSubjectId(int subjectId) { this.subjectId = subjectId; }

    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
