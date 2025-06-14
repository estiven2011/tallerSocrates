package co.edu.poli.ces3.socrates.repositories;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectRepository {

    private final MysqlConnection connection;

    public SubjectRepository(MysqlConnection connection) {
        this.connection = connection;
    }

    public List<Subject> findAll() throws SQLException {
        List<Subject> subjects = new ArrayList<>();
        String sql = "SELECT id, code, name, description, credits, faculty FROM subjects";
        Statement stmt = connection.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            subjects.add(new Subject(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("credits"),
                    rs.getString("faculty")
            ));
        }
        return subjects;
    }
}
