import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrackerBarrelPegGame {
    public int _size;
    public String _filename;

    public CrackerBarrelPegGame(int N, String filename) {
    this._size = N;
    this._filename = filename;
    }

    public void solveGame() throws IOException {
        GameBoard gameBoard = new GameBoard(this._size);
        int[][] boards = gameBoard._boards;
        GamePrinter gamePrinterObj = new GamePrinter(0, new ArrayList<int[]>(), _filename);;
        for(int i=0; i<gameBoard._board_size;i++){
            List<int[]> snapshots = new ArrayList<int[]>();
        GameSolver gameSolver = new GameSolver(boards[i], gameBoard._board_size);
        boolean solved = gameSolver.checkSolvable(boards[i], gameBoard._board_size, snapshots);
        if(solved){
            GamePrinter gamePrinter = new GamePrinter(i, gameSolver._positions, _filename);
            gamePrinterObj = gamePrinter;
        }
        }
        gamePrinterObj.printGames();
    }

        public static void main(String[] args) throws IOException {
        CrackerBarrelPegGame game = new CrackerBarrelPegGame(4, "puzzle.txt");
        game.solveGame();
            System.out.println("Finished solving.");
    }
}
