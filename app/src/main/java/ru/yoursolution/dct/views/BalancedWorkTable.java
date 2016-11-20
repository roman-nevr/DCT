package ru.yoursolution.dct.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;

import ru.yoursolution.dct.R;
import ru.yoursolution.dct.charts.BalancedWorkTableChart;
import ru.yoursolution.dct.model.Element;
import ru.yoursolution.dct.model.WorkType;

/**
 * TODO: document your custom view class.
 */
public class BalancedWorkTable extends View {
    private int lineSize;

    private Point displaySize = new Point(500,300);
    private DisplayMetrics metrics = new DisplayMetrics();
    private int lineSpace;
    private ArrayList<Element> elements;
    private ArrayList<Integer> colors;

    public void setElements(ArrayList<Element> elements) {
        this.elements = elements;
    }

    public BalancedWorkTable(Context context) {
        super(context);
        init(null, 0);
    }

    public BalancedWorkTable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public BalancedWorkTable(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.BalancedWorkTable, defStyle, 0);

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.

        lineSize = a.getDimensionPixelSize(R.styleable.BalancedWorkTable_spaceSize, 50);


        a.recycle();

        // Set up a default TextPaint object
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            getDisplay().getRealSize(displaySize);
            getDisplay().getMetrics(metrics);
        }
        else {
            metrics.densityDpi = 240;
        }
        lineSpace = lineSize;//(lineSize * metrics.densityDpi) / 24;
        if(isInEditMode()){
            //lineSpace = 53;
        }
        int measuredWidth = measureWidth(widthMeasureSpec);
        int measuredHeight = measureHeight(heightMeasureSpec);
        // Вы ДОЛЖНЫ сделать вызов метода setMeasuredDimension,
        // иначе получится выброс исключения при
        // размещении элемента внутри разметки.
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private int measureHeight(int heightMeasureSpec) {
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        // Размер по умолчанию, если ограничения не были установлены.
        int result = displaySize.y;

        if ((specMode == MeasureSpec.AT_MOST)||(specMode == MeasureSpec.UNSPECIFIED)) {

        }else if (specMode == MeasureSpec.EXACTLY) {
            // Если ваш элемент может поместиться внутри этих границ, верните это значение.
            result = specSize;
        }
        return result;
    }

    private int measureWidth(int widthMeasureSpec) {
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        // Размер по умолчанию, если ограничения не были установлены.
        int result = displaySize.x;

        if ((specMode == MeasureSpec.AT_MOST)||(specMode == MeasureSpec.UNSPECIFIED)) {


        }else if (specMode == MeasureSpec.EXACTLY) {
            // Если ваш элемент может поместиться внутри этих границ, верните это значение.
            result = specSize;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //super.onDraw(canvas);
        //fillCanvasWithLines(canvas);

        BalancedWorkTableChart.draw(canvas, getWidth(), getHeight(), metrics.densityDpi);



        /*Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache());
        ExternalStorageSaver.saveBitmapAs("test.jpg", bitmap, Bitmap.CompressFormat.JPEG, 100);
        bitmap.recycle();*/
    }






}
