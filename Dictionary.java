/**
  Ian Stranathan
  22.10.18
  CS 110
  Boggle phase 1
*/

/**
  Ditionary:
  Uses a dictionary text file to test if input tile letters form a word
*/

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

/**
  I hardcoded the string that is made from the tiles to be lowercase;
  the game only takes uppercase die and the Dictionary only has lower case words
   -- change later if this changes
  or find a way to make the scanner hasNext() method case insensitive
*/

public class Dictionary
{

  private File wordFile;
  private Scanner wordScanner;

  /**
    Constructor
    @param  fileName a text file of a lot of words acting as an english dictionary
  */
  public Dictionary(String fileName) throws IOException
  {
    this.wordFile = new File(fileName);
    this.wordScanner = new Scanner(wordFile);
  }

  /**
    Each array element is an array of tiles; each tile holds a letter
    @param tiles an ArrayList of Tile objects
    @return isAWord a boolean testing if it's a word
  */
  public boolean isValidWord(ArrayList<Tile> tiles) throws IOException
  {
    String tileWord = "";
    boolean isAWord = false;

    // concatenate a string from the letters of the tiles
    for (Tile tile : tiles)
    {
      tileWord += tile.toString();
      // lowercase check.. another annoying bug.
      tileWord = tileWord.toLowerCase();
    }

    while (this.wordScanner.hasNext())
    {
      if (this.wordScanner.next().equals(tileWord))
      {
        isAWord = true;
      }
    }
    // reset the scanner... was a hard bug to find
    this.wordScanner = new Scanner(this.wordFile);
    return isAWord;
  }
}
