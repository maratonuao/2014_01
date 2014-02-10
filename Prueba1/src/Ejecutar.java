
import java.util.Scanner;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oarboleda
 */
public class Ejecutar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Aplicativo basico, solo para probar Git\n ");                
        System.out.print("Digite el numero de ejecuciones:  ");
        int veces = in.nextInt();
        
        for(int i=0; i<veces; i++){
            System.out.println((i+1)+".  Hola mundo");
        }        
    }
}
