package co.edu.poli.ces3.socrates.repositories;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.config.QueryResult;
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
    public Object findById(int id) throws SQLException {
        PreparedStatement stm = this.getConnection()
                .prepareStatement("SELECT * FROM users WHERE id = ?");
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();

        if(rs.next()){
            return new User(
                            rs.getInt("id"),
                            rs.getString("names"),
                            rs.getString("lastName"),
                            rs.getString("password"),
                            rs.getDate("birthdate"),
                            rs.getString("email"),
                            rs.getBoolean("is_active"),
                            rs.getString("phone"),
                            rs.getString("gender"),
                            rs.getDate("created_at"),
                            rs.getDate("updated_at"),
                            rs.getDate("deleted_at")
                    );
        }
        return null;
    }

    @Override
    public List<User> find() throws SQLException
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
                            rs.getDate("birthdate"),
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

    @Override
    public Object upgrade(Object userUpdate) throws SQLException {
        QueryResult queryResult = getQueryUpdateAndParams(userUpdate, User.class);

        if(queryResult != null){
            PreparedStatement stm = this.getConnection()
                    .prepareStatement(queryResult.getSql());
            int i = 1;
            for(Object value: queryResult.getParameters()){
                stm.setObject(i++,value);
            }

            if(stm.executeUpdate() > 0){
                return this.findById(((User)userUpdate).getId());
            }
        }
        return null;
    }

    public static void main(String[] args) {
        UserRepository repo = null;
        try {
            repo = new UserRepository();

            for (User x: repo.find()) {
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
