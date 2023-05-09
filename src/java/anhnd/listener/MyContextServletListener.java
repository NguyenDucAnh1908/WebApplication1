/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author DucAnh
 */
public class MyContextServletListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Application is deploying.....");
        ServletContext context = sce.getServletContext();
        String siteMapsPath = context.getInitParameter("SITE_MAPS_PATH");
        Properties siteMaps = new Properties();
        InputStream is = null;
        try {
         is = context.getResourceAsStream(siteMapsPath);
         siteMaps.load(is);
         context.setAttribute("SITEMAPS", siteMaps);
        } catch (IOException ex) {
            Logger.getLogger(MyContextServletListener.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    context.log("MyContextServletListener   IO" + e.getMessage());
                }
            }
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application is destroying..............");
    }
}
