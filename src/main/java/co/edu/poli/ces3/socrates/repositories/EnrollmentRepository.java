package co.edu.poli.ces3.socrates.repositories;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Enrollment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {

    private final MysqlConnection connection;

    public EnrollmentRepository(MysqlConnection connection) {
        this.connection = connection;
    }

    public List<Enrollment> findAll() throws SQLException {
        List<Enrollment> list = new ArrayList<>();
        String query = "SELECT id, student_id, subject_id, semester, status FROM enrollments";
        Statement stmt = connection.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            list.add(new Enrollment(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getInt("subject_id"),
                    rs.getString("semester"),
                    rs.getString("status")
            ));
        }
        return list;
    }

    public Enrollment findById(int id) throws SQLException {
        String query = "SELECT * FROM enrollments WHERE id = ?";
        PreparedStatement stmt = connection.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return new Enrollment(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getInt("subject_id"),
                    rs.getString("semester"),
                    rs.getString("status")
            );
        }
        return null;
    }

    public void insert(Enrollment e) throws SQLException {
        String query = "INSERT INTO enrollments (student_id, subject_id, semester, status) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = connection.getConnection().prepareStatement(query);
        stmt.setInt(1, e.getStudentId());
        stmt.setInt(2, e.getSubjectId());
        stmt.setString(3, e.getSemester());
        stmt.setString(4, e.getStatus());
        stmt.executeUpdate();
    }

    public boolean update(Enrollment e) throws SQLException {
        String query = "UPDATE enrollments SET student_id = ?, subject_id = ?, semester = ?, status = ? WHERE id = ?";
        PreparedStatement stmt = connection.getConnection().prepareStatement(query);
        stmt.setInt(1, e.getStudentId());
        stmt.setInt(2, e.getSubjectId());
        stmt.setString(3, e.getSemester());
        stmt.setString(4, e.getStatus());
        stmt.setInt(5, e.getId());
        return stmt.executeUpdate() > 0;
    }

    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM enrollments WHERE id = ?";
        PreparedStatement stmt = connection.getConnection().prepareStatement(query);
        stmt.setInt(1, id);
        return stmt.executeUpdate() > 0;
    }
}
