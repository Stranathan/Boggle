/**
  Ian Stranathan
  06.12.18
  CS 110
  Boggle phase 3
*/

/**
  Board:
  represents the playing board and the letters showing on the board.
  Each row is an ArrayList of tile objects, and the entire board is an ArrayList of rows.
  Thus this contains an ArrayList of ArrayLists of Tile objects.
*/

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Scanner;

public class Board
{
  // seperate data from it's display -- seems like it will be easier to handle
  // later down the road if I do this
  public static ArrayList<ArrayList<Tile>> dataRows = new ArrayList<ArrayList<Tile>>();
  private ArrayList<ArrayList<String>> displayRows = new ArrayList<ArrayList<String>>();

  // expose size of board for making the 2D array
  private int boardSize = 4;
  private double squareSize = BoggleFinal.squareSize;

  // might change later, but no way to change it currently, if you're nitpicky it should
  // be constant I guess.
  private int numOfDies = 16;
  private int numOfSides = 6;

  // 16 die to randomly choose from
  private ArrayList<String> DIE0 = new ArrayList<String>();
  private ArrayList<String> DIE1 = new ArrayList<String>();
  private ArrayList<String> DIE2 = new ArrayList<String>();
  private ArrayList<String> DIE3 = new ArrayList<String>();
  private ArrayList<String> DIE4 = new ArrayList<String>();
  private ArrayList<String> DIE5 = new ArrayList<String>();
  private ArrayList<String> DIE6 = new ArrayList<String>();
  private ArrayList<String> DIE7 = new ArrayList<String>();
  private ArrayList<String> DIE8 = new ArrayList<String>();
  private ArrayList<String> DIE9 = new ArrayList<String>();
  private ArrayList<String> DIE10 = new ArrayList<String>();
  private ArrayList<String> DIE11 = new ArrayList<String>();
  private ArrayList<String> DIE12 = new ArrayList<String>();
  private ArrayList<String> DIE13 = new ArrayList<String>();
  private ArrayList<String> DIE14 = new ArrayList<String>();
  private ArrayList<String> DIE15 = new ArrayList<String>();

  // array of arrays to store each die
  private ArrayList<ArrayList<String>>  DIES = new ArrayList<ArrayList<String>>();

  /**
    Constructor, no param
    Board has 16 die, each die has 6 strings representing a face of a die
    Each board has sets of dies which it randomly parses to make tile objects and saves
    to a data arraylist and a display array list
  */
  public Board ()
  {
    // add given letters to each die
    DIE0.add("R");
    DIE0.add("I");
    DIE0.add("F");
    DIE0.add("O");
    DIE0.add("B");
    DIE0.add("X");

    DIE1.add("I");
    DIE1.add("F");
    DIE1.add("E");
    DIE1.add("H");
    DIE1.add("E");
    DIE1.add("Y");

    DIE2.add("D");
    DIE2.add("E");
    DIE2.add("N");
    DIE2.add("O");
    DIE2.add("W");
    DIE2.add("S");

    DIE3.add("U");
    DIE3.add("T");
    DIE3.add("O");
    DIE3.add("K");
    DIE3.add("N");
    DIE3.add("D");

    DIE4.add("H");
    DIE4.add("M");
    DIE4.add("S");
    DIE4.add("R");
    DIE4.add("A");
    DIE4.add("O");

    DIE5.add("L");
    DIE5.add("U");
    DIE5.add("P");
    DIE5.add("E");
    DIE5.add("T");
    DIE5.add("S");

    DIE6.add("A");
    DIE6.add("C");
    DIE6.add("I");
    DIE6.add("T");
    DIE6.add("O");
    DIE6.add("A");

    DIE7.add("Y");
    DIE7.add("L");
    DIE7.add("G");
    DIE7.add("K");
    DIE7.add("U");
    DIE7.add("E");

    DIE8.add("Qu");
    DIE8.add("B");
    DIE8.add("M");
    DIE8.add("J");
    DIE8.add("O");
    DIE8.add("A");

    DIE9.add("E");
    DIE9.add("H");
    DIE9.add("I");
    DIE9.add("S");
    DIE9.add("P");
    DIE9.add("N");

    DIE10.add("V");
    DIE10.add("E");
    DIE10.add("T");
    DIE10.add("I");
    DIE10.add("G");
    DIE10.add("N");

    DIE11.add("B");
    DIE11.add("A");
    DIE11.add("L");
    DIE11.add("I");
    DIE11.add("Y");
    DIE11.add("T");

    DIE12.add("E");
    DIE12.add("Z");
    DIE12.add("A");
    DIE12.add("V");
    DIE12.add("N");
    DIE12.add("D");

    DIE13.add("R");
    DIE13.add("A");
    DIE13.add("L");
    DIE13.add("E");
    DIE13.add("S");
    DIE13.add("C");

    DIE14.add("U");
    DIE14.add("W");
    DIE14.add("I");
    DIE14.add("L");
    DIE14.add("R");
    DIE14.add("G");

    DIE15.add("P");
    DIE15.add("A");
    DIE15.add("C");
    DIE15.add("E");
    DIE15.add("M");
    DIE15.add("D");

    // add dies to dies ArrayList:
    DIES.add(DIE0);
    DIES.add(DIE1);
    DIES.add(DIE2);
    DIES.add(DIE3);
    DIES.add(DIE4);
    DIES.add(DIE5);
    DIES.add(DIE6);
    DIES.add(DIE7);
    DIES.add(DIE8);
    DIES.add(DIE9);
    DIES.add(DIE10);
    DIES.add(DIE11);
    DIES.add(DIE12);
    DIES.add(DIE13);
    DIES.add(DIE14);
    DIES.add(DIE15);

    /**
      Select a random string from an array of arrays:
    */
    Random rand = new Random();

    for (int i = 0; i < boardSize; i++)
    {
      ArrayList<Tile>  aDataRow = new ArrayList<Tile>();
      ArrayList<String> aDisplayRow = new ArrayList<String>();


      for (int j = 0; j < boardSize; j++)
      {
        // ArrayList<String> selectedStringArray = new ArrayList<String>();
        int randomDieIndex = rand.nextInt(this.numOfDies); // random number to select rand index of dies
        ArrayList<String> selectedStringArray = new ArrayList<String>();
        selectedStringArray = DIES.get(randomDieIndex); // random index of DIES

        int randomIndex = rand.nextInt(this.numOfSides); // random number to select rand String from die of dies
        String letterOfDie = selectedStringArray.get(randomIndex); // random String from die

        Tile someTile = new Tile (letterOfDie, i, j, this.squareSize-3);

        aDataRow.add(someTile);
        // Qu handling
        if(someTile.toString().equals("Qu"))
        {
          aDisplayRow.add(someTile.toString());
        }
        else
        {
          aDisplayRow.add(someTile.toString());
        }
      }

      this.dataRows.add(aDataRow);
      this.displayRows.add(aDisplayRow);
    }
  }

  /**
    display method prints the display rows to console(string copies of tiles rows)
  */
  public void display()
  {
    for(ArrayList arr: this.displayRows)
    {
      // TRICK TO CORRECT CLI PRINT FORMAT:
      String myString = arr.toString();
      String replace1 = myString.replace(",", "");  //remove the commas
      String replace2 = replace1.replace("[", "");  //remove the right bracket
      String replace3 = replace2.replace("]", "");  //remove the left bracket
      String replaceFinal = replace3.trim();
      System.out.println(replaceFinal);
    }
  }
  /**
    @return datarows, an array lsit of array lists of Tiles
  */
  public ArrayList<ArrayList<Tile>> getDataRows()
  {
    return this.dataRows;
  }
  /**
    @return displayRows, an array lsit of array lists of Strings (letters of Tiles)
  */
  public ArrayList<ArrayList<String>> getDispRows()
  {
    return this.displayRows;
  }
}
