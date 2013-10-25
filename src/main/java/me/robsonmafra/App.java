package me.robsonmafra;

import me.robsonmafra.twitter.TwitterFilterStream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String[] words = {"java"};
        TwitterFilterStream stream = new TwitterFilterStream(words);
        stream.start();
    }
}
