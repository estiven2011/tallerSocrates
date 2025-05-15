package co.edu.poli.ces3.socrates.services;

import co.edu.poli.ces3.socrates.dao.User;
import co.edu.poli.ces3.socrates.repositories.UserRepository;

import java.sql.SQLException;
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
}
