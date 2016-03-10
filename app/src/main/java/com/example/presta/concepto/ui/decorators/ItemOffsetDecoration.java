package com.example.presta.concepto.ui.decorators;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.IntegerRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Presta on 08/03/2016.
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    // La medida en pizeles del espaciado
    private int mItemOffset;

    public ItemOffsetDecoration(Context context, @IntegerRes int integerResId){
        int itemOffsetDp = context.getResources().getInteger(integerResId);
        mItemOffset = convertDpToPx(itemOffsetDp, context.getResources().getDisplayMetrics().densityDpi);
    }

    public int convertDpToPx(int dps, int densidadDPI){
        return dps * (densidadDPI / 160);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffset,mItemOffset, mItemOffset, mItemOffset );
    }
}
