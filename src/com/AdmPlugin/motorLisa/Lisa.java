/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AdmPlugin.motorLisa;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.ArrayList;
import com.AdmPlugin.classpath.modificadorClassPath;
import com.InterfacePlugins.PluginsCompatible;
import com.libreriajRR.sistem.Directorio;

public class Lisa {
    
    private static File[] buscarPlugins(){
        final String extJar = ".jar";
        final String direccion = "/home/jairo/Escritorio/Prueba/Plugins/";
        
        Directorio dir = new Directorio();
        dir.setExt(extJar);
        dir.setBuscarEnSubCarpetas(false);
        return dir.getArchivos(direccion);
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
        
        ArrayList<PluginsCompatible> listaPlugins = new ArrayList<PluginsCompatible>();
        
        Iterator<PluginsCompatible> iterator = serviceLoad.iterator();
        
        while (iterator.hasNext()) {
            try {
                PluginsCompatible plugins =  iterator.next();
                listaPlugins.add(plugins);  
            } catch (Exception error) {
                System.err.println("Excepcion al obtener plugins: " + error.getMessage());
            }
        }   
         return listaPlugins.toArray(new PluginsCompatible[0]);
    }
}
