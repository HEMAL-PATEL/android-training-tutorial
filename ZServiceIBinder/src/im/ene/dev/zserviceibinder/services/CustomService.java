package im.ene.dev.zserviceibinder.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class CustomService extends Service {

	static final String TAG = "My Custom Service";

	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		Toast.makeText(this, "MyService#onCreate", Toast.LENGTH_SHORT).show();
		super.onCreate();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.i(TAG, "onStartCommand Received start id " + startId + ": " + intent);
        Toast.makeText(this, "MyService#onStartCommand", Toast.LENGTH_SHORT).show();
        return START_STICKY;
	}
	
	@Override
	public void onDestroy() {
		Log.i(TAG, "onDestroy");
        Toast.makeText(this, "MyService#onDestroy", Toast.LENGTH_SHORT).show();
	}
	
	public class CustomServiceLocalBinder extends Binder {
		public CustomService getService() {
			return CustomService.this;
		}
	}
	
	private final IBinder mIBinder = new CustomServiceLocalBinder();
	
	@Override
	public IBinder onBind(Intent intent) {
		Toast.makeText(this, "MyService#onBind"+ ": " + intent, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onBind" + ": " + intent);
		return mIBinder;
	}
	
	@Override
	public void onRebind(Intent intent) {
		Toast.makeText(this, "MyService#onRebind"+ ": " + intent, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onRebind" + ": " + intent);
	}
	
	@Override
	public boolean onUnbind(Intent intent) {
		Toast.makeText(this, "MyService#onUnbind"+ ": " + intent, Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onUnbind" + ": " + intent);
 
        return true;
	}

}