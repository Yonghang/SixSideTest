//package org.zhyh.sixside.sixsidelib;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Paint.FontMetrics;
//import android.graphics.Path;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//import android.util.Log;
//import android.util.TypedValue;
//import android.view.View;
//import android.view.View.OnClickListener;
//
//import static android.graphics.Paint.Align.CENTER;
//
///**
// * Created by zhyh on 11/2/15.
// */
//class SixSideItemView extends View implements OnClickListener {
//    private static final String TAG = SixSideItemView.class.getSimpleName();
//
//    private static SixSideItemView self;
//
//    public static final byte TEXT_ALIGN_LEFT = 1 << 0;
//    public static final byte TEXT_ALIGN_RIGHT = 1 << 1;
//    public static final byte TEXT_ALIGN_CENTER_VERTICAL = 1 << 2;
//    public static final byte TEXT_ALIGN_CENTER_HORIZONTAL = 1 << 3;
//    public static final byte TEXT_ALIGN_TOP = 1 << 4;
//    public static final byte TEXT_ALIGN_BOTTOM = 1 << 5;
//
//    private int mBackgroudColor;
//
//    private Path mBackPath;
//
//    private Paint mPaint;
//
//    private byte mTextAlign;
//    private int mTextColor;
//    private int mTextSize;
//
//    private int mViewWidth;
//    private int mViewHeight;
//
//    private float mTextCenterX;
//    private float mTextBaseLineY;
//
//    private Drawable mIcon;
//
//    private CharSequence mText;
//    private FontMetrics mFontMetrices;
//    private boolean mCheckd = false;
//
//    public OnClickListener getListener() {
//        return mListener;
//    }
//
//    public void setListener(OnClickListener Listener) {
//        this.mListener = Listener;
//    }
//
//    private OnClickListener mListener;
//
//    public SixSideItemView(Context context) {
//        this(context, null);
//    }
//
//    public SixSideItemView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public SixSideItemView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        self = this;
//        init();
//        initStyle(context, attrs, defStyleAttr);
//    }
//
//    private void initStyle(Context context, AttributeSet attrs, int defStyleAttr) {
//        TypedArray tmpTa = context.obtainStyledAttributes(attrs, R.styleable.SixSideItemView);
//        mTextColor = tmpTa.getColor(R.styleable.SixSideItemView_textColor, Color.BLACK);
//        mText = tmpTa.getString(R.styleable.SixSideItemView_text);
//        mBackgroudColor = tmpTa.getColor(R.styleable.SixSideItemView_hexagonColor, Color.YELLOW);
//        mTextSize = tmpTa.getDimensionPixelSize(R.styleable.SixSideItemView_textSize, (int) sp2px(18));
//        mTextAlign = (byte) tmpTa.getInt(R.styleable.SixSideItemView_textAlign, TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_HORIZONTAL);
//        mIcon = tmpTa.getDrawable(R.styleable.SixSideItemView_iconDrawable);
//        tmpTa.recycle();
//    }
//
//    private void init() {
//        setOnClickListener(self);
//        setBackgroundColor(Color.parseColor("#00000000"));
//        mText = "";
//        mBackPath = new Path();
//        mPaint = new Paint();
//        mPaint.setTextSize(sp2px(18));
//        mPaint.setAntiAlias(true);
//        mPaint.setTextAlign(CENTER);
//        mTextAlign = TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL;
//        mTextColor = Color.BLACK;
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int wMode = MeasureSpec.getMode(widthMeasureSpec);
//        int wSize = MeasureSpec.getSize(widthMeasureSpec);
//
//        int hMode = MeasureSpec.getMode(heightMeasureSpec);
//        int hSize = MeasureSpec.getSize(heightMeasureSpec);
////        setMeasuredDimension(wSize, hSize);
//        Log.i(TAG, "onMeasure()--wMode=" + wMode + ",wSize=" + wSize + ",hMode=" + hMode + ",hSize=" + hSize);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int measuredHeight = getMeasuredHeight();
//        int measuredWidth = getMeasuredWidth();
//        if (measuredHeight > measuredWidth) {
//            setMeasuredDimension(measuredWidth, measuredWidth);
//            measuredHeight = measuredWidth;
//        } else if (measuredHeight < measuredWidth) {
//            setMeasuredDimension(measuredHeight, measuredHeight);
//            measuredWidth = measuredHeight;
//        }
//        double hight = Math.sqrt(measuredWidth * measuredWidth / 4 - measuredWidth * measuredWidth / 16);
//        mBackPath.reset();
//        mBackPath.moveTo(0, measuredHeight / 2);
//        mBackPath.lineTo(measuredWidth / 4, (float) (measuredHeight / 2 - hight));
//        mBackPath.lineTo(measuredWidth * 3 / 4, (float) (measuredHeight / 2 - hight));
//        mBackPath.lineTo(measuredWidth, measuredHeight / 2);
//        mBackPath.lineTo(measuredWidth * 3 / 4, (float) (measuredHeight / 2 + hight));
//        mBackPath.lineTo(measuredWidth / 4, (float) (measuredHeight / 2 + hight));
//        mBackPath.close();
//    }
//
//    @Override
//    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
//        Log.i(TAG, "onLayout");
//        mViewWidth = right - left;
//        mViewHeight = bottom - top;
//        super.onLayout(changed, left, top, right, bottom);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        mPaint.reset();
//        if (mCheckd) {
//            mPaint.setColor(Color.GRAY);
//        } else {
//            mPaint.setColor(mBackgroudColor);
//        }
//        canvas.drawPath(mBackPath, mPaint);
//        //绘制控件内容
//        mPaint.reset();
//        setTextLocation(mText);
//        canvas.drawText(mText.toString(), mTextCenterX, mTextBaseLineY, mPaint);
//    }
//
//    /**
//     * 定位文本绘制的位置
//     */
//    private void setTextLocation(CharSequence text) {
////        paint.setTextSize(textSize);
//        mPaint.setColor(mTextColor);
//        mFontMetrices = mPaint.getFontMetrics();
//        //文本的宽度
//        float textWidth = mPaint.measureText(text.toString());
//        float textCenterVerticalBaselineY = mViewHeight / 2 - mFontMetrices.descent + (mFontMetrices.descent - mFontMetrices.ascent) / 2;
//        switch (mTextAlign) {
//            case TEXT_ALIGN_CENTER_HORIZONTAL | TEXT_ALIGN_CENTER_VERTICAL:
//                mTextCenterX = (float) mViewWidth / 2;
//                mTextBaseLineY = textCenterVerticalBaselineY;
//                break;
//            case TEXT_ALIGN_LEFT | TEXT_ALIGN_CENTER_VERTICAL:
//                mTextCenterX = textWidth / 2;
//                mTextBaseLineY = textCenterVerticalBaselineY;
//                break;
//            case TEXT_ALIGN_RIGHT | TEXT_ALIGN_CENTER_VERTICAL:
//                mTextCenterX = mViewWidth - textWidth / 2;
//                mTextBaseLineY = textCenterVerticalBaselineY;
//                break;
//            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_CENTER_HORIZONTAL:
//                mTextCenterX = mViewWidth / 2;
//                mTextBaseLineY = mViewHeight - mFontMetrices.bottom;
//                break;
//            case TEXT_ALIGN_TOP | TEXT_ALIGN_CENTER_HORIZONTAL:
//                mTextCenterX = mViewWidth / 2;
//                mTextBaseLineY = -mFontMetrices.ascent;
//                break;
//            case TEXT_ALIGN_TOP | TEXT_ALIGN_LEFT:
//                mTextCenterX = textWidth / 2;
//                mTextBaseLineY = -mFontMetrices.ascent;
//                break;
//            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_LEFT:
//                mTextCenterX = textWidth / 2;
//                mTextBaseLineY = mViewHeight - mFontMetrices.bottom;
//                break;
//            case TEXT_ALIGN_TOP | TEXT_ALIGN_RIGHT:
//                mTextCenterX = mViewWidth - textWidth / 2;
//                mTextBaseLineY = -mFontMetrices.ascent;
//                break;
//            case TEXT_ALIGN_BOTTOM | TEXT_ALIGN_RIGHT:
//                mTextCenterX = mViewWidth - textWidth / 2;
//                mTextBaseLineY = mViewHeight - mFontMetrices.bottom;
//                break;
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        mCheckd = !mCheckd;
//        invalidate();
//        if (mListener != null) {
//            mListener.onClick(self);
//        }
//    }
//
//    public int getBackgroudColor() {
//        return mBackgroudColor;
//    }
//
//    public void setBackgroudColor(int mBackgroudColor) {
//        this.mBackgroudColor = mBackgroudColor;
//    }
//
//    public byte getTextAlign() {
//        return mTextAlign;
//    }
//
//    public void setTextAlign(byte mTextAlign) {
//        this.mTextAlign = mTextAlign;
//    }
//
//    public int getTextColor() {
//        return mTextColor;
//    }
//
//    public void setTextColor(int mTextColor) {
//        this.mTextColor = mTextColor;
//    }
//
//    public CharSequence getText() {
//        return mText;
//    }
//
//    public void setText(CharSequence mText) {
//        this.mText = mText;
//    }
//
//    public int getTextSize() {
//        return mTextSize;
//    }
//
//    public void setTextSize(int mTextSize) {
//        this.mTextSize = mTextSize;
//    }
//
//    private float dp2px(int dp) {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
//    }
//
//    private float sp2px(int sp) {
//        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
//    }
//
//    public interface OnClickListener {
//        void onClick(SixSideItemView view);
//    }
//
//    @Override
//    public boolean isInEditMode() {
//        return true;
//    }
//}
