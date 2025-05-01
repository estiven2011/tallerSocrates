package co.edu.poli.ces3.socrates.interfaces;

import java.util.List;

public interface ICrud {
    int insert();
    List<?> select();
    double delete(int id);
    int update();
}
