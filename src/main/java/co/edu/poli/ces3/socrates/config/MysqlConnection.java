package co.edu.poli.ces3.socrates.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class MysqlConnection {

    private String url;
    private String user;
    private String password;
    private String dataBase;
    private String host;
    private String port;
    private Connection connection;


    public MysqlConnection(){
        this.user = "ces3";
        this.password = "ces32025";
        this.dataBase = "socrates";
        this.host = "127.0.0.1";
        this.port = "3306";
        this.url = "jdbc:mysql://" + host + ":" + port + "/" + dataBase;
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

}
