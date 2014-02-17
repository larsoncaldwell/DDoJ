import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class Zoom implements Runnable
{
    private static final String CLASS_NAME = "Zoom";
    private static final int MAX_SPEED = 200;
    private static final int MIN_SPEED = 0;

    private int mSpeed = 1;
    private int mX = (int)ThreadsOfGlory.getInstance().getScene().getWindow().getX();
    private int mY = (int)ThreadsOfGlory.getInstance().getScene().getWindow().getY();;
    private boolean manualSpeed = false;
    private int runNumber;

    JFrame frame;

    public void run()
    {
	setup();
	int newX;
	int width;
	int size = (int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	runNumber = ThreadsOfGlory.getInstance().getRunNumber();

	while (ThreadsOfGlory.getInstance().isRunning(CLASS_NAME + "-" + runNumber))
	{
	    width = (int)ThreadsOfGlory.getInstance().getScene().getWindow().getWidth();
	    if (!manualSpeed)
	    {
		mSpeed++;
		if (mSpeed >= MAX_SPEED)
		{
		    manualSpeed = true;
		}
	    }
	    try
	    {
		mX = (int)ThreadsOfGlory.getInstance().getScene().getWindow().getX();
		newX = ((mX + (mSpeed / 2) + width) % (size + width)) - width;
		ThreadsOfGlory.getInstance().getScene().getWindow().setX(newX);
		
		Thread.sleep(25);
	    }
	    catch (Exception e)
	    {
		break;
	    }
	}
	frame.dispose();
    }
    
    public void setup()
    {
	frame = new JFrame("Zoom");
	JButton minus = new JButton("Slower");
	minus.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent event)
	    {
		if (mSpeed > MIN_SPEED)
		{
		    mSpeed -= 10;
		}
		else
		{
		    mSpeed = MIN_SPEED;
		}
		manualSpeed = true;
	    }
	});

	JButton stop = new JButton("Stop");
	stop.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent event)
	    {
		mSpeed = 0;
		manualSpeed = true;
	    }
	});

	JButton plus  = new JButton("Faster");
	plus.addActionListener(new ActionListener()
	{
	    public void actionPerformed(ActionEvent event)
	    {
		if (mSpeed < MAX_SPEED)
		{
		    mSpeed += 10;
		}
		else
		{
		    mSpeed = MAX_SPEED;
		}
		manualSpeed = true;
	    }
	});

	frame.getContentPane().add(BorderLayout.EAST, plus);
	frame.getContentPane().add(BorderLayout.CENTER, stop);
	frame.getContentPane().add(BorderLayout.WEST, minus);
	frame.setSize(220, 100);
	frame.setResizable(false);
	frame.setVisible(true);
    }    
}
