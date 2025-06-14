package co.edu.poli.ces3.socrates.dao;

public class Subject {
    private int id;
    private String code;
    private String name;
    private String description;
    private int credits;
    private String faculty;

    public Subject() {}

    public Subject(int id, String code, String name, String description, int credits, String faculty) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.credits = credits;
        this.faculty = faculty;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getFaculty() { return faculty; }
    public void setFaculty(String faculty) { this.faculty = faculty; }
}
