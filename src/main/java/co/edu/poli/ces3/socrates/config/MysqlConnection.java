package co.edu.poli.ces3.socrates.config;

import co.edu.poli.ces3.socrates.dao.User;
import co.edu.poli.ces3.socrates.utils.annotations.Column;
import co.edu.poli.ces3.socrates.utils.annotations.Table;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public abstract class MysqlConnection {

    private String url;
    private String user;
    private String password;
    private String dataBase;
    private String host;
    private String port;
    private Connection connection;


    public MysqlConnection() throws Exception{
        this.user = "ces3";
        this.password = "ces32025";
        this.dataBase = "socrates";
        this.host = "127.0.0.1";
        this.port = "3306";
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + dataBase;

        if(!this.connect())
            throw new Exception("Error estableciendo la conexion");

    }

    public abstract void disconnect();

    public boolean connect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(this.url,this.user, this.password);

            System.out.println("Conexion Exitosa!!!");

            return true;
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public List getAllUsers(){

        User u = new User("Andres", "Berrio");
        u.setEmail("andres@gmail.com");
        System.out.println(u);
        return null;
    }

    public String getUrl() {
        return url;
    }

    public Connection getConnection() {
        return connection;
    }

    public QueryResult getQueryUpdateAndParams(Object data, Class<?> clazz){
        boolean hasFieldsToUpdate = false;
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        LinkedList<Object> valuesFieldsToUpdate = new LinkedList<>();
        LinkedList<Object> valuesFieldsPrimaryKey = new LinkedList<>();
        if (tableAnnotation != null) {
            StringBuilder sql = new StringBuilder("UPDATE " + tableAnnotation.name() + " SET ");
            StringBuilder sqlWhere = new StringBuilder(" WHERE ");
            try {
                for (Field field : clazz.getDeclaredFields()) {
                    Column column = field.getAnnotation(Column.class);
                    field.setAccessible(true);
                    if(column != null && field.get(data) != null) {
                        if (!column.primaryKey()) {
                            sql.append(column.name()).append(" = ?,");
                            valuesFieldsToUpdate.add(field.get(data));
                            hasFieldsToUpdate = true;
                        } else {
                            sqlWhere.append(field.getName()).append(" = ?,");
                            valuesFieldsPrimaryKey.add(field.get(data));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

            if(hasFieldsToUpdate) {
                sql.deleteCharAt(sql.length()-1);
                sqlWhere.deleteCharAt(sqlWhere.length()-1);

                sql.append(sqlWhere);

                valuesFieldsToUpdate.addAll(valuesFieldsPrimaryKey);

                return new QueryResult(sql.toString(), valuesFieldsToUpdate);
            }
            return null;
        } else {
            throw new IllegalStateException("Class " + clazz.getSimpleName() + " is missing @Table annotation");
        }
    }
}

