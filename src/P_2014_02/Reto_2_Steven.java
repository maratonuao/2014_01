/*
 * Algoritmo que lee datos de un archivo
 * Los datos son almacenados en un Hash map
 * Los datos son ordenados ascendentemente
 * Se eliminan los datos que indican en el segundo archivo
 */

package P_2014_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 * @author Steven Sotelo
 */
public class Reto_2_Steven 
{
        
    public static void main(String[] args)
    {        
        try
        {
            //Datos para lectura del archivo
            String file="Entrada1.txt";
            String file2="datosBorrar.txt";
            BufferedReader lector=new BufferedReader(new FileReader(new File(file)));
            String linea;
            String[] aux;
            
            HashMap <Long,Integer> datos=new HashMap<Long,Integer>();
            
            // Este ciclo carga los datos y los almacena en un hashmap
            // La clave del hash es el dato que encuentra
            // El valor almacenado en el hash es la cantidad de veces que se encuentra el número repetido            
            while((linea=lector.readLine())!=null)
            {
                // Se parte la cadena para buscar cada número
                aux=linea.split("\t");                
                for(int i=0;i<aux.length;i++)
                {
                    if(aux[i] != null && !aux[i].equals(""))
                    {                        
                        Long j=Long.parseLong(aux[i]);                
                        Integer veces = datos.get(j);
                        if(veces!=null)
                            datos.put(j,veces+1);
                        else
                            datos.put(j,1);
                    }                    
                }                
            }
            lector.close();
            
            Object[] keys=datos.keySet().toArray();
            // Se puede realizar el ordenamiento mediante el API de java con Arrays.sort debido a que hashmap tiene una implementacion de comparable
            // El algoritmo implementado para el ordenamiento es el mergesort por ser de tipo object
            Arrays.sort(keys);
            
            //Lectura del archivo de borrar los datos
            lector=new BufferedReader(new FileReader(new File(file2)));
            linea=null;            
            //Indice para recorrer las claves ya ordenadas
            int pos=0;
            while((linea=lector.readLine())!=null)
            {
                // Se asigna los datos a borrar, esto depende si vienen separados por espacio o por tab
                aux=linea.split("\t").length > 1 ? linea.split("\t") : linea.split(" ");                
                // Tomo la cantidad de números que debo eliminar solamente, ya que siempre elimino los menores de la lista
                Integer cantidad=Integer.parseInt(aux[1]);
                //Creo un ciclo para eliminar la cantidad de datos que viene el registro 
                while(cantidad > 0)                
                {
                    //Obtengo la cantidad de veces que tengo registrado el número
                    Integer cDatos = datos.get(Long.parseLong(keys[pos].toString()));
                    // Si la cantidad de veces que tengo registrado el numero es mayor a la cantidad de datos a borrar
                    // Disminuyo su número de veces                  
                    if(cDatos > cantidad)
                    {
                        datos.put(Long.parseLong(keys[pos].toString()), cDatos-cantidad);
                        cantidad=0;
                    }
                    // Si por el contrario la cantidad de veces que tengo el número es menor o igual a los datos a borrar
                    // Remuevo el dato, itero el indice en 1, asigno 0 a la clave para saber que ya salio de la lista
                    // Disminuyo la cantidad de datos a borrar para la siguiente iteracion
                    else
                    {
                        datos.remove(Long.parseLong(keys[pos].toString()));
                        keys[pos++]=0;
                        cantidad-=cDatos;
                    }
                }
            }
            lector.close();
            for(Object k:keys)
            {
                if(Long.parseLong(k.toString()) != 0)
                {
                    for(int i=1;i<=datos.get(Long.parseLong(k.toString()));i++)
                        System.out.println(k);
                }
            }
            
            // La ejecución tarda 1 min 48 sec
        }
        catch(IOException exIO)
        {
            System.out.println(exIO);
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }    
}