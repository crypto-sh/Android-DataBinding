package ir.prime.primevideo.service;


import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;


import ir.prime.primevideo.PrimeVideoPlayer;
import ir.prime.primevideo.R;
import ir.prime.primevideo.Utils.PublicFunction;

import ir.prime.primevideo.exoplayer2.ui.SimpleExoPlayerView;


public class VideoPlayerOverviewService extends Service implements View.OnTouchListener {

    float offsetX;
    float offsetY;
    int originalXPos;
    int originalYPos;
    boolean moving;

    WindowManager windowManager             = null;

    PrimeVideoPlayer    player              = null;

    SimpleExoPlayerView simpleExoPlayerView = null;

    final String TAG = VideoPlayerOverviewService.class.getSimpleName();

    public VideoPlayerOverviewService() {

    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
            WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.WRAP_CONTENT,
                    WindowManager.LayoutParams.TYPE_PHONE,
                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                    PixelFormat.TRANSLUCENT);

            params.gravity = Gravity.TOP | Gravity.LEFT;
            params.x = 100;
            params.y = 200;

            if (simpleExoPlayerView != null) {
                simpleExoPlayerView.setKeepScreenOn(true);
                simpleExoPlayerView.setLayoutParams(
                        new WindowManager.LayoutParams(
                                PublicFunction.convertDpToPixelsInt(200,getApplicationContext()),
                                PublicFunction.convertDpToPixelsInt(112,getApplicationContext())));
                simpleExoPlayerView.setOnTouchListener(this);
                windowManager.addView(simpleExoPlayerView, params);
            } else {
                simpleExoPlayerView= new SimpleExoPlayerView(getApplicationContext());
                simpleExoPlayerView.setKeepScreenOn(true);
                simpleExoPlayerView.setLayoutParams(
                        new WindowManager.LayoutParams(
                                PublicFunction.convertDpToPixelsInt(200,getApplicationContext()),
                                PublicFunction.convertDpToPixelsInt(112,getApplicationContext())));
                simpleExoPlayerView.setOnTouchListener(this);
                windowManager.addView(simpleExoPlayerView, params);
            }
        } catch (Exception e) {
            PublicFunction.LogData(false,TAG,e.getMessage());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (simpleExoPlayerView != null){
            player = new PrimeVideoPlayer(getApplicationContext(),simpleExoPlayerView);
            player.init(getString(R.string.ad_tag_url)
                    ,getString(R.string.content_url));
        }
        return START_NOT_STICKY;
    }


    @Override
    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int[] topLeftLocationOnScreen = new int[2];
            simpleExoPlayerView.getLocationOnScreen(topLeftLocationOnScreen);
            moving = false;
            offsetX = topLeftLocationOnScreen[0] - event.getRawX();
            offsetY = topLeftLocationOnScreen[1] - event.getRawY();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            WindowManager.LayoutParams params = (WindowManager.LayoutParams) simpleExoPlayerView.getLayoutParams();
            int newX = (int) (offsetX + event.getRawX());
            int newY = (int) (offsetY + event.getRawY());
            if (Math.abs(newX - originalXPos) < 1 && Math.abs(newY - originalYPos) < 1 && !moving) {
                return false;
            }
            params.x = newX;
            params.y = newY;
            windowManager.updateViewLayout(simpleExoPlayerView, params);
            moving = true;
        }
        return false;
    }
}
