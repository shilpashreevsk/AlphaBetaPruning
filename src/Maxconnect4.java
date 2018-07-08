import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Maxconnect4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    if( args.length != 4 ) 
    {
      System.out.println("Four command-line arguments are needed:\n"
                         + "Usage: java [program name] interactive [input_file] [computer-next / human-next] [depth]\n"
                         + " or:  java [program name] one-move [input_file] [output_file] [depth]\n");

      exit_function( 0 );
     }
    String game_mode = args[0];				// the game mode;
    String input = args[1];
    String output = args[2];
    int depthLevel = Integer.parseInt(args[3]);  		// the depth level of the ai search
    int aiplayer=0;
    if(game_mode.equalsIgnoreCase("interactive")) { 	//If file does not exists then create one with value 1
    	File file = new File(input);
    	if(!file.exists()) {
    		file.createNewFile();
    		FileOutputStream fos = new FileOutputStream(file);
    		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    		for(int i=1;i<=6;i++)
    		{
    			for(int j=1;j<=7;j++)
    			{
    				bw.write("0");
    			}
    			bw.newLine();
    			
    		}
    		bw.write("1");
    		bw.close();
    	}
    	
    }
    GameBoard currentGame = new GameBoard( input );
    AiPlayer calculon = new AiPlayer();
		
    //  variables to keep up with the game
    int playColumn = 99;				//  the players choice of column to play
    boolean playMade = false;			//  set to true once a play has been made
    

    if( game_mode.equalsIgnoreCase( "interactive" ) ) 
    {
    	if(!(output.equalsIgnoreCase("computer-next")||output.equalsIgnoreCase("human-next")))
    	{
    		System.out.println("Invalid player");
    		exit_function(0);
    	}
	if(output.equalsIgnoreCase("computer-next"))
        {
			aiplayer=2;
        }
        else if(output.equalsIgnoreCase("human-next"))
        {
            aiplayer=1;
        }
        System.out.print("\nMaxConnect-4 game\n");
        System.out.print("game state before move:\n");
        currentGame.printGameBoard();
        System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
			", Player2 = " + currentGame.getScore( 2 ) + "\n " );
        while( currentGame.getPieceCount() < 42 ) 
        {
            int current_player = currentGame.getCurrentTurn();
            if(aiplayer==1) {
                playColumn = calculon.findBestPlay( currentGame, current_player, depthLevel );
            	aiplayer=2;}
            else if(aiplayer==2)
            {
                boolean isvalid;
                do
                {
                    System.out.println();
                    System.out.print("Make A Move : ");
                    Scanner in = new Scanner(System.in);
                    playColumn = Integer.parseInt(in.nextLine());
                    isvalid= currentGame.isValidPlay(playColumn);
                }while(!isvalid);
                aiplayer=1;
            }
            currentGame.playPiece( playColumn );
            System.out.println("move " + currentGame.getPieceCount() 
                           + ": Player " + current_player
                           + ", column " + playColumn);
            System.out.print("game state after move:\n");
            currentGame.printGameBoard();
            System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                            ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
            if(current_player == 1)
                currentGame.printGameBoardToFile( "computer.txt" );
            else
               currentGame.printGameBoardToFile( "human.txt" ); 
        } 
        System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
        playMade = true;
	return;
    } 
			
    else if( !game_mode.equalsIgnoreCase( "one-move" ) ) 
    {
      System.out.println( "\n" + game_mode + " is an unrecognized game mode \n try again. \n" );
      return;
    }

    /////////////   one-move mode ///////////
    // the output game file
    
    System.out.print("\nMaxConnect-4 game\n");
    System.out.print("game state before move:\n");
    
    //print the current game board
    currentGame.printGameBoard();
    // print the current scores
    System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
			", Player2 = " + currentGame.getScore( 2 ) + "\n " );
    
    // ****************** this chunk of code makes the computer play
    if( currentGame.getPieceCount() < 42 ) 
    {
        int current_player = currentGame.getCurrentTurn();
	playColumn = calculon.findBestPlay( currentGame, current_player, depthLevel );
	currentGame.playPiece( playColumn );
        	
        // display the current game board
        System.out.println("move " + currentGame.getPieceCount() 
                           + ": Player " + current_player
                           + ", column " + playColumn);
        System.out.print("game state after move:\n");
        currentGame.printGameBoard();
    
        // print the current scores
        System.out.println( "Score: Player 1 = " + currentGame.getScore( 1 ) +
                            ", Player2 = " + currentGame.getScore( 2 ) + "\n " );
        
        currentGame.printGameBoardToFile( output );
    } 
    else 
    {
	System.out.println("\nI can't play.\nThe Board is Full\n\nGame Over");
    }
	
    //************************** end computer play
			
    
    return;
    
}
    
  private static void exit_function( int value )
  {
      System.out.println("exiting from MaxConnectFour.java!\n\n");
      System.exit( value );
  }
}