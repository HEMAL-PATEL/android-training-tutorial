package im.ene.samplevideoplayer;

import im.ene.samplevideoplayer.views.CustomSurfaceView;
import im.ene.samplevideoplayer.views.VideoControllerView;
import im.ene.samplevideoplayer.views.VideoControllerView.MediaPlayerControl;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

@SuppressLint("NewApi")
public class MainActivity extends ActionBarActivity implements Callback,
		OnClickListener, OnPreparedListener, OnCompletionListener,
		OnBufferingUpdateListener, OnErrorListener, MediaPlayerControl {

	private static String mv1 = "http://www.auby.no/files/video_tests/h264_720p_mp_3.1_3mbps_aac_shrinkage.mp4";

	private static String mv2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

	private Context mContext;

	Uri targetUri;

	private FrameLayout mContainer;
	private MediaPlayer mediaPlayer;
	private CustomSurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private VideoControllerView controller;
	private boolean pausing = false;

	TextView mediaUri;
	Button buttonPlayVideo, buttonPauseVideo;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mContext = this;

		// targetUri = Uri.parse(mv1);

		mContainer = (FrameLayout) findViewById(R.id.video_container);
		mediaUri = (TextView) findViewById(R.id.mediauri);

		buttonPlayVideo = (Button) findViewById(R.id.playvideoplayer);
		buttonPauseVideo = (Button) findViewById(R.id.pausevideoplayer);
		buttonPlayVideo.setOnClickListener(this);
		buttonPauseVideo.setOnClickListener(this);

		surfaceView = (CustomSurfaceView) findViewById(R.id.surfaceview);
		surfaceView.setFocusable(true);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);

		controller = new VideoControllerView(this);
		controller.show();

		mediaPlayer = new MediaPlayer();

	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		mediaPlayer.release();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		controller.show();
		return false;
	}

	@Override
	public void onClick(View v) {
		if (v == buttonPlayVideo) {
			playVideo(mv1);
		} else if (v == buttonPauseVideo) {
			playVideo(mv2);
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mediaPlayer.setDisplay(holder);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		controller.setAnchorView(mContainer);
		controller.setMediaPlayer(this);
		
		if (surfaceView.getVisibility() == View.GONE) {
			surfaceView.setVisibility(View.VISIBLE);
		}
		
		int videoHeight = mp.getVideoHeight();
		int videoWidth = mp.getVideoWidth();

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width_ = size.x;
		int height_ = width_ * videoHeight / videoWidth;

		surfaceHolder.setFixedSize(width_, height_);
//		surfaceView.getLayoutParams().height = height_;
		surfaceView.setDimensions(width_, height_);
		mp.start();

	}

	private void playVideo(String uri) {
		if (mediaPlayer == null) {
			mediaPlayer = new MediaPlayer();
		}

		if (mediaPlayer.isPlaying()) {
			mediaPlayer.stop();
		}

		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(mContext, Uri.parse(uri));
			mediaPlayer.prepareAsync();

			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setOnBufferingUpdateListener(this);
			mediaPlayer.setOnPreparedListener(this);
			mediaPlayer.setOnErrorListener(this);
			// surfaceView.requestFocus();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onCompletion(MediaPlayer mp) {

	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		mediaUri.setText(percent + "%");
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		Log.d("mediaplayer", "error");
		return false;
	}

	// Implement VideoMediaController.MediaPlayerControl
	@Override
	public boolean canPause() {
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		return true;
	}

	@Override
	public boolean canSeekForward() {
		return true;
	}

	@Override
	public int getBufferPercentage() {
		return 0;
	}

	@Override
	public int getCurrentPosition() {
		if (mediaPlayer != null)
			return mediaPlayer.getCurrentPosition();
		else return 0;
	}

	@Override
	public int getDuration() {
		if (mediaPlayer != null)
			return mediaPlayer.getDuration();
		else return 0;
	}

	@Override
	public boolean isPlaying() {
		if (mediaPlayer != null)
			return mediaPlayer.isPlaying();
		else return false;
	}

	@Override
	public void pause() {
		if (mediaPlayer != null)
			mediaPlayer.pause();
	}

	@Override
	public void seekTo(int i) {
		if (mediaPlayer != null)
			mediaPlayer.seekTo(i);
	}

	@Override
	public void start() {
		mediaPlayer.start();
	}

	@Override
	public boolean isFullScreen() {
		return false;
	}

	@Override
	public void toggleFullScreen() {
		
	}
	// End VideoMediaController.MediaPlayerControl
}
