package im.ene.dev.zbroadcastreceiver2;

import im.ene.dev.zbroadcastreceiver2.R;
import im.ene.dev.zbroadcastreceiver2.receivers.CustomBroadcastReceiver;
import im.ene.dev.zbroadcastreceiver2.receivers.CustomBroadcastReceiver.OnBroadcastReceiveListener;
import im.ene.dev.zbroadcastreceiver2.services.CustomBroadcastService;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity 
implements OnSeekBarChangeListener, OnClickListener, OnBroadcastReceiveListener {

	public static final String BROADCAST_ACTIVITY_ACTION = "im.ene.br2.broadcast_from_main_activity";

	private Button mButton;
	private TextView mTextView;
	private SeekBar mSeekBar;

	private Intent mServiceIntent;
	private Intent mActivityIntent;
	private Intent mBroadcastIntent;

	private CustomBroadcastReceiver mReceiver;

	private Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		mButton = (Button) findViewById(R.id.main_button);
		mButton.setOnClickListener(this);

		mTextView = (TextView) findViewById(R.id.main_textview);
//		mTextView.setOnClickListener(this);

		mSeekBar = (SeekBar) findViewById(R.id.main_seekbar);
		mSeekBar.setMax(10000);
		mSeekBar.setOnSeekBarChangeListener(this);

		mReceiver = new CustomBroadcastReceiver();
		mReceiver.setOnBroadcastReceiveListener(this);

		mHandler = new Handler();

		mBroadcastIntent = new Intent(MainActivity.BROADCAST_ACTIVITY_ACTION);
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
	protected void onResume() {
		super.onResume();

		/* register a receiver to receive broadcast from service */
		registerReceiver(mReceiver, new IntentFilter(CustomBroadcastService.BROADCAST_SERVICE_ACTION));
	}

	@Override
	protected void onPause() {
		super.onPause();

		/* stop/pause receiving broadcast from service */
		try {
			unregisterReceiver(mReceiver);
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		try {
			stopService(mServiceIntent);
		} catch (Exception er) {
			er.printStackTrace();
		}
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacksAndMessages(null);
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mBroadcastIntent.putExtra("progress", seekBar.getProgress());
		sendBroadcast(mBroadcastIntent);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_button:
			if (mServiceIntent == null) {
				mServiceIntent = new Intent(this, CustomBroadcastService.class);
				startService(mServiceIntent);
			}
			break;
		}
	}

	@Override
	public void onBroadcastReceiveUpdateUI(Intent intent) {
		int progress = intent.getIntExtra("progress", 1);
		mSeekBar.setProgress(progress);
		mTextView.setText(progress + "");
	}

}
