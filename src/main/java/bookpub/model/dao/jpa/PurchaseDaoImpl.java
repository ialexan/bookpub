package bookpub.model.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bookpub.model.Purchase;
import bookpub.model.User;
import bookpub.model.dao.PurchaseDao;

@Repository
public class PurchaseDaoImpl implements PurchaseDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Purchase getPurchase( Long id )
    {
        return entityManager.find( Purchase.class, id );
    }

    @Override
    public List<Purchase> getPurchases( User user )
    {
        String query = "from Purchase where user = :user order by date desc";

        return entityManager.createQuery( query, Purchase.class )
            .setParameter( "user", user )
            .getResultList();
    }

    @Override
    @Transactional
    public Purchase savePurchase( Purchase purchase )
    {
        return entityManager.merge( purchase );
    }

}
