/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scr.ru.avalon.j210;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import scr.ru.avalon.j210.DirectoryBean.IDirectory;


/**
 *
 * @author user
 */
@Stateless
@Path("/directory")
public class ApplicationBean {
    
    @EJB
    IDirectory dirctoryBean;
    
    @GET
    @Path("/getContext")
    @Produces("text/html")
    public String getContent(@QueryParam("dirName") String dirName){
        
        if (dirctoryBean.getContent(dirName)!=null){
            return dirctoryBean.getContent(dirName).toString();
        }
        else
            return"getContext error";

    }
    
    @GET
    @Path("/findFile")
    @Produces("text/html")
    public String findFile(@QueryParam("dirName") String dirName, @QueryParam("fileName")String fileName){
        
        if (dirctoryBean.findFile(dirName, fileName)!=null){
            return dirctoryBean.findFile(dirName, fileName).toString();
        }
        else {
            return ("findFile error: " + dirName + "-->" + fileName);
        }
        
    }
    
        
//    private Map<String, Type> mapMainDir;
//    
//    private final String mainDir = "D:/Test";
//    
//    public static enum Type {
//        DIRECTORY,
//        FILE;
//    }
//    
//    @PostConstruct
//    public void init(){
//        mapMainDir = new HashMap<>();
//        File dir = new File (mainDir);
//        if (dir.isDirectory()){
//            mapMainDir = addNewFilesFromDir(dir);
//        }
//    }
//    
//    
//    
//    @GET
//    @Path("/getContext")
//    @Produces("text/html")
//    public String getContent(@QueryParam("dirName") String dirName){
//        if (dirName.trim().isEmpty())
//            return mapMainDir.toString();
//        else
//        {
//            File newDir = new File(mainDir + "/" + dirName);
//            Map<String, Type> map = addNewFilesFromDir(newDir);
//            if(map!=null)
//                return map.toString();
//            else
//                return"Catalog error";
//        }
//
//    }
//    
//    @GET
//    @Path("/findFile")
//    @Produces("text/html")
//    public String findFile(@QueryParam("dirName") String dirName, @QueryParam("fileName")String fileName){
//        
//        if (dirName.trim().isEmpty() && fileName.trim().isEmpty()){
//            return ("file and catalog error");
//        }
//        
//        Map<String, Type> map = new HashMap<>();
//        if (dirName.trim().isEmpty() && !fileName.trim().isEmpty()){
//            File file = new File(mainDir + "/" + fileName);
//            if (file.exists()){
//                map.put(file.getName(), Type.FILE);
//            }
//            else
//                return ("file error");
//        }
//        else {
//            File file = new File(mainDir + "/" + dirName + "/" + fileName);
//            if (file.exists()){
//                map.put(file.getName(), Type.FILE);
//                
//            }
//            else
//                return ("file and catalog error " + dirName + "-->" + fileName);
//        }
//        return map.toString();
//    }
//    
//    
//    private Map<String, Type> addNewFilesFromDir(File dir){
//        Map<String, Type> map = new HashMap<>();
//        if (dir.exists()){
//            for (File item : dir.listFiles()) {
//                if (item.isDirectory()) {
//                    map.put(item.getName(), Type.DIRECTORY);
//                } 
//                else{
//                    map.put(item.getName(), Type.FILE);
//                } 
//            }
//            return map;
//        }
//        else
//            return null;
//    }
}
