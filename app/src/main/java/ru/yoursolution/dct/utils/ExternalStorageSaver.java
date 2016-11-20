package ru.yoursolution.dct.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.OperationCanceledException;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import ru.yoursolution.dct.interfaces.ISave;

import static ru.yoursolution.dct.utils.utils.LOG_TAG;

/**
 * Created by Admin on 16.11.2016.
 */

public class ExternalStorageSaver{

    public static String saveBitmapAs(String name, Bitmap src, Bitmap.CompressFormat format, int quality) throws UnsupportedOperationException {

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        src.compress(format, quality, os);
        byte[] array = os.toByteArray();

        File sdDir;
        File fileName;
        String sdState = android.os.Environment.getExternalStorageState();
        if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
            sdDir = new File(android.os.Environment.getExternalStorageDirectory(), "cache");
            fileName = new File(sdDir, name);
        } else {
            throw new UnsupportedOperationException("External storage not mounted");
        }
        if (!sdDir.exists())
            sdDir.mkdirs();
        if (fileName.exists())
            fileName.delete();
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            outputStream.write(array);
            outputStream.flush();
            outputStream.close();
            //outputStream.ope
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fileName.toString();
    }

    @Deprecated
    public void test() {
        File fileName = null;
        File sdDir = null;
        String sdState = android.os.Environment.getExternalStorageState();
        if (sdState.equals(android.os.Environment.MEDIA_MOUNTED)) {
            sdDir = new File(android.os.Environment.getExternalStorageDirectory(), "cache");
            fileName = new File(sdDir, "primer.txt");
        } else {
            //fileName = getCacheDir();
        }
        if (!sdDir.exists())
            sdDir.mkdirs();
        if (fileName.exists())
            fileName.delete();
        try {
            FileWriter f = new FileWriter(fileName);
            f.write("hello world");
            f.flush();
            f.close();
        } catch (Exception e) {

        }
    }


    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getDownloadDir(Context context, String fileName) {
        // Get the directory for the app's private pictures directory.
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_DOWNLOADS), fileName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;

    }
}
