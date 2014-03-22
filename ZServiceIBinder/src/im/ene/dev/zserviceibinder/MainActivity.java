package im.ene.dev.zserviceibinder;

import im.ene.dev.zserviceibinder.R;
import im.ene.dev.zserviceibinder.services.CustomService;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	private Button mBtnStartService, mBtnStopService, mBtnBindService, mBtnUnbindService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		mBtnStartService = (Button) findViewById(R.id.btn_start_service);
		mBtnStartService.setOnClickListener(this);
		mBtnStopService = (Button) findViewById(R.id.btn_stop_service);
		mBtnStopService.setOnClickListener(this);
		mBtnBindService = (Button) findViewById(R.id.btn_bind_service);
		mBtnBindService.setOnClickListener(this);
		mBtnUnbindService = (Button) findViewById(R.id.btn_unbind_service);
		mBtnUnbindService.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){

		case R.id.btn_start_service:
			startService(new Intent(MainActivity.this, CustomService.class));
			break;

		case R.id.btn_stop_service:
			stopService(new Intent(MainActivity.this, CustomService.class));
			break;

		case R.id.btn_bind_service:
			doBindService();
			break;

		case R.id.btn_unbind_service:
			doUnbindService();
			break;
		}
	}

	private CustomService mBoundService;
	private boolean mIsBound;

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			Toast.makeText(MainActivity.this, "Activity:onServiceConnected", 
					Toast.LENGTH_SHORT).show();
			mBoundService = ((CustomService.CustomServiceLocalBinder) service).getService();
		}

		public void onServiceDisconnected(ComponentName className) {
			mBoundService = null;
			Toast.makeText(MainActivity.this, "Activity:onServiceDisconnected",
					Toast.LENGTH_SHORT).show();
		}
	};

	void doBindService() {
		bindService(new Intent(MainActivity.this,
				CustomService.class), mConnection, Context.BIND_AUTO_CREATE);
		mIsBound = true;
	}

	void doUnbindService() {
		if (mIsBound) {
			unbindService(mConnection);
			mIsBound = false;
		}
	}
}
