package org.zhyh.sixside.sixsidelib;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.sromku.polygon.Point;
import com.sromku.polygon.Polygon;

/**
 * Created by zhyh on 11/3/15.
 */
public class SixAngleImageView extends ImageView {

    private SixAngleImageView self;

    private int mWidth;
    private int mHeight;
    private int mLenght;
    private Paint paint;

    private int color;
    private float textsize = 26;

    private String texts;
    private Bitmap home_flight;
    long down, up = 0;
    private float lastDownX, lastDownY;

    public OnSexangleImageClickListener getListener() {
        return listener;
    }

    public void setListener(OnSexangleImageClickListener listener) {
        this.listener = listener;
    }

    public OnLongSexangleImageClickListener getLongListener() {
        return longListener;
    }

    public void setLongListener(OnLongSexangleImageClickListener longListener) {
        this.longListener = longListener;
    }

    private OnSexangleImageClickListener listener;
    private OnLongSexangleImageClickListener longListener;

    private Polygon pathContainer;

    public SixAngleImageView(Context context) {
        this(context, null);

    }

    public SixAngleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SixAngleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        initStyleable(context, attrs, defStyleAttr);
    }

    private void initStyleable(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SixAngleImageView);
        color = ta.getColor(R.styleable.SixAngleImageView_itemSixColor, Color.parseColor("#f0f0f0"));
        textsize = ta.getDimensionPixelSize(R.styleable.SixAngleImageView_itemTextSize, sp2px(18));
        texts = ta.getString(R.styleable.SixAngleImageView_itemString);
        Drawable drawable = ta.getDrawable(R.styleable.SixAngleImageView_itemImage);
        if (drawable != null) {
            home_flight = ((BitmapDrawable) drawable).getBitmap();
//            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
//            Matrix matrix = new Matrix();
//            float scale = ((float) this.getWidth() * 3 / 4) / bitmap.getWidth();
//            matrix.postScale(scale, scale);
//            home_flight = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        ta.recycle();
    }

    private int sp2px(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }

    private void init() {
        self = this;
    }

    public interface OnSexangleImageClickListener {
        void onClick(View view);
    }

    public interface OnLongSexangleImageClickListener {
        void onDoubleClick(View view);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        if (mWidth > mHeight) {
            setMeasuredDimension(mHeight, mHeight);
        }
        if (mHeight > mWidth) {
            setMeasuredDimension(mWidth, mWidth);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mWidth = getWidth();
        mHeight = getHeight();

        mLenght = mWidth / 2;

        double radian30 = 30 * Math.PI / 180;
        float a = (float) (mLenght * Math.sin(radian30));
        float b = (float) (mLenght * Math.cos(radian30));
        float c = (mHeight - 2 * b) / 2;

        if (null == paint) {
            paint = new Paint();
            paint.setAntiAlias(true);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(color);
//            paint.setAlpha(100);
        }
        //画六边形
        Path path = new Path();
        Point point1 = new Point(getWidth(), getHeight() / 2);
        path.moveTo(getWidth(), getHeight() / 2);
        Point point2 = new Point(getWidth() - a, getHeight() - c);
        path.lineTo(getWidth() - a, getHeight() - c);
        Point point3 = new Point(getWidth() - a - mLenght, getHeight() - c);
        path.lineTo(getWidth() - a - mLenght, getHeight() - c);
        Point point4 = new Point(0, getHeight() / 2);
        path.lineTo(0, getHeight() / 2);
        Point point5 = new Point(a, c);
        path.lineTo(a, c);
        Point point6 = new Point(getWidth() - a, c);
        path.lineTo(getWidth() - a, c);
        path.close();
        pathContainer = new Polygon.Builder()
                .addVertex(point1)
                .addVertex(point2)
                .addVertex(point3)
                .addVertex(point4)
                .addVertex(point5)
                .addVertex(point6)
                .close()
                .build();
        canvas.save();
        canvas.drawPath(path, paint);
        canvas.restore();

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(textsize);
        // 去锯齿
        paint.setAntiAlias(true);

        //画背景
        if (home_flight != null) {
            canvas.save();
            canvas.clipPath(path);
            Matrix matrix = new Matrix();
            float scale = ((float) this.getWidth() * 4 / 7) / home_flight.getWidth();
            if (scale != 0) {
                matrix.postScale(scale, scale);
                home_flight = Bitmap.createBitmap(home_flight, 0, 0, home_flight.getWidth(), home_flight.getHeight(), matrix, true);
                matrix.reset();
            }
            matrix.postTranslate(this.getWidth() / 2 - home_flight.getWidth() / 2, this.getHeight() / 2 - home_flight.getHeight() / 2);
            canvas.drawBitmap(home_flight, matrix, paint);
            canvas.restore();
        }
        if (!TextUtils.isEmpty(texts)) {
            canvas.save();
            canvas.clipPath(path);
            canvas.drawText(texts, getWidth() / 2 - 18, getHeight() - 30, paint);
            canvas.restore();
        }
    }

    private boolean isScaled = false;
    /**
     * XY轴同时缩放
     */
    private void loadscaleDown() {
        if (isScaled) {
            return;
        }
        isScaled = true;
        PropertyValuesHolder propertyValues1 = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.9f);
        PropertyValuesHolder propertyValues2 = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.9f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(self, propertyValues1, propertyValues2);
        animator.setDuration(200);
        animator.start();
    }

    private void loadscaleUp() {
        if (!isScaled) {
            return;
        }
        PropertyValuesHolder propertyValues3 = PropertyValuesHolder.ofFloat("scaleX", 0.9f, 1.0f);
        PropertyValuesHolder propertyValues4 = PropertyValuesHolder.ofFloat("scaleY", 0.9f, 1.0f);
        ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(self, propertyValues3, propertyValues4);
        animator.setDuration(200);
        animator.start();
        isScaled = false;
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    private boolean isFinish;//是否不响应点击时间

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastDownX = event.getX();
                lastDownY = event.getY();
                Point point = new Point(lastDownX, lastDownY);
                if (pathContainer.contains(point)) {
                    loadscaleDown();
                }

                //上次按下的时间与本次按下的时间在20~350毫秒之间时，响应双击事件
                if ((System.currentTimeMillis() - down) > 20 && (System.currentTimeMillis() - down) <= 350 && longListener != null) {
                    longListener.onDoubleClick(this);
                }
                down = System.currentTimeMillis();
                break;

            case MotionEvent.ACTION_UP:

                if (isFinish) {
                    isFinish = false;
                } else {
                    loadscaleUp();
                    //响应点击事件
                    if (Math.abs(event.getX() - lastDownX) <= 5 && Math.abs(event.getY() - lastDownY) <= 5) {
                        if (listener != null) {
                            listener.onClick(this);
                        }
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                if (isFinish) {
                    return true;
                }
                //按下后，如果移动的距离大于当前view 1/3 距离 则不响应点击时间
//                float moveX = event.getX() - lastDownX;
//                float moveY = event.getY() - lastDownY;
//                if (Math.abs(moveX) > getWidth() / 3
//                        || Math.abs(moveY) > getHeight() / 3) {
//                }
                Point point1 = new Point(event.getX(), event.getY());
                if (pathContainer.contains(point1)) {
                    loadscaleDown();
                } else {
                    isFinish = true;
                    loadscaleUp();
                }
                break;

            // 滑动出去不会调用action_up,调用action_cancel
            case MotionEvent.ACTION_CANCEL:
                loadscaleUp();
                break;

        }

        return true;
    }
}
