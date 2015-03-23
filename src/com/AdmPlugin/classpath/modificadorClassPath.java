/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.AdmPlugin.classpath;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class modificadorClassPath {

    private static final String metodoAddUrl = "addURL";
    private static final Class[] parametroMetodo = new Class[]{URL.class};
    private final URLClassLoader loader;
    private final Method metodoAdd;

    public modificadorClassPath() throws NoSuchMethodException{
     loader = (URLClassLoader) ClassLoader.getSystemClassLoader();
     metodoAdd = URLClassLoader.class.getDeclaredMethod(metodoAddUrl, parametroMetodo);
     metodoAdd.setAccessible(true);
        
    }
    
    
    public URL[] getUrls(){
        return loader.getURLs();
    }
    
    public void addUrl(URL url){
        if (url != null){
            try {
              metodoAdd.invoke(loader, new Object[]{url});
            } catch (Exception error) {
                System.out.println("Exepcion al guardar URl" + error.getLocalizedMessage());
            }
        }
    }
    
    public void addUrls(URL[] urls){
        if (urls != null){
            for(URL url:urls){
                 addUrl(url);
            }
        }
    
    }
    
    public void addArchivo(File archivo) throws MalformedURLException{
        if(archivo != null){
            addUrl(archivo.toURI().toURL());
        }
    }
    
    public void addArchivo(String nombreArchivo) throws MalformedURLException{
        addArchivo(new File(nombreArchivo));
    }
    
}
