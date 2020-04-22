package ejb.session.stateless;

import entity.Product;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ProductSessionBeanLocal {

    public List<Product> retrieveAllProducts();
    
}
