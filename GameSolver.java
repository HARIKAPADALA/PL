import java.util.ArrayList;
import java.util.List;

public class GameSolver {
    public int _size;
    public int[] _board;
    List<int[]> _positions;

    public int[][] _moves = {{0, 1, 3}, {0, 2, 5}, {1, 3, 6}, {1, 4, 8}, {2, 4, 7}, {2, 5, 9}, {3, 4, 5}, {6, 7, 8}, {7, 8, 9}};

    public GameSolver(int[] board, int n) {
        this._board = board;
        this._positions = new ArrayList<int[]>();
        this._size = n;
    }

    public int get1sCount(int[] board, int size) {
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (board[i] == 1) {
                count++;
            }
        }
        return count;
    }

    public boolean checkArraysEqual(int[] a, int[] b){
        for(int i=0; i < 3; i++){
            if(a[i]!=b[i])
                return false;
        }
        return true;
    }

    public boolean checkExists(int from, int mid, int to) {
        int[] a = {from, mid, to};
        int[] b = {to, mid, from};
        for (int i = 0; i < this._moves.length; i++) {
            if (checkArraysEqual(this._moves[i],a) || checkArraysEqual(this._moves[i],b)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkMovePossible(int[] board, int from, int mid, int to) {
        if (board[from] == 1 && board[mid] == 1 && board[to] == 0 && from != mid && mid != to && to != from && checkExists(from, mid, to)) {
            return true;
        }
        return false;
    }

    public boolean checkSolvable(int[] board, int size, List<int[]> snaps) {
        int count = get1sCount(board, size);
        if (count == 1) {
            this._positions = snaps;
            return true;
        } else {
            for (int i = 0; i < size; i++) {
                if (board[i] == 0)
                    continue;
                for (int j = 0; j < size; j++) {
                    if (board[j] == 0)
                        continue;
                    for (int k = 0; k < size; k++) {
                        if (board[k] == 1)
                            continue;
                        if (checkMovePossible(board, i, j, k)) {
                            this._board[i] = 0;
                            this._board[j] = 0;
                            this._board[k] = 1;

                            int[] a = {i,j,k};
                            boolean nex = checkSolvable(this._board, size, snaps);
                            if (nex) {
                                snaps.add(a);
                                return true;
                            } else {
                                this._board[i] = 1;
                                this._board[j] = 1;
                                this._board[k] = 0;
                                continue;
                            }
                        }
                    }
                }
            }
            return false;
        }
    }
}
