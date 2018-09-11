package ir.prime.baseClass;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ir.prime.custom_view.SwipeBackLayout;
import ir.prime.R;
import ir.prime.enum_package.DragDirectMode;
import ir.prime.enum_package.DragEdge;


/**
 * Created by alishatergholi on 10/31/17.
 */
public abstract class BaseSwipeActivity extends BaseActivity implements SwipeBackLayout.SwipeBackListener {

    private ImageView ivShadow;

    private SwipeBackLayout swipeBackLayout;

    private static final DragEdge DEFAULT_DRAG_EDGE = DragEdge.LEFT;

    protected abstract int getLayoutResourceId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(getContainer());
        View view = LayoutInflater.from(this).inflate(layoutResID, null);
        swipeBackLayout.addView(view);
    }

    private View getContainer() {
        RelativeLayout container = new RelativeLayout(this);
        swipeBackLayout = new SwipeBackLayout(this);
        swipeBackLayout.setDragEdge(DEFAULT_DRAG_EDGE);
        swipeBackLayout.setOnSwipeBackListener(this);

        ivShadow = new ImageView(this);
        ivShadow.setBackgroundColor(getResources().getColor(R.color.black_p50));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        container.addView(ivShadow, params);
        container.addView(swipeBackLayout);
        return container;
    }

    public void setEnableSwipe(boolean enableSwipe) {
        swipeBackLayout.setEnablePullToBack(enableSwipe);
    }

    public void setDragEdge(DragEdge dragEdge) {
        swipeBackLayout.setDragEdge(dragEdge);
    }

    public void setDragDirectMode(DragDirectMode dragDirectMode) {
        swipeBackLayout.setDragDirectMode(dragDirectMode);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return swipeBackLayout;
    }

    @Override
    public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
        ivShadow.setAlpha(1 - fractionScreen);
    }
}
