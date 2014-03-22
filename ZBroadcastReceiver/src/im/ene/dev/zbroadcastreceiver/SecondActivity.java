package im.ene.dev.zbroadcastreceiver;

import im.ene.dev.zbroadcastreceiver.receivers.CustomBroadcastReceiver;
import im.ene.dev.zbroadcastreceiver.receivers.CustomBroadcastReceiver.OnBroadcastReceiveListener;
import im.ene.dev.zbroadcastreceiver.services.CustomBroadcastService;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity implements OnClickListener, OnBroadcastReceiveListener {

	private Button mButton;
	private Context mContext;
	private TextView mTextView;
	private String mLatestString = "";
	
	private CustomBroadcastReceiver mReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_activity_view);
		
		mButton = (Button) findViewById(R.id.second_button);
		mTextView = (TextView) findViewById(R.id.second_textview);
		mTextView.setText(mLatestString);
		
		mButton.setOnClickListener(this);
		mTextView.setOnClickListener(this);
		
		mReceiver = new CustomBroadcastReceiver();
		mReceiver.setOnBroadcastReceiveListener(this);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		registerReceiver(mReceiver, new IntentFilter(CustomBroadcastService.BROADCAST_ACTION));
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.second_button:
			
			break;

		case R.id.second_textview:
			finish();
			break;
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		try {
			unregisterReceiver(mReceiver);
		} catch (Exception er) {
			er.printStackTrace();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		finish();
	}

	@Override
	public void onBroadcastReceiveUpdateUI(Intent intent) {
		mLatestString = "SUB - " + intent.getStringExtra("text");
		mTextView.setText(mLatestString);
		
	}
}
