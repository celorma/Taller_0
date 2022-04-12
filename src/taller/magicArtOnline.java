package taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class magicArtOnline {
	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner (System.in);
		
		File filePlayers = new File ("Jugadores.txt");
		File fileSpells = new File ("Hechizos.txt");
		File filePlayerSpells = new File ("HechizosJugadores.txt");
		File fileEnemy = new File ("Enemigo.txt");
		
		Scanner archJugadores = new Scanner(filePlayers);
		Scanner archHechizos = new Scanner(fileSpells);
		Scanner archHechizoJugadores = new Scanner(filePlayerSpells);
		Scanner archEnemigos = new Scanner(fileEnemy);
		
		int largo = 100;
		
		String[][] datosEnemigos = new String[][] {{"F","C","B","A","S"}, //Clases
												   {"100","250","500","750","1000"}, //Experiencia otorgada
												   {"75","50","25","10","1"}, //Probabilidad de aparición
												   {"1","2","5","10","20"}}; // Puntos de estadística
		
		String[][] jugadores = new String[largo][8];
		String[][] hechizos = new String[largo][2];
		String[][] hechizosJugadores = new String[largo][2];
		String[][] enemigos = new String[largo][5];

		rellenarMatriz(archJugadores, jugadores);
		rellenarMatriz(archHechizos, hechizos);
		rellenarMatriz(archHechizoJugadores, hechizosJugadores);
		rellenarMatriz(archEnemigos, enemigos);
		
		
		System.out.println("prueba");
		
		sc.close();
	}

	private static boolean buscarElemento(String[][] matriz, String elemento,int indiceInicial) 
	{
		for (int i=0;i<matriz.length;i++)
		{
			for (int j=0;indiceInicial<matriz[0].length;j++)
			{
				if (elemento.equals(matriz[i][j]))
				{
					return true;
				}
				
			}
		}
		return false;
	}
	
	private static void ingresarListaEnMatriz(String[] lista,String[][] matriz) 
	{
		int i = 0;
		while(matriz[i][0] != null)
		{
			i++;
		}
		matriz[i] = lista;
	}
	
	private static void imprimirMatriz(String[][] matriz, int indiceInicial) 
	{
		for (int i=0;i<matriz.length;i++)
		{
			System.out.print("[ ");
			for (int j=indiceInicial;j<matriz[0].length;j++)
			{
				System.out.print(matriz[i][j]+" ");
			}
			System.out.println("]");
		}
	}

	private static void rellenarMatriz(Scanner archivo, String[][] matriz) 
	{
		int cont = 0;
		while (archivo.hasNextLine())
		{
			String[] partes = archivo.nextLine().split(",");
			matriz[cont] = partes;
			cont++;
		}
	}

}
