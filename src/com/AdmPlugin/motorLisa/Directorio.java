package com.AdmPlugin.motorLisa;

import com.library.util.Empty;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Vector;

public class Directorio {

    {
        ext = Empty.getString();
        buscarEnSubCarpetas = Empty.getBool();
    }

    private String ext;
    private boolean buscarEnSubCarpetas;
    
    private File[] getArchivosEnDirectorio(File Directorio){
        File[] archivos = Directorio.listFiles();
        Vector<File> listaArchivos = new Vector<>();
        

        for (File archivo:archivos) {
            if(archivo.isDirectory() && buscarEnSubCarpetas){
                for (File archivoEnDirectorio : getArchivosEnDirectorio(archivo)) {
                   if (archivoEnDirectorio.getName().endsWith(ext)) 
                        listaArchivos.add(archivoEnDirectorio);
                }
            }else{
                if (archivo.getName().endsWith(ext)) 
                    listaArchivos.add(archivo);
            }
        }
        
        return listaArchivos.toArray( new File[0]);
        
    }

    public File[] getArchivos(String path) {

        File Directorio = new File(path);

        if (Directorio.exists()) 
            return getArchivosEnDirectorio(Directorio);
        else
            return new File[0];
            
        
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public void setBuscarEnSubCarpetas(boolean buscarEnSubCarpetas) {
        this.buscarEnSubCarpetas = buscarEnSubCarpetas;
    }
    
    
}
