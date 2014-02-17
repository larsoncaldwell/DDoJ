
class ShapeDimension
{
    private int mWidth;
    private int mHeight;

    public ShapeDimension(int pWidth, int pHeight)
    {
	mWidth = pWidth;
	mHeight = pHeight;
    }

    public void setWidth(int pWidth)
    {
	mWidth = pWidth;
    }
    
    public void setHeight(int pHeight)
    {
	mHeight = pHeight;
    }

    public int getWidth()
    {
	return mWidth;
    }

    public int getHeight()
    {
	return mHeight;
    }
}
