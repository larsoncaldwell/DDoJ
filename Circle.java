import java.awt.Color;

class Circle implements Oval
{
    int mRadius;
    ShapeCoordinate mCoordinate;
    Color mColor;
    
    public Circle(int pRadius, int x, int y, Color pColor)
    {
	mRadius = pRadius;
	mCoordinate = new ShapeCoordinate (x, y);
	mColor = pColor;
    }

    public int getRadius()
    {
	return mRadius;
    }

    public void setRadius(int pRadius)
    {
	mRadius = pRadius;
    }

    public ShapeDimension getRadii()
    {
	return new ShapeDimension(mRadius, mRadius);
    }

    public ShapeCoordinate getCoordinate()
    {
	return mCoordinate;
    }
    
    public Color getColor()
    {
	return mColor;
    }
}
