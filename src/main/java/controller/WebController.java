package controller;

import entity.Log;
import service.LocationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WebController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String routerIp = LocationManager.getRouterIPAddress();
        req.setAttribute("ip", routerIp);

        LocationManager.saveClientCurrentLocation(req);
        List<Log> clientIpAddress = LocationManager.getClientIpAddress();
        req.setAttribute("ipList", clientIpAddress);

        List<String> columns = new ArrayList<String>(Arrays.asList(new String[]{"Time", "Country", "City", "IP", "Browser", "OS"}));
        req.setAttribute("columns", columns);

        req.getRequestDispatcher("/index.jsp").forward(req, res);
    }
}

