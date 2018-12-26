import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import java.util.Optional;
import javafx.scene.control.TextInputControl;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;

public class Timer extends StackPane
{
    private Timeline animation;
    private Text tempTime = new Text("3:00");
    private int currentSecond = 60;
    private String secondStr = "";
    private int count = 0;

    /**
      Constructor: A timer is a just a stack pane with an animation timeline
      No Parameters
    */
    public Timer ()
    {
      this.tempTime.setFont(Font.getDefault());
      this.tempTime.setFill(Color.RED);
      getChildren().add(this.tempTime);
      this.animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timeText()));
      this.animation.setCycleCount(Timeline.INDEFINITE);
      animation.play();
    }
    /**
      Sets the count of the counter -- used to workaround multiple timers with different timelines
      this is used in GUI to keep window from prematurely closing
    */
    public void setCount(int num)
    {
      this.count = num;
    }
    /**
      Main logic to set up Timer --  hacky workaround to translate seconds into Strings
      closes program after 3 minutes
    */
    public void timeText()
    {
      if(this.currentSecond > 0 && this.count < 60 )
      {
        this.currentSecond -= 1;

        if(this.currentSecond > 9)
        {
          this.secondStr = "2:" + this.currentSecond;
          this.tempTime.setText(secondStr);
          this.count += 1;
        }
        else
        {
          this.secondStr = "2:0" + this.currentSecond;
          this.tempTime.setText(secondStr);
          this.count += 1;
        }
      }
      else if(this.currentSecond > 0 && this.count >= 60 && this.count < 120)
      {
        this.currentSecond -= 1;

        if(this.currentSecond > 9)
        {
          this.secondStr = "1:" + this.currentSecond;
          this.tempTime.setText(secondStr);
          this.count += 1;
        }
        else
        {
          this.secondStr = "1:0" + this.currentSecond;
          this.tempTime.setText(secondStr);
          this.count += 1;
        }
      }
      else if(this.currentSecond > 0 && this.count >= 120 && this.count < 180)
      {
        this.currentSecond -= 1;

        if(this.currentSecond > 9)
        {
          this.secondStr = "0:" + this.currentSecond;
          this.tempTime.setText(secondStr);
          this.count += 1;
        }
        else
        {
          this.secondStr = "0:0" + this.currentSecond;
          this.tempTime.setText(secondStr);
          this.count += 1;
        }
      }
      if(this.currentSecond == 0)
      {
        this.currentSecond = 60;
      }
      if(this.count >= 180)
      {
        this.secondStr = "0:00";
        this.animation.stop();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(null);
        alert.setHeaderText("You scored: " + GameGUI.score);
        alert.setContentText("Good game, but you ran out of time. Click OK to exit.");

        alert.setOnHidden(evt -> Platform.exit());

        alert.show();
      }
    }
}
