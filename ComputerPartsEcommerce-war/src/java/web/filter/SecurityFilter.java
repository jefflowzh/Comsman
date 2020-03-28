package web.filter;

import entity.Staff;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import util.enumeration.StaffAccessRightEnum;



@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})

public class SecurityFilter implements Filter
{    
    FilterConfig filterConfig;
    
    private static final String CONTEXT_ROOT = "/ComputerPartsEcommerce-war";
    
   

    public void init(FilterConfig filterConfig) throws ServletException
    {
        this.filterConfig = filterConfig;
    }



    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        HttpSession httpSession = httpServletRequest.getSession(true);
        String requestServletPath = httpServletRequest.getServletPath();        
        System.out.print("FILTERING");
        

        if(httpSession.getAttribute("isLogin") == null)
        {
            httpSession.setAttribute("isLogin", false);
        }

        Boolean isLogin = (Boolean)httpSession.getAttribute("isLogin");
        
        
        
        if(!excludeLoginCheck(requestServletPath))
        {
            if(isLogin == true)
            {
                System.out.print("ISLOGIN");
                Staff currentStaffEntity = (Staff)httpSession.getAttribute("currentStaffEntity");
                chain.doFilter(request, response);
//                if(checkAccessRight(requestServletPath, currentStaffEntity.getRole()))
//                {
//                    chain.doFilter(request, response);
//                }
//                else
//                {
//                    httpServletResponse.sendRedirect(CONTEXT_ROOT + "/accessRightError.xhtml");
//                }
            }
            else
            {
                httpServletResponse.sendRedirect(CONTEXT_ROOT + "/accessdenied.xhtml");
            }
        }
        else
        {
            chain.doFilter(request, response);
        }
    }



    public void destroy()
    {
    }
    
    
    
//    private Boolean checkAccessRight(String path, StaffAccessRightEnum accessRight)
//    {        
//        if(accessRight.equals(StaffAccessRightEnum.CASHIER))
//        {            
//            if(path.equals("/cashierOperation/checkout.xhtml") ||
//                path.equals("/cashierOperation/voidRefund.xhtml") ||
//                path.equals("/cashierOperation/viewMySaleTransactions.xhtml"))
//            {
//                return true;
//            }
//            else
//            {
//                return false;
//            }
//        }
//        else if(accessRight.equals(AccessRightEnum.MANAGER))
//        {
//            if(path.equals("/cashierOperation/checkout.xhtml") ||
//                path.equals("/cashierOperation/voidRefund.xhtml") ||
//                path.equals("/cashierOperation/viewMySaleTransactions.xhtml") ||
//                path.equals("/systemAdministration/createNewStaff.xhtml") ||
//                path.equals("/systemAdministration/viewStaffDetails.xhtml") ||
//                path.equals("/systemAdministration/viewAllStaffs.xhtml") ||
//                path.equals("/systemAdministration/createNewProduct.xhtml") ||
//                path.equals("/systemAdministration/viewProductDetails.xhtml") ||
//                path.equals("/systemAdministration/updateProduct.xhtml") ||
//                path.equals("/systemAdministration/deleteProduct.xhtml") ||
//                path.equals("/systemAdministration/viewAllProducts.xhtml") ||
//                path.equals("/systemAdministration/searchProductsByName.xhtml") ||
//                path.equals("/systemAdministration/filterProductsByCategory.xhtml") ||
//                path.equals("/systemAdministration/filterProductsByTags.xhtml") ||
//                path.equals("/systemAdministration/deleteStaff.xhtml"))
//            {
//                return true;
//            }
//            else
//            {
//                return false;
//            }
//        }
//        
//        return false;
//    }



    private Boolean excludeLoginCheck(String path)
    {
        System.out.print("CHECK EXCLUDE");
        if(path.equals("/login.xhtml") ||
            path.equals("/accessdenied.xhtml") ||
            path.startsWith("/javax.faces.resource"))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
