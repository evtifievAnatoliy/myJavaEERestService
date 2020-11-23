package scr.ru.avalon.j210.DirectoryBean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
@Local
public interface IDirectory {
    
    public static enum Type {
        DIRECTORY,
        FILE;
    }
    
    public Map<String, Type> getContent(String dirName);
    
    public Map<String, Type> findFile(String dirName, String fileName);
    
}
