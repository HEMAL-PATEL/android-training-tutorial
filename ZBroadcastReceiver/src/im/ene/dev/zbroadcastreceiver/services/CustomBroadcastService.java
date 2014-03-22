package im.ene.dev.zbroadcastreceiver.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class CustomBroadcastService extends Service {

	public static final String BROADCAST_ACTION = "im.ene.broadcast_action";

	private Intent mIntent;
	private int counter;
	private Handler mHandler;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		/* this intent will be used to send broadcast */
		mIntent = new Intent(CustomBroadcastService.BROADCAST_ACTION);
		counter = 0;
		mHandler = new Handler();
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mHandler.postDelayed(mSendBroadcastRunnable, 500);
		return super.onStartCommand(intent, flags, startId);
	}

	public Runnable mSendBroadcastRunnable = new Runnable() {

		@Override
		public void run() {
			sendBroadcastToUpdateUI();
			mHandler.postDelayed(this, 1000);
		}
	};

	public void sendBroadcastToUpdateUI() {
		counter++;
		mIntent.putExtra("text", counter + "");
		sendBroadcast(mIntent);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		mHandler.removeCallbacksAndMessages(null);
	}

}
