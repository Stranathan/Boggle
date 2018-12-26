/**
Ian Stranathan
CS 110
Boggle Phase 3
*/

import java.util.ArrayList;
import java.util.Random;
import java.io.*;
import java.util.Scanner;
import java.io.IOException;
import java.lang.*;

public class GameGUI
{
  private ArrayList<Tile> selectedTiles;
  private ArrayList<Word> words;
  public static Board board;
  private Dictionary dict;
  public static int score;

  private int lastSelectedI;
  private int lastSelectedJ;

  public String stateMessage;

  /**
    Constructor
  */
  public GameGUI() throws IOException
  {
    this.selectedTiles = new ArrayList<Tile>();
    this.words = new ArrayList<Word>();
    this.score = 0;
    this.board = new Board();
    this.dict = new Dictionary("dictionary.txt");
    this.lastSelectedI = 100;
    this.lastSelectedJ = 100;
    this.stateMessage = "";
  }
  /**
    getClickIndices method used for testing, not used otherwise
  */
  public void getClickIndices()
  {
    System.out.println("the saved i index is: " + lastSelectedI + " and the the saved j index is: " + lastSelectedJ);
  }
  /**
    isValidSelection method used for logic of Boggle selection
    @param row the i'th input (i,j) cartesian
    @param col the jth input (i,j) cartesian
    @return boolean
  */
  public boolean isValidSelection(int row, int col)
  {

    if (lastSelectedI == 100 || lastSelectedJ == 100)
    {
      lastSelectedI = row;
      lastSelectedJ = col;
      return true;
    }
    //
    else if((Math.abs(lastSelectedI - row) <= 1) && (Math.abs(lastSelectedJ - col) <= 1))
    {
       lastSelectedI = row;
       lastSelectedJ = col;
       return true;
    }
    else
    {
      return false;
    }
  }
  /**
    addToSelected method
    @param row the i'th input (i,j) cartesian
    @param col the jth input (i,j) cartesian
  */
  public void addToSelected(int row, int col)
  {
    ArrayList<ArrayList<Tile>> copyOfBoard = this.board.getDataRows();
    ArrayList<Tile> copyOfOneRow = copyOfBoard.get(row);
    Tile tile = copyOfOneRow.get(col);
    this.selectedTiles.add(tile);
  }
  /**
    displaySelectedTiles, CLI Display of the selected tiles
  */
  public void displaySelectedTiles()
  {
    ArrayList<String> displaySelected = new ArrayList<String>();
    for (Tile tile: this.selectedTiles)
    {
      displaySelected.add(tile.toString());
    }
    System.out.println("Selected letters:  " + displaySelected);
  }
  /**
    getSelectedTiles
    @return selectedTileString, string of the selected Tiles
  */
  public String getSelectedTiles()
  {
    String selectedTileString = "";

    for (Tile tile: this.selectedTiles)
    {
      selectedTileString += tile.toString();
    }
    return selectedTileString;
  }
  /**
    getSelectedTilesArr
    @return selectedTiles, arraylist of selected Tiles
  */
  public ArrayList<Tile> getSelectedTilesArr ()
  {
    return selectedTiles;
  }
  /**
    displayWords
    CLI display of collected words from words arraylist
  */
  public void displayWords()
  {
    ArrayList<String> displayWords = new ArrayList<String>();
    for (Word word: this.words)
    {
      displayWords.add(word.getWord());
    }
    System.out.println("Words:  " + displayWords);
  }
  /**
    getWords
    @return returns the arraylist of collected words for Boggle GUI displayWords
  */
  public ArrayList<String> getWords()
  {
    ArrayList<String> displayWords = new ArrayList<String>();
    for (Word word: this.words)
    {
      displayWords.add(word.getWord());
    }
    return displayWords;
  }
  /**
    displayBoard calls the Board class' display method. Print of board.
  */
  public void displayBoard()
  {
    board.display();
  }

  public void removeFromSelected(int row, int col)
  {
    if(this.selectedTiles.size() > 0)
    {
      this.selectedTiles.remove(this.selectedTiles.size()-1);
    }
  }
  /**
    clearSelected
    Clears selected arraylist
  */
  public void clearSelected()
  {
    this.lastSelectedI = 100;
    this.lastSelectedJ = 100;
    this.selectedTiles.clear();
  }
  /**
    testSelected tests with word class and and dictionary class to see if
    a word in the selectedtile array is in fact a word
  */
  public void testSelected() throws IOException
  {
    if(this.dict.isValidWord(this.selectedTiles))
    {
      Word aWord = new Word(this.selectedTiles);

      if(this.words.size() == 0)
      {
        this.words.add(aWord);
        this.score += aWord.getPoints();
        clearSelected();
        this.stateMessage = "Great first word!";
      }
      // check to see if word has been made already:
      else if(this.words.size() > 0)
      {
        boolean sameWordCheck = false;

        for (Word word: this.words)
        {
          if(word.getWord().equalsIgnoreCase(aWord.getWord()))
          {
            this.stateMessage = "You've already made this word";
            sameWordCheck = true;
            clearSelected();
          }
        }
        if(sameWordCheck == false)
        {
          this.stateMessage = "Good Job! That's a word";
          this.words.add(aWord);
          this.score += aWord.getPoints();
          clearSelected();
        }
      }
    }
    else
    {
      this.stateMessage = "Sorry, that's not a word";
      clearSelected();
    }
  }
  /**
    getTile returns a tile from the board with the given I and J indices
    @return tile, a tile from the board
  */
  public Tile getTile(int row, int col)
  {
    ArrayList<ArrayList<Tile>> copyOfBoard = this.board.getDataRows();
    ArrayList<Tile> copyOfOneRow = copyOfBoard.get(row);
    Tile tile = copyOfOneRow.get(col);
    return tile;
  }
  /**
    Displays instance variable score
  */
  public void displayScore()
  {
    System.out.printf("Score:  %d\n", this.score);
  }
  /**
    Displays instance variable score
    @return gets the score
  */
  public String getScore()
  {
    return Integer.toString(this.score);
  }

}
