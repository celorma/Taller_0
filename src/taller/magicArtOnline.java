package taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class magicArtOnline 
{
	public static void main(String[] args) throws FileNotFoundException 
	{
		File filePlayers = new File ("Jugadores.txt");
		File fileSpells = new File ("Hechizos.txt");
		File filePlayerSpells = new File ("HechizosJugadores.txt");
		File fileEnemy = new File ("Enemigo.txt");
		
		Scanner archPlayers = new Scanner(filePlayers);
		Scanner archSpells = new Scanner(fileSpells);
		Scanner archPlayerSpells = new Scanner(filePlayerSpells);
		Scanner archEnemy = new Scanner(fileEnemy);
		
		int longArray = 100;
		
		String user = null, password = null, registerConfirmation = null;
		String menu = null;
		String combatType = null;
		String enemy = null;
		
		String[] newPlayer = new String[] {"nuevoUsuario","nuevaContraseña","5","5","5","5","1","0"};
		String[] enemyStats = null;
		
		String[][] enemyData = new String[][] {{"S","1000","1" ,"20"},
							                   {"A","750" ,"10","10"},
							                   {"B","500" ,"25","5" },
							                   {"C","250" ,"50","2" },
							                   {"F","100" ,"75","1" }};
		
		String[][] players = new String[longArray][8];
		String[][] spells = new String[longArray][2];
		String[][] playerSpells = new String[longArray][2];
		String[][] enemies = new String[longArray][5];
		
		
		

		fillArray(archPlayers, players);
		fillArray(archSpells, spells);
		fillArray(archPlayerSpells, playerSpells);
		fillArray(archEnemy, enemies);
		
		
		
		do
		{
			user = input("Ingrese su nombre de usuario: ");
			password = input("Ingrese su contraseña: ");
			division();
			
			if (!search(players,user,0,0)) 
			{
		    	registerConfirmation = input("Usuario no encontrado, desea registrarse?(SI/NO): ");
		    	
		    	if (registerConfirmation.equals("SI"))
				{
		    		do
		    		{
						user = input("Ingrese un nuevo nombre de usuario: ");
						password = input("Ingrese una nueva contraseña: ");
						
						if (!search(players,user,0,0)) 
						{
							System.out.println();
							System.out.println("Registrado correctamente!");
							newPlayer[0] = user;
							newPlayer[1] = password;
							enterListIntoArray(newPlayer, players);
							division();
							break;
						}
						
						else
						{
							division();
							System.out.println("Nombre de usuario no disponible, ingrese uno distinto");
						}
						
		    		}while (search(players,user,0,0));
				}
			}
			
			if (!compare(players,user,password))
			{
				System.out.println("Contraseña equivocada, intente de nuevo");
			}
		
		}while(!compare(players,user,password));
		
		System.out.println("Bienvenido "+user+", qué desea hacer?: ");
		System.out.println();
		System.out.println("*Pelear contra un enemigo");
		System.out.println("*Aprender hechizo");
		System.out.println("*Ver estadisticas de un jugador");
		System.out.println("*Ver estadisticas de hechizos");
		System.out.println("*Ver ranking de jugadores con mas experiencia");
		System.out.println();
		menu = input("Ingresar opción: ");
		division();
		
		if (menu.equals("Pelear contra un enemigo"))
		{
			
			
			
			System.out.println("Qué tipo de combate desea?");
			System.out.println();
			System.out.println("*JcE");
			System.out.println("*JvJ");
			System.out.println();
			combatType = input("Ingresar opción: ");
			division();
			
			if (combatType.equals("JcE"))
				enemyStats = enemy(enemies,enemyData);
				enemy = enemyStats[0];
			{
				System.out.println(user+" se ha encontrado con "+enemy);
			}
		}
	}



	private static void division() {
		System.out.println();
		System.out.println("o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o-o");
		System.out.println();
	}
	
	private static String input(String print) {
		Scanner sc = new Scanner (System.in);
		System.out.print(print);
		String variable = sc.nextLine();
		return variable;
	}
	
	private static void enterListIntoArray(String[] list,String[][] array) //Ingresa una lista a la primera fila de elementos nulos de una matriz
	{
		int i = 0;
		while(array[i][0] != null)
		{
			i++;
		}
		array[i] = list;
	}
	
	private static void printArray(String[][] array, int initialColumn) //Imprime una matriz desde una columna Inicial hasta el máximo de columnas de la matriz misma
	{
		for (int i=0;i<array.length;i++)
		{
			System.out.print("[ ");
			for (int j=initialColumn;j<array[0].length;j++)
			{
				System.out.print(array[i][j]+" ");
			}
			System.out.println("]");
		}
	}

	private static void fillArray(Scanner archive, String[][] array) //Rellena una matriz ya creada con los elementos de un respectivo archivo
	{
		int count = 0;
		while (archive.hasNextLine()) //Si detecta que hay una línea en el archivo, sigue leyendo
		{
			String[] parts = archive.nextLine().split(","); //Se crea una lista con los elementos del archivo separado por comas
			array[count] = parts; //Se remplaza la fila de la matriz por la lista "partes" actual
			count++;
		}
	}
  // en teoria le das una palarabra (chekstring) y este te retorna true o false buscando en la matriz true es que esta y false es que no esta la palabra en la matriz
	private static boolean search(String [][] array,String chekstring, int initialColumn, int finalColumn) 
	{
		for (int i=0;i<array.length;i++)
		{
			for (int j=initialColumn;j<=finalColumn;j++) 
			{
				if (array[i][j] != null)
				{
					if (array[i][j].equals(chekstring)) 
					{
					return true;
					}
				}
			}
				
		}
		return false;
	}
	
	private static boolean compare(String[][] array,String user1, String password2) // ver si estan en misma fila 
	{
		for (int i=0;i<array.length;i++)//cantidad de filas
		{
			if (array[i][0] != null && array[i][1] != null)
			{
				if (array[i][0].equals(user1) && array[i][1].equals(password2)) 
		      	{
		    	  	return true;
	      		}	
			}
		}
    return false;
	}
	
	private static String enemyClass(String[][] enemyData) 
	{
		
		double[] probabilities = new double[enemyData.length];
	    double[] acumulated = new double[enemyData.length];
	    
	    double previus = 0;
	    double probability = Math.random();
	    String classOfEnemy = null;
	    
	    
	    for (int i=0;i<enemyData.length;i++)
	    {
	    	
	    	probabilities[i] = Double.parseDouble(enemyData[i][2])/(100*1.61);
	    	acumulated[i] = probabilities[i] + previus;
			previus = acumulated[i];
			if (probability<acumulated[i])
		    {
		    	classOfEnemy = enemyData[i][0];
		    	break;
		    }
	    }
	    
	    return classOfEnemy;
	    
	}
	
	private static String[] enemy(String[][] enemies,String[][] enemyData) 
	{
		String enemyClass = enemyClass(enemyData);
		String[] enemy;
		
		while (true)
		{
			int probability = (int) (Math.random()*enemies.length);
			if(enemyClass.equals(enemies[probability][3]))
			{
				enemy = enemies[probability];
				break;
			}
		}
		return enemy;
	}
}












