package org.zhyh.sixside.sixsidelib;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by zhyh on 11/4/15.
 */
public class SixAngleViewGroup extends ViewGroup {
    private Direction mDirection = Direction.CLOCKWISE;

    private int mRadius;

    /**
     * 该容器的内边距,无视padding属性，如需边距请用该变量
     */
    private float mPadding = 0;

    /**
     * 该容器内child item的默认尺寸
     */
    private static final float RADIO_DEFAULT_CHILD_DIMENSION = 1 / 2.8f;
    /**
     * 布局时的开始角度
     */
    private double mStartAngle = -90;


    public SixAngleViewGroup(Context context) {
        super(context);
    }

    public SixAngleViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SixAngleViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int layoutRadius = mRadius;

        // Laying out the child views
        final int childCount = getChildCount();

        int left, top;
        // menu item 的尺寸
        int cWidth = (int) (layoutRadius * RADIO_DEFAULT_CHILD_DIMENSION);

        // 根据menu item的个数，计算角度
        int cCount = getChildCount();
        float angleDelay;
        if (cCount <= 1) {
            angleDelay = 360;
        } else {
            angleDelay = 360 / (getChildCount() - 1);
        }

        // 遍历去设置menuitem的位置
        for (int i = 0; i < childCount; i++) {
            final View child = getChildAt(i);
            if (i == 0) {
                int cl = layoutRadius / 2 - child.getMeasuredWidth() / 2;
                int cr = cl + child.getMeasuredWidth();
                child.layout(cl, cl, cr, cr);
                continue;
            }
            mStartAngle %= 360;

            // 计算，中心点到menu item中心的距离
            float tmp = layoutRadius / 2f - cWidth / 2 - mPadding;

            // tmp cosa 即menu item中心点的横坐标
            left = layoutRadius / 2 + (int) Math.round(tmp * Math.cos(Math.toRadians(mStartAngle)) - 1 / 2f * cWidth);
            // tmp sina 即menu item的纵坐标
            top = layoutRadius / 2 + (int) Math.round(tmp * Math.sin(Math.toRadians(mStartAngle)) - 1 / 2f * cWidth);

            child.layout(left, top, left + cWidth, top + cWidth);
            // 叠加尺寸
            if (mDirection == Direction.CLOCKWISE) {
                mStartAngle += angleDelay;
            } else {
                mStartAngle -= angleDelay;
            }
        }
    }

    public enum Direction {
        CLOCKWISE, COUNTERCLOCKWISE
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int resWidth = 0;
        int resHeight = 0;

        /**
         * 根据传入的参数，分别获取测量模式和测量值
         */
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        /**
         * 如果宽或者高的测量模式非精确值
         */
        if (widthMode != MeasureSpec.EXACTLY || heightMode != MeasureSpec.EXACTLY) {
            // 主要设置为背景图的高度
            resWidth = getSuggestedMinimumWidth();
            // 如果未设置背景图片，则设置为屏幕宽高的默认值
            resWidth = resWidth == 0 ? getDefaultWidth() : resWidth;

            resHeight = getSuggestedMinimumHeight();
            // 如果未设置背景图片，则设置为屏幕宽高的默认值
            resHeight = resHeight == 0 ? getDefaultWidth() : resHeight;
        } else {
            // 如果都设置为精确值，则直接取小值；
            resWidth = resHeight = Math.min(width, height);
        }
        setMeasuredDimension(resWidth, resHeight);
        // 获得半径
        mRadius = Math.max(getMeasuredWidth(), getMeasuredHeight());
        // menu item数量
        final int count = getChildCount();
        // menu item尺寸
        int childSize = (int) (mRadius * RADIO_DEFAULT_CHILD_DIMENSION);
        // menu item测量模式
        int childMode = MeasureSpec.EXACTLY;
        // 迭代测量
        for (int i = 0; i < count; i++) {
            final View child = getChildAt(i);
            if (child.getVisibility() == GONE) {
                continue;
            }
            // 计算menu item的尺寸；以及和设置好的模式，去对item进行测量
            int makeMeasureSpec = -1;
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(childSize, childMode);
            child.measure(makeMeasureSpec, makeMeasureSpec);
        }
//        mPadding = RADIO_PADDING_LAYOUT * mRadius;
    }

    /**
     * 获得默认该layout的尺寸
     *
     * @return
     */
    private int getDefaultWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(
                Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return Math.min(outMetrics.widthPixels, outMetrics.heightPixels);
    }

}
