package Model.DAO;


import data.Offre;
import data.TypeO;

import java.util.ArrayList;
import java.util.List;

public interface IDAOOffre {
    void create(String profle, String Description, TypeO t);
    List<Offre> retrieve();
    void update(Offre c);
    boolean delete(int id);
}
