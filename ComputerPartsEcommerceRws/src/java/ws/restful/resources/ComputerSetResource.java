/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.ComputerSetSessionBeanLocal;
import ejb.session.stateless.CustomerSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.ComputerSet;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.PowerSupply;
import entity.RAM;
import entity.SSD;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.exception.ComputerPartNotFoundException;
import util.exception.CustomerNotFoundException;
import util.exception.IncompatiblePartException;
import ws.restful.model.CompatibilityCheckReq;
import ws.restful.model.CompatibilityCheckRsp;
import ws.restful.model.ErrorRsp;

/**
 * REST Web Service
 *
 * @author zeplh
 */
@Path("ComputerSet")
public class ComputerSetResource {
    
     CustomerSessionBeanLocal customerSessionBean = lookupCustomerSessionBeanLocal();
     ComputerSetSessionBeanLocal computerSetSessionBean = lookupComputerSetSessionBeanLocal();
     ComputerPartSessionBeanLocal computerPartSessionBean = lookupComputerPartSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ComputerSetResource
     */
    public ComputerSetResource() {
    }

//    @Path("CurrentComputerSetPartsList")
//    @GET
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response CurrentComputerSetPartsList(@QueryParam("email") String email) {
//        try {
//            Customer customer = customerSessionBean.retrieveCustomerByEmail(email, Boolean.FALSE, Boolean.TRUE);
//            List<ComputerPart> currentBuild =  customer.getCurrComputerBuild();
//            ComputerSet currentComputerSet = new ComputerSet();
//            if(currentBuild != null && currentBuild.isEmpty() == false){
//                for(ComputerPart currentComputerPart : currentBuild){
//                    if(currentComputerPart instanceof CPU){
//                        CPU currentPart = (CPU) currentComputerPart; 
//                        currentComputerSet.setCpu(currentPart);
//                    }
//                    else if(currentComputerPart instanceof MotherBoard){
//                        MotherBoard currentPart = (MotherBoard) currentComputerPart; 
//                        currentComputerSet.setMotherBoard(currentPart);
//                    }
//                    else if(currentComputerPart instanceof RAM){
//                        RAM currentPart = (RAM) currentComputerPart; 
//                        currentComputerSet.addRam(currentPart);
//                    }
//                    else if(currentComputerPart instanceof GPU){
//                        GPU currentPart = (GPU) currentComputerPart; 
//                        currentComputerSet.addGpu(currentPart);
//                    }
//                    else if(currentComputerPart instanceof HDD){
//                        HDD currentPart = (HDD) currentComputerPart; 
//                       currentComputerSet.addHdd(currentPart);
//                    }
//                    else if(currentComputerPart instanceof SSD){
//                        SSD currentPart = (SSD) currentComputerPart; 
//                       currentComputerSet.addSsd(currentPart);
//                    }
//                    else if(currentComputerPart instanceof PowerSupply){
//                        PowerSupply currentPart = (PowerSupply) currentComputerPart; 
//                        currentComputerSet.setPsu(currentPart);
//                    }
//                    else if(currentComputerPart instanceof ComputerCase){
//                        ComputerCase currentPart = (ComputerCase) currentComputerPart; 
//                        currentComputerSet.setCompCase(currentPart);
//                    }
//                    else if(currentComputerPart instanceof CPUAirCooler){
//                        CPUAirCooler currentPart = (CPUAirCooler) currentComputerPart; 
//                        currentComputerSet.setAirCooler(currentPart);
//                    }
//                    else if(currentComputerPart instanceof CPUWaterCooler){
//                        CPUWaterCooler currentPart = (CPUWaterCooler) currentComputerPart; 
//                        currentComputerSet.setWaterCooler(currentPart);
//                    }
//                }
//            }
//            CurrentComputerSetPartsListRsp currentCompletedComputerSet = new CurrentComputerSetPartsListRsp(currentComputerSet);
//            System.out.println("go");
//            return Response.status(Response.Status.OK).entity(currentCompletedComputerSet).build();
//        } catch (CustomerNotFoundException ex) {
//            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
//            return Response.status(Response.Status.UNAUTHORIZED).entity(errorRsp).build();
//        } catch (Exception ex) {
//            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
//        }
//    }
    
//    @Path("CurrentComputerSetPartsList")
//    @GET
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response CurrentComputerSetPartsList(@QueryParam("email") String email) {
//        try {
//            Customer customer = customerSessionBean.retrieveCustomerByEmail(email, Boolean.FALSE, Boolean.TRUE);
//            System.out.println("here");
//            List<ComputerPart> currentBuild =  customer.getCurrComputerBuild();
//            if(currentBuild.isEmpty() || currentBuild == null){
//                currentBuild = new ArrayList();
//            }
//            CurrentComputerSetPartsListRsp currentCompletedComputerSet = new CurrentComputerSetPartsListRsp(currentBuild);
//            System.out.println("go");
//            return Response.status(Response.Status.OK).entity(currentCompletedComputerSet).build();
//        } catch (CustomerNotFoundException ex) {
//            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
//            return Response.status(Response.Status.UNAUTHORIZED).entity(errorRsp).build();
//        } catch (Exception ex) {
//            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
//        }
//    }
    
    @Path("compatibilityCheck")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response CompatibilityCheck(CompatibilityCheckReq compatibilityCheckReq) {
        try {
            ComputerSet currentComputerSet = new ComputerSet();
            for(Long id : compatibilityCheckReq.getCurrentComputerBuildPartIds()){
                 ComputerPart currentComputerPart = computerPartSessionBean.retrieveComputerPartById(id);
                 if(currentComputerPart instanceof CPU){
                        CPU currentPart = (CPU) currentComputerPart; 
                        currentComputerSet.setCpu(currentPart);
                    }
                    else if(currentComputerPart instanceof MotherBoard){
                        MotherBoard currentPart = (MotherBoard) currentComputerPart; 
                        currentComputerSet.setMotherBoard(currentPart);
                    }
                    else if(currentComputerPart instanceof RAM){
                        RAM currentPart = (RAM) currentComputerPart; 
                        currentComputerSet.addRam(currentPart);
                    }
                    else if(currentComputerPart instanceof GPU){
                        GPU currentPart = (GPU) currentComputerPart; 
                        currentComputerSet.addGpu(currentPart);
                    }
                    else if(currentComputerPart instanceof HDD){
                        HDD currentPart = (HDD) currentComputerPart; 
                       currentComputerSet.addHdd(currentPart);
                    }
                    else if(currentComputerPart instanceof SSD){
                        SSD currentPart = (SSD) currentComputerPart; 
                       currentComputerSet.addSsd(currentPart);
                    }
                    else if(currentComputerPart instanceof PowerSupply){
                        PowerSupply currentPart = (PowerSupply) currentComputerPart; 
                        currentComputerSet.setPsu(currentPart);
                    }
                    else if(currentComputerPart instanceof ComputerCase){
                        ComputerCase currentPart = (ComputerCase) currentComputerPart; 
                        currentComputerSet.setCompCase(currentPart);
                    }
                    else if(currentComputerPart instanceof CPUAirCooler){
                        CPUAirCooler currentPart = (CPUAirCooler) currentComputerPart; 
                        currentComputerSet.setAirCooler(currentPart);
                    }
                    else if(currentComputerPart instanceof CPUWaterCooler){
                        CPUWaterCooler currentPart = (CPUWaterCooler) currentComputerPart; 
                        currentComputerSet.setWaterCooler(currentPart);
                    }
            }
            System.out.println("here");
            boolean compatible = computerSetSessionBean.compatibilityCheck(currentComputerSet, compatibilityCheckReq.getAddedPartId());
            CompatibilityCheckRsp compatibilityCheckRsp = new CompatibilityCheckRsp(compatible,"No error");
            System.out.println("go");
            return Response.status(Response.Status.OK).entity(compatibilityCheckRsp).build();
        } catch (IncompatiblePartException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.OK).entity(new CompatibilityCheckRsp(false, ex.getMessage().toString())).build();
        } catch (ComputerPartNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build(); 
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    //lookupProductSessionBeanLocal()
    private ComputerPartSessionBeanLocal lookupComputerPartSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (ComputerPartSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/ComputerPartSessionBean!ejb.session.stateless.ComputerPartSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
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
   
   
   private ComputerSetSessionBeanLocal lookupComputerSetSessionBeanLocal(){
        try {
            javax.naming.Context c = new InitialContext();
            return (ComputerSetSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/ComputerSetSessionBean!ejb.session.stateless.ComputerSetSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
