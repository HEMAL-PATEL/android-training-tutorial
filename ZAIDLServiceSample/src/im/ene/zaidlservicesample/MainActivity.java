package im.ene.zaidlservicesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
		setContentView(R.layout.first);
		System.out.println("----main activity---onCreate---");
		btnNext = (Button) findViewById(R.id.button1);
		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent ob = new Intent(MainActivity.this, Second.class);
				startActivity(ob);
			}
		});
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		System.out.println("----main activity---onStart---");
		overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
	}

}
