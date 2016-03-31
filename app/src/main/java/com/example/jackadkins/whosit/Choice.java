package com.example.jackadkins.whosit;

/**
 * Created by Jack Adkins on 3/23/2016.
 */
public class Choice
{
    private String answer;
    private int key;

    public Choice(String answer, int key)
    {
        this.answer = answer;
        this.key = key;
    }

    public int getKey()
    {
        return key;
    }

    public String getAnswer()
    {
        return answer;
    }
}
