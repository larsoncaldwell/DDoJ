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
import java.util.ArrayList;
import java.lang.System;
import javafx.stage.WindowEvent;

public class ThreadsOfGlory extends Application {
    
    private static ThreadsOfGlory instance;

    private static final int[] COLUMNS = new int[]{3, 3, 25, 3, 11, 11, 41, 3, 0};
    private static final int[] ROWS    = new int[]{3, 7,  6, 5,  3, 62,  3, 8, 3};
    private static final ObservableList<String> RUNNABLES = FXCollections.observableArrayList();
    private static final ObservableList<String> RUNNING   = FXCollections.observableArrayList();

    private static GridPane mGrid;
    private static Scene mScene;
    private static Label textLabel;
    private static Label runnableLabel;
    private static Label runningLabel;
    private static TextField textField;
    private static ListView<String> runnableList;
    private static ArrayList<Runnable> runnables;
    private static ListView<String> runningList;
    private static Button startButton;
    private static Button stopButton;

    private static boolean isOff = false;
    private static int runNumber = 2;

    public ThreadsOfGlory()
    {
	super();
	synchronized(ThreadsOfGlory.class)
	{
	    if (instance != null)
	    {
		throw new UnsupportedOperationException(
			  getClass() + " is singleton but constructor called more than once");
	    }

	    instance = this;
	}
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static ThreadsOfGlory getInstance()
    {
	if (instance == null)
	{
	    instance = new ThreadsOfGlory();
	}
	return instance;
    }

    public Scene getScene()
    {
	return mScene;
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

	mScene = new Scene(mGrid, 420, 350);

	primaryStage.setTitle("Threads of Glory");
        primaryStage.setScene(mScene);
        primaryStage.show();

	setClose();
    }

    public void setClose()
    {
	mScene.getWindow().setOnCloseRequest(new EventHandler<WindowEvent>()
	{
	    public void handle(WindowEvent event)
	    {
		isOff = true;
		System.exit(0);
	    }
	});
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
	    public void handle(ActionEvent event)
	    {
		String runnableName = textField.getText();
	        textField.setText("");		
		try
		{
		    Class runnable = Class.forName(runnableName);
		    Runnable item  = (Runnable)runnable.newInstance();
		    runnables.add(item);
		    RUNNABLES.add(runnableName);
		    runnableList.setItems(RUNNABLES);
	    	}
		catch (Exception e)
		{
		}
	    }
	});
    }

    private void setRunnableList()
    {
	runnables = new ArrayList<Runnable>();
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
	startButton.setOnAction(new EventHandler<ActionEvent>()
	{
	    public void handle(ActionEvent event)
	    {
		try
		{
		    int index = runnableList.getSelectionModel().getSelectedIndex();
		    new Thread(runnables.get(index)).start();
		    RUNNING.add(RUNNABLES.get(index) + "-" + runNumber);
		    runNumber += 2;
		}
		catch (Exception e)
		{
		}
	    }
	});
    }

    private void setStopButton()
    {
	stopButton = new Button();
	stopButton.setPrefSize(100, 40);
	stopButton.setText("Stop");
	stopButton.setOnAction(new EventHandler<ActionEvent>()
	{
	    public void handle(ActionEvent event)
	    {
		try
		{
		    int index = runningList.getSelectionModel().getSelectedIndex();
		    RUNNING.remove(index);
		    runNumber -= 2;
		}
		catch (Exception e)
		{
		}
	    }
	});

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

    public static boolean isRunning(String runnable)
    {
	if ((RUNNING.indexOf(runnable) != -1) && !isOff)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }

    public static int getRunNumber()
    {
	return runNumber - 2;
    }
}
