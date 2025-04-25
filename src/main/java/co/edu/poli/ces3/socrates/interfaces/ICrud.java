package co.edu.poli.ces3.socrates.interfaces;

import java.util.List;

public interface ICrud {
    int insert();
    List<Object> select();
    double delete(int id);
    int update();
}
