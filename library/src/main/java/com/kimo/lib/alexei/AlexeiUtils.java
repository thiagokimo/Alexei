package com.kimo.lib.alexei;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import static java.lang.Thread.MIN_PRIORITY;

/**
 * Created by Kimo on 8/15/14.
 */
public class AlexeiUtils {

    public static int [][] getPixelsMatrixFromBitmap(Bitmap bitmap) {

        int [][] pixelMatrix = new int[bitmap.getWidth()][bitmap.getHeight()];

        for(int i = 0; i < bitmap.getWidth(); i++)
            for(int j = 0; j < bitmap.getHeight(); j++)
                pixelMatrix[i][j] = bitmap.getPixel(i,j);

        return pixelMatrix;

    }

    public static Bitmap getBitmapFromImageView(ImageView theImage) {
        return ((BitmapDrawable)theImage.getDrawable()).getBitmap();
    }

    public static Bitmap getBitmapFromURI(Context context, Uri uri) {
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);

            return bitmap;
        } catch (IOException e) {
            throw new IllegalArgumentException("Error creating bitmap from URI");
        }
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };

        //This method was deprecated in API level 11
        //Cursor cursor = managedQuery(contentUri, proj, null, null, null);

        CursorLoader cursorLoader = new CursorLoader(
                context,
                contentUri, proj, null, null, null);
        Cursor cursor = cursorLoader.loadInBackground();

        int column_index =
                cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    public static ExecutorService getDefaultCalculator() {
        return Executors.newFixedThreadPool(1 , new ThreadFactory() {
            @Override
            public Thread newThread(final Runnable r) {
                return new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Thread.currentThread().setPriority(MIN_PRIORITY);
                        r.run();
                    }
                }, "IdleThread");
            }
        });
    }
}
