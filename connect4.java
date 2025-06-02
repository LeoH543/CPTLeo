import arc .*;

public class connect4{
	public static void main(String[] args){
		Console con = new Console();
		
		int intChoice;
		
		//Title
		con.println("CONNECT 4");
		con.println("------------");
		
		//Menu Options
		con.println("Main Menu");
		con.println("========");
		con.println("(1) Play Game");
		con.println("(2) Leaderboard");
		con.println("(3) Choose Theme");
		con.println("(4) Create Theme");
		con.println("(5) Quit");
		
		//Read Users Choice
		con.println("Enter in your choice");
		intChoice = con.readInt();
		
	}
}
		
