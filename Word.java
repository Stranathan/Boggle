/**
  Ian Stranathan
  22.10.18
  CS 110
  Boggle phase 1
*/

/**
  Word:
  word class takes in an array of tiles to make a string and assign points based off
  the length of that string
*/

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Word
{
  private String tileWord = "";

  private final int LENGTH_3_4 = 1;
  private final int LENGTH5 = 2;
  private final int LENGTH6 = 3;
  private final int LENGTH7 = 5;
  private final int LENGTH8 = 11;

  /**
    Constructor
    @param  tiles, an arraylist of tiles
  */
  public Word(ArrayList<Tile> tiles)
  {
    for (Tile tile : tiles)
    {
      // concatenate string of letters
      this.tileWord += tile.toString();
    }
  }
  /**
    getPoints method: assigns an integer point value for the length of the string
    made from the input tiles
  */
  public int getPoints()
  {
    int points = 0;
    int length = tileWord.length();
    if (length <= 4)
    {
      points = LENGTH_3_4;
    }
    else if (length == 5)
    {
      points = LENGTH5;
    }
    else if (length == 6)
    {
      points = LENGTH6;
    }
    else if (length == 7)
    {
      points = LENGTH7;
    }
    else if (length >= 8)
    {
      points = LENGTH8;
    }
    return points;
  }
  /**
    getWord() method returns the word the Word class made from the input tiles
  */
  public String getWord()
  {
    return tileWord;
  }
}
