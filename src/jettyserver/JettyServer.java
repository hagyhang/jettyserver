/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jettyserver;

import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import jettyserver.handles.EmployeeServlet;
import jettyserver.handles.SearchServlet;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 *
 * @author cpu10488
 */
public class JettyServer {

    static final Logger logger = Logger.getLogger(JettyServer.class.getName());
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        
        ServletHandler servlet_handler = new ServletHandler();
        servlet_handler.addServletWithMapping(EmployeeServlet.class, "/employee");
        servlet_handler.addServletWithMapping(SearchServlet.class, "/search");
        
        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{ "index.html" });
        resource_handler.setResourceBase("./src/resources");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { resource_handler, servlet_handler});
        server.setHandler(handlers);
        
        server.start();
        try {
            server.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(JettyServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
