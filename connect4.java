import arc .*;

public class connect4{
	public static void main(String[] args){
		Console con = new Console();
		
		int intChoice;
        String[][] strBoard = new String [6][7];
		String strPlayer1;
		String strPlayer2;
		int intWins1 = 0;
		int intWins2 = 0;
		int intRow;
		int intRows = 6;
		int intCols = 7;
		int intCol;
		
		//Title
		con.println("CONNECT 4");
		con.println("------------");
		
		
		//Menu Options
		con.println("Main Menu");
		con.println("=========");
		con.println("(1) Play Game");
		con.println("(2) Leaderboard");
		con.println("(3) Choose Theme");
		con.println("(4) Create Theme");
		con.println("(5) Quit");
		
		//Read Users Choice
		con.println("Enter in your choice");
		intChoice = con.readInt();
		
		
		// Enter in the Usernames first before the game starts
		if(intChoice == 1){
			con.clear();
			con.println("PLAY GAME");
			con.println("-------------");
			
			con.println("Enter Username for Player 1: ");
			strPlayer1 = con.readLine();
			
			con.println("Enter Useranme for Player 2: ");
			strPlayer2 = con.readLine();
			
			con.clear();
			
			con.println(strPlayer1 + " (Wins: " + intWins1 + ")      CONNECT 4      " + strPlayer2 + " (Wins: " + intWins2 + ")");

			     con.println("================================================");

        // Column numbers on top of the board
        con.println("   ");
        for (intCol = 1; intCol <= intCols; intCol++) {
            con.print(intCol + " ");
        }
			con.println();

        // Draw board
        for (intRow = 0; intRow < intRows; intRow++) {
            con.println("|  ");
            for (intCol = 0; intCol < intCols; intCol++) {
                con.print(strBoard[intRow][intCol]);
            }
            con.println("|");
        }

        con.println("================================================");
	
	}		
			
			//The leaderboard for all wins for player 1 and player 2
			if(intChoice == 2){
				con.clear();
				TextOutputFile leaderboard = new TextOutputFile("leaderboard.txt");
					int intLeaderboardWins1;
					int intLeaderboardWins2;
					
					intLeaderboardWins1 = intWins1;
					intLeaderboardWins2 = intWins2;



					leaderboard.println(intLeaderboardWins1);

					leaderboard.println(intLeaderboardWins2);
					
					leaderboard.close();
	}
	
	}			
	}
				
				
	

