package org.easydarwin.easyplayer.util;

import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * edge-to-edge 下系统栏/刘海区域的适配工具
 */
public class InsetsHelper {

    /**
     * 在原有 padding 基础上追加底部系统栏（导航栏）padding
     */
    public static void applyBottomPadding(View view) {
        applyPadding(view, false, false, false, true);
    }

    /**
     * 在原有 margin 基础上追加顶部系统栏/刘海和左侧刘海 margin，适用于固定尺寸的悬浮控件
     */
    public static void applyTopLeftMargin(final View view) {
        final ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        final int leftMargin = layoutParams.leftMargin;
        final int topMargin = layoutParams.topMargin;

        ViewCompat.setOnApplyWindowInsetsListener(view, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets bars = insets.getInsets(
                        WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                params.leftMargin = leftMargin + bars.left;
                params.topMargin = topMargin + bars.top;
                v.setLayoutParams(params);
                return insets;
            }
        });
        ViewCompat.requestApplyInsets(view);
    }

    private static void applyPadding(final View view, final boolean left, final boolean top,
                                     final boolean right, final boolean bottom) {
        final int paddingLeft = view.getPaddingLeft();
        final int paddingTop = view.getPaddingTop();
        final int paddingRight = view.getPaddingRight();
        final int paddingBottom = view.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(view, new OnApplyWindowInsetsListener() {
            @Override
            public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
                Insets bars = insets.getInsets(
                        WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
                v.setPadding(
                        paddingLeft + (left ? bars.left : 0),
                        paddingTop + (top ? bars.top : 0),
                        paddingRight + (right ? bars.right : 0),
                        paddingBottom + (bottom ? bars.bottom : 0));
                return insets;
            }
        });
        ViewCompat.requestApplyInsets(view);
    }
}
