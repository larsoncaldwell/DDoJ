import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.geometry.Pos;
import javafx.geometry.HPos;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ThreadsOfGlory extends Application {
    
    private static final int[] COLUMNS = new int[]{3, 3, 25, 3, 11, 11, 41, 3, 0};
    private static final int[] ROWS    = new int[]{3, 7,  6, 5,  3, 62,  3, 8, 3};
    private static final ObservableList<String> RUNNABLES = FXCollections.observableArrayList();
    private static final ObservableList<String> RUNNING   = FXCollections.observableArrayList();

    GridPane mGrid;
    Scene mScene;

    Label     textLabel;
    TextField textField;

    Label    runnableLabel;
    ListView<String> runnableList;

    Label  runningLabel;
    ListView<String> runningList;

    Button startButton;
    Button stopButton;

    public static void main(String[] args)
    {
        launch(args);
    }

    public void start(final Stage primaryStage)
    {

	setLabels();
	setTextField();
	setRunnableList();
	setRunningList();
	setStartButton();
	setStopButton();
	setGrid();
	addComponents();

	mScene = new Scene(mGrid, 400, 350);

	primaryStage.setTitle("Threads of Glory");
        primaryStage.setScene(mScene);
        primaryStage.show();
    }

    private void setLabels()
    {
	textLabel     = new Label("Enter Runnable:");
	runnableLabel = new Label("Runnables");
	runningLabel  = new Label("Running Threads");
    }

    private void setTextField()
    {
	textField = new TextField();
	textField.setPrefSize(300, 25);
	textField.setOnAction(new EventHandler<ActionEvent>()
	{
	    //textField.setPromptText("");
	});
    }

    private void setRunnableList()
    {
	runnableList = new ListView<String>(RUNNABLES);
	runnableList.setPrefSize(200, 250);
    }

    private void setRunningList()
    {
	runningList = new ListView<String>(RUNNING);
	runningList.setPrefSize(200, 250);
    }

    private void setStartButton()
    {
	startButton = new Button();
	startButton.setPrefSize(100, 40);
	startButton.setText("Start");
    }

    private void setStopButton()
    {
	stopButton = new Button();
	stopButton.setPrefSize(100, 40);
	stopButton.setText("Stop");
    }

    private void setGrid()
    {
	mGrid = new GridPane();
	
	for (int i = 0; i < 9; i++)
	{
	    RowConstraints    row    = new RowConstraints();
	    ColumnConstraints column = new ColumnConstraints();
	    row.setPercentHeight  (ROWS[i]);
	    column.setPercentWidth(COLUMNS[i]);
	    mGrid.getRowConstraints   ().add(row);
	    mGrid.getColumnConstraints().add(column);
	}

	
    }

    private void addComponents()
    {
	mGrid.setHalignment(startButton,   HPos.CENTER);
	mGrid.setHalignment(stopButton,    HPos.CENTER);
	mGrid.setHalignment(runnableLabel, HPos.CENTER);
	mGrid.setHalignment(runningLabel,  HPos.CENTER);
	mGrid.setHalignment(runnableList,  HPos.CENTER);
	mGrid.setHalignment(runningList,   HPos.CENTER);

	mGrid.add(textLabel,     2, 1      );
	mGrid.add(textField,     4, 1, 3, 1);
	mGrid.add(runnableLabel, 1, 3, 4, 1);
	mGrid.add(runnableList,  1, 5, 4, 1);
	mGrid.add(runningLabel,  6, 3      );
	mGrid.add(runningList,   6, 5      );
	mGrid.add(startButton,   1, 7, 4, 1);
	mGrid.add(stopButton,    6, 7      );
    }
}
