import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner myObj = new Scanner(System.in);

        Random rand = new Random();
        int n = rand.nextInt(10);

        int numero = 0;

        while(numero != n){
            System.out.print("Cual es el numero que estoy pensando: ");
            numero = myObj.nextInt();
                if(numero == n){
                    System.out.print("Adivinaste");
                    break;
                }else if(numero > n){
                    System.out.println("El numero a adivinar es menor que el que mencionaste");
                }else if(numero < n){
                    System.out.println("El numero a adivinar es mayor que el que mencionaste");
                }else{
                    System.out.print("Esta es una opcion no valida");
                }
        }
        
        
    }
}
