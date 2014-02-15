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
            
            //Tabla hash
            //Hashtable <Long,Integer> datos=new Hashtable<Long,Integer>();
            /*long tiempoIProceso, tiempoFProceso, duracionProceso;

            tiempoIProceso = System.currentTimeMillis();*/
            // El trabajar con un HashMap redujo el tiempo de carga de 4024ms a 3524ms
            HashMap <Long,Integer> datos=new HashMap<Long,Integer>();
            // Este ciclo carga los datos y los almacena en la tabla hash
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
            
            /*tiempoFProceso = System.currentTimeMillis();
            duracionProceso = tiempoFProceso - tiempoIProceso;
            System.out.println("DuracionProceso="+duracionProceso+"ms \tEquivalente a "+duracionProceso/1000 + " s");
            */
            Object[] keys=datos.keySet().toArray();
            // Se puede realizar el ordenamiento mediante el API de java con Arrays.sort debido a Hashtable tiene una implementacion de comparable
            // El algoritmo implementado para el ordenamiento es el mergesort por ser de tipo object
            Arrays.sort(keys);
            
            
            //Lectura del archivo de borrar los datos
            lector=new BufferedReader(new FileReader(new File(file2)));
            linea=null;
            HashMap <Long,Integer> datosBorrar=new HashMap<Long,Integer>();
            
            while((linea=lector.readLine())!=null)
            {
                aux=linea.split("\t");
                Long j=Long.parseLong(aux[0]);
                Integer cantObj = datos.get(j);
                Integer cantVal = Integer.parseInt(aux[1]);
                if(cantObj==null)
                    datosBorrar.put(j,cantVal);
                else
                    datosBorrar.put(j, cantObj + cantVal);
            }
            
            Object[] keysBorrar=datosBorrar.keySet().toArray();
            Arrays.sort(keysBorrar);
            
            for(Object k : keysBorrar)
            {
                Long num=Long.parseLong(k.toString());
                Integer veces=datosBorrar.get(num);
                int pos=Arrays.binarySearch(keys, k);
                Integer cant=datos.get(keys[pos]);
                if(veces==1 && cant==1)
                    datos.remove(keys[pos]);
                else if(veces==1 && cant > 1)
                    datos.put(Long.parseLong(keys[pos].toString()), datos.get(keys[pos]) - 1);
                else if(veces > 1)
                {
                    while(veces > 0 && pos >= 0)
                    {
                        int temp= datos.get(keys[pos]);
                        if(veces >= temp)
                            datos.remove(keys[pos]);
                        else
                            datos.put(Long.parseLong(keys[pos].toString()), datos.get(keys[pos]) - veces);
                        veces-=temp;
                        pos-=1;
                    }
                }
            }
            for(Object k : keys)
            {
                Integer can=datos.get(Long.parseLong(k.toString()));
                if(can != null && can == 1 )
                    System.out.println(k);
                else if(can != null && can >1)
                {
                    while(can > 0)
                        System.out.println(k);
                }
            }
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