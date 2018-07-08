import java.util.LinkedList;
public class AiPlayer {	
    public AiPlayer() 
    {
    }

    /**
     * This method plays a piece randomly on the board
     * @param currentGame The GameBoard object that is currently being used to
     * play the game.
     * @param current_player
     * @return an integer indicating which column the AiPlayer would like
     * to play in.
     */
    int currentplayer, oppositeplayer, depth, prevcurrentplayerscore, prevoppositeplayerscore, playerchoice = 100;
    public int getutility(GameBoard currentGame, int player, int connect_number)
    {
        int count = 0;
        int[][] util_val = currentGame.getGameBoard();
        for( int i = 0; i < 6; i++ ) {
	    for( int j = 0; j < 4; j++ ) 
            {
		if((( util_val[ i ][j] == 0 ) &&
		    ( util_val[ i ][ j+1 ] == player ) &&
		    ( util_val[ i ][ j+2 ] == player ) &&
		    ( util_val[ i ][ j+3 ] == player ) ) ||
                    (( util_val[ i ][j] == player ) &&
		    ( util_val[ i ][ j+1 ] == player ) &&
		    ( util_val[ i ][ j+2 ] == player ) &&
		    ( util_val[ i ][ j+3 ] == 0 ) )) 
                        {
                            if(connect_number == 3 )
                                count ++;
                        }
                else if((( util_val[ i ][j] == 0 ) &&
		    ( util_val[ i ][ j+1 ] == 0 ) &&
		    ( util_val[ i ][ j+2 ] == player ) &&
		    ( util_val[ i ][ j+3 ] == player ) ) ||
                    (( util_val[ i ][j] == player ) &&
		    ( util_val[ i ][ j+1 ] == player ) &&
		    ( util_val[ i ][ j+2 ] == 0 ) &&
		    ( util_val[ i ][ j+3 ] == 0 ) )) 
                        {
                            if(connect_number == 2 )
                                count ++;
                        }
                else if((( util_val[ i ][j] == 0 ) &&
		    ( util_val[ i ][ j+1 ] == 0 ) &&
		    ( util_val[ i ][ j+2 ] == 0 ) &&
		    ( util_val[ i ][ j+3 ] == player ) ) ||
                    (( util_val[ i ][j] == player ) &&
		    ( util_val[ i ][ j+1 ] == 0 ) &&
		    ( util_val[ i ][ j+2 ] == 0 ) &&
		    ( util_val[ i ][ j+3 ] == 0 ) )) 
                        {
                            if(connect_number == 1 )
                                count ++;
                        }
	    }
	}
        // end horizontal

	//check vertically
	for( int i = 0; i < 3; i++ ) {
	    for( int j = 0; j < 7; j++ ) {
		if( ( util_val[ i ][ j ] == 0 ) &&
		    ( util_val[ i+1 ][ j ] == player ) &&
		    ( util_val[ i+2 ][ j ] == player ) &&
		    ( util_val[ i+3 ][ j ] == player ) ) 
                        {
                            if(connect_number == 3 )
                                count ++;
                        }
                else if( ( util_val[ i ][ j ] == 0 ) &&
		    ( util_val[ i+1 ][ j ] == 0 ) &&
		    ( util_val[ i+2 ][ j ] == player ) &&
		    ( util_val[ i+3 ][ j ] == player ) ) 
                        {
                            if(connect_number == 2 )
                                count ++;
                        }
                else if( ( util_val[ i ][ j ] == 0 ) &&
		    ( util_val[ i+1 ][ j ] == 0 ) &&
		    ( util_val[ i+2 ][ j ] == 0 ) &&
		    ( util_val[ i+3 ][ j ] == player ) ) 
                        {
                            if(connect_number == 1 )
                                count ++;
                        }
	    }
	}// end vertically
	
	//check diagonally - backs lash ->	\
	for( int i = 0; i < 3; i++ ){
            for( int j = 0; j < 4; j++ ) {
		if( (( util_val[ i ][ j ] == player ) &&
                    ( util_val[ i+1 ][ j+1 ] == player ) &&
                    ( util_val[ i+2 ][ j+2 ] == player ) &&
                    ( util_val[ i+3 ][ j+3 ] == 0 )) ||
                    (( util_val[ i ][ j ] == 0 ) &&
                    ( util_val[ i+1 ][ j+1 ] == player ) &&
                    ( util_val[ i+2 ][ j+2 ] == player ) &&
                    ( util_val[ i+3 ][ j+3 ] == player )) ) 
                        {
                            if(connect_number == 3 )
                                count ++;
                        }
                else if( (( util_val[ i ][ j ] == player ) &&
                    ( util_val[ i+1 ][ j+1 ] == player ) &&
                    ( util_val[ i+2 ][ j+2 ] == 0 ) &&
                    ( util_val[ i+3 ][ j+3 ] == 0 )) ||
                    (( util_val[ i ][ j ] == 0 ) &&
                    ( util_val[ i+1 ][ j+1 ] == 0 ) &&
                    ( util_val[ i+2 ][ j+2 ] == player ) &&
                    ( util_val[ i+3 ][ j+3 ] == player )) ) 
                        {
                            if(connect_number == 2 )
                                count ++;
                        }
                else if( (( util_val[ i ][ j ] == player ) &&
                    ( util_val[ i+1 ][ j+1 ] == 0 ) &&
                    ( util_val[ i+2 ][ j+2 ] == 0 ) &&
                    ( util_val[ i+3 ][ j+3 ] == 0 )) ||
                    (( util_val[ i ][ j ] == 0 ) &&
                    ( util_val[ i+1 ][ j+1 ] == 0 ) &&
                    ( util_val[ i+2 ][ j+2 ] == 0 ) &&
                    ( util_val[ i+3 ][ j+3 ] == player )) ) 
                        {
                            if(connect_number == 1 )
                                count ++;
                        }
            }
        }
	//check diagonally - forward slash -> /
	for( int i = 0; i < 3; i++ ){
            for( int j = 0; j < 4; j++ ) {
		if( (( util_val[ i+3 ][ j ] == player ) &&
                    ( util_val[ i+2 ][ j+1 ] == player ) &&
                    ( util_val[ i+1 ][ j+2 ] == player ) &&
                    ( util_val[ i ][ j+3 ] == 0 )) ||
                    (( util_val[ i+3 ][ j ] == 0 ) &&
                    ( util_val[ i+2 ][ j+1 ] == player ) &&
                    ( util_val[ i+1 ][ j+2 ] == player ) &&
                    ( util_val[ i ][ j+3 ] == player )) ) 
                        {
                            if(connect_number == 3 )
                                count ++;
                        }
                else if( (( util_val[ i+3 ][ j ] == player ) &&
                    ( util_val[ i+2 ][ j+1 ] == player ) &&
                    ( util_val[ i+1 ][ j+2 ] == 0 ) &&
                    ( util_val[ i ][ j+3 ] == 0 )) ||
                    (( util_val[ i+3 ][ j ] == 0 ) &&
                    ( util_val[ i+2 ][ j+1 ] == 0 ) &&
                    ( util_val[ i+1 ][ j+2 ] == player ) &&
                    ( util_val[ i ][ j+3 ] == player )) ) 
                        {
                            if(connect_number == 2 )
                                count ++;
                        }
                else if( (( util_val[ i+3 ][ j ] == player ) &&
                    ( util_val[ i+2 ][ j+1 ] == 0 ) &&
                    ( util_val[ i+1 ][ j+2 ] == 0 ) &&
                    ( util_val[ i ][ j+3 ] == 0 )) ||
                    (( util_val[ i+3 ][ j ] == 0 ) &&
                    ( util_val[ i+2 ][ j+1 ] == 0 ) &&
                    ( util_val[ i+1 ][ j+2 ] == 0 ) &&
                    ( util_val[ i ][ j+3 ] == player )) )
                        {
                            if(connect_number == 1 )
                                count ++;
                        }
            }
        }
	return count;
    }
        
    public int findBestPlay( GameBoard currentGame, int current_player, int maxdepthlevel ) 
    {
        depth = maxdepthlevel;
        currentplayer = current_player;
        if(currentplayer == 1)
            oppositeplayer = 2;
        else 
            oppositeplayer = 1;
        prevcurrentplayerscore = currentGame.getScore(currentplayer);
        prevoppositeplayerscore = currentGame.getScore(oppositeplayer);
        int temp_utility = max_value(currentGame, -100000, 100000, 0);
        return playerchoice;
    }
    public int max_value(GameBoard currentGame_comp, int alpha, int beta, int currentdepthlevel)
    {
        currentdepthlevel++;
        if(depth == currentdepthlevel || currentGame_comp.getPieceCount() >= 42)
            return evalfunction(currentGame_comp);
        int utility = -999999;
        LinkedList<node> children = new LinkedList<node>();
        for(int i = 0; i < 7; i++)
        {
            for(int j=5;j>=0;j--)
            {
                if(currentGame_comp.getGameBoard()[j][i] == 0)
                {
                    node child_state = new node();
                    child_state.i = i;
                    child_state.j = j;
                    children.add(child_state);
                    break;
                }
            }
        }
        int [][] temp = currentGame_comp.getGameBoard();
        int [][] store_temp = new int[6][7];
        for(int k = 0; k < 6; k++)
        {
            for(int l=0;l<7;l++)
            {
                store_temp[k][l] = temp[k][l];
            }
        }
        for(node current_child : children)
        {
            GameBoard tempstate = new GameBoard(store_temp);
            int [][] tempref = tempstate.getGameBoard();
            tempref[current_child.j][current_child.i] = currentplayer;
            GameBoard tempgame = new GameBoard(tempref);
            int temp_utility = min_value(tempgame,alpha,beta,currentdepthlevel);
            if(temp_utility > utility)
            {
                utility = temp_utility;
                playerchoice = current_child.i;
            }
            if(utility >= beta)
                return utility;
            if(utility > alpha)
                alpha = utility;
        }
        return utility;
    }
    
    public int min_value(GameBoard currentGame_comp, int alpha, int beta, int currentdepthlevel)
    {
        currentdepthlevel++;
        if(depth == currentdepthlevel || currentGame_comp.getPieceCount() >= 42)
            return evalfunction(currentGame_comp);
        int utility = 9999999;
        LinkedList<node> children = new LinkedList<node>();
        for(int i = 0; i < 7; i++)
        {
            for(int j=5;j>=0;j--)
            {
                if(currentGame_comp.getGameBoard()[j][i] == 0)
                {
                    node child_state = new node();
                    child_state.i = i;
                    child_state.j = j;
                    children.add(child_state);
                    break;
                }
            }
        }
        int[][] temp = currentGame_comp.getGameBoard();
        int [][] store_temp = new int[6][7];
        for(int k = 0; k < 6; k++)
        {
            for(int l=0;l<7;l++)
            {
                store_temp[k][l] = temp[k][l];
            }
        }
        for(node current_child : children)
        {
            GameBoard tempstate = new GameBoard(store_temp);
            int [][] tempref = tempstate.getGameBoard();
            tempref[current_child.j][current_child.i] = oppositeplayer;
            GameBoard tempgame = new GameBoard(tempref);
            int temp_utility = max_value(tempgame,alpha,beta,currentdepthlevel);
            if(temp_utility < utility)
                utility = temp_utility;
            if(utility <= alpha)
                return utility;
            if(utility < beta)
                beta = utility;
        }
        return utility;
    }
    public int evalfunction(GameBoard currentGame)
    {
        int utility = 0;
        int newcurrentplayerscore = currentGame.getScore(currentplayer);
        int newoppositeplayerscore = currentGame.getScore(oppositeplayer);
        return newcurrentplayerscore + getutility(currentGame, currentplayer, 3) + getutility(currentGame, currentplayer,2) + getutility(currentGame, currentplayer,1) - newoppositeplayerscore-getutility(currentGame, oppositeplayer, 3) + getutility(currentGame, oppositeplayer,2) + getutility(currentGame, oppositeplayer,1);
    }
}