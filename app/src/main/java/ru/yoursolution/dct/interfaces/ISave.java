package ru.yoursolution.dct.interfaces;

import android.graphics.Bitmap;

/**
 * Created by Admin on 16.11.2016.
 */

public interface ISave {
    String save(String name, Bitmap src, Bitmap.CompressFormat format, int quality);
}
