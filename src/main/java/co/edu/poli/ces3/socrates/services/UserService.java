package co.edu.poli.ces3.socrates.services;

import co.edu.poli.ces3.socrates.dao.User;
import co.edu.poli.ces3.socrates.repositories.UserRepository;
import co.edu.poli.ces3.socrates.utils.annotations.Column;
import co.edu.poli.ces3.socrates.utils.annotations.Table;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserService {


    private UserRepository repository;

    public UserService(){
        try {
            repository = new UserRepository();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getUsers(){
        List listUsers = null;
        try {
            listUsers = this.repository.find();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            return listUsers;
        }
    }

    public User upgrade(User userUpdate) {
        try {
            return (User)repository.upgrade(userUpdate);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
