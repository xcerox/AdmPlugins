
package com.AdmPlugin.motorLisa;

import  com.library.util.Empty;
import java.io.File;
import java.io.FilenameFilter;

public class Directorio {
    
    {
        extencionFiltro = Empty.getString();
        archivosContados = Empty.getInt();
    }
    
    private String extencionFiltro;
    private int archivosContados;

    public int getArchivosContados() {
        return archivosContados;
    }
    
    private void escribirCarpeta(File[] archivos){
        
        for(File archivo:archivos){
            if(archivo.isDirectory()){
              File[] directorio = archivo.listFiles();
              escribirCarpeta(directorio);
            }else{
                System.out.println(archivo.getAbsolutePath());      
            }
        }
    }
    
    public void doBuscarRutas(String ruta){
        
        File Directorio = new File(ruta);
        
        if(Directorio.exists()){
         
            File[] archivos;

            if(!extencionFiltro.isEmpty())
            {
                archivos = Directorio.listFiles( new FilenameFilter() {

                    @Override
                    public boolean accept(File dir, String name) {
                       return name.endsWith(extencionFiltro);
                    }
                });
            }else{
                archivos = Directorio.listFiles();
            }
            
            escribirCarpeta(archivos);
         }  
    }

    public void setExtencionFiltro(String extencionFiltro) {
        this.extencionFiltro = extencionFiltro;
    }
}