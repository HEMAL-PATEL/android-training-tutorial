package im.ene.dev.zbroadcastreceiver2.services;

import im.ene.dev.zbroadcastreceiver2.MainActivity;
import im.ene.dev.zbroadcastreceiver2.receivers.CustomBroadcastReceiver;
import im.ene.dev.zbroadcastreceiver2.receivers.CustomBroadcastReceiver.OnBroadcastReceiveListener;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;

public class CustomBroadcastService extends Service implements OnBroadcastReceiveListener {
	
	public static final String BROADCAST_SERVICE_ACTION = "im.ene.br2.broadcast_from_service";
	
	public static final String TAG = "im.ene.br2.service";
	
	public static int MAX = 10000;
	
	private Intent mBroadcastIntent;
	
	private CustomBroadcastReceiver mReceiver;
	
	private Handler mHandler;

	private int counter;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		mBroadcastIntent = new Intent(CustomBroadcastService.BROADCAST_SERVICE_ACTION);
		
		mReceiver = new CustomBroadcastReceiver();
		mReceiver.setOnBroadcastReceiveListener(this);
		registerReceiver(mReceiver, new IntentFilter(MainActivity.BROADCAST_ACTIVITY_ACTION));
		
		mHandler = new Handler();
		counter = 0;
		
		super.onCreate();
	}
	
	public Runnable mSendBroadcastRunnable = new Runnable() {

		@Override
		public void run() {
			sendBroadcastToUpdateUI();
			mHandler.postDelayed(this, 100);
		}
	};
	
	public void sendBroadcastToUpdateUI() {
		counter += 10;
		if (counter >= MAX)
			counter = 0;
		mBroadcastIntent.putExtra("progress", counter);
		sendBroadcast(mBroadcastIntent);
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		mHandler.postDelayed(mSendBroadcastRunnable, 100);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onBroadcastReceiveUpdateUI(Intent intent) {
		counter = intent.getIntExtra("progress", 1);
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		/* stop/pause receiving broadcast from activity */
		try {
			unregisterReceiver(mReceiver);
		} catch (Exception er) {
			er.printStackTrace();
		}
	}
}
