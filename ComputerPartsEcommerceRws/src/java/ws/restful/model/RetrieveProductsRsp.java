package ws.restful.model;

import entity.Product;
import java.util.List;

public class RetrieveAllProductsRsp {
    
    private List<Product> products;

    public RetrieveAllProductsRsp() {
    }

    public RetrieveAllProductsRsp(List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
  
    
}
