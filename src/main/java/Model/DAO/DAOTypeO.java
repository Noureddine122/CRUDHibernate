package Model.DAO;


import data.TypeO;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.UserTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author noureddine
 */
public class DAOTypeO implements IDAOTypeO {
    @PersistenceUnit(unitName = "default")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;

    public DAOTypeO(EntityManagerFactory entityManagerFactory, UserTransaction userTransaction) {
        this.entityManagerFactory = entityManagerFactory;
        this.userTransaction = userTransaction;
    }

    @Override
    public void create(String libelle) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            userTransaction.begin();
            em.joinTransaction();
            TypeO f = new TypeO();
            f.setLibelle(libelle);
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
    public List<TypeO> retrieve() {
        EntityManager em = entityManagerFactory.createEntityManager();
        String query = "Select c from TypeO c ";
        TypedQuery<TypeO> tq = em.createQuery(query,TypeO.class);
        try{
            return tq.getResultList();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(TypeO c) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            userTransaction.begin();
            em.joinTransaction();
            TypeO contat = em.find(TypeO.class,c.getIdType());
            contat.setLibelle(c.getLibelle());
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
    public boolean delete(TypeO c) {
        EntityManager em = entityManagerFactory.createEntityManager();
        try{
            userTransaction.begin();
            em.joinTransaction();
            TypeO contat = em.find(TypeO.class,c.getIdType());
            em.remove(contat);
            em.persist(contat);
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
