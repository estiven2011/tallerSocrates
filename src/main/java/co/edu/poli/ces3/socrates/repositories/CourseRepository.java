package co.edu.poli.ces3.socrates.repositories;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.dao.Course;
import co.edu.poli.ces3.socrates.interfaces.ICrud;

import java.sql.SQLException;
import java.util.List;

public class CourseRepository extends MysqlConnection implements ICrud {
    public CourseRepository() throws Exception {
    }

    @Override
    public void disconnect() {

    }

    @Override
    public int insert() {
        return 0;
    }

    @Override
    public List<?> find() throws SQLException {
        return null;
    }

    @Override
    public Object findById(int id) {
        return null;
    }

    @Override
    public double delete(int id) {
        return 0;
    }

    @Override
    public int update() {
        return 0;
    }
}
