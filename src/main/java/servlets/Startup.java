package servlets;


import dataLayer.DAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class Startup extends HttpServlet {

        public void init() throws ServletException {
            System.out.println("I AM RUNNING!");
            try {
                //Alex DAO.connect("jdbc:postgresql://192.168.1.137:5432/bank");
                DAO.connect("jdbc:postgresql://127.0.0.1:5432/bank");
                //DAO.connect("jdbc:postgresql://192.168.1.137:5432/testbank");

            } catch(Exception E){
                System.out.println(E.getMessage());
            }
    }
}

/* Useful in case servlet should be run with context
public class MyContextListener
  implements ServletContextListener{

  @Override
  public void contextDestroyed(ServletContextEvent arg0) {
    //do stuff
  }

  @Override
  public void contextInitialized(ServletContextEvent arg0) {
    //do stuff before web application is started
  }
}
 */
