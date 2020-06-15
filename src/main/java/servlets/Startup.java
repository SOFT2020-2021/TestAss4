package servlets;

import dataLayer.DAO;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class Startup extends HttpServlet {

        public void init() {
            try {
                DAO.connect("jdbc:postgresql://127.0.0.1:5432/bank");
            } catch(Exception E){
                System.out.println(E.getMessage());
                System.exit(1);
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
