/**
Ian Stranathan
CS 110
Boggle Phase 3
*/

// I'm sure a lot of these imports are not needed, but I'm not using an IDE
// and my textEditor doesn't show which dependencies are unused.

// Sorry for overcomplications/ convolutions/ generally bad programming
// https://xkcd.com/1513/

import java.lang.Math;
import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ListView;
import javafx.scene.control.ButtonBar;
import java.util.ArrayList;
import javafx.scene.control.ButtonBar.ButtonData;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import java.util.Optional;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.IOException;

public class BoggleFinal extends Application
{
  public static double screenWidth = 700;
  public static double screenHeight = 600;
  public static double boardSize =  screenWidth * (double)7/10;
  public static double squareSize = boardSize/4;
  private Pane board;
  private StackPane selectedWordPane;
  private Tile[][] tileArr;
  private GameGUI game; //
  public TextArea listOfCompletedWords;
  private Text tempScore;
  private TextField displayField;
  private Text selectedText;
  private Timer timer;
  private HBox TimerScoreHBox2;
  private VBox boardBox;

  @Override
  public void start(Stage stage) throws IOException
  {
    // intialize game and draw board
    this.game = new GameGUI();
    drawBoardInit();
    drawSelectedWordsInit();
    this.listOfCompletedWords = new TextArea();

    // #-- LHS of GUI
    HBox root = new HBox();
    VBox LHS = new VBox();
    this.boardBox = new VBox();
    HBox textAndButton = new HBox();
    VBox LHStexts = new VBox();
    VBox checkCancelVBox = new VBox();

    // Display Message
    displayField = new TextField();

    displayField.setPromptText("BOGGLE!");
    displayField.positionCaret(4);
    displayField.setEditable(false);
    // displayField.setPromptText();
    displayField.setPrefSize((boardSize + 8)*(3.0/4.0), ((screenHeight - boardSize) - 2)/2.0); // difference of each sqaure subtractio

    //// Check and Clear Button
    Button checkSelectedButton = new Button();
    checkSelectedButton.setPrefSize(squareSize, (screenHeight - boardSize)/2.0);
    Image checkMarkImage48 = new Image(getClass().getResourceAsStream("checkIcon32.png"));
    checkSelectedButton.setGraphic(new ImageView(checkMarkImage48));

    Button clearSelectedButton = new Button();
    clearSelectedButton.setPrefSize(squareSize, (screenHeight - boardSize)/2.0);
    Image clearImage = new Image(getClass().getResourceAsStream("clear32.png"));
    clearSelectedButton.setGraphic(new ImageView(clearImage));

    checkCancelVBox.getChildren().addAll(checkSelectedButton, clearSelectedButton);

    // #-- RHS of GUI
    VBox RHS = new VBox();
    VBox RHSContainer = new VBox();
    VBox wordListBox = new VBox();
    VBox wordListBox2 = new VBox();
    HBox endBox = new HBox();
    HBox endBox2 = new HBox();
    HBox TimerScoreHBox = new HBox();
    this.TimerScoreHBox2 = new HBox();

    // TIMER
    this.timer = new Timer();
    TimerScoreHBox2.setAlignment(Pos.CENTER);
    TimerScoreHBox2.getChildren().add(this.timer);
    TitledBorder timerPane = new TitledBorder("Timer");
    timerPane.setAlignment(Pos.CENTER);
    timerPane.setPrefSize((screenWidth - boardSize - 4)/2.0, 50); // arbitrary y
    timerPane.getChildren().add(TimerScoreHBox2);

    // SCORE
    this.tempScore = new Text();
    tempScore.setText("0");
    tempScore.setFill(Color.RED);
    tempScore.setFont(Font.getDefault());
    TitledBorder scorePane = new TitledBorder("Score");
    scorePane.setPrefSize((screenWidth - boardSize - 4)/2.0, 50);
    scorePane.getChildren().add(tempScore);
    TimerScoreHBox.getChildren().addAll(timerPane, scorePane);

    // WORD LIST
    listOfCompletedWords.setPrefSize((screenWidth - boardSize - 30),425);
    listOfCompletedWords.setEditable(false);
    listOfCompletedWords.setMouseTransparent(true);
    listOfCompletedWords.setFocusTraversable(false);

    TitledBorder completedWordPane = new TitledBorder("Completed Words");
    completedWordPane.setPrefSize((screenWidth - boardSize - 4), 440);
    completedWordPane.setAlignment(Pos.CENTER);
    wordListBox2.getChildren().add(listOfCompletedWords);
    // double top, double right, double bottom, double left
    wordListBox2.setPadding(new Insets(15, 0, 0, 0));
    completedWordPane.getChildren().add(wordListBox2);
    wordListBox.getChildren().add(completedWordPane);
    // putting things on this pane made everything line up better
    Pane tempPane2 = new Pane();
    tempPane2.setPrefSize(screenWidth - (boardSize - 4), (screenHeight - 4));
    tempPane2.setStyle("-fx-background-color: -fx-background");

    TitledBorder endPanel = new TitledBorder("Play Again or End Game");
    endPanel.setPrefSize((screenWidth - boardSize - 4), 100);
    endPanel.setAlignment(Pos.CENTER);

    Button playAgainButton = new Button();
    Image playAgainImage32 = new Image(getClass().getResourceAsStream("playAgain32.png"));
    playAgainButton.setGraphic(new ImageView(playAgainImage32));

    Button endButton = new Button();
    Image endImage32 = new Image(getClass().getResourceAsStream("end32.png"));
    endButton.setGraphic(new ImageView(endImage32));
    endBox2.setAlignment(Pos.CENTER);
    endBox2.getChildren().addAll(playAgainButton, endButton);
    endPanel.getChildren().add(endBox2);

    endBox.getChildren().add(endPanel);
    endBox.setPadding(new Insets(2, 0, 0, 0));
    // #--
    // double top, double right, double bottom, double left
    boardBox.setPadding(new Insets(0, 0, 2, 0));
    boardBox.getChildren().add(board);

    LHStexts.setPadding(new Insets(0, 2, 0, 0));
    LHStexts.getChildren().addAll(selectedWordPane, displayField);
    checkCancelVBox.setPadding(new Insets(0, 0, 0, 0));

    textAndButton.setPadding(new Insets(2, 2, 0, 0));
    textAndButton.getChildren().addAll(LHStexts, checkCancelVBox);

    LHS.setPadding(new Insets(0, 1, 0, 0));
    LHS.getChildren().addAll(boardBox, textAndButton);

    // RHS SIZING --  double top, double right, double bottom, double left
    wordListBox.setPadding(new Insets(2, 1, 0, 0));
    // put 1 boxSizer here
    RHSContainer.getChildren().addAll(TimerScoreHBox, wordListBox, endBox);
    tempPane2.getChildren().addAll(RHSContainer);
    RHS.setPadding(new Insets(0, 2, 0, 0));
    RHS.getChildren().addAll(tempPane2);

    root.setPrefSize(screenWidth, screenHeight);
    // double top, double right, double bottom, double left
    root.setPadding(new Insets(3, 3, 3, 3));
    root.getChildren().addAll(LHS, RHS);

    playAgainButton.setOnAction(this::handleNewGame);
    endButton.setOnAction(this::handleEndGame);
    checkSelectedButton.setOnAction(this::handleCheckClick);
    clearSelectedButton.setOnAction(this::handleClearClick);

    // Highest level container Scene -- seems like convention to call the thing
    // that goes in scene "root"
    Scene scene = new Scene(root);

    //Setting title to the Stage
    stage.setTitle("Boggle Final");

    // disable resizing because everything is hardcoded...
    stage.setResizable(false);

    // Adding scene to the stage
    stage.setScene(scene);

    // link css file
    scene.getStylesheets().add("style.css");

    //Displaying the contents of the stage

    stage.show();
  }

  // #-- NEW GAME
  // pretty hacky, sorry it's so messy
  public void handleNewGame(ActionEvent event)
  {
    try
    {
      Alert alert = new Alert(AlertType.CONFIRMATION);
      alert.setTitle("Restart Game");
      alert.setHeaderText("Would you like restart the game?");
      alert.setContentText("Ok to restart, cancel to continue");

      Optional<ButtonType> result = alert.showAndWait();
      if (result.get() == ButtonType.OK)
      {
        this.game = new GameGUI();

        this.board.getChildren().clear();

        // workaround -- super hacky and over complicated, but it works
        for (int i = 0; i < 4; i++)
        {
          ArrayList<String> copyOfOneDisplayRow = this.game.board.getDispRows().get(i);
          for (int j = 0; j < 4; j++)
          {
            this.tileArr[j][i].setLetter(copyOfOneDisplayRow.get(j));
            this.tileArr[j][i].setText(this.tileArr[j][i].toString());

            Tile newTile = this.tileArr[j][i];
            newTile.setTranslateX(j * squareSize);
            newTile.setTranslateY(i * squareSize);
            board.getChildren().add(newTile);
          }
        }
        drawSelectedWords();
        // workaround2
        this.TimerScoreHBox2.getChildren().remove(this.timer);
        this.timer.setCount(0);

        this.timer = new Timer();
        this.TimerScoreHBox2.getChildren().add(this.timer);
        this.tempScore.setText("0");
        this.listOfCompletedWords.clear();
        this.displayField.setText("");
      }
    }
    catch (IOException e) {
    }

}
  public void handleEndGame(ActionEvent event)
  {
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Quit Boggle");
    alert.setHeaderText("Thanks for playing; you scored: " + this.game.getScore() + " points.");
    alert.setContentText("Click OK to quit, or cancel to continue");

    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK)
    {

      Platform.exit();
    }
  }
  // Handles the action events on the check button
  public void handleCheckClick(ActionEvent event)
  {
    try
    {
      // turn off visual cue
      for (int i = 0; i < 4; i++)
      {
        for (int j = 0; j < 4; j++)
        {
          this.tileArr[i][j].setIsSelected(false);
        }
      }

      redrawBoard();

      this.game.testSelected();
      this.displayField.setText(this.game.stateMessage);

      this.listOfCompletedWords.clear();

      for(String str: this.game.getWords())
      {
        this.listOfCompletedWords.appendText(str + "\n");
      }

      this.tempScore.setText(this.game.getScore());

      this.game.clearSelected();
      drawSelectedWords();
    }
    catch (IOException e) {
    }

  }
  // handles the action event of the clear button
  public void handleClearClick(ActionEvent event)
  {
    this.game.clearSelected();
    drawSelectedWords();

    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        this.tileArr[i][j].setIsSelected(false);
      }
    }
    redrawBoard();
  }

  // handles mouse event on the board panel
  public void handleClick(MouseEvent event)
  {
    double j = Math.floor(event.getX() / squareSize);
    double i = Math.floor(event.getY() / squareSize);
    // System.out.println(event.getX() + ", " + event.getY() );
    // System.out.println(i + ", " + j);
    // System.out.println(i + ", " + j);
    int ii = (int)i;
    int jj = (int)j;

    // System.out.println("GUI:  " + ii + ", " + jj);
    // this.game.getClickIndices();

    if (this.game.isValidSelection(ii, jj))
    {
      if(this.game.getSelectedTilesArr().contains(this.game.getTile(ii,jj)))
      {
        this.displayField.setText("That tile is already selected");
      }
      else
      {
        this.tileArr[jj][ii].selection();
        this.game.addToSelected(ii, jj);
        // this.game.displaySelectedTiles();
        drawSelectedWords();
        this.displayField.setText("");
      }
    }
    else
    {
      this.displayField.setText("Sorry, that's an invalid tile");
    }
  }
  // first draw, makes and populates board and tile arrays
  public void drawBoardInit()
  {
    this.board = new Pane();
    board.setOnMouseClicked(this::handleClick);
    this.tileArr = new Tile[4][4];

    board.setPrefSize(this.boardSize-2 , this.boardSize-2);
    // Make tiles and add them to the board
    for (int i = 0; i < 4; i++)
    {
      ArrayList<Tile> copyOfOneRow = this.game.board.getDataRows().get(i);

      for (int j = 0; j < 4; j++)
      {
        Tile tile = copyOfOneRow.get(j);
        tile.setTranslateX(j * squareSize);
        tile.setTranslateY(i * squareSize);
        board.getChildren().add(tile);
        this.tileArr[j][i] = tile;
      }
    }
  }
  // redraws board when the selection flag is changes in submitting or clearing
  public void redrawBoard()
  {
    this.board.getChildren().clear();

    for (int i = 0; i < 4; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        Tile redrawTile = this.tileArr[j][i];
        redrawTile.setTranslateX(j * squareSize);
        redrawTile.setTranslateY(i * squareSize);
        board.getChildren().add(redrawTile);
      }
    }
  }
  // Makes the purple pane used to show the selectedTIle array of game
  public void drawSelectedWordsInit()
  {
    this.selectedWordPane = new StackPane();
    // selectedWordPane.setStyle("-fx-background-color: red");
    this.selectedWordPane.setAlignment(Pos.CENTER);
    selectedWordPane.setPrefSize((this.boardSize)*(3.0/4.0), ((this.screenHeight - this.boardSize) - 2)/2.0); // difference of each sqaure subtractio
    //
    selectedText = new Text("");
    // selectedText.setText(this.game.getSelectedTiles());
    selectedText.setFont(Font.font(28));
    //
    Rectangle selectedTextRect = new Rectangle((this.boardSize)*(3.0/4.0), ((this.screenHeight - this.boardSize) - 2)/2.0);
    selectedTextRect.setFill(Color.PLUM);
    selectedTextRect.setStroke(Color.BLACK);
    //
    selectedWordPane.getChildren().addAll(selectedTextRect, selectedText);
  }
  // Changes purple pane used to show the selectedTIle array of game
  public void drawSelectedWords()
  {
    this.selectedWordPane.getChildren().clear();
    String str = this.game.getSelectedTiles();
    Text selectedText = new Text(str);
    selectedText.setFont(Font.font(32));
    Rectangle selectedTextRect = new Rectangle((this.boardSize)*(3.0/4.0), ((this.screenHeight - this.boardSize) - 2)/2.0);
    selectedTextRect.setFill(Color.PLUM);
    selectedTextRect.setStroke(Color.BLACK);
    selectedWordPane.getChildren().addAll(selectedTextRect, selectedText);
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
