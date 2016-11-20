package ru.yoursolution.dct.charts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;

/**
 * Created by roma on 20.11.2016.
 */

public class GanttDiagramChart {

    public static void draw(Canvas canvas, int width, int height, int pixelDensity){

        PointF mPoint1 = new PointF(width/1.2F, height/1.2F);
        PointF mPoint2 = new PointF(width/24, height/1.2F);
        Path myPath1;
        Paint paint  = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
        paint.setColor(Color.RED);

        myPath1 = drawCurve(canvas, width, height, paint, mPoint1, mPoint2);
        canvas.drawPath(myPath1, paint);
        canvas.drawText("111", 0,0, paint);
    }

    private static Path drawCurve(Canvas canvas, int width, int height, Paint paint, PointF mPointa, PointF mPointb) {
        Path myPath = new Path();
        myPath.moveTo(63*width/64, height/10);
        myPath.quadTo(mPointa.x, mPointa.y, mPointb.x, mPointb.y);
        return myPath;
    }
}
