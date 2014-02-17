import java.awt.*;
import javax.swing.*;
import com.sun.awt.AWTUtilities;
import java.util.List;
import java.util.ArrayList;

class Bubbles implements Runnable
{
    private static final String CLASS_NAME = "Bubbles";
    private static final int MIN_BUBBLE_SIZE = 10;
    private static final int MAX_BUBBLE_SIZE = 100;
    private static final int NUM_BUBBLES = 20;
    private static final int SWAY_TIME = 25;
    private static final Color[] COLORS = {Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.magenta};

    JFrame mainFrame;
    int screenHeight;
    int screenWidth;
    BubblesPanel bubblesPanel;
    ArrayList<Oval> bubbles;
    int swayTimer;

    public Bubbles()
    {
	screenHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	screenWidth  = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	bubbles = new ArrayList<Oval>();
	swayTimer = 0;
    }

    public void run()
    {
	start();
	int runNumber = ThreadsOfGlory.getInstance().getRunNumber();
	try
	{
	    while (ThreadsOfGlory.getInstance().isRunning(CLASS_NAME + "-" + runNumber))
	    {
		bubblesPanel.repaint();
		updateBubbles();
		swayTimer = (swayTimer + 1) % SWAY_TIME;
		Thread.sleep(25);
	    }
	}
	catch (Exception e)
	{
	}
	mainFrame.dispose();
    }

    public void start()
    {
	mainFrame = new JFrame();
	mainFrame.setSize(screenWidth, screenHeight);
	mainFrame.setUndecorated(true);
	mainFrame.getContentPane().setLayout(new FlowLayout());
	bubblesPanel = new BubblesPanel();
	mainFrame.setGlassPane(bubblesPanel);
	mainFrame.getGlassPane().setVisible(true);
	AWTUtilities.setWindowOpaque(mainFrame, false);
	mainFrame.setVisible(true);
	addBubbles();
    }

    public void addBubbles()
    {
	for (int i = 0; i < NUM_BUBBLES; i++)
	{
	    int   x     = (int)(Math.random() * screenWidth);
	    int   y     = (int)(Math.random() * screenHeight);
	    int   size  = (int)(Math.random() * (MAX_BUBBLE_SIZE - MIN_BUBBLE_SIZE)) + MIN_BUBBLE_SIZE;
	    Color color = COLORS[(int)(Math.random() * 6)];
	    bubbles.add(new Circle(size, x, y, color));
	}
    }

    public void updateBubbles()
    {
	for (Oval bubble : bubbles)
	{
	    int bubbleSize = bubble.getRadii().getWidth();
	    int x          = bubble.getCoordinate().getX();
	    int y          = bubble.getCoordinate().getY();
	    int speed      = (MAX_BUBBLE_SIZE - (bubbleSize  - 10)) / 10;
	    int swayAmount = (speed / 2) + 1;
	    y = y - speed;
	    if (y < 0)
	    {
		y = screenHeight;
	    }
	    if (swayTimer < SWAY_TIME / 2)
	    {
		swayAmount = -swayAmount;
	    }
	    x = x + swayAmount;
	    bubble.getCoordinate().setX(x);
	    bubble.getCoordinate().setY(y);
	}
    }

    class BubblesPanel extends JPanel
    {
	Graphics2D paintbrush;
	GradientPaint gradient;

	BubblesPanel()
	{
	    setOpaque(false);
	}
	
	public void paintComponent(Graphics g)
	{
	    paintbrush = (Graphics2D) g;
	    paintBubbles();
	}

	public void paintBubbles()
	{
	    for (Oval bubble : bubbles)
	    {
		paintBubble(bubble);
	    }
	}

	public void paintBubble(Oval bubble)
	{
	    gradient = new GradientPaint(bubble.getCoordinate().getX(), bubble.getCoordinate().getY(), bubble.getColor(), 
					 bubble.getCoordinate().getX() + bubble.getRadii().getWidth(),
					 bubble.getCoordinate().getY() + bubble.getRadii().getHeight(), Color.white);
	    paintbrush.setPaint(gradient);
	    paintbrush.fillOval(bubble.getCoordinate().getX(), bubble.getCoordinate().getY(), bubble.getRadii().getWidth(), bubble.getRadii().getHeight());
	}
    }
}
