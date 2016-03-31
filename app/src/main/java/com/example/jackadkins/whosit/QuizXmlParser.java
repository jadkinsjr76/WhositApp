package com.example.jackadkins.whosit;

import android.content.Context;
import android.widget.Toast; // debugging
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by Jack Adkins on 3/25/2016.
 */
public class QuizXmlParser
{
    public void parseXml(Context context)
    {
        InputStream xmlStream;
       /* File xmlFile = new File("c://musicQuiz.xml");

        if(!xmlFile.exists())
        {
            Toast.makeText(context, "file does not exist", Toast.LENGTH_LONG).show();
            return;
        }*/

        int event;
        String text = null;

        try
        {
            // create factory and pullParser and set feature and input
            XmlPullParserFactory xmlFactoryObj = XmlPullParserFactory.newInstance();
            XmlPullParser quizParser = xmlFactoryObj.newPullParser();
            quizParser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            xmlStream = new BufferedInputStream(new FileInputStream("musicQuiz.xml"));//new FileInputStream("c://musicQuiz.xml");
            quizParser.setInput(xmlStream, null);
            event = quizParser.getEventType();
            Toast.makeText(context, "Pre loop", Toast.LENGTH_LONG).show();
            while(event != XmlPullParser.END_DOCUMENT)
            {
                String name = quizParser.getName();

                switch(event)
                {
                    case XmlPullParser.START_TAG:
                        Toast.makeText(context, "Start_Tag", Toast.LENGTH_LONG);
                        break;
                    case XmlPullParser.TEXT:
                        text = quizParser.getText();
                        Toast.makeText(null, "test", Toast.LENGTH_LONG).show();
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
            }
        }
       catch(Exception e)
       {
           Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show();;
       }
    }
}
