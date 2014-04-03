package im.ene.zintenttutorial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	public static final String PRESS_TOPLEFT_BUTTON = "im.ene.zintent.activity.button.topleft";
	
	public static final String PRESS_TOPRIGHT_BUTTON = "im.ene.zintent.activity.button.topright";
	
	public static final String PRESS_MIDLEFT_BUTTON = "im.ene.zintent.activity.button.midleft";
	
	public static final String PRESS_MIDRIGHT_BUTTON = "im.ene.zintent.activity.button.midright";
	
	public static final String PRESS_BOTLEFT_BUTTON = "im.ene.zintent.activity.button.botleft";
	
	public static final String PRESS_BOTRIGHT_BUTTON = "im.ene.zintent.activity.button.botright";
	
	private Button mBtnTopLeft, mBtnTopRight, mBtnMidLeft, mBtnMidRight,
			mBtnBotLeft, mBtnBotRight;
	
	private Intent mActivityIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		mBtnTopLeft = (Button) findViewById(R.id.btn_topleft);
		mBtnTopRight = (Button) findViewById(R.id.btn_topright);
		mBtnMidLeft = (Button) findViewById(R.id.btn_midleft);
		mBtnMidRight = (Button) findViewById(R.id.btn_midright);
		mBtnBotLeft = (Button) findViewById(R.id.btn_botleft);
		mBtnBotRight = (Button) findViewById(R.id.btn_botright);

		mBtnTopLeft.setOnClickListener(this);
		mBtnTopRight.setOnClickListener(this);
		mBtnMidLeft.setOnClickListener(this);
		mBtnMidRight.setOnClickListener(this);
		mBtnBotLeft.setOnClickListener(this);
		mBtnBotRight.setOnClickListener(this);
		
		mActivityIntent = new Intent();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_topleft:
			mActivityIntent.setAction(PRESS_TOPLEFT_BUTTON);
			mActivityIntent.putExtra("action", PRESS_BOTLEFT_BUTTON);
			sendBroadcast(mActivityIntent);
			break;

		case R.id.btn_topright:

			break;

		case R.id.btn_midleft:

			break;

		case R.id.btn_midright:

			break;

		case R.id.btn_botleft:

			break;

		case R.id.btn_botright:

			break;
		}

	}
}
