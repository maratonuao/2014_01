package P_2014_02;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author oarboleda
 */
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

class Propuesta_Reto2_AndresTorres {

    private static String archivo = "D:\\Moodle\\Maraton\\Reto1_2014_1\\Ejercicio1\\Entrada1.txt";
    private static String borrar = "D:\\Moodle\\Maraton\\Reto1_2014_1\\Ejercicio1\\datosBorrar.txt";

//    public static int busquedaBinaria(Object[] Keys, long dato) {
//        int pos = (Keys.length - 1) / 2;
//
//        while (pos != 0) {
//            if (Long.parseLong(Keys[pos].toString()) - dato < 0) {
//                pos++;
//            } else if (Long.parseLong(Keys[pos].toString()) - dato > 0) {
//                pos--;
//            } else {
//                return pos;	// !! Lo encontró...retórnelo !!
//            }
//        }
//        return -1;
//    }
    public static void mostrarArchivo(String nombre) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombre);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void mostrar(HashMap<Long, Integer> arr, Object[] Keys) {

//        Object[] Keys = arr.keySet().toArray();
//        Arrays.sort(Keys);
        for (Object k : Keys) {
            System.out.println(k + " " + arr.get(Long.parseLong(k.toString())));
        }
    }

    public static HashMap<Long, Integer> Proceso(String nombre) {

        HashMap<Long, Integer> contenedor = new HashMap<Long, Integer>();

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(nombre);

            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            //Agregado
            String[] entradas;
            Integer k = new Integer(1);

            //fin agregado
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                //Agregado
                entradas = linea.split("\t");
                for (int i = 0; i < entradas.length; i++) {

                    if (entradas[i] != null && !entradas[i].equals("")) {
                        Long key = Long.parseLong(entradas[i]);

                        Integer cont = contenedor.get(key);
                        if (cont != null) {
                            contenedor.put(key, k + 1);
                        } else {
                            contenedor.put(key, 1);
                        }
                    }
                }
            }
            //Fin agregado

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }//        

        return contenedor;
    }

    public static HashMap<Long, Integer> Borrador(HashMap<Long, Integer> contenedor, String borrador, Object[] keys) {

        HashMap<Long, Integer> Elementos = new HashMap<Long, Integer>();

        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(borrador);

            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            //Agregado
            String[] entradas;

            //fin agregado
            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                //Agregado
                entradas = linea.split(" ");
//                System.out.println(entradas[1]);
                if (entradas[1].contains("\t")) {
                    entradas[1] = entradas[1].replace("\t", "0");
                }

                Elementos.put(Long.parseLong(entradas[0]), Integer.parseInt(entradas[1]));
            }
            //Fin agregado

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }//        

        Object[] Borrar = Elementos.keySet().toArray();
        Arrays.sort(Borrar);
        int keyAnterior = 0;

        for (Object j : Borrar) {
            Long keyBorrar = Long.parseLong(j.toString());
            Integer numEliminar = new Integer(Elementos.get(keyBorrar));

            if (contenedor.containsKey(keyBorrar)) {
//                int keyAnterior = busquedaBinaria(keys, keyBorrar) - 1;                

                while (numEliminar > 0 && keyAnterior < keys.length - 1) {

                    if (contenedor.get((Long) keys[keyAnterior]) != null) {
                        int existentes = 0;
                        existentes = contenedor.get((Long) keys[keyAnterior]);

                        int restantes = numEliminar - existentes;

                        if (restantes >= 0) {
                            contenedor.remove((Long) keys[keyAnterior]);
                            numEliminar = restantes;

                        } else {
                            if (restantes < 0) {
                                contenedor.put(Long.parseLong(keys[keyAnterior].toString()), (restantes * (-1)));
                                numEliminar = 0;
                            }
                        }
                    }
                    keyAnterior++;
                }
            }
        }

        return contenedor;

    }

    public static void main(String[] arg) {
        long tiempoILectura, tiempoFLectura, duracionLectura,
                tiempoIProceso, tiempoFProceso, duracionProceso,
                tiempoISalida, tiempoFSalida, duracionSalida, tiempoIBorrado,
                tiempoFBorrado, duracionBorrado;

        // Despliegue del archivo       
        tiempoILectura = System.currentTimeMillis();
//        mostrarArchivo(archivo);
        tiempoFLectura = System.currentTimeMillis();
        duracionLectura = tiempoFLectura - tiempoILectura;
//------------------------------------------------------------------------------
        // Proceso
        tiempoIProceso = System.currentTimeMillis();
        // Actualizar con el proceso propuesto        
//        mostrarArchivo(archivo);
        HashMap<Long, Integer> arr = Proceso(archivo);
        //Ordenar llaves
        Object[] Keys = arr.keySet().toArray();
        Arrays.sort(Keys);

        tiempoFProceso = System.currentTimeMillis();
        duracionProceso = tiempoFProceso - tiempoIProceso;
        //termina proceso              

//------------------------------------------------------------------------------
        //Tiempo de borrado
        tiempoIBorrado = System.currentTimeMillis();

        arr = Borrador(arr, borrar, Keys);
//        System.out.println(arr.size());
        
        tiempoFBorrado = System.currentTimeMillis();
        duracionBorrado = tiempoFBorrado - tiempoIBorrado;

//------------------------------------------------------------------------------        
        // Generacion de la salida
        tiempoISalida = System.currentTimeMillis();
        // Actualizar con el proceso propuesto        
        Keys = arr.keySet().toArray();
        Arrays.sort(Keys);
        mostrar(arr, Keys);

//        System.out.println(arr.size());
//        System.out.println("-------------------------------------------------");
        tiempoFSalida = System.currentTimeMillis();
        duracionSalida = tiempoFSalida - tiempoISalida;
//-----------------------------------------------------------------------------
        
        System.out.println("DuracionLectura=" + duracionLectura + "ms \tEquivalente a " + duracionLectura / 1000 + " s"
                + "\nDuracionProceso=" + duracionProceso + "ms \tEquivalente a " + duracionProceso / 1000 + " s"
                + "\nDuracionSalida=" + duracionSalida + "ms \tEquivalente a " + duracionSalida / 1000 + " s"
                + "\nDuracionBorrado=" + duracionBorrado + "ms \tEquivalente a " + duracionBorrado / 1000 + " s"
        );
    }
}
