package com.example.andro17.app4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public final class DrawableUtils {

    private DrawableUtils() {
        // Private constructor to prevent instances of this class.
    }

    public static Bitmap drawableFromUrl(String url) throws java.io.IOException {
        Bitmap x;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestProperty("User-agent", "Mozilla/4.0");
        connection.connect();
        InputStream input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        return x;
    }

}
