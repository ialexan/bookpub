package bookpub.model.dao;

import java.util.List;

import bookpub.model.Purchase;
import bookpub.model.User;

public interface PurchaseDao {

    Purchase getPurchase( Long id );

    List<Purchase> getPurchases( User user );

    Purchase savePurchase( Purchase purchase );

}
