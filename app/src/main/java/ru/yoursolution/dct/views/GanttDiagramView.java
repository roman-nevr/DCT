package ru.yoursolution.dct.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import ru.yoursolution.dct.R;

/**
 * Created by Admin on 15.11.2016.
 */

public class GanttDiagramView extends View {

    private Point displaySize = new Point(500,300);
    private DisplayMetrics metrics = new DisplayMetrics();
    private int lineSize, lineSpace;

    public GanttDiagramView(Context context) {
        super(context);
    }

    public GanttDiagramView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GanttDiagramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    float w,h;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        w = getWidth();
        h = getHeight();

        PointF mPoint1 = new PointF(w/1.2F, h/1.2F);
        PointF mPoint2 = new PointF(w/24, h/1.2F);
        Path myPath1 = new Path();
        Paint paint  = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2.0f);
        paint.setColor(Color.RED);

        myPath1 = drawCurve(canvas, paint, mPoint1, mPoint2);
        canvas.drawPath(myPath1, paint);
        canvas.drawText("111", 0,0, paint);
    }
    private Path drawCurve(Canvas canvas, Paint paint, PointF mPointa, PointF mPointb) {

        Path myPath = new Path();
        myPath.moveTo(63*w/64, h/10);
        myPath.quadTo(mPointa.x, mPointa.y, mPointb.x, mPointb.y);
        return myPath;
    }
}
