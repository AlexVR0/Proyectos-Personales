import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {

        Scanner myScan = new Scanner(System.in);
        HashMap<String, Long> agenda = new HashMap<String, Long>();

        //Nombre de archivo y creacion
        String nombreArchivo = "agenda.txt";
        File myArchivo = new File(nombreArchivo);

        //Carga de Documento agenda.txt
        if(myArchivo.exists()){
            try (BufferedReader myReader = new BufferedReader(new FileReader(myArchivo))){
                String line;
                while ((line = myReader.readLine()) != null){
                    String[] parts = line.split(":");
                    if (parts.length == 2){
                        agenda.put(parts[0], Long.valueOf(parts[1])); //Long.valueOf regresa el valor del String en forma de Long
                    }
                }
                System.out.println("Agenda cargada correctamente");
            } catch (IOException e){
                System.out.println("Error al cargar la agenda");
                e.printStackTrace();
            }
        }

            for(int opcion = 0; opcion != 6;){
                //Menu de opciones
                System.out.print("""
                1. Agregar un contacto 
                2. Buscar un contacto 
                3. Eliminar un contacto 
                4. Actualizar un contacto 
                5. Mostrar todos los contactos 
                6. Guardar y Salir 
                """);

            
                System.out.print("Ingresa una opcion: ");
                opcion = myScan.nextInt();
                myScan.nextLine(); //Limpiar el buffer


                switch(opcion){
                    case 1:
                        System.out.println("Ingresa el nombre y numero de contacto");
                        System.out.print("Ingresa el nombre: ");
                        String nombre = myScan.nextLine();
                        System.out.print("Ingresa el numero sin guiones ni espacios: ");
                        long numero = myScan.nextLong();

                        boolean numeroExiste = false;
                        //Comprobrobar si un numero esta repetido
                        for(Map.Entry<String, Long> entry : agenda.entrySet()){
                            if(entry.getValue().equals(numero)){
                                numeroExiste = true;
                                break;
                            }
                        }

                        if(numeroExiste == true){
                            System.out.println("Este numero ya lo tiene asignado otro contacto");
                        } else{
                            agenda.put(nombre, numero);
                            System.out.println("El contacto fue guardado exsitosamente!");
                        }

                        break;
                    case 2:
                        System.out.print("""
                        Buscar por nombre ingresa 1
                        Buscar por numero ingresa 2
                        """);

                        System.out.print("Ingresa una opcion: ");
                        int opcionBuscar = myScan.nextInt();
                        myScan.nextLine(); //Limpiar el buffer

                        //Busqueda por nombre
                        if(opcionBuscar == 1){
                            System.out.print("Ingresa el nombre del contacto que buscas: ");
                            String nombreBuscado = myScan.nextLine();
                            if(agenda.containsKey(nombreBuscado)){
                                for(Map.Entry<String, Long> entry : agenda.entrySet()){
                                    if(entry.getKey().equals(nombreBuscado))
                                    System.out.println("El contacto: " + entry.getKey() + ", tiene el numero telefonico: " + entry.getValue() + "\n");
                                }
                            } else {
                                System.out.println("Contacto no encontrado, intente de nuevo \n");
                            }
                        //Busqueda por numero
                        } else if(opcionBuscar == 2){
                            System.out.print("Ingresa el numero telefonico del contacto que buscas: ");
                            Long numBuscado = myScan.nextLong();

                            //Imprimir el nombre de la persona si el valor de la llave coincide
                            if(agenda.containsValue(numBuscado)){
                                for(Map.Entry<String, Long> entry : agenda.entrySet()){
                                    if(entry.getValue().equals(numBuscado)){
                                        System.out.println("El Nombre de la persona asignada a este numero es: " + entry.getKey() + "\n");
                                    }
                                }
                            } else {
                                System.out.println("Contacto no encontrado, intente de nuevo");
                            }
                        } else{
                            System.out.println("Esta no es una opcion valida, intente de nuevo");
                        }

                        break;
                    case 3:
                        System.out.print("""
                        De que manera quieres eliminar tu contacto
                        1. Eliminar por nombre
                        2. Eliminar por numero telefonico
                        """);

                        System.out.print("Ingresa una opcion: ");
                        int opcionEliminar = myScan.nextInt();
                        switch(opcionEliminar){
                            case 1:
                                //Eliminar contacto en base a su nombre
                                System.out.print("Escribe el nombre del contacto que quieres elminiar: ");
                                myScan.nextLine(); // Limpiar buffer
                                String elimCont = myScan.nextLine();

                                //Elimina el contacto del HashMap agenda por medio de su llave
                                if(agenda.containsKey(elimCont)){
                                    System.out.println("Estas seguro que deseas eliminar este contacto? \n si para confirmar \n no para regresar al menu principal");
                                    System.out.print("Ingresa una opcion: ");
                                    String confirmacion = myScan.nextLine();
                                    if(confirmacion.equals("si")){
                                        agenda.remove(elimCont);
                                        System.out.println("Contacto eliminado correctamente");
                                    } else if(confirmacion.equals("no")){
                                        System.out.println("El contacto no ah sido borrado, regresando al menu principal");
                                    } else{System.out.println("Esta no es una opcion valida, intente de nuevo");}

                                } else{
                                System.out.println("Contacto no encontrado, intente de nuevo");
                                }
                                break;
                            case 2:
                                //Eliminar contacto de HashMap agenda en base a su numero
                                System.out.print("Ingresa el numero del contacto que quieres eliminar: ");
                                Long elimContNumero = myScan.nextLong();
                                if(agenda.containsValue(elimContNumero)){
                                    //Confirmar si desea eliminar su contacto
                                    System.out.println("Estas seguro que deseas eliminar este contacto? \n si para confirmar \n no para regresar al menu principal");
                                    System.out.print("Ingresa una opcion: ");
                                    myScan.nextLine(); //Limpiar buffer
                                    String confirmacion = myScan.nextLine();
                                    if(confirmacion.equals("si")){
                                        //Eliminar contacto del HashMap mediante su valor
                                        for(Map.Entry<String, Long> entry : agenda.entrySet()){
                                            if(entry.getValue().equals(elimContNumero)){
                                                String llave = entry.getKey();
                                                agenda.remove(llave);
                                                System.out.println("Contacto eliminado correctamente");
                                                break;
                                            }
                                        }
                                    } else if(confirmacion.equals("no")){
                                        System.out.println("El contacto no ah sido borrado, regresando al menu principal");
                                    } else{System.out.println("Esta no es una opcion valida, intente de nuevo");}
                                    
                                } else {
                                    System.out.println("Este numero de contacto no existe en la agenda");
                                }
                                break;
                        }          
                        break;
                    case 4:
                        System.out.println("""
                        Que deseas actualizar de tu contacto?
                        1. Numero telefonico
                        2. Nombre """);
                        System.out.print("Ingresa una opcion: ");
                        int opcionActualizar = myScan.nextInt();
                        switch(opcionActualizar){
                            case 1:
                                System.out.print("Ingresa el nombre del contacto a modificar: ");
                                myScan.nextLine(); //Limpiar Buffer
                                String clave = myScan.nextLine();
                                if(agenda.containsKey(clave)){
                                    System.out.print("Ingresa el nuevo numero de la persona: ");
                                    Long nuevoNumero = myScan.nextLong();
                                    //Verificar si otra persona ya tiene ese numero
                                    boolean numeroExisteAct = false;
                                    for(Map.Entry<String, Long> entry : agenda.entrySet()){
                                        if(entry.getValue().equals(nuevoNumero)){
                                            numeroExisteAct = true;
                                            break;
                                        }
                                    }
                                    if(numeroExisteAct == true){
                                        System.out.println("Este numero ya lo tiene asignado otro contacto");
                                    } else{
                                        agenda.replace(clave, nuevoNumero);
                                        System.out.println("El contacto fue actualizado correctamente");
                                    }   
                                } else {
                                    System.out.println("El contacto que quieres modificar no existe");
                                }
                            break;
                            case 2:
                                System.out.print("Ingresa el nombre del contacto a modificar: ");
                                myScan.nextLine(); //Limpiar Buffer
                                String clave2 = myScan.nextLine();
                                if(agenda.containsKey(clave2)){
                                    //Almacena el numero antes de borrar la clave
                                    Long tempNumero = agenda.get(clave2);
                                    System.out.print("Ingresa el nuevo nombre asignado el telefono: ");
                                    String nuevoNombre = myScan.nextLine();
                                    //Verificar si otra persona ya tiene ese nombre
                                    boolean nombreExisteAct = false;
                                    for(Map.Entry<String, Long> entry : agenda.entrySet()){
                                        if(entry.getKey().equals(nuevoNombre)){
                                            nombreExisteAct = true;
                                            break;
                                        }
                                    }
                                    if(nombreExisteAct == true){
                                        System.out.println("Este nombre ya lo tiene asignado otro contacto");
                                    } else{
                                        agenda.remove(clave2);
                                        agenda.put(nuevoNombre, tempNumero);
                                        System.out.println("El contacto fue actualizado correctamente");
                                    }
                                } else {
                                    System.out.println("El contacto que quieres modificar no existe");
                                }
                            break;
                        }
                        break;
                    case 5:
                        //Imprime los contactos de la agenda
                        System.out.println("CONTACTOS DE LA AGENDA");
                        for(String contacto : agenda.keySet()){
                            System.out.println("Nombre: " + contacto + " \nNumero Telefonico: " + agenda.get(contacto));
                        }
                        break;
                    case 6:
                        try(BufferedWriter myWriter = new BufferedWriter(new FileWriter(myArchivo))){
                            for(Map.Entry<String, Long> entry: agenda.entrySet()){
                                myWriter.write(entry.getKey() + ":" + entry.getValue());
                                myWriter.newLine();
                            }
                            System.out.println("Los contactos se guardaron correctamente, Hasta luego!");
                        } catch (IOException e){
                            System.out.println("Error al guardar la agenda");
                            e.printStackTrace();
                        }
                        break;
                    default:
                        System.out.println("Opcion invalida, intente de nuevo");
                }
            }
        myScan.close();
    }
}