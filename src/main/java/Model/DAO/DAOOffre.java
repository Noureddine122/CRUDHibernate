package Model.DAO;


import data.Offre;
import data.TypeO;
import jakarta.annotation.Resource;
import jakarta.persistence.*;
import jakarta.transaction.UserTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author noureddine
 */
public class DAOOffre implements IDAOOffre {
    @PersistenceUnit(unitName = "default")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;


    public DAOOffre(EntityManagerFactory entityManagerFactory, UserTransaction userTransaction) {
        this.entityManagerFactory = entityManagerFactory;
        this.userTransaction = userTransaction;
    }

    @Override
    public void create(String profile, String description, TypeO t) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            userTransaction.begin();
            em.joinTransaction();
            Offre f = new Offre();
            f.setProfile(profile);
            f.setDescription(description);
            f.setTypeOByIdType(t);
            em.persist(f);
            em.flush();
            userTransaction.commit();

        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
    }

    @Override
    public List<Offre> retrieve() {
        EntityManager em = entityManagerFactory.createEntityManager();
        String query = "Select c from Offre c ";
        TypedQuery<Offre> tq = em.createQuery(query,Offre.class);
        try{
            return tq.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(Offre c) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction et = null;
        Offre contat = null;
        try{
            userTransaction.begin();
            em.joinTransaction();
            contat = em.find(Offre.class,c.getIdOffre());
            contat.setProfile(c.getProfile());
            contat.setDescription(c.getDescription());
            contat.setTypeOByIdType(c.getTypeOByIdType());
            em.persist(contat);
            em.flush();
            userTransaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            em.close();
        }
    }

    @Override
    public boolean delete(int id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            userTransaction.begin();
            em.joinTransaction();
            Offre contat =em.find(Offre.class,id);
            em.remove(contat);
            //em.persist(contat);
            em.flush();
            userTransaction.commit();
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally{
            em.close();
        }
    }
}
