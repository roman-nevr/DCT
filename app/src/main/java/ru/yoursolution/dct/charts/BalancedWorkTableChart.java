package ru.yoursolution.dct.charts;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;

import ru.yoursolution.dct.R;
import ru.yoursolution.dct.model.Element;
import ru.yoursolution.dct.model.WorkType;

/**
 * Created by roma on 20.11.2016.
 */

public class BalancedWorkTableChart {


    public static void draw(Canvas canvas, int width, int height, int pixelDensity, ArrayList<Element> elements, ){

        fillCanvasWithDashedLines(canvas);
        drawAxis(canvas);
        placeAxisText(canvas);
        drawElements(canvas);
        drawLabels(canvas);
    }

    private static void drawLabels(Canvas canvas) {

    }

    private static Path drawCurve(Canvas canvas, int width, int height, Paint paint, PointF mPointa, PointF mPointb) {
        Path myPath = new Path();
        myPath.moveTo(63*width/64, height/10);
        myPath.quadTo(mPointa.x, mPointa.y, mPointb.x, mPointb.y);
        return myPath;
    }

    static ArrayList<Element> getElements(){
        ArrayList<Element> elements = new ArrayList<>();
        elements.add(new Element("Тащить", 10, WorkType.USEFUL_WORK, Color.GREEN));
        elements.add(new Element("Курить", 20, WorkType.WASTE_WORK, Color.RED));
        elements.add(new Element("Катить", 10, WorkType.TRANSIT, Color.GRAY));
        elements.add(new Element("Отдыхать", 50, WorkType.WAITING, Color.BLUE));
        return elements;
    }

    private void drawElements(Canvas canvas, ArrayList<Element> elements, int width, int ) {
        elements = getElements();
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

    private static void placeAxisText(Canvas canvas) {
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

    private Path formDashedLine(Path path, float xStart, float yStart, float xStop, float yStop){
        path.moveTo(xStart, yStart);
        path.lineTo(xStop, yStop);
        return path;
    }

    private static void fillCanvasWithDashedLines(Canvas canvas){
        Paint mPaint = new Paint();
        mPaint.setStrokeWidth(1.0f);
        mPaint.setColor(ContextCompat.getColor(getContext(), R.color.grey));
        mPaint.setStyle(Paint.Style.STROKE);
        float[] intervals = new float[]{6f, 3f};
        mPaint.setPathEffect(new DashPathEffect(intervals, 0));
        int lineNumber = getHeight() / lineSpace;
        Path path = new Path();
        for (int lineN = 1; lineN < lineNumber; lineN++){
            path = formDashedLine(path, 0,  getHeight() - lineN * lineSpace, getWidth(),getHeight() - lineN * lineSpace);
        }
        canvas.drawPath(path, mPaint);
    }

    private static void drawAxis(Canvas canvas) {
        Paint axisLinePaint = new Paint();
        axisLinePaint.setStyle(Paint.Style.STROKE);
        axisLinePaint.setStrokeWidth(2.0f);
        canvas.drawLine(0 + lineSpace * 2, getHeight(), 0 + lineSpace * 2,0, axisLinePaint); //vert
        canvas.drawLine(0,getHeight() - lineSpace, getWidth(), getHeight() - lineSpace,axisLinePaint); //horiz
    }

    private void fillCanvasWithLines(Canvas canvas) {
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
