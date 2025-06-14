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

    public Subject findById(int id) throws SQLException {
        String sql = "SELECT * FROM subjects WHERE id = ?";
        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Subject(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getString("description"),
                    rs.getInt("credits"),
                    rs.getString("faculty")
            );
        }
        return null;
    }

    public void insert(Subject subject) throws SQLException {
        String sql = "INSERT INTO subjects (code, name, description, credits, faculty) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
        stmt.setString(1, subject.getCode());
        stmt.setString(2, subject.getName());
        stmt.setString(3, subject.getDescription());
        stmt.setInt(4, subject.getCredits());
        stmt.setString(5, subject.getFaculty());
        stmt.executeUpdate();
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM subjects WHERE id = ?";
        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
    }

    public boolean update(Subject subject) throws SQLException {
        String sql = "UPDATE subjects SET code = ?, name = ?, description = ?, credits = ?, faculty = ? WHERE id = ?";
        PreparedStatement stmt = connection.getConnection().prepareStatement(sql);
        stmt.setString(1, subject.getCode());
        stmt.setString(2, subject.getName());
        stmt.setString(3, subject.getDescription());
        stmt.setInt(4, subject.getCredits());
        stmt.setString(5, subject.getFaculty());
        stmt.setInt(6, subject.getId());
        return stmt.executeUpdate() > 0;
    }
}
