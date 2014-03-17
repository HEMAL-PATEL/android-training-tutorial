package im.ene.dev.zbroadcastreceiver;

import im.ene.dev.zbroadcastreceiver.receiver.CustomBroadcastReceiver;
import im.ene.dev.zbroadcastreceiver.receiver.CustomBroadcastReceiver.ReceiverInterface;
import im.ene.dev.zbroadcastreceiver.receiver.CustomBroadcastService;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener, ReceiverInterface {

	private Button mButton;
	private TextView mTextView;
	private Context mContext;
	private Intent mActivityIntent;
	private Intent mServiceIntent;

	private CustomBroadcastReceiver mReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		mButton = (Button) findViewById(R.id.main_button);
		mTextView = (TextView) findViewById(R.id.main_textview);

		mButton.setOnClickListener(this);
		mTextView.setOnClickListener(this);

		mReceiver = new CustomBroadcastReceiver();
		mReceiver.setReceiverInterface(this);

	}

	@Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mReceiver, new IntentFilter(CustomBroadcastService.BROADCAST_ACTION));
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
		switch (v.getId()) {
		case R.id.main_button:
			if (mServiceIntent == null) {
				mServiceIntent = new Intent(this, CustomBroadcastService.class);
				startService(mServiceIntent);
			}
			break;

		case R.id.main_textview:
			mActivityIntent = new Intent(this, SecondActivity.class);
			startActivity(mActivityIntent);
			break;
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		try {
			unregisterReceiver(mReceiver);
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	@Override
	public void updateTextView(Intent intent) {
		mTextView.setText("MAIN - " + intent.getStringExtra("text"));
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

}
