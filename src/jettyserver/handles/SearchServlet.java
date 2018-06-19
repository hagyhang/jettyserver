/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jettyserver.handles;

import hapax.Template;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hagyhang
 */
public class SearchServlet extends HttpServlet{
    @Override
    protected void doGet( HttpServletRequest request,
                          HttpServletResponse response ) throws ServletException,
                                                        IOException{
        
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            response.getWriter().println(getLayout());
        } catch (TemplateException ex) {
            Logger.getLogger(EmployeeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
        public String getLayout() throws TemplateException{
        int result;
        TemplateLoader templateLoader = TemplateResourceLoader.create("layout/");
        //Template load file
        Template template = templateLoader.getTemplate("search.xtm");
        //Use TemplateDictionary to put to xtm
        TemplateDictionary templeDictionary = new TemplateDictionary();
        String data = template.renderToString(templeDictionary);
        return data;
    }
}
