package controller;

import entity.Image;
import service.LocationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.logging.*;
import java.util.Set;

public class OverviewController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        if (    session.getAttribute("username") != null &&
                session.getAttribute("password") != null &&

                //put username/password to see images
                session.getAttribute("username").equals("") &&
                session.getAttribute("password").equals("")) {

            //String path = System.getProperties().getProperty("catalina.base") + "\\work\\Catalina\\localhost\\_\\WEB-INF\\classes\\images";
            String path = "/var/lib/tomcat7/webapps/wcam/resources/images";
            Logger.getLogger(OverviewController.class.getName()).log(Level.INFO, "path: " + path);
            Set<Image> fileList = LocationManager.getScreenShots(path);
            if (!fileList.isEmpty()) {
                req.setAttribute("lastItem", fileList.iterator().next());
            }
            req.setAttribute("fileList", fileList);
            req.setAttribute("location", path);

            req.getRequestDispatcher("/overview.jsp").forward(req, res);
        } else {
            res.sendRedirect("/wcam/login");
        }
    }
}
