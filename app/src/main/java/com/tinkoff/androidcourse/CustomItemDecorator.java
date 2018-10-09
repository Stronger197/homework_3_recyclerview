package com.tinkoff.androidcourse;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;

public class CustomItemDecorator extends RecyclerView.ItemDecoration {

    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CustomItemDecorator() {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
    }

    private static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return (int) (dp * (metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int dividerLeft = parent.getPaddingLeft();
        int dividerRight = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount - 1; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop = child.getBottom() + params.bottomMargin;
            int dividerBottom = dividerTop + convertDpToPixel(1f, parent.getContext());
            c.drawRect(dividerLeft, dividerTop, dividerRight, dividerBottom, paint);
        }
    }

}
