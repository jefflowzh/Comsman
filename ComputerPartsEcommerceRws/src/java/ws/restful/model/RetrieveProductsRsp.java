package ws.restful.model;

import entity.Product;
import java.util.List;

public class RetrieveProductsRsp {
    
    private List<? extends Product> products;

    public RetrieveProductsRsp() {
    }

    public RetrieveProductsRsp(List<? extends Product> products) {
        this.products = products;
    }

    public List<? extends Product> getProducts() {
        return products;
    }

    public void setProducts(List<? extends Product> products) {
        this.products = products;
    }
    
}