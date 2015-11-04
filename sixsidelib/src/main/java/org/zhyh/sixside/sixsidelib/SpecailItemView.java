//package org.zhyh.sixside.sixsidelib;
//
//import android.content.Context;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Paint.Align;
//import android.graphics.Paint.FontMetrics;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.view.View;
//import android.widget.TextView;
//
///**
// * Created by zhyh on 11/2/15.
// */
//class SpecailItemView extends TextView implements View.OnClickListener {
//    private static final String TAG = SpecailItemView.class.getSimpleName();
//
//    public static final int TEXT_ALIGN_LEFT = 0x00000001;
//    public static final int TEXT_ALIGN_RIGHT = 0x00000010;
//    public static final int TEXT_ALIGN_CENTER_VERTICAL = 0x00000100;
//    public static final int TEXT_ALIGN_CENTER_HORIZONTAL = 0x00001000;
//    public static final int TEXT_ALIGN_TOP = 0x00010000;
//    public static final int TEXT_ALIGN_BOTTOM = 0x00100000;
//
//
//    /**
//     * 控件画笔
//     */
//    private Paint paint;
//    /**
//     * 文字的方位
//     */
//    private int textAlign;
//    /**
//     * 文字的颜色
//     */
//    private int textColor;
//    /**
//     * 控件的宽度
//     */
//    private int viewWidth;
//    /**
//     * 控件的高度
//     */
//    private int viewHeight;
//    /**
//     * 文本中轴线X坐标
//     */
//    private float textCenterX;
//    /**
//     * 文本baseline线Y坐标
//     */
//    private float textBaselineY;
//
//    private String text;
//
//    private FontMetrics fm;
//
//    private boolean checked = false;
//
//    public SpecailItemView(Context context) {
//        super(context);
//        init();
//    }
//
//    public SpecailItemView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        init();
//    }
//
//    public SpecailItemView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//    }
//
//    private void init() {
//        setOnClickListener(this);
//        text = getText().toString();
//        setText("");
//        paint = new Paint();
//        paint.setTextSize(22);
//        paint.setAntiAlias(true);
//        paint.setTextAlign(Align.CENTER);
//        //默认情况下文字居中显示
//        textAlign = TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL;
//        //默认的文本颜色是黑色
//        textColor = Color.BLACK;
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int wMode = MeasureSpec.getMode(widthMeasureSpec);
//        int wSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        int hMode = MeasureSpec.getMode(heightMeasureSpec);
//        int hSize = MeasureSpec.getSize(heightMeasureSpec);
//        setMeasuredDimension(wSize, hSize);
//        Log.i(TAG, "onMeasure()--wMode=" + wMode + ",wSize=" + wSize + ",hMode=" + hMode + ",hSize=" + hSize);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.i(TAG, "onLayout");
//        viewWidth = right - left;
//        viewHeight = bottom - top;
//        super.onLayout(changed, left, top, right, bottom);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//        //绘制控件内容
//        setTextLocation(text);
//        canvas.drawText(text, textCenterX, textBaselineY, paint);
//    }
//
//    /**
//     * 定位文本绘制的位置
//     */
//    private void setTextLocation(String text) {
////        paint.setTextSize(textSize);
//        paint.setColor(textColor);
//        fm = paint.getFontMetrics();
//        //文本的宽度
//        float textWidth = paint.measureText(text);
//        float textCenterVerticalBaselineY = viewHeight / 2 - fm.descent + (fm.descent - fm.ascent) / 2;
//        switch (textAlign) {
//            case TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL:
//                textCenterX = (float) viewWidth / 2;
//                textBaselineY = textCenterVerticalBaselineY;
//                break;
//            case TEXT_ALIGN_LEFT | TEXT_ALIGN_CENTER_VERTICAL:
//                textCenterX = textWidth / 2;
//                textBaselineY = textCenterVerticalBaselineY;
//                break;
//            case TEXT_ALIGN_RIGHT | TEXT_ALIGN_CENTER_VERTICAL:
//                textCenterX = viewWidth - textWidth / 2;
//                textBaselineY = textCenterVerticalBaselineY;
//                break;
//            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_CENTER_HORIZONTAL:
//                textCenterX = viewWidth / 2;
//                textBaselineY = viewHeight - fm.bottom;
//                break;
//            case TEXT_ALIGN_TOP | TEXT_ALIGN_CENTER_HORIZONTAL:
//                textCenterX = viewWidth / 2;
//                textBaselineY = -fm.ascent;
//                break;
//            case TEXT_ALIGN_TOP | TEXT_ALIGN_LEFT:
//                textCenterX = textWidth / 2;
//                textBaselineY = -fm.ascent;
//                break;
//            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_LEFT:
//                textCenterX = textWidth / 2;
//                textBaselineY = viewHeight - fm.bottom;
//                break;
//            case TEXT_ALIGN_TOP | TEXT_ALIGN_RIGHT:
//                textCenterX = viewWidth - textWidth / 2;
//                textBaselineY = -fm.ascent;
//                break;
//            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_RIGHT:
//                textCenterX = viewWidth - textWidth / 2;
//                textBaselineY = viewHeight - fm.bottom;
//                break;
//        }
//    }
//
//    public interface OnClickListener {
//        void onClick(View v, boolean checked);
//    }
//
//    private OnClickListener mListener;
//
//    public void setOnClickListener(OnClickListener listener) {
//        mListener = listener;
//    }
//
//    @Override
//    public void onClick(View v) {
//        checked = !checked;
////        setBackgroundResource(checked ? 0 : R.drawable.logo);
//        setBackgroundColor(Color.GRAY);
//        if (mListener != null) {
//            mListener.onClick(v, checked);
//        }
//    }
//
//    public String getTextString() {
//        return text;
//    }
//}
