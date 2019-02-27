package battleshipgame;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battle {
	static int rows = 10; 
	static int colums = 10; 
	static public int [][] sea = new int [rows][colums];
	public Battle() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// Displaying the Ocean
		System.out.println("*** Welcome to the game of the Battle Ships ***");
		System.out.println();
		System.out.println("right now, the ocean is empty.");
		System.out.println();
		System.out.println("  0123456789");
		for (int i= 0; i < rows; i++) {
			 System.out.print(i + "|");
			for (int j = 0; j < colums; j++ ) {
				sea[i][j] = 0; 
				 System.out.print(" ");	
			}
			System.out.println("|" + i);	
		}
		System.out.println("  0123456789");
		
	// Deploying the player's ships
		Scanner input = new Scanner(System.in);
		for (int i = 1; i <= 5; i++) {
			System.out.print("Enter X coordinate for your " +i+ ". ship: ");
			int x = input.nextInt(); 
			System.out.print("Enter Y coordinate for your " +i+ ". ship: ");
			int y = input.nextInt();
			if (  x <= 9 && x >= 0 && y <=9 && y >=0 && sea[y][x]!= 1) {
				sea[y][x] =1; 
			} else {
				System.out.println("the coordinates should be between 0 to 9 and should be repeated.");
				i--; 
			}
		}
		System.out.println("  0123456789");
		for (int i= 0; i < rows; i++) {
			 System.out.print(i + "|");
			for (int j = 0; j < colums; j++ ) {
				if(sea[i][j] == 0) {
					System.out.print(" ");
				}else {
					System.out.print("@");
				}	 	
			}
			System.out.println("|" + i);	
		}
		System.out.println("  0123456789");
		
		//Deploying computer's ships
		System.out.println("computer is deploying ships.");
		for (int i = 1; i <= 5; i++) {
			int x = ThreadLocalRandom.current().nextInt(0, 10); 
			int y = ThreadLocalRandom.current().nextInt(0, 10);
			
			if (x <= 9 && x >= 0 && y <=9 && y >=0 && sea[y][x] == 0) {
				sea[y][x] = 2;
				System.out.println(i + ". ship DEPLOYED");
			} else {
				
				i--; 
			}
		}
		
		// the battle
		int [][] playerGusses = new int [10][10];
		int [][] computerGusses = new int [10][10];
		for (int z = 0; z<1; z++) {
			for (int j = 0; j<1 ; j++) {
			System.out.println("YOUR TURN");
			System.out.println("Enter X coordinate: ");
			int x = input.nextInt(); 
			System.out.println("Enter Y coordinate: " );
			int y = input.nextInt();
			if (x<0 || x>9 || y<0 || y>9 ) {
				System.out.println("the coordinates should not be greater than 9 and smaller than 0");
				j--;
			} else if(playerGusses[y][x] == 1) {
				System.out.println("you have already guesed this coordinates. Guess again.");
				j--; 
			} else if(sea[y][x] == 0) {
				playerGusses[y][x] = 1;
				sea[y][x] = 3; // missed guesses by player
				System.out.println("Sorry, you missed");
			} else if(sea[y][x] == 1) {
				sea[y][x] = -1;
				playerGusses[y][x] = 1;
				System.out.println("Oh no, you sunk your own ship :(");
			} else if(sea[y][x] == 2) {
				sea[y][x] = -2;
				playerGusses[y][x] = 1; 
				System.out.println("Boom! You sunk the ship!");
			}
			}
			// computer's turn
			System.out.println("COMPUTER'S TURN");
			 
			for (int j = 0; j<1 ; j++) {
				int x = ThreadLocalRandom.current().nextInt(0, 10); 
				int y = ThreadLocalRandom.current().nextInt(0, 10);
				if (x<0 || x>9 || y<0 || y>9 ) {
					System.out.println("computer gussed out of range");
					j--;
				} else if(computerGusses[y][x] == 1) {
					System.out.println("computer repeated its guess.");
					j--; 
				} else if(sea[y][x] == 0) {
					computerGusses[y][x] = 1; 
					System.out.println("Computer missed");
				} else if(sea[y][x] == 2) {
					computerGusses[y][x] = 1;
					sea[y][x] = -2;
					System.out.println("The Computer sunk one of its own ships");
				} else if(sea[y][x] == 1) {
					computerGusses[y][x] = 1;
					sea[y][x] = -1;
					System.out.println("The Computer sunk one of your ships!");
				}
				}
			// printing the map
			int countPlayerships = 0;
			int countComputerships = 0;
			System.out.println("  0123456789");
			for (int i= 0; i < rows; i++) {
				 System.out.print(i + "|");
				for (int j = 0; j < colums; j++ ) {
					 if (sea[i][j] == 3){
						System.out.print("-");
					} else if (sea[i][j] == -1) {
						System.out.print("x");
					} else if (sea[i][j] == 1) {
						System.out.print("@");
						countPlayerships++; 
					} else if (sea[i][j] == 2) {
						System.out.print(" ");
						countComputerships++; 
					} else if (sea[i][j] == -2) {
						System.out.print("!");
					} else if(sea[i][j] == 0) {
						System.out.print(" ");
					} else  {
						System.out.print(" ");
					}	 	
				}
				System.out.println("|" + i);	
			}
			
			System.out.println("  0123456789");
			System.out.println(" ");
			
			System.out.print("Your ships: " + countPlayerships + "  |  ");
			System.out.println("Computer's ships: " + countComputerships );
			System.out.println("------------------------------------------");
			
			if(countPlayerships == 0) {
				System.out.println("You lost the Game! (:");
			} else if (countComputerships == 0) {
				System.out.println("Hooray! You won the battle! :)");
				} else {
					z--; 
				}
			
		}
		
	}
}
