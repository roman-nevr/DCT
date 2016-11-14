package ru.yoursolution.dct.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
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
import ru.yoursolution.dct.model.Element;
import ru.yoursolution.dct.model.WorkType;
import ru.yoursolution.dct.utils.ExternalStorageSaver;

/**
 * TODO: document your custom view class.
 */
public class BalancedWorkTable extends View {
    private int lineSize;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;
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
        fillCanvasWithDashedLines(canvas);
        drawAxis(canvas);
        placeAxisText(canvas);
        drawChart(canvas);
        drawLabels(canvas);
        /*Bitmap bitmap = Bitmap.createBitmap(this.getDrawingCache());
        ExternalStorageSaver.save("test.jpg", bitmap, Bitmap.CompressFormat.JPEG, 100);
        bitmap.recycle();*/
    }

    private void drawLabels(Canvas canvas) {

    }

    ArrayList<Element> getElements(){
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(new Element("Тащить", 10, WorkType.USEFUL_WORK));
        elements.add(new Element("Курить", 20, WorkType.WASTE_WORK));
        elements.add(new Element("Катить", 10, WorkType.TRANSIT));
        elements.add(new Element("Отдыхать", 50, WorkType.WAITING));
        return elements;
    }

    ArrayList<Integer> getColors(){
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GREEN);
        colors.add(Color.RED);
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        return colors;
    }

    private void drawChart(Canvas canvas) {
        elements = getElements();
        colors = getColors();
        int time =calcWorkTime(elements);
        float chartHeight = getHeight() - 2 * lineSpace;
        float oneSecondHeight = chartHeight / time;
        float elementHeight = getHeight() - lineSpace;
        for(Element element : elements){
            elementHeight = drawElement(canvas, element, elementHeight, oneSecondHeight);
        }
        //elementHeight = drawElement(canvas, elements.get(0), elementHeight, oneSecondHeight);
    }

    private float drawElement(Canvas canvas, Element element, float elementHeight, float oneSecondHeight) {
        Paint mPaint = new Paint();
        mPaint.setColor(WorkType.getColor(element.getWorkType()));
        Paint smallTextPaint = new Paint();
        smallTextPaint.setTextSize(30f);
        smallTextPaint.setTypeface(Typeface.MONOSPACE);
        smallTextPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawRect(getWidth()*0.2f, elementHeight - oneSecondHeight * element.getDuration(), getWidth()*0.6f, elementHeight, mPaint);
        canvas.drawText(element.getName()+", "+element.getDuration()+" c", getWidth() * 0.65f, elementHeight - (oneSecondHeight * element.getDuration())/2f, smallTextPaint);
        return elementHeight - oneSecondHeight * element.getDuration();
    }

    private int calcWorkTime(ArrayList<Element> elements) {
        int time = 0;
        for(Element element : elements){
            time = time + element.getDuration();
        }
        return time;
    }

    private void placeAxisText(Canvas canvas) {
        Paint textPaint = new Paint();
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.SANS_SERIF);
        textPaint.setTextSize(30f);
        canvas.drawText("Таблица" , getWidth() / 2, lineSpace, textPaint);
        canvas.save();
        canvas.translate(0, getHeight());
        canvas.rotate(-90);
        canvas.drawText("время" , getHeight() / 2, lineSpace, textPaint);
        canvas.restore();
    }

    private void drawAxis(Canvas canvas) {
        Paint axisLinePaint = new Paint();
        axisLinePaint.setStyle(Paint.Style.FILL);
        axisLinePaint.setStrokeWidth(2.0f);
        canvas.drawLine(0 + lineSpace * 2, getHeight(), 0 + lineSpace * 2,0, axisLinePaint); //vert
        canvas.drawLine(0,getHeight() - lineSpace, getWidth(), getHeight() - lineSpace,axisLinePaint); //horiz
    }

    private void fillCanvasWithDashedLines(Canvas canvas) {
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(1.0f);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.grey));
        mPaint.setStyle(Paint.Style.STROKE);
        float[] intervals = new float[]{2f, 2f};
        mPaint.setPathEffect(new DashPathEffect(intervals, 0));

        int lineNumber = getHeight() / lineSpace;
        for (int lineN = 1; lineN < lineNumber; lineN++){
            canvas.drawLine(0, getHeight() - lineN * lineSpace, getWidth(),getHeight() - lineN * lineSpace, mPaint);
        }
    }


}
