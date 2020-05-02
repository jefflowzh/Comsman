/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.CustomerSessionBeanLocal;
import entity.Customer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.CustomerEmailExistException;
import util.exception.CustomerNotFoundException;
import util.exception.InvalidLoginCredentialException;
import ws.restful.model.ErrorRsp;
import ws.restful.model.CustomerLoginRsp;
import ws.restful.model.CustomerRegistrationReq;
import ws.restful.model.CustomerRegistrationRsp;
import ws.restful.model.UpdateCustomerReq;
import ws.restful.model.UpdateCustomerRsp;

/**
 * REST Web Service
 *
 * @author jeffl
 */
@Path("Customer")
public class CustomerResource {

    CustomerSessionBeanLocal customerSessionBean = lookupCustomerSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CustomerResource
     */
    public CustomerResource() {
    }

    // http://localhost:8080/ComputerPartsEcommerceRws/Resources/Customer/customerLogin?email=customer@gmail.com&password=password
    @Path("customerLogin")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerLogin(@QueryParam("email") String email, @QueryParam("password") String password) {
        try {
            Customer customer = customerSessionBean.customerLogin(email, password);
            CustomerLoginRsp customerLoginRsp = new CustomerLoginRsp(customer);
            return Response.status(Status.OK).entity(customerLoginRsp).build();
        } catch (InvalidLoginCredentialException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.UNAUTHORIZED).entity(errorRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @Path("customerRegistration")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerRegistration(CustomerRegistrationReq customerRegistrationReq) {
        try {
            Long newCustomerId = customerSessionBean.createNewCustomer(customerRegistrationReq.getNewCustomer());
            CustomerRegistrationRsp customerRegistrationRsp = new CustomerRegistrationRsp(newCustomerId);
            return Response.status(Status.OK).entity(customerRegistrationRsp).build();
        } catch (CustomerEmailExistException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("updateCustomerDetails")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerDetails(UpdateCustomerReq updateCustomerReq) {
        try {
            customerSessionBean.updateCustomer(updateCustomerReq.getCustomer(), true, false, false, false);
            return Response.status(Status.OK).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("updateCustomerCart")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerCart(UpdateCustomerReq updateCustomerReq) {
        try {
            customerSessionBean.updateCustomer(updateCustomerReq.getCustomer(), false, false, true, false);
            return Response.status(Status.OK).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
//    @Path("updateCustomerCurrComputerBuild")
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response updateCustomerCurrComputerBuild(UpdateCustomerReq updateCustomerReq) {
//        try {
//            customerSessionBean.updateCustomer(updateCustomerReq.getCustomer(), false, false, false, true);
//            return Response.status(Status.OK).build();
//        } catch (Exception ex) {
//            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
//            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
//        }
//    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerUpdate(UpdateCustomerReq customerUpdateReq){
        try{
            customerSessionBean.updateCustomer(customerUpdateReq.getCustomer(), true, false, false, false);
            Customer updatedCustomer = this.customerSessionBean.retrieveCustomerById(customerUpdateReq.getCustomer().getUserId(), true , true);
            UpdateCustomerRsp cur =  new UpdateCustomerRsp(updatedCustomer);
            return Response.status(Status.OK).entity(cur).build();
        }
        catch(Exception ex){
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    private CustomerSessionBeanLocal lookupCustomerSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CustomerSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/CustomerSessionBean!ejb.session.stateless.CustomerSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
