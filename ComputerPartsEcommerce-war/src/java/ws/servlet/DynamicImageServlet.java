/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author weidonglim
 */
@WebServlet(name = "DynamicImageServlet", urlPatterns = {"/images/dynamic/*"})

public class DynamicImageServlet extends HttpServlet {

    private String imageDirectory = "C:\\IS3106_Project_Images_Src\\";
    //private String imageDirectory = "/Users/weidonglim/Documents/IS3106_Project_Images_Src/";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            File directory = new File(imageDirectory);
            if (! directory.exists()){
                directory.mkdir();
            }
            
            // Get image file.
            String file = request.getParameter("file");
            
            //check empty
            if(file == null || "".equals(file)) 
                return;
            
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(imageDirectory + file));

            // Get image contents.
            byte[] bytes = new byte[in.available()];
            in.read(bytes);
            in.close();
            // Write image contents to response.
            response.getOutputStream().write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}