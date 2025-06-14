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
    private String database;
    private String host;
    private String port;
    private Connection connection;


    public MysqlConnection() throws Exception{
        this.user = "root";
        this.password = "";
        this.database = "socrates";
        this.host =  "127.0.0.1";
        this.port = "3306";
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + database;

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

    /**
     * sql: UPDATE nombre_tabla SET campo1 = ?, campo2 = ?, campo3 = ?, campoN = ? WHERE id = ?
     *
     * params: [valor1, valor2, valor3, valorN, valorId]
     */
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
                            sqlWhere.append(column.name()).append(" = ? AND ");
                            valuesFieldsPrimaryKey.add(field.get(data));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                System.out.println(e.getMessage());
            }

            if(hasFieldsToUpdate) {
                sql.deleteCharAt(sql.length()-1);
                sqlWhere.delete(sqlWhere.length()-5, sqlWhere.length());

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

