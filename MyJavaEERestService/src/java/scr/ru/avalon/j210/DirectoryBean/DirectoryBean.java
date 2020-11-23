/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr.ru.avalon.j210.DirectoryBean;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.ws.rs.Path;

@Stateless
public class DirectoryBean implements IDirectory{
    
    private Map<String, Type> mapMainDir;
    
    private String mainDir = "D:/Test";

    
    
    @PostConstruct
    public void init(){
        mapMainDir = new HashMap<>();
        File dir = new File (mainDir);
        if (dir.isDirectory()){
            mapMainDir = addNewFilesFromDir(dir);
        }
    }
    
//    @Override
//    public boolean setMainDir(String mainDir) {
//        
//        File file = new File(mainDir);
//        if (file.exists() && file.isDirectory()){
//            this.mainDir = mainDir;
//            return true;
//        }
//        else
//            return false;
//    }
    
    @Override
    public Map<String, Type> getContent(String dirName) {
        if (dirName.trim().isEmpty())
            return mapMainDir;
        else
        {
            File newDir = new File(mainDir + "/" + dirName);
            Map<String, Type> map = addNewFilesFromDir(newDir);
            if(map!=null)
                return map;
            else
                return null;
        }
        
    }

    @Override
    public Map<String, Type> findFile(String dirName, String fileName) {
        if (dirName.trim().isEmpty() && fileName.trim().isEmpty()){
            return null;
        }
        
        Map<String, Type> map = new HashMap<>();
        if (dirName.trim().isEmpty() && !fileName.trim().isEmpty()){
            File file = new File(mainDir + "/" + fileName);
            if (file.exists() && file.isFile()){
                map.put(file.getName(), Type.FILE);
            }
            else
                return null;
        }
        else {
            File file = new File(mainDir + "/" + dirName + "/" + fileName);
            if (file.exists()){
                map.put(file.getName(), Type.FILE);
                
            }
            else
                return null;
        }
        return map;
    }
    
    private Map<String, Type> addNewFilesFromDir(File dir){
        Map<String, Type> map = new HashMap<>();
        if (dir.exists()){
            for (File item : dir.listFiles()) {
                if (item.isDirectory()) {
                    map.put(item.getName(), Type.DIRECTORY);
                } 
                else{
                    map.put(item.getName(), Type.FILE);
                } 
            }
            return map;
        }
        else
            return null;
    }
    
}
