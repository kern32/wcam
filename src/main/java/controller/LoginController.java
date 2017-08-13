package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String un=request.getParameter("username");
        String pw=request.getParameter("password");
        HttpSession session = request.getSession(true);

        //put username/password to see images
        if(un.equals("") && pw.equals("")) {
            session.setAttribute("", "");
            session.setAttribute("", "");

            response.sendRedirect("/wcam/screen.overview");
        }
        else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}