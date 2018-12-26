/**
Ian Stranathan
CS 110
Boggle phase 3
*/

import javafx.scene.shape.Rectangle;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseButton;
import javafx.event.EventHandler;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;


public class Tile extends StackPane
{
  private String letter;
  private int rowIndex;
  private int columnIndex;
  private boolean isSelected;
  private Text text;
  private Rectangle sqr;
  /**
    Constructor
    @param  letter the letter of the tile
    @param  rowIndex the i'th position saved in the board array
    @param  columnIndex the j'th position saved in the board array
    @param  columsquareSizenIndex the size of the tile used in BoggleFile
  */
  public Tile(String letter, int rowIndex, int columnIndex, double squareSize)
  {
    setStyle("-fx-background-color: lightblue");
    this.letter = letter;
    this.text = new Text(this.letter);

    this.rowIndex = rowIndex;
    this.columnIndex = columnIndex;
    this.isSelected = false;

    this.sqr = new Rectangle(squareSize, squareSize);
    this.sqr.setFill(null);
    this.sqr.setStroke(Color.BLACK);

    this.text.setFont(Font.font(48));
    setAlignment(Pos.CENTER);
    getChildren().addAll(this.sqr, this.text);
  }
  /**
    setIsSelected method, method to visually indicate if board cell has been selected
    manually changes tile's selection boolean
    @param flag a boolean for if the tile is selected
  */
  public void setIsSelected(boolean flag)
  {
    this.isSelected = flag;
    if(this.isSelected == true)
    {
      this.sqr.setFill(Color.PINK);
    }
    else
    {
      this.sqr.setFill(null);
    }
  }
  /**
    selection method, method to help visually indicate if board cell has been selected
    changes tile's selection boolean
  */
  public void selection ()
  {
    this.isSelected = !this.isSelected;

    if(this.isSelected == true)
    {
      this.sqr.setFill(Color.PINK);
    }
    else
    {
      this.sqr.setFill(null);
    }
  }

  /**
    toString method returns the instance letter of the Tile
    @return letter, the string primitive of the tile
  */
  @Override
  public String toString()
  {
    return this.letter;
  }
  /**
    setLetter sets the the letter of the tile
  */
  public void setLetter(String letter)
  {
    this.letter = letter;
  }
  /**
    getText returns the text GUI control used for the letter display
    @return text, the text widget of the tile
  */
  public Text getText()
  {
    return this.text;
  }
  /**
    setText sets text GUI control used for the letter display
  */
  public void setText(String someStr)
  {
    this.text.setText(someStr);
  }
  /**
    getRowIndex method returns the row index
    @return the i'th index of the board
  */
  public int getRowIndex()
  {
    return this.rowIndex;
  }
  /**
    getColumnIndex method returns the column index
    @return the j'th index of the board
  */
  public int getColumnIndex()
  {
    return this.columnIndex;
  }
}
