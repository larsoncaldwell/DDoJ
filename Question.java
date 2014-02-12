/**
 *
 *
 */

abstract class Question
{
    private String mFormat;
    private String mPrompt;

    public String getFormat()
    {
	return mFormat;
    }

    public String getPrompt()
    {
	return mPrompt;
    }

    public void setFormat(String pFormat)
    {
	mFormat = pFormat;
    }

    public String setPrompt(String pPrompt)
    {
	mPrompt = pPrompt;
    }

    public ArrayList<Option> getOptions();
    boolean isRight(String pAnswer);
}
