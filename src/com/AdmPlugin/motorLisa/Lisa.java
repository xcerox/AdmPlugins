/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AdmPlugin.motorLisa;

import java.io.File;
import java.io.FilenameFilter;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Vector;
import com.AdmPlugin.classpath.modificadorClassPath;
import com.InterfacePlugins.PluginsCompatible;
import java.util.Stack;

public class Lisa {
    private static final String extensionJar = ".jar";
    private static final String directorio_plugins = "/home/jairo/Escritorio/Prueba/Plugins/"; 
    
    
    public Lisa() {
    }
    
    private static File[] buscarPlugins(){
    
    Vector<File> urls = new Vector<File>();
    File directorioPlugins = new File(directorio_plugins);
    
    if(directorioPlugins.exists() && directorioPlugins.isDirectory()){
         File[] jars  = directorioPlugins.listFiles(new FilenameFilter() {

             @Override
             public boolean accept(File dir, String name) {
                 return  name.endsWith(extensionJar);
             }
         });
    
         for( File jar: jars){
             urls.add(jar);
         }
                 
    }
    
    return urls.toArray(new File[0]);
    
    }
    
    public static boolean loadPlugins(){
      boolean isLoad = true;
        
      try{
          File[] jars = buscarPlugins();
          
          if(jars.length > 0){
           modificadorClassPath mc = new modificadorClassPath();
          
              for (File jar : jars) {
                  try {
                      mc.addArchivo(jar);
                  } catch (MalformedURLException error) {
                      System.err.println("Url incorrecta" + error.getLocalizedMessage());
                  }       
                }
            }
        }catch(Exception error){
            isLoad = false;
             System.err.println(error.getMessage());
            }
      return  isLoad;
    }
    
    public static PluginsCompatible[] getPlugins(){
        ServiceLoader<PluginsCompatible> serviceLoad = ServiceLoader.load(PluginsCompatible.class);
        serviceLoad.reload();
        
        Vector<PluginsCompatible> Pcompatible = new Vector<PluginsCompatible>();
        
        Iterator<PluginsCompatible> iterator = serviceLoad.iterator();
        
        while (iterator.hasNext()) {
            try {
                PluginsCompatible plugins =  iterator.next();
                Pcompatible.add(plugins);  
            } catch (Exception error) {
                System.err.println("Excepcion al obtener plugins: " + error.getMessage());
            }
        }   
         return Pcompatible.toArray(new PluginsCompatible[0]);
    }
}
