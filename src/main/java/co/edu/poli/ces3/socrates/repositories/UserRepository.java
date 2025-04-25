package co.edu.poli.ces3.socrates.repositories;

import co.edu.poli.ces3.socrates.config.MysqlConnection;
import co.edu.poli.ces3.socrates.interfaces.ICrud;

import java.util.List;

public class UserRepository extends MysqlConnection implements ICrud {
    @Override
    public void disconnect() {

    }

    @Override
    public int insert() {
        return 0;
    }

    @Override
    public List<Object> select() {
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
