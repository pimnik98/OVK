package uk.openvk.android.legacy.ui.views.base;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;

import uk.openvk.android.legacy.ui.views.base.InfinityNestedScrollView;

public class InfinitySmoothScrollView extends InfinityNestedScrollView {
    public InfinitySmoothScrollView(Context context) {
        super(context);
    }

    public InfinitySmoothScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            if(ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
                onTouchEvent(ev);
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}
