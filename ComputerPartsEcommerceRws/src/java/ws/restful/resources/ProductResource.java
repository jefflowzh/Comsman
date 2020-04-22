/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.PeripheralSessionBeanLocal;
import ejb.session.stateless.ProductSessionBeanLocal;
import entity.Peripheral;
import entity.Product;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveAllPeripheralsRsp;
import ws.restful.model.RetrieveAllProductsRsp;

/**
 * REST Web Service
 *
 * @author jeffl
 */
@Path("Product")
public class ProductResource {

    PeripheralSessionBeanLocal peripheralSessionBeanLocal = lookupPeripheralSessionBeanLocal();

    ProductSessionBeanLocal productSessionBeanLocal = lookupProductSessionBeanLocal();
    

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductResource
     */
    public ProductResource() {
    }

//    @Path("retrieveAllProducts")
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response retrieveAllProducts() {        
//        try{
//            List<Product> products = productSessionBean.retrieveAllProducts();      
//            RetrieveAllProductsRsp retrieveAllProductsRsp = new RetrieveAllProductsRsp(products); 
//            return Response.status(Status.OK).entity(retrieveAllProductsRsp).build();
//        } catch (Exception ex) {
//            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
//            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
//        }
//    }
    
    @Path("retrieveAllPeripherals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPeripherals() {        
        try{
            List<Peripheral> peripherals = peripheralSessionBeanLocal.retrieveAllPeripherals();      
            RetrieveAllPeripheralsRsp retrieveAllPeripheralsRsp = new RetrieveAllPeripheralsRsp(peripherals); 
            return Response.status(Status.OK).entity(retrieveAllPeripheralsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    /**
     * PUT method for updating or creating an instance of ProductResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putXml(String content) {
    }

    private ProductSessionBeanLocal lookupProductSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ProductSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/ProductSessionBean!ejb.session.stateless.ProductSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private PeripheralSessionBeanLocal lookupPeripheralSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PeripheralSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/PeripheralSessionBean!ejb.session.stateless.PeripheralSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
