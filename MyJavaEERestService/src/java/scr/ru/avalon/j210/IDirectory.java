/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr.ru.avalon.j210;

/**
 *
 * @author user
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Интерфейс определяет методы для работы с каталогами сервера.
 * @author Ю.Д.Заковряшин, 2018-2020.
 */
//@Stateless
//@Path("/directory")
public class IDirectory {
    
//    private Map<String, Type> map;
//    
//    public static enum Type {
//        /**
//         * Определяет каталог
//         */
//        DIRECTORY,
//        /**
//         * Определяет файл
//         */
//        FILE;
//    }
//    
//    @PostConstruct
//    public void init(){
//        map = new HashMap<>();
//    }
//    
//    @GET
//    @Produces("text/html")
//    public Map<String, Type> getContent(
//        @PathParam("directory") String dirName){
//        map.put(dirName, Type.FILE);
//        return map;
//    }
//    
//    
//    
//    @GET
//    @Produces(MediaType.TEXT_HTML)
//    public Map<String, Type> findFile(
//        @PathParam("directory") String dirName,
//            	@QueryParam("file") String fileName){
//        map.put(dirName, Type.DIRECTORY);
//        return map;
//    }
}
