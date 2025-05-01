package co.edu.poli.ces3.socrates.dao;

import java.util.Date;

public class User {
    private Integer id;
    private String names;
    private String lastName;
    private String password;
    private Date birthdate;
    private String email;
    private Boolean isActive;
    private String phone;
    private String gender;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAat;

    public User(String names, String lastName) {
        this.names = names;
        this.lastName = lastName;
    }

    public User(Integer id, String names, String lastName, String password, Date birthdate, String email, Boolean isActive, String phone, String gender, Date createdAt, Date updatedAt, Date deletedAat) {
        this.id = id;
        this.names = names;
        this.lastName = lastName;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.isActive = isActive;
        this.phone = phone;
        this.gender = gender;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAat = deletedAat;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAat() {
        return deletedAat;
    }

    public void setDeletedAat(Date deletedAat) {
        this.deletedAat = deletedAat;
    }

    @Override
    public String toString() {
        return "Nombre: " + this.names + ", Apellido: " + this.lastName + ", Email: " + this.email;
    }

    @Override
    public boolean equals(Object obj) {
        return this.id.equals(((User) obj).id);
    }

    public static void main(String[] args) {
        User user1 = new User("Andres", "Berrio");
        user1.setEmail("andres@gmail.com");
        user1.setId(5);

        User user2 = new User("Juan Pablo", "Uribe");
        user2.setEmail("juanpablouribe@elpoli.edu.co");
        user2.setId(5);
        System.out.println("***************");
        System.out.println(user1.equals(user2));
        System.out.println("***************");
    }
}
