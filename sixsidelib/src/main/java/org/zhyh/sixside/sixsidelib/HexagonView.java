//package org.zhyh.sixside.sixsidelib;
//
//import android.content.Context;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.Color;
//import android.graphics.Paint;
//import android.graphics.Path;
//import android.graphics.RectF;
//import android.graphics.drawable.BitmapDrawable;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//import android.util.TypedValue;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Toast;
//
//import com.sromku.polygon.Point;
//import com.sromku.polygon.Polygon;
//
//import java.util.LinkedList;
//import java.util.List;
//
//import static android.widget.Toast.LENGTH_SHORT;
//
///**
// * Created by zhyh on 11/2/15.
// */
//class HexagonView extends View {
//    private static final String TAG = HexagonView.class.getSimpleName();
//    private static final int COLOR_1 = Color.parseColor("#ff0000");
//    private static final int COLOR_2 = Color.parseColor("#00ff00");
//    private static final int COLOR_3 = Color.parseColor("#0000ff");
//    private static final int COLOR_4 = Color.parseColor("#ffff00");
//    private static final int COLOR_5 = Color.parseColor("#ff00ff");
//    private static final int COLOR_6 = Color.parseColor("#00ffff");
//    private static final int COLOR_7 = Color.parseColor("#dfd0cd");
//
//    private int mColor1 = COLOR_1;
//    private int mColor2 = COLOR_2;
//    private int mColor3 = COLOR_3;
//    private int mColor4 = COLOR_4;
//    private int mColor5 = COLOR_5;
//    private int mColor6 = COLOR_6;
//    private int mColor7 = COLOR_7;
//
//    private Drawable mDraw1;
//    private Drawable mDraw2;
//    private Drawable mDraw3;
//    private Drawable mDraw4;
//    private Drawable mDraw5;
//    private Drawable mDraw6;
//    private Drawable mDraw7;
//
//    private int mViewHeight;
//    private int mViewWidth;
//
//    private int spacePx;
//    private double sideLength;
//
//    private double tmpHeight;
//
//    private double centerX;
//    private double centerY;
//
//    public OnItemClickListener getListener() {
//        return listener;
//    }
//
//    public void setListener(OnItemClickListener listener) {
//        this.listener = listener;
//    }
//
//    private OnItemClickListener listener;
//
//    private List<Polygon> pathContainer = new LinkedList<>();
//
//    public HexagonView(Context context) {
//        this(context, null);
//    }
//
//    public HexagonView(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public HexagonView(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        init();
//        initstyle(context, attrs);
//    }
//
//    private void initstyle(Context context, AttributeSet attrs) {
//        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.HexagonView);
//        mColor1 = ta.getColor(R.styleable.HexagonView_color1, COLOR_1);
//        mColor2 = ta.getColor(R.styleable.HexagonView_color2, COLOR_2);
//        mColor3 = ta.getColor(R.styleable.HexagonView_color3, COLOR_3);
//        mColor4 = ta.getColor(R.styleable.HexagonView_color4, COLOR_4);
//        mColor5 = ta.getColor(R.styleable.HexagonView_color5, COLOR_5);
//        mColor6 = ta.getColor(R.styleable.HexagonView_color6, COLOR_6);
//        mColor7 = ta.getColor(R.styleable.HexagonView_color7, COLOR_7);
//
//        mDraw1 = ta.getDrawable(R.styleable.HexagonView_img1);
//        mDraw2 = ta.getDrawable(R.styleable.HexagonView_img2);
//        mDraw3 = ta.getDrawable(R.styleable.HexagonView_img3);
//        mDraw4 = ta.getDrawable(R.styleable.HexagonView_img4);
//        mDraw5 = ta.getDrawable(R.styleable.HexagonView_img5);
//        mDraw6 = ta.getDrawable(R.styleable.HexagonView_img6);
//        mDraw7 = ta.getDrawable(R.styleable.HexagonView_img7);
//    }
//
//    private void init() {
//        spacePx = (int) dp2px(5);
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//                break;
//            case MotionEvent.ACTION_UP:
//                float x = event.getX();
//                float y = event.getY();
//                Point point = new Point(x, y);
//                for (Polygon polygon : pathContainer) {
//                    if (polygon.contains(point)) {
//                        int i = pathContainer.indexOf(polygon);
//                        if (listener == null) {
//                            Toast.makeText(getContext(), "点击了第" + i + "项", LENGTH_SHORT).show();
//                        } else {
//                            listener.onClick(this, i);
//                        }
//                        break;
//                    }
//                }
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        mViewHeight = getMeasuredHeight();
//        mViewWidth = getMeasuredWidth();
//        centerX = mViewWidth / 2;
//        centerY = mViewHeight / 2;
//        if (mViewWidth > mViewHeight) {
//            sideLength = (double) (mViewHeight - 3 * spacePx) / 5;
//        } else {
//            sideLength = (double) (mViewWidth - 3 * spacePx) / 5;
//        }
//        tmpHeight = Math.sqrt(sideLength * sideLength * 3 / 4);
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        Paint paint = new Paint();
//
//        pathContainer.clear();
//        Path path0 = new Path();
//        path0.reset();
//        Point point0_1 = new Point((float) (centerX - sideLength), (float) centerY);
//        Point point0_2 = new Point((float) (centerX - sideLength / 2), (float) (centerY - tmpHeight));
//        Point point0_3 = new Point((float) (centerX + sideLength / 2), (float) (centerY - tmpHeight));
//        Point point0_4 = new Point((float) (centerX + sideLength), (float) (centerY));
//        Point point0_5 = new Point((float) (centerX + sideLength / 2), (float) (centerY + tmpHeight));
//        Point point0_6 = new Point((float) (centerX - sideLength / 2), (float) (centerY + tmpHeight));
//        pathContainer.add(new Polygon.Builder()
//                .addVertex(point0_1)
//                .addVertex(point0_2)
//                .addVertex(point0_3)
//                .addVertex(point0_4)
//                .addVertex(point0_5)
//                .addVertex(point0_6)
//                .close()
//                .build());
//        path0.moveTo(point0_1.x, point0_1.y);
//        path0.lineTo(point0_2.x, point0_2.y);
//        path0.lineTo(point0_3.x, point0_3.y);
//        path0.lineTo(point0_4.x, point0_4.y);
//        path0.lineTo(point0_5.x, point0_5.y);
//        path0.lineTo(point0_6.x, point0_6.y);
//        path0.close();
//
//        paint.setColor(mColor1);
//
//        canvas.drawPath(path0, paint);
//        if (mDraw1 != null) {
//            canvas.save();
//            RectF rectF = new RectF();
//            rectF.left = (point0_1.x + point0_2.x) / 2;
//            rectF.top = (point0_1.y + point0_2.y) / 2;
//            rectF.right = (point0_3.x + point0_4.x) / 2;
//            rectF.bottom = (point0_1.y + point0_6.y) / 2;
//            canvas.drawBitmap(((BitmapDrawable) mDraw1).getBitmap(), null, rectF, null);
//            canvas.restore();
//        }
//
//        Path path1 = new Path();
//        path1.reset();
//        Point point1_1 = new Point((float) (point0_1.x), (float) (point0_1.y - 2 * tmpHeight - spacePx));
//        Point point1_2 = new Point((float) (point0_2.x), (float) (point0_2.y - 2 * tmpHeight) - spacePx);
//        Point point1_3 = new Point((float) (point0_3.x), (float) (point0_3.y - 2 * tmpHeight) - spacePx);
//        Point point1_4 = new Point((float) (point0_4.x), (float) (point0_4.y - 2 * tmpHeight) - spacePx);
//        Point point1_5 = new Point((float) (point0_5.x), (float) (point0_5.y - 2 * tmpHeight) - spacePx);
//        Point point1_6 = new Point((float) (point0_6.x), (float) (point0_6.y - 2 * tmpHeight) - spacePx);
//        pathContainer.add(new Polygon.Builder()
//                .addVertex(point1_1)
//                .addVertex(point1_2)
//                .addVertex(point1_3)
//                .addVertex(point1_4)
//                .addVertex(point1_5)
//                .addVertex(point1_6)
//                .close()
//                .build());
//        path1.moveTo(point1_1.x, point1_1.y);
//        path1.lineTo(point1_2.x, point1_2.y);
//        path1.lineTo(point1_3.x, point1_3.y);
//        path1.lineTo(point1_4.x, point1_4.y);
//        path1.lineTo(point1_5.x, point1_5.y);
//        path1.lineTo(point1_6.x, point1_6.y);
//        path1.close();
//        paint.setColor(mColor2);
//        canvas.drawPath(path1, paint);
//        if (mDraw2 != null) {
//            canvas.save();
//            RectF rectF = new RectF();
//            rectF.left = (point1_1.x + point1_2.x) / 2;
//            rectF.top = (point1_1.y + point1_2.y) / 2;
//            rectF.right = (point1_3.x + point1_4.x) / 2;
//            rectF.bottom = (point1_1.y + point1_6.y) / 2;
//            canvas.drawBitmap(((BitmapDrawable) mDraw2).getBitmap(), null, rectF, null);
//            canvas.restore();
//        }
//
//        Point point2_1 = new Point((point0_3.x + spacePx), (point0_3.y + point1_5.y) / 2);
//        Point point2_2 = new Point((float) (point2_1.x + sideLength / 2), (float) (point2_1.y - tmpHeight));
//        Point point2_3 = new Point((float) (point2_2.x + sideLength), point2_2.y);
//        Point point2_4 = new Point((float) (point2_1.x + 2 * sideLength), point2_1.y);
//        Point point2_5 = new Point(point2_3.x, (float) (point2_3.y + 2 * tmpHeight));
//        Point point2_6 = new Point(point2_2.x, point2_5.y);
//        pathContainer.add(new Polygon.Builder()
//                .addVertex(point2_1)
//                .addVertex(point2_2)
//                .addVertex(point2_3)
//                .addVertex(point2_4)
//                .addVertex(point2_5)
//                .addVertex(point2_6)
//                .close()
//                .build());
//        Path path2 = new Path();
//        path2.moveTo(point2_1.x, point2_1.y);
//        path2.lineTo(point2_2.x, point2_2.y);
//        path2.lineTo(point2_3.x, point2_3.y);
//        path2.lineTo(point2_4.x, point2_4.y);
//        path2.lineTo(point2_5.x, point2_5.y);
//        path2.lineTo(point2_6.x, point2_6.y);
//        path2.close();
//        paint.setColor(mColor3);
//        canvas.drawPath(path2, paint);
//        if (mDraw3 != null) {
//            canvas.save();
//            RectF rectF = new RectF();
//            rectF.left = (point2_1.x + point2_2.x) / 2;
//            rectF.top = (point2_1.y + point2_2.y) / 2;
//            rectF.right = (point2_3.x + point2_4.x) / 2;
//            rectF.bottom = (point2_1.y + point2_6.y) / 2;
//            canvas.drawBitmap(((BitmapDrawable) mDraw3).getBitmap(), null, rectF, null);
//            canvas.restore();
//        }
//
//
//        Point point3_1 = new Point(point2_1.x, (float) (point2_1.y + 2 * tmpHeight) + spacePx);
//        Point point3_2 = new Point(point2_2.x, (float) (point2_2.y + 2 * tmpHeight) + spacePx);
//        Point point3_3 = new Point(point2_3.x, (float) (point2_3.y + 2 * tmpHeight) + spacePx);
//        Point point3_4 = new Point(point2_4.x, (float) (point2_4.y + 2 * tmpHeight) + spacePx);
//        Point point3_5 = new Point(point2_5.x, (float) (point2_5.y + 2 * tmpHeight) + spacePx);
//        Point point3_6 = new Point(point2_6.x, (float) (point2_6.y + 2 * tmpHeight) + spacePx);
//        pathContainer.add(new Polygon
//                .Builder()
//                .addVertex(point3_1)
//                .addVertex(point3_2)
//                .addVertex(point3_3)
//                .addVertex(point3_4)
//                .addVertex(point3_5)
//                .addVertex(point3_6)
//                .close()
//                .build());
//        Path path3 = new Path();
//        path3.moveTo(point3_1.x, point3_1.y);
//        path3.lineTo(point3_2.x, point3_2.y);
//        path3.lineTo(point3_3.x, point3_3.y);
//        path3.lineTo(point3_4.x, point3_4.y);
//        path3.lineTo(point3_5.x, point3_5.y);
//        path3.lineTo(point3_6.x, point3_6.y);
//        path3.close();
//        paint.setColor(mColor4);
//        canvas.drawPath(path3, paint);
//        if (mDraw4 != null) {
//            canvas.save();
//            RectF rectF = new RectF();
//            rectF.left = (point3_1.x + point3_2.x) / 2;
//            rectF.top = (point3_1.y + point3_2.y) / 2;
//            rectF.right = (point3_3.x + point3_4.x) / 2;
//            rectF.bottom = (point3_1.y + point3_6.y) / 2;
//            canvas.drawBitmap(((BitmapDrawable) mDraw4).getBitmap(), null, rectF, null);
//            canvas.restore();
//        }
//
//        Point point4_1 = new Point(point0_1.x, (float) (point0_1.y + 2 * tmpHeight + spacePx));
//        Point point4_2 = new Point(point0_2.x, (float) (point0_2.y + 2 * tmpHeight + spacePx));
//        Point point4_3 = new Point(point0_3.x, (float) (point0_3.y + 2 * tmpHeight + spacePx));
//        Point point4_4 = new Point(point0_4.x, (float) (point0_4.y + 2 * tmpHeight + spacePx));
//        Point point4_5 = new Point(point0_5.x, (float) (point0_5.y + 2 * tmpHeight + spacePx));
//        Point point4_6 = new Point(point0_6.x, (float) (point0_6.y + 2 * tmpHeight + spacePx));
//        pathContainer.add(new Polygon.Builder()
//                .addVertex(point4_1)
//                .addVertex(point4_2)
//                .addVertex(point4_3)
//                .addVertex(point4_4)
//                .addVertex(point4_5)
//                .addVertex(point4_6)
//                .close()
//                .build());
//        Path path4 = new Path();
//        path4.moveTo(point4_1.x, point4_1.y);
//        path4.lineTo(point4_2.x, point4_2.y);
//        path4.lineTo(point4_3.x, point4_3.y);
//        path4.lineTo(point4_4.x, point4_4.y);
//        path4.lineTo(point4_5.x, point4_5.y);
//        path4.lineTo(point4_6.x, point4_6.y);
//        paint.setColor(mColor5);
//        canvas.drawPath(path4, paint);
//        if (mDraw5 != null) {
//            canvas.save();
//            RectF rectF = new RectF();
//            rectF.left = (point4_1.x + point4_2.x) / 2;
//            rectF.top = (point4_1.y + point4_2.y) / 2;
//            rectF.right = (point4_3.x + point4_4.x) / 2;
//            rectF.bottom = (point4_1.y + point4_6.y) / 2;
//            canvas.drawBitmap(((BitmapDrawable) mDraw5).getBitmap(), null, rectF, null);
//            canvas.restore();
//        }
//
//        Point point5_1 = new Point((float) (point3_1.x - 3 * sideLength - 2 * spacePx), point3_1.y);
//        Point point5_2 = new Point((float) (point3_2.x - 3 * sideLength - 2 * spacePx), point3_2.y);
//        Point point5_3 = new Point((float) (point3_3.x - 3 * sideLength - 2 * spacePx), point3_3.y);
//        Point point5_4 = new Point((float) (point3_4.x - 3 * sideLength - 2 * spacePx), point3_4.y);
//        Point point5_5 = new Point((float) (point3_5.x - 3 * sideLength - 2 * spacePx), point3_5.y);
//        Point point5_6 = new Point((float) (point3_6.x - 3 * sideLength - 2 * spacePx), point3_6.y);
//        pathContainer.add(new Polygon.Builder()
//                .addVertex(point5_1)
//                .addVertex(point5_2)
//                .addVertex(point5_3)
//                .addVertex(point5_4)
//                .addVertex(point5_5)
//                .addVertex(point5_6)
//                .close()
//                .build());
//        Path path5 = new Path();
//        path5.moveTo(point5_1.x, point5_1.y);
//        path5.lineTo(point5_2.x, point5_2.y);
//        path5.lineTo(point5_3.x, point5_3.y);
//        path5.lineTo(point5_4.x, point5_4.y);
//        path5.lineTo(point5_5.x, point5_5.y);
//        path5.lineTo(point5_6.x, point5_6.y);
//        paint.setColor(mColor6);
//        canvas.drawPath(path5, paint);
//        if (mDraw6 != null) {
//            canvas.save();
//            RectF rectF = new RectF();
//            rectF.left = (point5_1.x + point5_2.x) / 2;
//            rectF.top = (point5_1.y + point5_2.y) / 2;
//            rectF.right = (point5_3.x + point5_4.x) / 2;
//            rectF.bottom = (point5_1.y + point5_6.y) / 2;
//            canvas.drawBitmap(((BitmapDrawable) mDraw6).getBitmap(), null, rectF, null);
//            canvas.restore();
//        }
//
//        Point point6_1 = new Point(point5_1.x, (float) (point5_1.y - 2 * tmpHeight - spacePx));
//        Point point6_2 = new Point(point5_2.x, (float) (point5_2.y - 2 * tmpHeight - spacePx));
//        Point point6_3 = new Point(point5_3.x, (float) (point5_3.y - 2 * tmpHeight - spacePx));
//        Point point6_4 = new Point(point5_4.x, (float) (point5_4.y - 2 * tmpHeight - spacePx));
//        Point point6_5 = new Point(point5_5.x, (float) (point5_5.y - 2 * tmpHeight - spacePx));
//        Point point6_6 = new Point(point5_6.x, (float) (point5_6.y - 2 * tmpHeight - spacePx));
//        pathContainer.add(new Polygon.Builder()
//                .addVertex(point6_1)
//                .addVertex(point6_2)
//                .addVertex(point6_3)
//                .addVertex(point6_4)
//                .addVertex(point6_5)
//                .addVertex(point6_6)
//                .close()
//                .build());
//        Path path6 = new Path();
//        path6.moveTo(point6_1.x, point6_1.y);
//        path6.lineTo(point6_2.x, point6_2.y);
//        path6.lineTo(point6_3.x, point6_3.y);
//        path6.lineTo(point6_4.x, point6_4.y);
//        path6.lineTo(point6_5.x, point6_5.y);
//        path6.lineTo(point6_6.x, point6_6.y);
//        paint.setColor(mColor7);
//        canvas.drawPath(path6, paint);
//        if (mDraw7 != null) {
//            canvas.save();
//            RectF rectF = new RectF();
//            rectF.left = (point6_1.x + point6_2.x) / 2;
//            rectF.top = (point6_1.y + point6_2.y) / 2;
//            rectF.right = (point6_3.x + point6_4.x) / 2;
//            rectF.bottom = (point6_1.y + point6_6.y) / 2;
//            canvas.drawBitmap(((BitmapDrawable) mDraw7).getBitmap(), null, rectF, null);
//            canvas.restore();
//        }
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
//    public interface OnItemClickListener{
//        void onClick(HexagonView view, int index);
//    }
//}
