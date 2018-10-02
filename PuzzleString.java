package puzzle_string;

import java.io.LineNumberReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;

import java.lang.*;

public class PuzzleString {
  int foundRow;
  int foundColumn;
  String word;
  char[][] wordPuzzle;
  int found = 0;
  int x[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
  int y[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

  public static void main(String[] args) throws Exception {
      PuzzleString p = new PuzzleString();
      p.puzzleString();
  }

  private void puzzleString() throws IOException{
      Object[] input = convertDataToArray(new File("puzzle_string/input.txt").getAbsolutePath());
      // System.out.println("File: "+input[0]);

      // Object[] input = { 3, 3, 4, "catt", "aata", "tatc", "cat", 5, 5, "gogog", "ooooo", "godog", "ooooo", "gogog", "dog", 2, 8, "bananana", "kalibrrr", "nana" };

      int T = (Integer) Integer.valueOf((String) input[0]);
      int point = 0;
      int row = 0;
      for (int i = 1; i <= T; i++) {
          found = 0;
          point++;
          row = (Integer) Integer.valueOf((String) input[point]);
          point++;
          int col = (Integer) Integer.valueOf((String) input[point]);
          wordPuzzle = new char[row][col];
          for (int j = 0; j < wordPuzzle.length; j++) {
              point++;
              wordPuzzle[j] = input[point].toString().toCharArray();
          }
          point++;
          word = input[point].toString();

          for (int r = 0; r < row; r++) {
              for (int c = 0; c < col; c++) {
                  searchWordPuzzle(wordPuzzle, r, c, word);
              }
          }
          System.out.println("Case "+i+": "+found);
      }
  }

  void searchWordPuzzle(char grid[][], int row, int col, String word) {
      if (grid[row][col] != word.charAt(0))
          return;
      int len = word.length();
      for (int dir = 0; dir < 8; dir++){
          int k, rd = row + x[dir], cd = col + y[dir];
          for (k = 1; k < len; k++){
            if (rd >= grid.length || rd < 0 || cd >= grid[0].length || cd < 0)
                  break;
              if (grid[rd][cd] != word.charAt(k))
                  break;

              rd += x[dir];
              cd += y[dir];
          }
          if (k == len)
              found++;
      }
      return;
  }

  Object[] convertDataToArray(String file) throws IOException {
    FileReader fr = null;
    LineNumberReader lnr = null;
    String str;
    int i;

    ArrayList<Object> arrObj  = new ArrayList<Object>();

    try {
      // create new reader
      fr  = new FileReader(file);
      lnr = new LineNumberReader(fr);

      // read lines till the end of the stream
      while((str = lnr.readLine())!=null) {
        i = lnr.getLineNumber();
        arrObj.add(str);
        // System.out.print("("+i+")");
        // System.out.println(str);
      }
    } catch(Exception e) {
      e.printStackTrace();
    } finally {
      if(fr!=null)
        fr.close();
      if(lnr!=null)
        lnr.close();
    }
    return arrObj.toArray();
  }
}
