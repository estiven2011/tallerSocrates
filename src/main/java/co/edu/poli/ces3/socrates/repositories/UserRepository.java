package co.edu.poli.ces3.socrates.repositories;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.User;
import co.edu.poli.ces3.socrates.interfaces.ICrud;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends MysqlConnection implements ICrud {

    public UserRepository() throws Exception {
    }

    @Override
    public void disconnect() {

    }

    @Override
    public int insert() {
        return 0;
    }

    @Override
    public List<User> select() throws SQLException
    {
        PreparedStatement stm = this.getConnection()
                                    .prepareStatement("SELECT * FROM users");
        ResultSet rs = stm.executeQuery();

        List<User> listUsers = new ArrayList();

        while(rs.next()){
            listUsers.add(
                    new User(
                            rs.getInt("id"),
                            rs.getString("names"),
                            rs.getString("lastName"),
                            rs.getString("password"),
                            rs.getDate("birthday"),
                            rs.getString("email"),
                            rs.getBoolean("is_active"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getDate("created_at"),
                            rs.getDate("updated_at"),
                            rs.getDate("deleted_at")
                    )
            );
        }

        return listUsers;
    }

    @Override
    public double delete(int id) {
        return 0;
    }

    @Override
    public int update() {
        return 0;
    }

    public static void main(String[] args) {
        UserRepository repo = null;
        try {
            repo = new UserRepository();

            for (User x: repo.select()) {
                System.out.println("***********");
                System.out.println(
                        "Nombre:" + x.getNames()
                );
                System.out.println("***********");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(
                repo.getUrl()
        );
    }
}
