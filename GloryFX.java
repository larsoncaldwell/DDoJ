import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
//import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Pos;

public class GloryFX extends Application {
    
    Label     textLabel;
    TextField textField;

    Label  runnableLabel;
    Button runnableList;

    Label  runningLabel;
    Button runningList;

    Button startButton;
    Button stopButton;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(final Stage primaryStage)
    {
	textLabel     = new Label();
	textLabel.setText("Enter Runnable:");

	textField     = new TextField();
	textField.setPrefSize(300, 25);

	runnableLabel = new Label();
	runnableLabel.setText("Runnables");

	runnableList  = new Button();
	runnableList.setPrefSize(200, 250);
	
	runningLabel  = new Label("Running Threads");
	runningLabel.setPrefSize(100, 35);

	runningList   = new Button();
	runningList.setPrefSize(200, 250);

	startButton   = new Button();
	startButton.setPrefSize(100, 40);

	stopButton    = new Button();
	stopButton.setPrefSize(100, 40);

	GridPane panel = new GridPane();
	panel.setAlignment(Pos.CENTER);

	int[] columns = new int[]{0, 0, 93, 30, 43, 150};
	int[] rows    = new int[]{0, 22, 0, 18, 204, 25};

	for (int i = 0; i < 6; i++)
	{
	    panel.getColumnConstraints().add(new ColumnConstraints(columns[i]));
	    panel.getRowConstraints().add(new RowConstraints(rows[i]));
	}

	panel.add(textLabel,     2, 1      );
	panel.add(textField,     3, 1, 3, 1);
	panel.add(runnableLabel, 2, 3, 2, 1);
	panel.add(runnableList,  2, 4, 2, 1);
	panel.add(runningLabel,  5, 3      );
	panel.add(runningList,   5, 4      );
	panel.add(startButton,   2, 5, 2, 1);
	panel.add(stopButton,    5, 5      );

	panel.setHgap(10);
	panel.setVgap(10);
	panel.setGridLinesVisible(true);

	Scene scene = new Scene(panel, 400, 350);

	primaryStage.setTitle("Threads of Glory");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
