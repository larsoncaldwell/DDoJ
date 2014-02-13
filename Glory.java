import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

class Glory extends JFrame
{

    private static Glory instance;

    private JLabel enterRunnableLabel;
    private JLabel runnablesLabel;
    private JLabel runningThreadsLabel;
    private JTextField runnableTextField;
    private JList runnableList;
    private JList threadList;
    private JButton startButton;
    private JButton stopButton;

    private JPanel runnableListPanel;
    private JPanel runningThreadsPanel;
    private JPanel enterRunnablePanel;

    private Vector<String> runnableVector; 
    private Vector<String> threadVector;

    private Glory()
    {
    }

    public static void main(String[] args)
    {
	  Glory.getInstance().start();
    }

    public static Glory getInstance()
    {
	    if (instance == null)
    	{
  	    instance = new Glory();
    	}
    	return instance;
    }

    public void start()
    {
    	runnableVector = new Vector<String>();
	    threadVector = new Vector<String>();

    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	setSize(500, 500);
	
    	initializeEnterRunnablePanel();
    	initializeRunnableListPanel();
    	initializeRunningThreadsPanel();

    	add(BorderLayout.NORTH, enterRunnablePanel);
    	add(BorderLayout.WEST, runnableListPanel);
    	add(BorderLayout.EAST, runningThreadsPanel);

    	setVisible(true);
    }

    private void initializeEnterRunnablePanel()
    {
    	enterRunnablePanel = new JPanel();
    	enterRunnableLabel = new JLabel("Enter Runnable:");
    	runnableTextField  = new JTextField();
    	enterRunnablePanel.add(enterRunnableLabel);
    	enterRunnablePanel.add(runnableTextField);
    }

    private void initializeRunnableListPanel()
    {
    	runnableListPanel  = new JPanel();
    	runnablesLabel     = new JLabel("Runnables");
    	runnableList       = new JList<String>(runnableVector);
    	startButton        = new JButton("Start");
    	startButton.addActionListener(new StartListener());
    	runnableListPanel.add(runnablesLabel);
    	runnableListPanel.add(runnableList);
    	runnableListPanel.add(startButton);
    }

    private void initializeRunningThreadsPanel()
    {
    	runningThreadsPanel = new JPanel();
    	runningThreadsLabel = new JLabel("Running Threads");
    	threadList          = new JList<String>(threadVector);
    	stopButton          = new JButton("Stop");
    	stopButton.addActionListener(new StopListener());
    	runningThreadsPanel.add(runningThreadsLabel);
    	runningThreadsPanel.add(threadList);
    	runningThreadsPanel.add(stopButton);
    }

    private class StartListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent event)
    	{
	      System.out.println("Start");
    	}
    }

    private class StopListener implements ActionListener
    {
    	public void actionPerformed(ActionEvent event)
    	{
  	    System.out.println("Stop");
    	}
    }
}
