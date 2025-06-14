package co.edu.poli.ces3.socrates.services;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Subject;
import co.edu.poli.ces3.socrates.repositories.SubjectRepository;

import java.sql.SQLException;
import java.util.List;

public class SubjectService {

    private final SubjectRepository repository;

    public SubjectService(MysqlConnection connection) {
        this.repository = new SubjectRepository(connection);
    }

    public List<Subject> getAllSubjects() throws SQLException {
        return repository.findAll();
    }

    public Subject getSubjectById(int id) throws SQLException {
        return repository.findById(id);
    }

    public void createSubject(Subject subject) throws SQLException {
        repository.insert(subject);
    }

    public boolean deleteSubject(int id) throws SQLException {
        return repository.delete(id);
    }

    public boolean updateSubject(Subject subject) throws SQLException {
        return repository.update(subject);
    }
}
