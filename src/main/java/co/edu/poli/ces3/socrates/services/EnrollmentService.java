package co.edu.poli.ces3.socrates.services;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Enrollment;
import co.edu.poli.ces3.socrates.repositories.EnrollmentRepository;

import java.sql.SQLException;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository repository;

    public EnrollmentService(MysqlConnection connection) {
        this.repository = new EnrollmentRepository(connection);
    }

    public List<Enrollment> getAllEnrollments() throws SQLException {
        return repository.findAll();
    }

    public Enrollment getEnrollmentById(int id) throws SQLException {
        return repository.findById(id);
    }

    public void createEnrollment(Enrollment enrollment) throws SQLException {
        repository.insert(enrollment);
    }

    public boolean updateEnrollment(Enrollment enrollment) throws SQLException {
        return repository.update(enrollment);
    }

    public boolean deleteEnrollment(int id) throws SQLException {
        return repository.delete(id);
    }
}
