/*
 * Algoritmo que lee datos de un archivo
 * Los datos son almacenados en una tabla hash
 * Los datos del hash son ordenados ascendentemente
 */

package P_2014_02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

/**
 *
 * @author Steven Sotelo
 */
public class Reto_1 {
    
    public static void main(String[] args)
    {        
        try
        {
            //Datos para lectura del archivo
            String file="Calentamiento1.txt";
            BufferedReader lector=new BufferedReader(new FileReader(new File(file)));
            String linea;
            //Tabla hash
            Hashtable <Long,Integer> datos=new Hashtable<Long,Integer>();
            // Este ciclo carga los datos y los almacena en la tabla hash
            // La clave del hash es el dato que encuentra
            // El valor almacenado en el hash es la cantidad de veces que se encuentra el número repetido            
            while((linea=lector.readLine())!=null)
            {
                // Se parte la cadena para buscar cada número
                String[] aux=linea.split("\t");
                for(int i=0;i<aux.length;i++)
                {
                    Long j=Long.parseLong(aux[i]);
                    if(datos.containsKey(j))
                        datos.put(j,Integer.parseInt(String.valueOf(datos.get(j)))+1);
                    else
                        datos.put(j,1);
                }                
            }
            Object[] keys=datos.keySet().toArray();
            // Se puede realizar el ordenamiento mediante el API de java con Arrays.sort debido a Hashtable tiene una implementacion de comparable
            // El algoritmo implementado para el ordenamiento es el mergesort por ser de tipo object
            Arrays.sort(keys);
            // Se imprimen los datos
            for(Object k : keys)
                System.out.println(k + " " + datos.get(Long.parseLong(k.toString())));
            
            // Un nuevo comentario
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