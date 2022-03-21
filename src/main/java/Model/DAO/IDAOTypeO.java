package Model.DAO;


import data.TypeO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author noureddine
 */
public interface IDAOTypeO {
    void create(String Libelle);
    List<TypeO> retrieve();
    void update(TypeO c);
    boolean delete(TypeO c);
}
