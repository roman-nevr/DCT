package ru.yoursolution.dct.utils;

import java.io.File;

/**
 * Created by roma on 20.11.2016.
 */

public class fileUtils {
    static File getFullFilename(String filename){
        return new File(android.os.Environment.getExternalStorageDirectory(), filename);
    }
}
