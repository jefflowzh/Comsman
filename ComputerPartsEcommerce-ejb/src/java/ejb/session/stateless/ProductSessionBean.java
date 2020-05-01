package ejb.session.stateless;

import entity.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import util.exception.ProductNotFoundException;

@Stateless
public class ProductSessionBean implements ProductSessionBeanLocal {

    @PersistenceContext(unitName = "ComputerPartsEcommerce-ejbPU")
    private EntityManager em;
   
    @Override
    public Product retrieveProductById(Long productId) throws ProductNotFoundException {
        Product product = em.find(Product.class, productId);
        
        if (product != null) {
            return product;
        } else {
            throw new ProductNotFoundException("Product ID " + productId + " does not exist!");
        }
    }
    
    @Override
    public List<Product> retrieveAllProducts() {
        Query query = em.createQuery("SELECT p FROM Product p");
        
        return query.getResultList();
    }

}
