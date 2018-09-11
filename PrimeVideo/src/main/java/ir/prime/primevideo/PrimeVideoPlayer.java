package ir.prime.primevideo;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;


import ir.prime.primevideo.Utils.PublicFunction;
import ir.prime.primevideo.exoplayer2.C;
import ir.prime.primevideo.exoplayer2.ExoPlaybackException;
import ir.prime.primevideo.exoplayer2.ExoPlayerFactory;
import ir.prime.primevideo.exoplayer2.PlaybackParameters;
import ir.prime.primevideo.exoplayer2.Player;
import ir.prime.primevideo.exoplayer2.SimpleExoPlayer;
import ir.prime.primevideo.exoplayer2.Timeline;
import ir.prime.primevideo.exoplayer2.ext.ima.ImaAdsLoader;
import ir.prime.primevideo.exoplayer2.source.ExtractorMediaSource;
import ir.prime.primevideo.exoplayer2.source.MediaSource;
import ir.prime.primevideo.exoplayer2.source.MediaSourceEventListener;
import ir.prime.primevideo.exoplayer2.source.TrackGroupArray;
import ir.prime.primevideo.exoplayer2.source.ads.AdsMediaSource;
import ir.prime.primevideo.exoplayer2.source.dash.DashMediaSource;
import ir.prime.primevideo.exoplayer2.source.dash.DefaultDashChunkSource;
import ir.prime.primevideo.exoplayer2.source.hls.HlsMediaSource;
import ir.prime.primevideo.exoplayer2.trackselection.AdaptiveTrackSelection;
import ir.prime.primevideo.exoplayer2.trackselection.DefaultTrackSelector;
import ir.prime.primevideo.exoplayer2.trackselection.TrackSelection;
import ir.prime.primevideo.exoplayer2.trackselection.TrackSelectionArray;
import ir.prime.primevideo.exoplayer2.trackselection.TrackSelector;
import ir.prime.primevideo.exoplayer2.ui.SimpleExoPlayerView;
import ir.prime.primevideo.exoplayer2.upstream.BandwidthMeter;
import ir.prime.primevideo.exoplayer2.upstream.DataSource;
import ir.prime.primevideo.exoplayer2.upstream.DefaultBandwidthMeter;
import ir.prime.primevideo.exoplayer2.upstream.DefaultDataSourceFactory;
import ir.prime.primevideo.exoplayer2.util.Util;
import ir.prime.primevideo.service.VideoPlayerOverviewService;

/**
 * Created by alishatergholi on 2/15/18.
 */
public class PrimeVideoPlayer implements AdsMediaSource.MediaSourceFactory,Player.EventListener {

    Context context;

    private long contentPosition;

    private ImaAdsLoader adsLoader;

    private SimpleExoPlayer player;

    private SimpleExoPlayerView simpleExoPlayerView;

    private DataSource.Factory mediaDataSourceFactory;

    private DataSource.Factory manifestDataSourceFactory;

    final String TAG = PrimeVideoPlayer.class.getSimpleName();

    public PrimeVideoPlayer(Context context, SimpleExoPlayerView playerView) {
        this.simpleExoPlayerView    = playerView;
        this.context                = context;
        manifestDataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getString(R.string.application_name)));
        mediaDataSourceFactory    = new DefaultDataSourceFactory(context, Util.getUserAgent(context, context.getString(R.string.application_name)), new DefaultBandwidthMeter());
    }

    public void init(String advertisementUrl,String contentUrl){

        // Create a default track selector.
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        // Create a player instance.
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector);
        // Bind the player to the view.
        simpleExoPlayerView.setPlayer(player);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, context.getString(R.string.application_name)));
        // This is the MediaSource representing the content media (i.e. not the ad).
        MediaSource contentMediaSource =
                new ExtractorMediaSource.Factory(dataSourceFactory)
                        .createMediaSource(Uri.parse(contentUrl));

        if (!PublicFunction.StringIsEmptyOrNull(advertisementUrl)){
            adsLoader = new ImaAdsLoader(context, Uri.parse(advertisementUrl));
            MediaSource mediaSourceWithAds = new AdsMediaSource(
                            contentMediaSource,
                            /* adMediaSourceFactory= */ this,
                            adsLoader,
                            simpleExoPlayerView.getOverlayFrameLayout(),
                            /* eventHandler= */ null,
                            /* eventListener= */ null);
            player.prepare(mediaSourceWithAds);
        }else{
            player.prepare(contentMediaSource);
        }

        // Prepare the player with the source.
        player.seekTo(contentPosition);
        player.setPlayWhenReady(true);
    }

    public static void StartPlayingOverlay(Activity activity){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(activity)){
            activity.startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION));
        }else{
            activity.startService(new Intent(activity,VideoPlayerOverviewService.class));
        }
    }

    public void reset() {
        if (player != null) {
            contentPosition = player.getContentPosition();
            player.release();
            player = null;
        }
    }

    public void release() {
        if (player != null) {
            player.release();
            player = null;
        }
        adsLoader.release();
    }

    // AdsMediaSource.MediaSourceFactory implementation.
    @Override
    public MediaSource createMediaSource(Uri uri, @Nullable Handler handler, @Nullable MediaSourceEventListener listener) {
        @C.ContentType int type = Util.inferContentType(uri);
        switch (type) {
            case C.TYPE_DASH:
                return new DashMediaSource.Factory(new DefaultDashChunkSource.Factory(mediaDataSourceFactory), manifestDataSourceFactory).createMediaSource(uri, handler, listener);
            case C.TYPE_HLS:
                return new HlsMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri, handler, listener);
            case C.TYPE_OTHER:
                return new ExtractorMediaSource.Factory(mediaDataSourceFactory).createMediaSource(uri, handler, listener);
            case C.TYPE_SS:
            default:
                throw new IllegalStateException("Unsupported type: " + type);
        }
    }

    @Override
    public int[] getSupportedTypes() {
        return new int[] {C.TYPE_DASH, C.TYPE_HLS, C.TYPE_OTHER};
    }


    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {
        PublicFunction.LogData(true,TAG,"onTimelineChanged");
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        PublicFunction.LogData(true,TAG,"onTracksChanged");
    }

    @Override
    public void onLoadingChanged(boolean isLoading) {
        PublicFunction.LogData(true,TAG,"onLoadingChanged");
    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        PublicFunction.LogData(true,TAG,"onPlayerStateChanged");
    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {
        PublicFunction.LogData(true,TAG,"onRepeatModeChanged");
    }

    @Override
    public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
        PublicFunction.LogData(true,TAG,"onShuffleModeEnabledChanged");
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        PublicFunction.LogData(true,TAG,"onPlayerError : " + error);
    }

    @Override
    public void onPositionDiscontinuity(int reason) {
        PublicFunction.LogData(true,TAG,"onPositionDiscontinuity");
    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    @Override
    public void onSeekProcessed() {

    }
}
