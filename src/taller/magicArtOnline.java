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
		File fileUserSpells = new File ("HechizosJugadores.txt");
		File fileEnemy = new File ("Enemigo.txt");
		
		Scanner archPlayers = new Scanner(filePlayers);
		Scanner archSpells = new Scanner(fileSpells);
		Scanner archUserSpells = new Scanner(fileUserSpells);
		Scanner archEnemy = new Scanner(fileEnemy);
		
		int longArray = 100;
		int enemyHP, enemyATK, enemyVEL;
		int playerHP, playerATK, playerDEF, playerVEL;
		int spellATK = 0, userTurnATK = 0;
    	int expGained = 0, pointsGained = 0;
		
		String user = null, password = null;
		String confirmation = null;
		String option = null;
		String enemy = null;
		
		int score [] = null;
		
		String[] newPlayer = new String[] {"nuevoUsuario","nuevaContraseña","5","5","5","5","1","0"};
		String[] enemyStats = null ,playerStats = null;
	    String[] playerSpells = null;
	    
	    String [] stadistics = new String [] {"Vida","Ataque","Defensa","Velocidad","Cantidad de hechizos","XP"};
		String[][] enemyData = new String[][] {{"S","1000","1" ,"20"},
							                   {"A","750" ,"10","10"},
							                   {"B","500" ,"25","5" },
							                   {"C","250" ,"50","2" },
							                   {"F","100" ,"75","1" }};
		
		String[][] players = new String[longArray][8];
		String[][] spells = new String[longArray][2];
		String[][] userSpells = new String[longArray][2];
		String[][] enemies = new String[longArray][5];
		
		fillArray(archPlayers, players);
		fillArray(archSpells, spells);
		fillArray(archUserSpells, userSpells);
		fillArray(archEnemy, enemies);
		
		do
		{
			confirmation = "";
			user = input("Ingrese su nombre de usuario: ");
			password = input("Ingrese su contraseña: ");
			division();
			
			if (!search(players,user,0)) 
			{
		    	confirmation = input("Usuario no encontrado, desea registrarse? (SI/NO): ");
		    	confirmation = yesNone(confirmation);
		    	
		    	if (confirmation.equals("SI"))
				{
		    		do
		    		{
						user = input("Ingrese un nuevo nombre de usuario: ");
						password = input("Ingrese una nueva contraseña: ");
						
						if (!search(players,user,0)) 
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
						
		    		}while (search(players,user,0));
				}
			}
			
			if (!compare(players,user,password) && !confirmation.equals("NO"))
			{
				System.out.println("Contraseña equivocada, intente de nuevo");
				System.out.println();
			}
		
		}while(!compare(players,user,password));
		
		playerStats = userList(players,user);
		playerHP = Integer.parseInt(playerStats[2]);
		playerATK = Integer.parseInt(playerStats[3]);
		playerDEF = Integer.parseInt(playerStats[4]);
		playerVEL = Integer.parseInt(playerStats[5]);
		
		playerSpells = playerSpells(userSpells,user);
		
		do
		{
		System.out.println("Bienvenido "+user+", qué desea hacer?: ");
		System.out.println();
		System.out.println("*Pelear contra un enemigo");
		System.out.println("*Aprender hechizo");
		System.out.println("*Ver estadisticas de un jugador");
		System.out.println("*Ver estadisticas de hechizos");
		System.out.println("*Ver ranking de jugadores con mas experiencia");
		option = enterOption();
		option = menuComprobation(option);
		
			
			if (option.equals("Pelear contra un enemigo"))
			{
				do
				{
					System.out.println("Qué tipo de combate desea?");
					System.out.println();
					System.out.println("*JcE");
					System.out.println("*JvJ");
					option = enterOption();
					
					if(!option.equals("JcE") && !option.equals("JvJ"))
					{
						System.out.println("-Ingrese una opción válida-");
						System.out.println();
					}
					
				}while(!option.equals("JcE") && !option.equals("JvJ"));
				
				if (option.equals("JcE"))
				{
					do
					{
					enemyStats = enemy(enemies,enemyData);
					enemy = enemyStats[0];
					enemyHP = Integer.parseInt(enemyStats[1]);
					enemyATK = Integer.parseInt(enemyStats[2]);
					enemyVEL = Integer.parseInt(enemyStats[4]);
					expGained = boundValue(enemyData,enemyStats[3],1);
					pointsGained = boundValue(enemyData,enemyStats[3],3);
					
					System.out.println(user+" se ha encontrado con "+enemy+" (HP: "+enemyHP+" ;ATK: "+enemyATK+")");
					System.out.println();
		
				        while (enemyHP > 0 && playerHP > 0)
				        {
				        	System.out.println("Qué desea hacer?");
							System.out.println();
							System.out.println("*Atacar");
							System.out.println("*Usar habilidad");
							option = enterOption();
				
				            if (option.equals("Atacar"))
							{
								userTurnATK = playerATK;
							}
				
				            else if (option.equals("Usar habilidad"))
				    		{
						        System.out.println("Seleccione una habilidad:");
						        System.out.println();
						        for (int i=0;i<playerSpells.length;i++)
						        {
						       	  System.out.println("*"+playerSpells[i]+" (ATK: "+boundValue(spells,playerSpells[i],1)+")");
						        }
						        option = enterOption();
						        spellATK = boundValue(spells,option,1);
						        userTurnATK = spellATK;
				            }
				            
				            enemyHP -= userTurnATK;
				            playerHP -= enemyATK;
				            System.out.println(enemy+" recibe "+userTurnATK+" puntos de daño!");
				            System.out.println();
				            System.out.println("El enemigo ataca!");
				            System.out.println("Recibes "+enemyATK+" puntos de daño!");
				            division();
				
				            if (playerHP <= 0)
				            {
				            	System.out.println("Perdiste :c");
				              	System.out.println("Intentalo de nuevo!");
				            }
				            else if (enemyHP <= 0)
				            {
				            	System.out.println("Has ganado!");
				            	System.out.println("Recibes "+expGained+" puntos de experiencia");
				            	System.out.println("Recibes "+pointsGained+" punto(s) de estadística");
				            	division();
				            }
				        }
			        confirmation = input("Desea volver a batallar? (SI/NO): ");
			        confirmation = yesNone(confirmation);
			        
					}while(confirmation.equals("SI"));
		        }
			}
		
		
			else if (option.equals("Ver ranking de jugadores con mas experiencia")) 
			{
				score = new int [players.length];
				String nammes [] = new String[players.length];
				createList(players,score);
				createListS(players,nammes);
				orderLists(nammes,score);
				
				System.out.println("Jugadores con más experiencia:");
				System.out.println();
				for (int i =0; i<10;i++) 
				{
					if (nammes[i] != null || score [i] != 0)
					{
						System.out.print(nammes[i]+" ");
						System.out.println(score[i]+(" puntos de xp"));
						System.out.println("");
					}
					else 
					{
						break;
					}
					
				}
			}
			
			else if (option.equals("Ver estadisticas de un jugador")) 
			{
	            String ployer = input("ingrese nombre del jugador");
	            int indexLine = index(ployer,players,0);
	            System.out.println("Informacion del jugador:"+ ployer);
	            for (int i = 2; i < 8;i++) 
	            {
	                System.out.print(" "+players[indexLine][i]+" "+stadistics[i-2]);
	            }
			}
			
		}while(!option.equals("Pelear contra un enemigo") && !option.equals("Ver ranking de jugadores con mas experiencia") && !option.equals("Aprender hechizo") && !option.equals("Ver estadisticas de hechizos") && !option.equals("Ver ranking de jugadores con mas experiencia"));
	}


	private static String yesNone(String confirmation) 
	{
		System.out.println();
		while (!confirmation.equals("SI") && !confirmation.equals("NO"))
		{
			confirmation = input("-Ingrese una respuesta válida- : ");
			System.out.println();
			if (confirmation.equals("SI") || confirmation.equals("NO"))
			{
				break;
			}
		}
		return confirmation;
	}
	
	private static String menuComprobation(String option) 
	{
		System.out.println();
		while (!option.equals("Pelear contra un enemigo") && !option.equals("Ver ranking de jugadores con mas experiencia") && !option.equals("Aprender hechizo") && !option.equals("Ver estadisticas de hechizos") && !option.equals("Ver ranking de jugadores con mas experiencia"))
		{
			option = input("-Ingrese una respuesta válida- : ");
			System.out.println();
			if (option.equals("Pelear contra un enemigo") && option.equals("Ver ranking de jugadores con mas experiencia") && option.equals("Aprender hechizo") && option.equals("Ver estadisticas de hechizos") && option.equals("Ver ranking de jugadores con mas experiencia"))
			{
				break;
			}
		}
		return option;
	}


	private static void createList(String [][] players, int[] score) 
	{
		for (int i=0;i < players.length;i++) 
		{
			if (players[i][7] != null)
			{
				int aux =  Integer.parseInt(players[i][7]);
				score [i] = aux;
			}
			else 
			{
				break;
			}
		}
	
	}
	
	private static void createListS(String [][] players, String [] nammes) 
	{
		for (int i =0;i < players.length;i++) 
		{
			if (players[i][0] != null)
			{
				nammes [i] = players[i][0];
			}
			else 
			{
				break;
			}
		}
	}
	
	private static String enterOption() {
		System.out.println();
		String option = input("Ingresar opción: ");
		division();
		return option;
	}

	private static void orderLists (String [] names,int [] score) 
	{
		for (int i = 0; i < 10;i++) 
		{
			for (int k = i+1;k<10;k++) 
			{
				if (names[i] != null || score [i] != 0)
				{
					if (score [i] < score [k]) 
					{
						int aux = score [i];
						score [i] = score [k];
						score [k] = aux;
						String temp = names[i];
						names [i] = names [k];
						names [k] = temp;
					}	
				}
				else 
				{
					break;
				}
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
	
	private static void enterListIntoArray(String[] list,String[][] array) 
	{
		int i = 0;
		while(array[i][0] != null)
		{
			i++;
		}
		array[i] = list;
	}
	
	private static void printArray(String[][] array, int initialColumn) 
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

	private static void fillArray(Scanner archive, String[][] array) 
	{
		int count = 0;
		while (archive.hasNextLine()) 
		{
			String[] parts = archive.nextLine().split(","); 
			array[count] = parts; 
			count++;
		}
	}
  
	private static boolean search(String[][] array,String chekstring, int column) 
	{
		for (int i=0;i<array.length;i++)
		{
			if (array[i][column] != null)
			{
				if (array[i][column].equals(chekstring)) 
				{
				return true;
				}
			}	
		}
		return false;
	}
	
	private static String[] userList(String[][] playerArray,String user) 
	{
		String[] playerStats = null;
		for (int i=0;i<playerArray.length;i++)
		{
			if (playerArray[i][0].equals(user))
			{
				playerStats = playerArray[i];
				break;
			}
		}
		return playerStats;
	}
	
	private static boolean compare(String[][] array,String user, String password)
	{
		for (int i=0;i<array.length;i++)
		{
			if (array[i][0] != null && array[i][1] != null)
			{
				if (array[i][0].equals(user) && array[i][1].equals(password)) 
		      	{
		    	  	return true;
	      		}	
			}
		}
    return false;
	}
	
	private static String[] playerSpells(String[][] userSpellsArray,String user) 
	{
		int counter = 0;
    
		for (int i=0;i<userSpellsArray.length;i++)
		{
			
		if (userSpellsArray[i][0] == null)
			break;
			if (userSpellsArray[i][0].equals(user))
			{
				counter++;
			}
		}
		
		String[] playerSpells = new String[counter];
		counter = 0;

		for (int i=0;i<userSpellsArray.length;i++)
		{
			if (userSpellsArray[i][0] == null)
				break;
			if (userSpellsArray[i][0].equals(user))
			{
				playerSpells[counter] = userSpellsArray[i][1];
				counter++;
			}
		}
		return playerSpells;
	}

	private static int boundValue(String[][] array,String variable, int column) 
	{
	    int value = 0;
	    
	    for (int i=0;i<array.length;i++)
	    {
	        if (array[i][0].equals(variable))
	        {
	          value = Integer.parseInt(array[i][column]);
	          break;
	        }
	    }
	    return value;
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
	
	private static int index(String player, String players [] [],int line ) 
	{
		for (int i = 0;i < players.length;i++) 
	    {
	        for (int j = 0; i < players[0].length;i++) 
	        {
	        	if (players[i][j] != null) 
	        	{
                	if ((players[i][j]).equals(player)) 
                    {
                        line = i;
                        break;
                    }

	        	}
	        }
	    }
	    return line;
	}
	
}












