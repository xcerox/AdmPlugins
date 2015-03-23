
package com.AdmPlugin.Main;
import com.InterfacePlugins.PluginsCompatible;
import com.AdmPlugin.motorLisa.Lisa;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("--------------------------------");
        
        boolean isLoad = Lisa.loadPlugins();
        
        if (isLoad) {
            try {
                PluginsCompatible[] indicadores = Lisa.getPlugins();
                
                if (indicadores.length > 0) {
                    for(PluginsCompatible plugin: indicadores ) {
                        System.out.println("plugins :"+ plugin.getClass());
                        System.out.println("mensaje :" + plugin.getMenssage());
                        System.out.println();   
                    }
                }else{
                    System.out.println("No se encontraron Plugins");
                }
            } catch (Exception error) {
                 System.err.println("Exepcion: " + error.getMessage());
                 error.printStackTrace();
            }
        }else{
            System.out.println("Plugins no cargados");
            }
        
        System.out.println("--------------------------------");
    }
}
