package ir.prime.custom_view;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;


public class LayoutManagerCustom extends LinearLayoutManager {


    Context mContext;

    Boolean mCanScrollVertically = true;

    static final float MILLISECONDS_PER_INCH = 80f;

    final String TAG = LayoutManagerCustom.class.getSimpleName();

    public LayoutManagerCustom(Context context, boolean vertical) {
        super(context);
        mContext = context;
        if(vertical){
            this.setOrientation(VERTICAL);
        }else{
            this.setOrientation(HORIZONTAL);
        }

        this.setSmoothScrollbarEnabled(true);
    }

    @Override
    public boolean canScrollVertically() {
        return mCanScrollVertically;
    }

    public void canScrollVertically(boolean enable){
        mCanScrollVertically = enable;
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, final int position) {
        LinearSmoothScroller smoothScroller =
                new LinearSmoothScroller(mContext) {

                    //This controls the direction in which smoothScroll looks
                    //for your view
                    @Override
                    public PointF computeScrollVectorForPosition
                    (int targetPosition) {
                        return LayoutManagerCustom.this.computeScrollVectorForPosition(targetPosition);
                    }

                    //This returns the milliseconds it takes to
                    //scroll one pixel.
                    @Override
                    protected float calculateSpeedPerPixel
                    (DisplayMetrics displayMetrics) {
                        return MILLISECONDS_PER_INCH/displayMetrics.densityDpi;
                    }
                };
        smoothScroller.setTargetPosition(position);
        startSmoothScroll(smoothScroller);
    }
}
