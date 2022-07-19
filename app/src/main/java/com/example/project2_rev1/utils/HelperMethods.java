package com.example.project2_rev1.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Locale;

public class HelperMethods {

    public static Bitmap getBitmapFromPicture(Context context, int idx) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), idx);
        return bitmap;
    }

    public static String toCamelCase(String str) {
        return str.charAt(0) + str.substring(1).toLowerCase();
    }
}
