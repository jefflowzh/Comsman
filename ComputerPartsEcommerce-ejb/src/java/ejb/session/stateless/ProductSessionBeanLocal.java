package ejb.session.stateless;

import entity.Product;
import java.util.List;
import javax.ejb.Local;
import util.exception.ProductNotFoundException;

@Local
public interface ProductSessionBeanLocal {

    public List<Product> retrieveAllProducts();

    public Product retrieveProductById(Long productId) throws ProductNotFoundException;
    
}
