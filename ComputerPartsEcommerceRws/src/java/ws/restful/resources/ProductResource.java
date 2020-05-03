/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.ComputerPartSessionBeanLocal;
import ejb.session.stateless.PeripheralSessionBeanLocal;
import ejb.session.stateless.PreBuiltComputerSetModelSessionBeanLocal;
import ejb.session.stateless.ProductSessionBeanLocal;
import entity.CPU;
import entity.CPUAirCooler;
import entity.CPUWaterCooler;
import entity.ComputerCase;
import entity.ComputerPart;
import entity.GPU;
import entity.HDD;
import entity.MotherBoard;
import entity.Peripheral;
import entity.PowerSupply;
import entity.PreBuiltComputerSetModel;
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
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.ProductNotFoundException;
import ws.restful.model.ErrorRsp;
import ws.restful.model.RetrieveIndividualProductRsp;
import ws.restful.model.RetrievePreBuiltComputerModelsRsp;
import ws.restful.model.RetrieveProductsRsp;

/**
 * REST Web Service
 *
 * @author jeffl
 */
@Path("Product")
public class ProductResource {

    PreBuiltComputerSetModelSessionBeanLocal preBuiltComputerSetModelSessionBeanLocal = lookupPreBuiltComputerSetModelSessionBeanLocal();

    ComputerPartSessionBeanLocal computerPartSessionBeanLocal = lookupComputerPartSessionBeanLocal();

    PeripheralSessionBeanLocal peripheralSessionBeanLocal = lookupPeripheralSessionBeanLocal();

    ProductSessionBeanLocal productSessionBeanLocal = lookupProductSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProductResource
     */
    public ProductResource() {
    }

    @Path("retrieveProductById")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveProductById(@QueryParam("productId") String productId) {
        try {
            Product product = productSessionBeanLocal.retrieveProductById(Long.parseLong(productId)); 
            RetrieveIndividualProductRsp retrieveProductByIdRsp = new RetrieveIndividualProductRsp(product);
            return Response.status(Status.OK).entity(retrieveProductByIdRsp).build();
        } catch (ProductNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllPeripherals")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPeripherals() {        
        try{
            List<Peripheral> peripherals = peripheralSessionBeanLocal.retrieveAllPeripherals();      
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(peripherals); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllComputerCases")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllComputerCases() {        
        try{
            List<ComputerCase> computerCases = computerPartSessionBeanLocal.retrieveAllComCase();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(computerCases); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllCpu")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCpu() {        
        try{
            List<CPU> cpus = computerPartSessionBeanLocal.retrieveAllCPU();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(cpus); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    @Path("retrieveAllGpu")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllGpu() {        
        try{
            List<GPU> gpus = computerPartSessionBeanLocal.retrieveAllGPU();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(gpus); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllMotherBoard")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllMotherboard() {        
        try{
            List<MotherBoard> mbs = computerPartSessionBeanLocal.retrieveAllMotherBoard();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(mbs); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllPowerSupply")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPowerSupply() {        
        try{
            List<PowerSupply> psus = computerPartSessionBeanLocal.retrieveAllPowerSupply();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(psus); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllRam")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllRam() {        
        try{
            List<RAM> rams = computerPartSessionBeanLocal.retrieveAllRAM();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(rams); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllCPUCoolers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCPUCoolers() {        
        try{
            List<CPUAirCooler> cpuAirCoolers = computerPartSessionBeanLocal.retrieveAllCPUAirCooler();
            List<CPUWaterCooler> cpuWaterCoolers = computerPartSessionBeanLocal.retrieveAllCPUWaterCooler();
            List<ComputerPart> cpuCoolers = new ArrayList<>();
            cpuCoolers.addAll(cpuAirCoolers);
            cpuCoolers.addAll(cpuWaterCoolers);
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(cpuCoolers); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllCPUAirCoolers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCPUAirCoolers() {        
        try{
            List<CPUAirCooler> cpuAirCoolers = computerPartSessionBeanLocal.retrieveAllCPUAirCooler();   
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(cpuAirCoolers); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllCPUWaterCoolers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCPUWaterCoolers() {        
        try{
             List<CPUWaterCooler> cpuWaterCoolers = computerPartSessionBeanLocal.retrieveAllCPUWaterCooler(); 
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(cpuWaterCoolers); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveAllHDD")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllHDD() {        
        try{
            List<HDD> hdd = computerPartSessionBeanLocal.retrieveAllHDD();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(hdd); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
     @Path("retrieveAllSSD")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllSSD() {        
        try{
            List<SSD> ssd = computerPartSessionBeanLocal.retrieveAllSSD();     
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(ssd); 
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @Path("retrievePreBuiltComputerSetModels")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePreBuiltComputerSetModels() {        
        try{
            List<PreBuiltComputerSetModel> preBuiltComputerSetModels = preBuiltComputerSetModelSessionBeanLocal.retrieveAllPreBuiltComputerSetModels();
            RetrievePreBuiltComputerModelsRsp retrievePreBuiltComputerModelsRsp = new RetrievePreBuiltComputerModelsRsp(preBuiltComputerSetModels); 
            return Response.status(Status.OK).entity(retrievePreBuiltComputerModelsRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveProductsBySearchTerm")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveProductsBySearchTerm(@QueryParam("searchTerm") String searchTerm) {
        try {
            List<Product> results = productSessionBeanLocal.retrieveProductsBySearchTerm(searchTerm);
            RetrieveProductsRsp retrieveProductsRsp = new RetrieveProductsRsp(results);
            return Response.status(Status.OK).entity(retrieveProductsRsp).build();
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

    private PeripheralSessionBeanLocal lookupPeripheralSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PeripheralSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/PeripheralSessionBean!ejb.session.stateless.PeripheralSessionBeanLocal");
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

    private PreBuiltComputerSetModelSessionBeanLocal lookupPreBuiltComputerSetModelSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (PreBuiltComputerSetModelSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/PreBuiltComputerSetModelSessionBean!ejb.session.stateless.PreBuiltComputerSetModelSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
