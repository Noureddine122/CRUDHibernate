package com.example.demo32;

import Model.DAO.DAOOffre;
import Model.DAO.DAOTypeO;
import data.Offre;
import data.TypeO;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.UserTransaction;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author noureddine
 */
@WebServlet(name = "helloServlet", value = "/hh")
public class HelloServlet extends HttpServlet {
    private String message;
    @PersistenceUnit(unitName = "default")
    private EntityManagerFactory emf;
    @Resource
    private UserTransaction userTransaction;

    @Override
    public void init() {

        message = "Hello World!";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
       // response.setContentType("text/html");

       // PrintWriter out = response.getWriter();
        try {
            userTransaction = InitialContext.doLookup("java:comp/UserTransaction");
            DAOTypeO daoTypeO = new DAOTypeO(emf,userTransaction);
            DAOOffre daoOffre = new DAOOffre(emf,userTransaction);


            request.setAttribute("list",daoTypeO.retrieve());
            request.setAttribute("listOffres",daoOffre.retrieve());
            this.getServletContext().getRequestDispatcher("/display.jsp").forward(request,response);

        } catch (NamingException | ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            userTransaction = InitialContext.doLookup("java:comp/UserTransaction");
            String profile = req.getParameter("Profile");
            String description = req.getParameter("Description");
            String idType = req.getParameter("list");

            DAOOffre daoOffre = new DAOOffre(emf,userTransaction);
            DAOTypeO daoTypeO = new DAOTypeO(emf,userTransaction);
            daoOffre.create(profile,description,daoTypeO.retrieve().get(Integer.parseInt(idType)-1));
            daoOffre.delete(1);
            daoOffre.delete(2);
            doGet(req,resp);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
    }
}