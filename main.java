import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Stream;

public class main {

    static Scanner entrada = new Scanner(System.in); // Defino la entrada del escaner como statico para usarlo en todas las funciones

    public static void main(String[] args) {
         String con;
        int opcion, entradasUsuario,opcion2;
        
        String opcionVehiculo;
        Tipado datos; //Almecenar datos basicos del vehiculo registrado
        ConstructorYate creadorYate = new ConstructorYate();
        ArrayList<Vehiculos> garaje = new ArrayList();  // Definino un arrayList de objetos
                                                        // para almacenar los diferentes objetos (tipos de vehiculos)

        do {
            mensaje("Deseas agregar un vehiculo\n 1. Sí\n 2. Mostrar garaje\n 3. Salir  ");
            try {   
                opcion = Integer.parseInt(entrada.nextLine());
            } catch (Exception e) {
                mensaje("Entrada invalida [EM1n]");
                opcion = 0;
                limpiarPantalla(1);
                continue;
            }
            
            switch (opcion) {
                case 1:
                    do{
                        if (garaje.size() >= 10){
                            mensajeAlerta("El garaje esta lleno");
                            break;
                        }
                        limpiarPantalla((float)0.001);
                        mensaje("Elija una opción: \n A. Avion\n B. Yate\n C. Carro\n D. Cancelar");
                        opcionVehiculo = entrada.nextLine().toUpperCase();

                        switch (opcionVehiculo) {

                            case "A":
                                datos = informacionVehiculo("Avion");

                                if (datos.getIntegridad()) {
                                    entradasUsuario = validarNumeroEntero("Ingrese altura maxima: ");
                                    if (entradasUsuario > 0) {
                                        garaje.add(new Avion(datos.getReferencia(), datos.getVelocidadMaxima(), datos.getColor(), entradasUsuario));
                                        mensajeAlerta("Vehiculo Agregado");
                                    } else {
                                        mensajeAlerta("Operacion Cancelada");
                                    }
                                } else {
                                    mensajeAlerta("Operacion Cancelada");
                                }
                                opcionVehiculo = "D";
                                break;

                            case "B":
                                datos = informacionVehiculo("Yate");

                                if (datos.getIntegridad()) {
                                    entradasUsuario = validarNumeroEntero("Ingrese peso: ");
                                    if (entradasUsuario > 0) {
                                        // Aplicación del patron BUILDER
                                        creadorYate.referencia(datos.getReferencia());
                                        creadorYate.velocidadMaxima(datos.getVelocidadMaxima());
                                        creadorYate.color(datos.getColor()).pesoMaximo(entradasUsuario); // Paso multiples constructores

                                        garaje.add(new Yate(creadorYate)); // Para crear Yate paso como parametro el constructor

                                        mensajeAlerta("Vehiculo Agregado");
                                    } else {
                                        mensajeAlerta("Operacion Cancelada");
                                    }
                                } else {
                                    mensajeAlerta("Operacion Cancelada");
                                }
                                opcionVehiculo = "D";
                                break;

                            case "C":
                                datos = informacionVehiculo("Carro");

                                if (datos.getIntegridad()) {
                                    entradasUsuario = validarNumeroEntero("Ingrese numero de puertas: ");
                                    if (entradasUsuario > 0) {
                                        garaje.add(new Carro(datos.getReferencia(), datos.getVelocidadMaxima(), datos.getColor(), entradasUsuario));
                                        mensajeAlerta("Vehiculo Agregado");
                                    } else {
                                        mensajeAlerta("Operacion Cancelada");
                                    }
                                } else {
                                    mensajeAlerta("Operacion Cancelada");
                                }
                                opcionVehiculo = "D";
                                break;

                            case "D":
                                mensajeAlerta("Operacion cancelada");
                                opcionVehiculo = "D";
                                break;
    
                            default:
                                mensaje("Ingreso una opcion incorrecta [M2c]");
                                continue;
                        }
                    } while (opcionVehiculo != "D");
                    limpiarPantalla(2);
                    break;

                case 2:
                    
                    limpiarPantalla((float) 0.5);
                    if (garaje.size() == 0){
                        mensajeAlerta("El garaje esta vacio");
                    } else {
                        
                        
                        do{
                         mensaje("1.ver los vehiculos de orden de mayor a menor velocidad\n 2.ver los vehiculos desorden\n 3.Vehiculo con Velocidad Max\n 4.Vehiculo con Velocidad Min\n 5.Mostrar Vehiculos Color Negro o Azul\n 6.Concatenar Referencias de los Vehiculos BLANCOS o VERDE \n 7.Salir");
                            try{
                               opcion2=entrada.nextInt();
                                System.out.println("\n");
                             }catch (Exception e){
                                 mensaje("Entrada invalida [EM1n]");
                                opcion2 = 7;
                 
                             }
                        
                         switch(opcion2){
                             case 1:
                                 ordena(garaje);
                                 muestra(garaje);
                                
                                break;
                             case 2:
                                 Collections.shuffle(garaje);
                                 muestra(garaje);
                                 mensaje("\n");
                                break;
                             case 3:
                                 ordena(garaje);
                                 mensaje("Vehiculo Con Velocidad Maxima");
                                 mensaje(garaje.get(0).getClass().getName() + "\t[ID: " + garaje.get(0).hashCode() + "]");
                                 mensaje(garaje.get(0)+"");
                                 mensaje("\n");
                                 break;
                             case 4: 
                                 ordena(garaje);
                                 mensaje("Vehiculo Con Velocidad Minima");
                                 mensaje(garaje.get(garaje.size()-1).getClass().getName() + "\t[ID: " + garaje.get(garaje.size()-1).hashCode() + "]");
                                 mensaje(garaje.get(garaje.size()-1)+"");
                                 
                                 break;
                             case 5:
                                    garaje.stream().filter(x -> x.getColor().equals(x.getColor().NEGRO)).forEach(x -> System.out.println("-----------\n"+x+"\n"));
                                    garaje.stream().filter(x -> x.getColor().equals(x.getColor().AZUL)).forEach(x -> System.out.println("-----------\n"+x+"\n"));
                                    
                                 break;
                             case 6:
                                garaje.stream().filter(j -> j.getColor().equals(j.getColor().BLANCO)).forEach(j -> System.out.print("Referencias: "+j.getReferencia()));
                                garaje.stream().filter(j -> j.getColor().equals(j.getColor().VERDE)).forEach(j -> System.out.print(j.getReferencia()));
                                mensaje("");
                                break;
                             case 7:
                                 System.out.println("Exit\n");
                                 break;
                                 
                             default:
                                mensaje("Ingreso una opcion incorrecta [M2c]\n");
                                
                         }   
                         
                        }while(opcion2!=7);
                        // Utlizacion de la accion CLONAR (patron PROTOTYPE)
                        mensaje("\nIngrese [S] para clonar un vehiculo o [Enter] para volvel al menu");
                        if (entrada.nextLine().trim().toUpperCase().equals("S")) {
                            if (garaje.size() >= 10){
                                mensajeAlerta("El garaje esta lleno");
                            } else {
                                try {
                                    garaje.add((Vehiculos)garaje.get(validarNumeroEntero("\nSeleccione el vehiculo a clonar")-1).clonar());
                                    mensajeAlerta("Vehiculo clonado corectamente");
                                } catch (Exception e) {
                                    mensajeAlerta("Error");
                                    mensaje("\n" + e);
                                }
                            }
                        }
                    }
                    limpiarPantalla(2);
                    break;

                case 3:
                    mensajeAlerta("Hasta luego!");
                    break;
                    

                default:
                    mensaje("Digito una opcion incorrecta [M1n]");
                    limpiarPantalla(1);
            }
        } while (opcion != 3);
        entrada.close();
    }

    public static Tipado informacionVehiculo(String tipo) {
        
        boolean error = false;
        String referencia;
        int selector, velocidadMax = 0;
        Colores color  = Colores.NEGRO;

        mensaje("Ingrese los datos del " + tipo);

        do {
            error = false;
            mensaje("Ingrese referencia:");
            referencia = entrada.nextLine().trim();
            if (referencia.length() > 0) {
                if (referencia.equals("0")) {
                    error = true;
                }
                break;
            } else {
                mensaje("Entrada invalida (ingrese 0 para cancelar): ");
                limpiarPantalla(1);
            }
        } while (true);
        
        if (!error) {
            velocidadMax = validarNumeroEntero("Ingrese velocidad maxima: ");
            if (velocidadMax <= 0) {
                error = true;
            }
        }

        if (!error) {
            do {
                error = false;
                mensaje("Ingrese color: ");
                for (Colores colorIndice:Colores.values()) {
                    mensaje("   " + (colorIndice.ordinal() + 1) + ". "+colorIndice.toString());
                }
                selector = validarNumeroEntero("");
                if (selector == 0) {
                    error = true;
                    break;
                } else {
                    try {
                        color = Colores.values()[selector-1];
                    }
                    catch (Exception er) {
                        mensaje("Seleccion invalida (ingrese 0 para cancelar)");
                        limpiarPantalla(1);
                        error = true;
                    }
                    if (!error) break;
                }
            } while (true);
        }

        if (!error) {
            return new Tipado(referencia, velocidadMax, color);
        } else {
            return new Tipado(false);
        }
    }

    public static int validarNumeroEntero(String mensaje) {
        boolean salir;
        int numero = -1; 
        do {
            salir = true;
            mensaje(mensaje);
            try {
                numero = -1;
                numero = Integer.parseInt(entrada.nextLine());
            } catch (Exception e) {
                mensaje("Entrada invalida (ingrese 0 para cancelar)\n" + e);
                salir = false;
                limpiarPantalla(1);
            }
            if (numero < 0) {
                salir = false;
            }
        } while (!salir);
        return numero;
    }

    public static void mensajeAlerta(String msj) {
        limpiarPantalla((float)0.01);
        mensaje(new String(new char[msj.length() + 4]).replace("\0", "="));
        mensaje("| " + msj + " |");
        mensaje(new String(new char[msj.length() + 4]).replace("\0", "="));
    }

    public static void mensaje(String msj) {
        System.out.println(msj);
    }

    //para liempiar la consola.
    public static void limpiarPantalla(float tiempo) {
        try {
            Thread.sleep((int)(tiempo * 1000));
            if (System.getProperty("os.name").contains("Windows")) {new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();}
            else {Runtime.getRuntime().exec("clear");}
        } catch (Exception error) {
            System.out.print(error);
        }//*/
        
    }
    
    
    public static void ordena(ArrayList<Vehiculos> nombre){
        Collections.sort(nombre,new Comparator<Vehiculos>(){
            public int compare(Vehiculos p1, Vehiculos p2) {
          return new Integer(p2.getVelocidadMaxima()).compareTo(new Integer(p1.getVelocidadMaxima()));
            }
          }
      );
        
    }
   
    
    
    public static void muestra(ArrayList<Vehiculos> nombre){
        /* for (int i = 0; i < nombre.size(); i++){
              mensaje(nombre.get(i).getClass().getName() + "\t[ID: " + nombre.get(i).hashCode() + "]");
              mensaje(nombre.get(i)+"");
              mensaje("------------------------.--");
           }*/ 
        nombre.forEach(person -> {
            System.out.println(nombre.get(nombre.indexOf(person)).getClass().getName() + "\t[ID: " + nombre.get(nombre.indexOf(person)).hashCode() + "]");
            System.out.println(person); 
            System.out.println("--------------------------");
        });
       
       
        
    }

   
    
    
   
}