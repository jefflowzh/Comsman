/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful.resources;

import ejb.session.stateless.CouponSessionBeanLocal;
import entity.Coupon;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import util.exception.CouponInvalidException;
import util.exception.CouponNotFoundException;
import ws.restful.model.ErrorRsp;
import ws.restful.model.CheckCouponByCodeRsp;
import ws.restful.model.vallidCouponsRsp;

/**
 * REST Web Service
 *
 * @author jeffl
 */
@Path("Coupon")
public class CouponResource {

    CouponSessionBeanLocal couponSessionBean = lookupCouponSessionBeanLocal();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CouponResource
     */
    public CouponResource() {
    }

    @Path("checkCouponByCode")
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkCouponByCode(@QueryParam("code") String code) {
        try {
            Coupon coupon = couponSessionBean.checkCouponByCode(code);
            CheckCouponByCodeRsp retrieveCouponByCodeRsp = new CheckCouponByCodeRsp(coupon);
            return Response.status(Status.OK).entity(retrieveCouponByCodeRsp).build();
        } catch (CouponInvalidException | CouponNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("validCoupons")
    @GET
    @Consumes()
    @Produces(MediaType.APPLICATION_JSON)
    public Response validCoupons() {
        try {
            List<Coupon> coupons = couponSessionBean.retrieveAllValidCoupon();
            vallidCouponsRsp valid = new vallidCouponsRsp(coupons);
            return Response.status(Status.OK).entity(valid).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    /**
     * PUT method for updating or creating an instance of CouponResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    private CouponSessionBeanLocal lookupCouponSessionBeanLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CouponSessionBeanLocal) c.lookup("java:global/ComputerPartsEcommerce/ComputerPartsEcommerce-ejb/CouponSessionBean!ejb.session.stateless.CouponSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
