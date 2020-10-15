package mochila;

import java.util.*;

public class Mochila {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Estas ArrayList son para guardar los elementos que nos dan el mejor resultado, se usara mas adelante.
        ArrayList<Integer> valores = new ArrayList<>();
        ArrayList<Integer> valores3 = new ArrayList<>();

        System.out.print("Digite la cantidad de elementos: ");
        int cant = sc.nextInt();//Digite la cantidad de elementos que quiere añadir

        if (cant == 1) { //Si la cantidad es 1 elemento, entonces ese sera el unico elemento que se usara
            //creamos la lista de elementos con el codigo de abajo
            int[][] elemento = new int[cant][cant + 1];
            pedir_datos(elemento, cant); //y pedimos los datos que nos solicita en la pregunta anterior
            System.out.print("\nEl unico posible elemento para llenar la mochila es: ");
            //como solo hay un elemento, pues ese sera el unico con el que se llenara la mochila
            System.out.println("[" + elemento[0][0] + "][" + elemento[0][1] + "]");

        } else {//si es mas de un elemento hacemos el siguiente codigo

            /*  
                Creamos la matriz donde se guardara los elementos donde [x][0] 
                Sera el volumen y [x][1] sera el beneficio
             */
            int[][] elemento = new int[cant][2];
            //  Aqui pedimos los datos necesarios
            pedir_datos(elemento, cant);
            //  Imprimimos la matriz original
            System.out.println("\nMatriz Original");
            imprimir(elemento);
            /*  
                Despues de imprimir la matriz de como la ingresamos, la ordenamos 
                linkeando el volumen y el beneficio
             */
            ordenar(cant, elemento);
            //imprimimos la matriz ordenada de menor a mayor volumen
            System.out.println("\nMatriz Modificada");
            imprimir(elemento);

            /*  La matriz ya esta ordenada del volumen menor al mayor, 
                El codigo de la mochila ya va aqui*/
            //tomamos en que el tamaño de la mochila es el ultimo volumen mayor
            int lengthBag = elemento[cant - 1][0];

            System.out.println("\nEl tamaño de la mochila es: " + lengthBag);

            //tomamos como referencia el primer elemento pero eso cambiara mas adelante
            int vol = elemento[0][0], ben = elemento[0][1];
            int auxvol, auxben, auxvol2 = 0, auxben2 = 0;//son datos auxiliares para mas adelante
            /* Toma el primer elemento y conje su volumen y si lo suma con el 
            volumen del siguiente elemento y es menor al tamaño*/

            for (int j = 1; j < elemento.length; j++) {
                if (elemento[j][0] > vol) {
                    vol = elemento[j][0];//Aqui tomamos el mayor elemento 
                    ben = elemento[j][1];// con su beneficio es decir, el ultimo elemento

                    for (int n = 0; n < cant; n++) {
                        //aqui recorremos desde el primer elemento hacia adelante y en el siguiente ciclo for
                        //cogemos elemento por elemento anterior de ese
                        auxvol = elemento[n][0];
                        auxben = elemento[n][1];
                        for (int k = n - 1; k >= 0; k--) {
                            //tomamos el penultimo elemento despues de elemento escogido arriba
                            if (n == 0) {//pero si el elemento escogido es el primero, cerramos el ciclo for
                                k++;
                            }

                            /*  Sumamos el dato seleccionado arriba y lo sumamos con el
                                elemento anterior a ese, si esa suma es menor o igual al tamaño de la mochila
                                lo sumamos con el elemento que teniamos antes y le sumamos el beneficio que 
                                en el elemento seleccionado nos ha dado.
                             */
                            if ((auxvol + elemento[k][0]) <= lengthBag) {

                                auxvol += elemento[k][0]; //suma del elemento anterior al que se escogio arriba
                                auxben += elemento[k][1]; //suma del beneficio anterior al que se escogio arriba

                                //el valor de K lo guardamos en el ArrayList
                                valores.add(k);
                            }

                            /*  
                                Si la suma de los elementos anteriores es igual al tamaño de la mochila
                                y la suma del auxben es mayor al auxben2 (Donde lo traducimos como el mejor caso)
                                entonces cambie los valores a los auxiliares auxvol2 y auxben2
                             */
                            if (auxvol == lengthBag && auxben > auxben2) {
                                valores.add(n);//agregamos N al array para ver el elemento que seleccionamos

                                //creamos un array list dentro de la condicional para que sea una lista en blanco
                                ArrayList<Integer> valores2 = new ArrayList<>(valores);
                                auxvol2 = auxvol;//guardamos el volumen del mejor de los casos 
                                auxben2 = auxben;// guardamos el beneficio mejor de los casos
                                valores3 = valores2;//y en el array de arriba lo copio con los valores de la lista 2
                            }
                        }
                        //ya que tengo que eliminar los valores es por eso que se creo otra lista
                        valores.removeAll(valores);
                    }
                }
            }
            if (auxben2 > ben) {
                System.out.println("\nEl volumen es: " + auxvol2 + "\nY el mejor beneficio es de: " + auxben2 + "\nde los volumenes y beneficios son: ");
                int a = valores3.size();
                Collections.sort(valores3);
                for (int i = 0; i < a; i++) {
                    int num = valores3.get(i);
                    System.out.println("[" + elemento[num][0] + "][" + elemento[num][1] + "]");
                }
            } else {
                System.out.println("\nEl volumen es: " + vol + "\nY el mejor beneficio es de: " + ben + "\nel volumen y el beneficio es el mayor elemento:");
                System.out.println("");
                System.out.println("[" + elemento[cant - 1][0] + "][" + elemento[cant - 1][1] + "]");
            }
        }
    }

    public static void pedir_datos(int a[][], int cant) {
        for (int i = 0; i < cant; i++) {
            System.out.print("\nDigite el volumen del elemento: ");
            int vol = sc.nextInt();
            System.out.print("Digite el beneficio del elemento: ");
            int ben = sc.nextInt();
            a[i][0] = vol;
            a[i][1] = ben;
        }
    }

    public static void ordenar(int cant, int a[][]) {
        //Organizar la matriz con un algortimo estilo burbuja pero modificado para nuestro codigo
        for (int i = 0; i <= cant; i++) {
            for (int j = 1; j < cant; j++) {
                int auxf, auxc;
                if (a[j - 1][0] > a[j][0]) {
                    auxf = a[j - 1][0];
                    auxc = a[j - 1][1];
                    a[j - 1][0] = a[j][0];
                    a[j - 1][1] = a[j][1];
                    a[j][0] = auxf;
                    a[j][1] = auxc;
                }
            }
        }
    }

    public static void imprimir(int a[][]) {
        for (int x = 0; x < a.length; x++) {
            System.out.println("");
            for (int y = 0; y < 2; y++) {
                System.out.print("[" + a[x][y] + "]");
            }
            System.out.println("");
        }
    }
}
