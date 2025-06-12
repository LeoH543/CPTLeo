import arc.*;
import java.awt.Color;
import java.awt.Font;

public class connect4 {
    String strPlayer1 = "Player 1";
    String strPlayer2 = "Player 2";
    int intWins1 = 0;
    int intWins2 = 0;
    int intRows = 6;
    int intCols = 7;
    int intChipSize = 70;
    int intStartX = 100;
    int intStartY = 100;
    Color boardColor = Color.BLUE;
    Color player1Color = Color.RED;
    Color player2Color = Color.YELLOW;
    Color emptySlotColor = Color.WHITE;

    public static void main(String[] args) {
        Console con = new Console();
        connect4 game = new connect4();
        game.loadLastTheme(con);

        while (true) {
            con.clear();
            con.println("CONNECT 4");
            con.println("------------");
            con.println("Main Menu");
            con.println("=========");
            con.println("(p) Play Game");
            con.println("(v) View High Scores");
            con.println("(h) Help");
            con.println("(c) Choose Theme");
            con.println("(t) Create Theme");
            con.println("(q) Quit");
            con.println("Enter your choice:");
            String choice = con.readLine().toLowerCase();

            if (choice.equals("p")) {
                con.clear();
                con.println("Enter Username for Player 1:");
                game.strPlayer1 = con.readLine();
                con.println("Enter Username for Player 2:");
                game.strPlayer2 = con.readLine();

                int[][] board = new int[game.intRows][game.intCols];
                int currentPlayer = 1;
                boolean gameRunning = true;

                while (gameRunning) {
                    con.clear();
                    con.setDrawFont(new Font("Arial", Font.BOLD, 18));
                    con.println(game.strPlayer1 + " (Wins: " + game.intWins1 + ")           CONNECT 4           " + game.strPlayer2 + " (Wins: " + game.intWins2 + ")\n");

                    con.setDrawColor(Color.BLACK);
                    for (int col = 0; col < game.intCols; col++) {
                        int x = game.intStartX + col * game.intChipSize + game.intChipSize / 2 - 5;
                        con.drawString("" + col, x, game.intStartY - 10);
                    }

                    con.setDrawColor(game.boardColor);
                    con.fillRect(game.intStartX, game.intStartY, game.intCols * game.intChipSize, game.intRows * game.intChipSize);

                    for (int row = 0; row < game.intRows; row++) {
                        for (int col = 0; col < game.intCols; col++) {
                            int x = game.intStartX + col * game.intChipSize;
                            int y = game.intStartY + row * game.intChipSize;
                            if (board[row][col] == 1) {
                                con.setDrawColor(game.player1Color);
                            } else if (board[row][col] == 2) {
                                con.setDrawColor(game.player2Color);
                            } else {
                                con.setDrawColor(game.emptySlotColor);
                            }
                            con.fillOval(x + 10, y + 10, game.intChipSize - 20, game.intChipSize - 20);
                        }
                    }

                    int moveCol = -1;
                    boolean validMove = false;
                    while (!validMove) {
                        con.println("\n" + (currentPlayer == 1 ? game.strPlayer1 : game.strPlayer2) + " - Choose a column (0-6):");
                        String input = con.readLine();
                        if (input.matches("[0-6]")) {
                            moveCol = Integer.parseInt(input);
                            if (board[0][moveCol] != 0) {
                                con.println("Column is full. Try again.");
                            } else {
                                validMove = true;
                            }
                        } else {
                            con.println("Invalid input. Please enter a number from 0 to 6.");
                        }
                    }

                    for (int row = game.intRows - 1; row >= 0; row--) {
                        if (board[row][moveCol] == 0) {
                            board[row][moveCol] = currentPlayer;

                            if (checkWin(board, row, moveCol, currentPlayer)) {
                                con.clear();
                                con.setDrawFont(new Font("Arial", Font.BOLD, 28));
                                con.setDrawColor(currentPlayer == 1 ? game.player1Color : game.player2Color);
                                con.drawString((currentPlayer == 1 ? game.strPlayer1 : game.strPlayer2) + " WINS!", 250, 100);

                                if (currentPlayer == 1) game.intWins1++;
                                else game.intWins2++;

                                game.saveLeaderboard(con);

                                con.println("\nDo you want to play again? (yes/no)");
                                String playAgain = con.readLine();
                                if (!playAgain.equalsIgnoreCase("yes")) {
                                    gameRunning = false;
                                } else {
                                    board = new int[game.intRows][game.intCols];
                                }
                            }
                            break;
                        }
                    }
                    currentPlayer = 3 - currentPlayer;
                }
            } else if (choice.equals("v")) {
                con.clear();
                con.println("==== LEADERBOARD ====\n");
                con.println(game.strPlayer1 + ": " + game.intWins1);
                con.println(game.strPlayer2 + ": " + game.intWins2);
                con.println("Press enter to return to menu.");
                con.readLine();
            } else if (choice.equals("h")) {
                con.clear();
                con.println("==== HELP ====");
                con.println("- (p) Play Game: Start a new Connect 4 match between two players.");
                con.println("- (v) View High Scores: See current player win counts.");
                con.println("- (h) Help: Show this help menu.");
                con.println("- (c) Choose Theme: Change game board and piece colors.");
                con.println("- (t) Create Theme: Make your own theme with RGB.");
                con.println("- (q) Quit: Exit the game.");
                con.println("Press enter to return to the main menu.");
                con.readLine();
            } else if (choice.equals("c")) {
                con.clear();
                con.println("Last chosen theme: " + game.loadLastThemeName());
                con.println("Choose a Theme:");
                con.println("1. Regular");
                con.println("2. Christmas");
                con.println("3. Halloween");
                String input = con.readLine();
                int theme = input.matches("[1-3]") ? Integer.parseInt(input) : 1;
                game.setTheme(theme);
                game.saveTheme(theme);
                con.println("Theme updated. Press enter to return to menu.");
                con.readLine();
            } else if (choice.equals("t")) {
                con.clear();
                con.println("CREATE A THEME");
                con.println("Enter RGB values for the Board Color:");
                int rBoard = con.readInt();
                int gBoard = con.readInt();
                int bBoard = con.readInt();

                con.println("Enter RGB values for Player 1's Piece:");
                int rP1 = con.readInt();
                int gP1 = con.readInt();
                int bP1 = con.readInt();

                con.println("Enter RGB values for Player 2's Piece:");
                int rP2 = con.readInt();
                int gP2 = con.readInt();
                int bP2 = con.readInt();

                game.boardColor = new Color(rBoard, gBoard, bBoard);
                game.player1Color = new Color(rP1, gP1, bP1);
                game.player2Color = new Color(rP2, gP2, bP2);
                game.emptySlotColor = Color.LIGHT_GRAY;
                game.saveTheme(0);
                con.println("Custom theme set. Press enter to return to menu.");
                con.readLine();
            } else if (choice.equals("q")) {
                con.println("Thanks for playing!");
                System.exit(0);
            } else {
                con.println("Invalid input. Try again.");
                con.readLine();
            }
        }
    }

    void saveLeaderboard(Console con) {
        TextOutputFile leaderboardOutput = new TextOutputFile("leaderboard.txt");
        leaderboardOutput.println(strPlayer1 + ": " + intWins1);
        leaderboardOutput.println(strPlayer2 + ": " + intWins2);
        leaderboardOutput.close();
    }

    void saveTheme(int themeCode) {
        TextOutputFile themeOutput = new TextOutputFile("LastTheme.txt");
        themeOutput.println(themeCode);
        themeOutput.close();
    }

    void loadLastTheme(Console con) {
        TextInputFile lastThemeInput = new TextInputFile("LastTheme.txt");
        String line = lastThemeInput.readLine();
        int theme = 1;
        if (line != null && line.matches("\\d")) {
            theme = Integer.parseInt(line);
        }
        lastThemeInput.close();
        setTheme(theme);
    }

    String loadLastThemeName() {
        TextInputFile lastThemeInput = new TextInputFile("LastTheme.txt");
        String line = lastThemeInput.readLine();
        lastThemeInput.close();
        if (line == null || !line.matches("\\d")) return "Regular";
        int theme = Integer.parseInt(line);
        switch (theme) {
            case 2: return "Christmas";
            case 3: return "Halloween";
            case 0: return "Custom";
            default: return "Regular";
        }
    }

    void setTheme(int theme) {
        if (theme == 1) {
            boardColor = Color.BLUE;
            player1Color = Color.RED;
            player2Color = Color.YELLOW;
            emptySlotColor = Color.WHITE;
        } else if (theme == 2) {
            boardColor = Color.WHITE;
            player1Color = Color.RED;
            player2Color = Color.GREEN;
            emptySlotColor = Color.LIGHT_GRAY;
        } else if (theme == 3) {
            boardColor = Color.ORANGE;
            player1Color = Color.BLACK;
            player2Color = Color.GREEN;
            emptySlotColor = Color.LIGHT_GRAY;
        }
    }

    public static boolean checkWin(int[][] board, int row, int col, int player) {
        int[][] directions = {{0, 1}, {1, 0}, {1, 1}, {1, -1}};
        for (int[] dir : directions) {
            int count = 1;
            count += countDirection(board, row, col, dir[0], dir[1], player);
            count += countDirection(board, row, col, -dir[0], -dir[1], player);
            if (count >= 4) return true;
        }
        return false;
    }

    public static int countDirection(int[][] board, int row, int col, int dRow, int dCol, int player) {
        int count = 0;
        for (int i = 1; i < 4; i++) {
            int newRow = row + i * dRow;
            int newCol = col + i * dCol;
            if (newRow < 0 || newRow >= board.length || newCol < 0 || newCol >= board[0].length) break;
            if (board[newRow][newCol] == player) count++;
            else break;
        }
        return count;
    }
}



