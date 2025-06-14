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
}
