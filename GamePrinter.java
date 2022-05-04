import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GamePrinter {
    private static List<Integer>  _positions = new ArrayList<Integer>();
    private static List<List<int[]>> _moves = new ArrayList<List<int[]>>();
    private static String _filename;

    public GamePrinter(int position, List<int[]> moves, String filename)  {

        this._positions.add(position);
        this._moves.add(moves);
        this._filename = filename;
    }

    public String getString (int val){
        if(val == 1){
            return "*";
        }
        return ".";
    }


    public String getGamePyramid(List<Integer> moves){
        String a0 = getString(moves.get(0));
        String a1 = getString(moves.get(1));
        String a3 = getString(moves.get(3));
        String a4 = getString(moves.get(4));
        String a2 = getString(moves.get(2));
        String a5 = getString(moves.get(5));
        String a6 = getString(moves.get(6));
        String a7 = getString(moves.get(7));
        String a8 = getString(moves.get(8));
        String a9 = getString(moves.get(9));
        String s = "";
        s+="   " + a0 + "\n";
        s+="  " + a1 + " " + a2 + "\n";
        s+=" " + a3 + " " + a4 + " " + a5 + "\n";
        s+= a6 + " " + a7 + " " + a8 + " " + a9 + "\n\n";
        return s;
    }

    public List<Integer> getNewBoard(List<Integer> board, int[] move){
        int a = move[0];
        int b = move[1];
        int c = move[2];

        board.set(a,0);
        board.set(b,0);
        board.set(c,1);

        return board;
    }

    public void printSingle(int position, List<int[]> moves, FileWriter myWriter) throws IOException {
        List<Integer> board = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            if (i == position) {
                board.add(0);
            } else {
                board.add(1);
            }
        }
        Collections.reverse(moves);
        for (int i = 0; i < moves.size(); i++) {
            String s = "m(" + moves.get(i)[0] + "," + moves.get(i)[1] + "," + moves.get(i)[2] + ")\n";
            myWriter.write(s);
        }
        myWriter.write("\n");

        for (int i = 0; i < moves.size(); i++) {
            String pyramid = getGamePyramid(board);
            board = getNewBoard(board, moves.get(i));
            myWriter.write(pyramid);
        }
        String pyramid = getGamePyramid(board);
        myWriter.write(pyramid + "\n");
        myWriter.write("solved("+ position + ")\n");
        myWriter.write("------------------------------------------------------------------------------------------\n\n");
    }


    public void printGames() throws IOException {
        FileWriter myWriter = new FileWriter(_filename);
        int size = _positions.size();
        for(int i=1; i<size; i++){
            printSingle(_positions.get(i),  _moves.get(i), myWriter);
        }
        myWriter.close();
    }

}
