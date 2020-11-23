/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr.ru.avalon.j210;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author user
 */
public class RSServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        String subCatalog = request.getParameter("fieldSubCatalog");
        String file = request.getParameter("fieldFile");
        
        Object sendSubCatalogBtn = request.getParameter("sendSubCatalogBtn");
        Object getFileBtn = request.getParameter("getFileBtn");
        
        
        
        if (sendSubCatalogBtn != null) {
            
            if (subCatalog.trim().isEmpty()) {
                // Если строка "пустая", то пользователь переадресуется на 
                // стартовую страницу приложения. 
                outHttpResponse(response, "Пожалуста введите имя Подкаталога");
                return;
            } else {
                outHttpResponse(response, "Состав Подкаталога: " + subCatalog);
                return;
                
            }
        }

        if (getFileBtn != null) {

            if (file.trim().isEmpty()) {
                // Если строка "пустая", то пользователь переадресуется на 
                // стартовую страницу приложения. 
                outHttpResponse(response, "Пожалуста введите имя файла");
                return;
            } else {
                
                outHttpResponse(response, "Ответ RSService для подкаталога: " + subCatalog + " и файла: " + file);
                
                return;
            }
        }

        

    }

    private void outHttpResponse(HttpServletResponse response, String str) throws IOException {
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"ru\">");
            out.println("<head>");
            out.println("<title>Сообщение</title>");
            out.println("<meta charset=\"UTF-8\" />");
            out.println("</head>");
            out.println("<body>");
            // Вывод подтверждения о добавлении полученного сообщения msg 
            // к коллекции сообщений.
            out.println("<p>Сообщение от Bean: \'" + str + "\'</p>");
            // Вывод числа обращений к данному методу.
            // Вывод кнопки возврата на стартовую страницу.
            out.println(goBack());
            out.println("</body>");
            out.println("</html>");
        }
    }

    private String getStartPage() {
        
            return "index.html";
        

    }

    private String goBack() {
        return "<form action=\"" + getStartPage() + "\">\n"
                + "            <input type=\"submit\" value=\"Назад\"/>\n"
                + "        </form>";
    }

}
