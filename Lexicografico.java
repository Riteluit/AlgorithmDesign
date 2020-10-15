package Main;

import java.util.Scanner;

public class Lexicografico {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String pal = sc.nextLine().toUpperCase(); // Lee la palabra entrate y lo convierte en mayuscula
		String[] word = pal.split(""); // Separa cada palabra y lo guarda en el Array "word"
		Invertir(Traducir(Ordenamiento_Burbuja(Convertir(word)))); // Llamada de funciones e imprimir resultados,
	}

	public static int[] Convertir(String[] a) { // Vamos a convertir la palabra en un codigo ASCII
		int num[]; // Crear cadena de caracteres para los numeros ascii
		num = new int[a.length];
		for (int i = 0; i <= a.length - 1; i++) {
			char b = a[i].charAt(0);// Se toma la primera letra
			int ascii = (int) b;// convertir char o palabra en numero ascii
			if (ascii <= 78) { // (A - N=78)
				num[i] = (ascii - 1); // se resta para añadir la letra Ñ
			} else {
				if (ascii == 209 || ascii == 241) { // Si la palabra es Ñ pongale de codigo ascii 78
					num[i] = 78;
				} else {
					num[i] = ascii; // Si esta entre (O y la Z) solo guardelo en el Array
				}
			}
		}
		return num;
	}

	public static int[] Ordenamiento_Burbuja(int[] a) {
		int temp; 	// Utilizamos este metodo para organizar los codigos ASCII, Asi quedan ordenadas
					// las palabra oreden alfabetico
		for (int k = 1; k < a.length; k++) {
			for (int j = 0; j < a.length - 1; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		return a;
	}

	public static String[] Traducir(int[] a) { // Pasar del codigo ascii a String
		String Conv[]; // crear un Array para las palabras convertidas
		Conv = new String[a.length];
		for (int i = 0; i <= a.length - 1; i++) {
			if (a[i] <= 77) {// Si el codigo es menor de 77
				char b = (char) a[i];
				Conv[i] = Character.toString((char) (b + 1)); // A ese codigo sumele 1, que se le habia restado en
																// convertir
			} else {
				if (a[i] == 78) { // Si es 78 significa que es una Ñ
					Conv[i] = Character.toString((char) (209)); // Agrege la Ñ al Array en ese mismo orden
				} else {
					char b = (char) a[i];
					Conv[i] = Character.toString((char) b); // convertir ese valor a String
				}
			}
		}
		return Conv; // Retornamos las palabras en orden alfabetico
	}

	public static void Invertir(String[] a) {
		System.out.println();
		for (int j = a.length; j > 0; j--) {
			System.out.print(a[j - 1]); // Toma el ultimo de la lista y lo imprime al prinicipio, hasta que su posicion
										// llegue a 0
		}
	}

}
