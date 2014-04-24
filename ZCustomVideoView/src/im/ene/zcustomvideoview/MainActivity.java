package im.ene.zcustomvideoview;

import im.ene.zcustomvideoview.views.CustomVideoView;
import im.ene.zcustomvideoview.views.VideoControllerView;
import im.ene.zcustomvideoview.views.VideoControllerView.MediaPlayerControl;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends ActionBarActivity implements OnClickListener,
		OnPreparedListener, MediaPlayerControl, OnCompletionListener {

	private Context mContext;
	private FrameLayout mPlayerContainer;
	private CustomVideoView mVideoView;
	private VideoControllerView mController;
	private Button buttonPlayVideo, buttonPauseVideo;
	private int mDisplayHeight, mDisplayWidth, smallHeight;

	private static String mv1 = "http://www.auby.no/files/video_tests/h264_720p_mp_3.1_3mbps_aac_shrinkage.mp4";

	private static String mv2 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mContext = this;

		mPlayerContainer = (FrameLayout) findViewById(R.id.video_container);

		mVideoView = (CustomVideoView) findViewById(R.id.video_view);

		mController = new VideoControllerView(mContext);

		buttonPlayVideo = (Button) findViewById(R.id.playvideoplayer);
		buttonPauseVideo = (Button) findViewById(R.id.pausevideoplayer);
		buttonPlayVideo.setOnClickListener(this);
		buttonPauseVideo.setOnClickListener(this);

		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		mDisplayWidth = size.x;
		mDisplayHeight = size.y;

		smallHeight = mDisplayWidth * 9 / 16;

		mVideoView.setDimensions(mDisplayWidth, smallHeight);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		Log.d("configuration changed", "is called");

		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			getWindow().clearFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FULLSCREEN);

			mVideoView.setDimensions(mDisplayHeight, mDisplayWidth);
			mVideoView.getHolder().setFixedSize(mDisplayHeight, mDisplayWidth);

		} else {
			getWindow().setFlags(
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN,
					WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

			mVideoView.setDimensions(mDisplayWidth, smallHeight);
			mVideoView.getHolder().setFixedSize(mDisplayWidth, smallHeight);

		}
	}

	@Override
	public void onClick(View v) {
		if (v == buttonPlayVideo) {
			play(mv1);
		} else if (v == buttonPauseVideo) {
			play(mv2);
		}

	}

	private void play(String uri) {
		if (mVideoView.isPlaying()) {
			mVideoView.stopPlayback();
		}
		mVideoView.setVideoURI(Uri.parse(uri));
		mVideoView.setOnPreparedListener(this);
		mVideoView.setOnCompletionListener(this);
	}

	@Override
	public void onPrepared(MediaPlayer mp) {
		mController.setAnchorView(mPlayerContainer);
		mController.setMediaPlayer(this);
		mVideoView.start();

	}

	@Override
	public void start() {
		mVideoView.start();

	}

	@Override
	public void pause() {
		mVideoView.pause();

	}

	@Override
	public int getDuration() {
		// TODO Auto-generated method stub
		return mVideoView.getDuration();
	}

	@Override
	public int getCurrentPosition() {
		// TODO Auto-generated method stub
		return mVideoView.getCurrentPosition();
	}

	@Override
	public void seekTo(int pos) {
		mVideoView.seekTo(pos);

	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return mVideoView.isPlaying();
	}

	@Override
	public int getBufferPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean canPause() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekBackward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canSeekForward() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isFullScreen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void toggleFullScreen() {
		hideContent();
		this.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mController.show();
		return false;
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub

	}

	private void hideContent() {
		findViewById(R.id.head_text).setVisibility(View.GONE);
		buttonPauseVideo.setVisibility(View.GONE);
		buttonPlayVideo.setVisibility(View.GONE);
	}

	private void showContent() {
		findViewById(R.id.head_text).setVisibility(View.VISIBLE);
		buttonPauseVideo.setVisibility(View.VISIBLE);
		buttonPlayVideo.setVisibility(View.VISIBLE);
	}
}
