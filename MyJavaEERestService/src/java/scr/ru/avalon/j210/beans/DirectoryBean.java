/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr.ru.avalon.j210.beans;

import java.util.Map;
import javax.ejb.Stateless;

/**
 *
 * @author user
 */
@Stateless
public class DirectoryBean implements IDirectory{

    @Override
    public Map<String, Type> getContent(String dirName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Type> findFile(String dirName, String fileName) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
