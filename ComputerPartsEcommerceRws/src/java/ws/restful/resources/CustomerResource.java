/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.CustomerOrderSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import ejb.session.stateless.LineItemSessionBeanLocal;
import ejb.session.stateless.ProductSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.ComputerSet;
import entity.Customer;
import entity.CustomerOrder;
import entity.LineItem;
import entity.GPU;
import entity.HDD;
import entity.LineItem;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.Product;
import entity.RAM;
import entity.SSD;
import java.util.ArrayList;
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
import ws.restful.model.CurrentComputerSetPartsListRsp;
import ws.restful.model.ErrorRsp;
import ws.restful.model.CustomerLoginRsp;
import ws.restful.model.CustomerOrderRsp;
import ws.restful.model.CustomerOrdersRsp;
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

    ComputerPartSessionBeanLocal computerPartSessionBean = lookupComputerPartSessionBeanLocal();

    ProductSessionBeanLocal productSessionBean = lookupProductSessionBeanLocal();

    LineItemSessionBeanLocal lineItemSessionBean = lookupLineItemSessionBeanLocal();

    CustomerSessionBeanLocal customerSessionBean = lookupCustomerSessionBeanLocal();
    CustomerOrderSessionBeanLocal customerOrderSessionBean = lookupCustomerOrderSessionBeanLocal();
    
    

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
            customer.getOrders().clear();
            List<ComputerPart> currentBuild =  customer.getCurrComputerBuild();
            ComputerSet currComputerBuild = new ComputerSet();
            
            
//            if(currentBuild.isEmpty() == false){
//                for(ComputerPart currentComputerPart : currentBuild){
//                    if(currentComputerPart instanceof CPU){
//                        CPU currentPart = (CPU) currentComputerPart; 
//                        currComputerBuild.setCpu(currentPart);
//                    }
//                    else if(currentComputerPart instanceof MotherBoard){
//                        MotherBoard currentPart = (MotherBoard) currentComputerPart; 
//                        currComputerBuild.setMotherBoard(currentPart);
//                    }
//                    else if(currentComputerPart instanceof RAM){
//                        RAM currentPart = (RAM) currentComputerPart; 
//                        currComputerBuild.addRam(currentPart);
//                    }
//                    else if(currentComputerPart instanceof GPU){
//                        GPU currentPart = (GPU) currentComputerPart; 
//                        currComputerBuild.addGpu(currentPart);
//                    }
//                    else if(currentComputerPart instanceof HDD){
//                        HDD currentPart = (HDD) currentComputerPart; 
//                       currComputerBuild.addHdd(currentPart);
//                    }
//                    else if(currentComputerPart instanceof SSD){
//                        SSD currentPart = (SSD) currentComputerPart; 
//                       currComputerBuild.addSsd(currentPart);
//                    }
//                    else if(currentComputerPart instanceof PowerSupply){
//                        PowerSupply currentPart = (PowerSupply) currentComputerPart; 
//                        currComputerBuild.setPsu(currentPart);
//                    }
//                    else if(currentComputerPart instanceof ComputerCase){
//                        ComputerCase currentPart = (ComputerCase) currentComputerPart; 
//                        currComputerBuild.setCompCase(currentPart);
//                    }
//                    else if(currentComputerPart instanceof CPUAirCooler){
//                        CPUAirCooler currentPart = (CPUAirCooler) currentComputerPart; 
//                        currComputerBuild.setAirCooler(currentPart);
//                    }
//                    else if(currentComputerPart instanceof CPUWaterCooler){
//                        CPUWaterCooler currentPart = (CPUWaterCooler) currentComputerPart; 
//                        currComputerBuild.setWaterCooler(currentPart);
//                    }
//                }
//            }

            CustomerLoginRsp customerLoginRsp = new CustomerLoginRsp(customer);//, currComputerBuild);
            return Response.status(Status.OK).entity(customerLoginRsp).build();
        } catch (InvalidLoginCredentialException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.UNAUTHORIZED).entity(errorRsp).build();
        }catch (StackOverflowError ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
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
            Customer customerToUpdate = customerSessionBean.retrieveCustomerById(updateCustomerReq.getUserId(), false, false);

            customerToUpdate.setFirstName(updateCustomerReq.getFirstName());
            customerToUpdate.setLastName(updateCustomerReq.getLastName());
            customerToUpdate.setAddress(updateCustomerReq.getAddress());
            customerToUpdate.setEmail(updateCustomerReq.getEmail());
            customerToUpdate.setContactNumber(updateCustomerReq.getContactNumber());
            customerToUpdate.setCardNumber(updateCustomerReq.getCardNumber());
            customerToUpdate.setCcv(updateCustomerReq.getCcv());

            customerSessionBean.updateCustomerMerge(customerToUpdate);
            
            Customer updatedCustomer = customerSessionBean.retrieveCustomerById(updateCustomerReq.getUserId(), true, true);
            UpdateCustomerRsp updateCustomerRsp =  new UpdateCustomerRsp(updatedCustomer);
            return Response.status(Status.OK).entity(updateCustomerRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("updateCustomerPassword")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomerPassword(UpdateCustomerReq updateCustomerReq) {
        try {
            Customer customerToUpdate = customerSessionBean.retrieveCustomerById(updateCustomerReq.getUserId(), false, false);
            customerToUpdate.setPassword(updateCustomerReq.getPassword());
            customerSessionBean.updateCustomerMerge(customerToUpdate);
            
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
            
            // Create new cart from all the ids
            
            List<LineItem> newCart = new ArrayList<>();
            
            // update cart products
            if (updateCustomerReq.getCartProductIds().size() > 0) {
                for (int i = 0; i < updateCustomerReq.getCartProductIds().size(); i++) {
                    Product product = productSessionBean.retrieveProductById(updateCustomerReq.getCartProductIds().get(i));
                    LineItem newLineItem = new LineItem(product, updateCustomerReq.getCartProductQuantities().get(i));
                    lineItemSessionBean.createNewLineItem(newLineItem);
                    newCart.add(newLineItem);
                }
            }
            
            // update cart computer sets
            if (updateCustomerReq.getCartComputerSetsPartIds().size() > 0) {
                // for each list of part ids sent back
                for (int i = 0; i < updateCustomerReq.getCartComputerSetsPartIds().size(); i++) {
                    ComputerSet newComputerSet = new ComputerSet();
                    
                    // for each partId, retrieve part and put them in a computer set
                    for (int j = 0; j < updateCustomerReq.getCartComputerSetsPartIds().get(i).size(); j++) {
                        Long partId = updateCustomerReq.getCartComputerSetsPartIds().get(i).get(j);
                        ComputerPart computerPart = computerPartSessionBean.retrieveComputerPartById(partId);
                        if (computerPart instanceof CPU) {
                            newComputerSet.setCpu((CPU) computerPart);
                        } else if (computerPart instanceof CPUAirCooler) {
                            newComputerSet.setAirCooler((CPUAirCooler) computerPart);
                        } else if (computerPart instanceof CPUWaterCooler) {
                            newComputerSet.setWaterCooler((CPUWaterCooler) computerPart);
                        } else if (computerPart instanceof ComputerCase) {
                            newComputerSet.setCompCase((ComputerCase) computerPart);
                        } else if (computerPart instanceof GPU) {
                            newComputerSet.getGpus().add((GPU) computerPart);
                        } else if (computerPart instanceof HDD) {
                            newComputerSet.getHdds().add((HDD) computerPart);
                        } else if (computerPart instanceof MotherBoard) {
                            newComputerSet.setMotherBoard((MotherBoard) computerPart);
                        } else if (computerPart instanceof PowerSupply) {
                            newComputerSet.setPsu((PowerSupply) computerPart);
                        } else if (computerPart instanceof RAM) {
                            newComputerSet.getRams().add((RAM) computerPart);
                        } else if (computerPart instanceof SSD) {
                            newComputerSet.getSsds().add((SSD) computerPart);
                        }
                    }
                    
                    LineItem newLineItem = new LineItem(newComputerSet, updateCustomerReq.getCartComputerSetsQuantities().get(i));
                    lineItemSessionBean.createNewLineItem(newLineItem);
                    newCart.add(newLineItem);
                }
            }
            
            Customer customerToUpdate = customerSessionBean.retrieveCustomerById(updateCustomerReq.getUserId(), false, false);
            customerToUpdate.setCart(newCart);
            
            customerSessionBean.updateCustomerMerge(customerToUpdate);
            return Response.status(Status.OK).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("CurrentComputerSetPartsList")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CurrentComputerSetPartsList(@QueryParam("email") String email) {
        try {
            Customer customer = customerSessionBean.retrieveCustomerByEmail(email, Boolean.FALSE, Boolean.TRUE);
            System.out.println("here");
            List<ComputerPart> currentBuild =  customer.getCurrComputerBuild();
            if(currentBuild == null){
                currentBuild = new ArrayList();
            }
            CurrentComputerSetPartsListRsp currentCompletedComputerSet = new CurrentComputerSetPartsListRsp(currentBuild);
            System.out.println("go");
            return Response.status(Response.Status.OK).entity(currentCompletedComputerSet).build();
        } catch (CustomerNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(errorRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
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
    
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response customerUpdate(UpdateCustomerReq customerUpdateReq){
//        try{
//            customerSessionBean.updateCustomer(customerUpdateReq.getCustomer(), true, false, false, false);
//            Customer updatedCustomer = this.customerSessionBean.retrieveCustomerById(customerUpdateReq.getCustomer().getUserId(), true , true);
//            UpdateCustomerRsp cur =  new UpdateCustomerRsp(updatedCustomer);
//            return Response.status(Status.OK).entity(cur).build();
//        }
//        catch(Exception ex){
//            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
//            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
//        }
//    }

    private CustomerSessionBeanLocal lookupCustomerSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CustomerSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/CustomerSessionBean!ejb.session.stateless.CustomerSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private LineItemSessionBeanLocal lookupLineItemSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (LineItemSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/LineItemSessionBean!ejb.session.stateless.LineItemSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    @Path("customerOrders")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerOrders(@QueryParam("email") String email) {
        try {
            Customer customer = customerSessionBean.retrieveCustomerByEmail(email, Boolean.FALSE, Boolean.TRUE);
            List<CustomerOrder> orders = customer.getOrders();
            for(CustomerOrder order : orders){
                  order.setCustomer(null);
                  
            }
            CustomerOrdersRsp customerOrdersRsp = new CustomerOrdersRsp(orders);
            System.out.println("go");
            return Response.status(Status.OK).entity(customerOrdersRsp).build();
        } catch (CustomerNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.UNAUTHORIZED).entity(errorRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveOrderById")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerOrder(@QueryParam("CustomerOrderId") String CustomerOrderId) {
        try {
            CustomerOrder customerOrder = customerOrderSessionBean.retrieveCustomerOrderById(Long.parseLong(CustomerOrderId), true);
            List<LineItem> item = customerOrder.getLineItems();
            
            CustomerOrderRsp customerOrderRsp = new CustomerOrderRsp(item);
            System.out.println("go");
            return Response.status(Status.OK).entity(customerOrderRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
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

    private ComputerPartSessionBeanLocal lookupComputerPartSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ComputerPartSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/ComputerPartSessionBean!ejb.session.stateless.ComputerPartSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    private CustomerOrderSessionBeanLocal lookupCustomerOrderSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CustomerOrderSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/CustomerOrderSessionBean!ejb.session.stateless.CustomerOrderSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
