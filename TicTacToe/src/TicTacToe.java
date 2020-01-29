import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToe extends JFrame {
    
    // constants for the game board
    public static final int ROWS = 4;  
    public static final int COLS = 4;

    // dimensions used
    public static final int CELL_SIZE = 200; 
    public static final int CANVAS_WIDTH =CELL_SIZE * COLS; 
    public static final int CANVAS_HEIGHT =CELL_SIZE * ROWS;
    public static final int GRID_WIDTH = 8;                   
    public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;
    public static final int CELL_PADDING = CELL_SIZE / 6;
    public static final int SYMBOL_SIZE = CELL_SIZE - CELL_PADDING * 2; 
    public static final int SYMBOL_STROKE_WIDTH = 8; 
    
    // track game states
    public enum GameState {
        PLAYING, DRAW, CROSS_WON, NOUGHT_WON
    }
    private GameState currentState;  // the current game state

    // representation of players and empty cells
    public enum Seed {
        EMPTY, CROSS, NOUGHT
    }
    private Seed currentPlayer;  // the current player

    private Seed[][] board   ; // set up board
    private DrawCanvas canvas; // gameboard drawing
    private JLabel statusBar;  // for each turn
    private JLabel statusBar1; //status bar player x 
    private JLabel statusBar2; //status bar player o 
    private JLabel winx;      // label for winner
    private int gameScorex=0;  //score count for x
    private int gameScoreo=0;  // score count for o
    private int num=1;       // double turn function 
    

    public TicTacToe() { 
        canvas = new DrawCanvas();  
        canvas.setPreferredSize(new Dimension(1080,800)); // dimensions

        canvas.addMouseListener(new MouseAdapter() { //mouseclick-listener in the board
            @Override
            
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                
                // get the row and column clicked
                int rowSelected = mouseY / CELL_SIZE;
                int colSelected = mouseX / CELL_SIZE;

                if (currentState == GameState.PLAYING) 
                {
                    
                    
                        
                        
                    if (rowSelected >= 0 && rowSelected < ROWS && colSelected >= 0
                            && colSelected < COLS && board[rowSelected][colSelected] == Seed.EMPTY)
                    {
                        board[rowSelected][colSelected] = currentPlayer; // make a move                       
                                               
                        updateGame(currentPlayer, rowSelected, colSelected);// update state
                        // switch player
                                               
                                                       
                        currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;                                                                              
                           
                        // checkturn();  double turn function does not work

                    }
                    
                } else
                {       // game over
                    initGame(); // restart the game
                }
                repaint();  // refresh paintComponent
                
            }
        });
            
        // status-bar to display status message
        
        statusBar = new JLabel("  ");                                                     
        statusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        statusBar.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));
        statusBar1= new JLabel("  ");
        statusBar1.setFont (new Font(Font.DIALOG_INPUT,Font.BOLD,16));
        statusBar1.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));
        statusBar2 = new JLabel("  ");
        statusBar2.setFont (new Font(Font.DIALOG_INPUT,Font.BOLD,16));
        statusBar2.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));
        winx= new JLabel (" ");
        winx.setFont (new Font(Font.DIALOG_INPUT,Font.BOLD,16));
        winx.setBorder(BorderFactory.createEmptyBorder(2,5,4,5));
               
              
               
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(statusBar, BorderLayout.PAGE_END);
        cp.add(statusBar1, BorderLayout.EAST);
        cp.add(statusBar2, BorderLayout.WEST);
        cp.add(winx,BorderLayout.NORTH);
        

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();  // pack all the components in this JFrame
        setTitle("Tic Tac Toe 4x4");
        setVisible(true);  // show this JFrame

        board = new Seed[ROWS][COLS];
        initGame(); // start the game board contents and game variables
        
        
    }

   /* public void checkturn(){                        Currently working only on first round only
        while(currentPlayer==Seed.CROSS)              Tried to make it happen with 4 cases and loop
        {                                             but no success 
        switch(num){
        case 1:
            currentPlayer = Seed.CROSS;
            num++;
            break;
        case 2:
             currentPlayer = Seed.NOUGHT;
            num++;
            break;
        case 3:
            currentPlayer = Seed.NOUGHT;
            num++;
            break;        
        case 4:
            currentPlayer = Seed.CROSS;
            num++;
            break;
        case 5:
            currentPlayer = Seed.CROSS;
            num++;
            break;          
        case 6:
            currentPlayer = Seed.NOUGHT;
            num++;
            break;
        case 7:
            currentPlayer = Seed.NOUGHT;
            num++;
            break;
        case 8:
             currentPlayer = Seed.CROSS;
            num++;
            break; 
         case 9:
            currentPlayer = Seed.CROSS;
            num++;
            break;
        case 10:
            currentPlayer = Seed.NOUGHT;
            num++;
            break;
        case 11:
            currentPlayer = Seed.NOUGHT;
            num++;
            break;
        case 12:
             currentPlayer = Seed.CROSS;
            num++;
            break;
        case 13:
            currentPlayer = Seed.CROSS;
            num++;
            break;
        case 14:
            currentPlayer = Seed.NOUGHT;
            num++;
            break;
        case 15:
            currentPlayer = Seed.NOUGHT;
            num++;
            break;
        case 16:
            currentPlayer = Seed.CROSS;
            num++;
            break;      
        
        
        }
        }
        
        
            
           
}    */     
      
    
    /** game-board contents and the status */
          
       
    
    public void initGame() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                board[row][col] = Seed.EMPTY; // all cells empty
            }
        }
        currentState = GameState.PLAYING; // set State to "Playing"
        
        currentPlayer=Seed.CROSS;       // initial turn X
       
    }
    
    public void gameScore(){               // game score for each player
        
        if (currentState == GameState.CROSS_WON){
            gameScorex++;
        }
        else if (currentState == GameState.NOUGHT_WON){
            gameScoreo++;
        }
        
    }
    
   

    /**update the current state after player makes the move in row or column*/
    public void updateGame(Seed theSeed, int rowSelected, int colSelected) {
        if (hasWon(theSeed, rowSelected, colSelected)) {  // check for win
            currentState = (theSeed == Seed.CROSS) ? GameState.CROSS_WON : GameState.NOUGHT_WON;
            gameScore();
            
        } else if (isDraw()) {  // check for draw
            currentState = GameState.DRAW;
        }
    }

    /** return true if draw  */
    public boolean isDraw() {
        for (int row = 0; row < ROWS; ++row) {
            for (int col = 0; col < COLS; ++col) {
                if (board[row][col] == Seed.EMPTY) {
                    return false;
                }
            }
        }
        return true;  // no more empty cell, it's a draw
    }

    /** return true if player x or player o has won */
    public boolean hasWon(Seed theSeed, int rowSelected, int colSelected) {
        
        return (board[rowSelected][0] == theSeed  // check 4 row
                && board[rowSelected][1] == theSeed
                && board[rowSelected][2] == theSeed
                && board[rowSelected] [3]==theSeed
                || board[0][colSelected] == theSeed      // check 4 column
                && board[1][colSelected] == theSeed
                && board[2][colSelected] == theSeed
                && board[3][colSelected] == theSeed
                || rowSelected == colSelected            // check 4 diagonal
                && board[0][0] == theSeed
                && board[1][1] == theSeed
                && board[2][2] == theSeed
                && board[3][3] ==theSeed
                || rowSelected + colSelected == 3  // check 4 reverse diagonal
                && board[0][3] == theSeed
                && board[1][2] == theSeed
                && board[2][1] == theSeed
                &&board [3][0]==theSeed);
    }

//   drawing
    class DrawCanvas extends JPanel {
        
        
        @Override
        
        public void paintComponent(Graphics g) {  // invoke via repaint()
            super.paintComponent(g);    // fill background
            setBackground(Color.DARK_GRAY); // set background color
            
            

            // draw the grid-lines
            g.setColor(Color.BLACK);
            for (int row = 1; row < ROWS; ++row) {
                g.fillRoundRect(0, CELL_SIZE * row - GRID_WIDHT_HALF,
                        CANVAS_WIDTH-1, GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
            }
            for (int col = 1; col < COLS; ++col) {
                g.fillRoundRect(CELL_SIZE * col - GRID_WIDHT_HALF, 0,
                        GRID_WIDTH, CANVAS_HEIGHT-1, GRID_WIDTH, GRID_WIDTH);
            }

            Graphics2D g2d = (Graphics2D)g;
            g2d.setStroke(new BasicStroke(SYMBOL_STROKE_WIDTH, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND)); // graphics for the board
            for (int row = 0; row < ROWS; ++row) {
                for (int col = 0; col < COLS; ++col) {
                    int x1 = col * CELL_SIZE + CELL_PADDING;
                    int y1 = row * CELL_SIZE + CELL_PADDING;
                    if (board[row][col] == Seed.CROSS) { //graphics for x
                        g2d.setColor(Color.RED);
                        int x2 = (col + 1) * CELL_SIZE - CELL_PADDING;
                        int y2 = (row + 1) * CELL_SIZE - CELL_PADDING;
                        g2d.drawLine(x1, y1, x2, y2);
                        g2d.drawLine(x2, y1, x1, y2);
                    } else if (board[row][col] == Seed.NOUGHT) {     //  graphics for o
                        g2d.setColor(Color.BLUE);
                        g2d.drawOval(x1, y1, SYMBOL_SIZE, SYMBOL_SIZE);
                    }
                }
            }

            // print status bars
            if (currentState == GameState.PLAYING) {
                statusBar.setForeground(Color.BLACK);
                statusBar1.setForeground(Color.RED);
                statusBar2.setForeground(Color.BLUE);
                statusBar1.setText("Player'X'score" +" "+ gameScorex);
                    statusBar2.setText("PlAYER'O'score" +" "+gameScoreo);
                
                if (currentPlayer == Seed.CROSS) {
                    statusBar.setText("Player 'X's Turn");
                } else {
                    statusBar.setText("Player 'O's Turn");
                }
            } else if (currentState == GameState.DRAW) {
                statusBar.setForeground(Color.RED);
                statusBar.setText("It's a Draw! Click to play again.");                
            } else if (currentState == GameState.CROSS_WON) {
                statusBar.setForeground(Color.RED);
                statusBar.setText("Player 'X' Won! Click to play again.");
              
            } else if (currentState == GameState.NOUGHT_WON) {
                statusBar.setForeground(Color.BLUE);
                statusBar.setText("Player 'O' Won! Click to play again.");
           
            }
            if (gameScorex==3) // end of game rule x
            {
                gameScorex=0;
                gameScoreo=0;
                statusBar1.setText("Player'X'score" +" "+ gameScorex);
                statusBar2.setText("PlAYER'O'score" +" "+gameScoreo);
                winx.setText("Player 'X' won this game");
                
                
            
                
            }else if (gameScoreo==3){   // end of game rule o
                gameScorex=0;
                gameScoreo=0;
                statusBar1.setText("Player'X'score" +" "+ gameScorex);
                statusBar2.setText("PlAYER'O'score" +" "+gameScoreo);
                winx.setText("Player 'O' won this game");
               
               
            }
        }
    }

    public static void main(String[] args) {
      
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe();
                
            }
            
        });
    }
}