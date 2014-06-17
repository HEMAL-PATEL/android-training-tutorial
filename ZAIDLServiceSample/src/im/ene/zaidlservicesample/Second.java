package im.ene.zaidlservicesample;

import android.app.Activity;
import android.os.Bundle;

public class Second extends Activity {

	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
		setContentView(R.layout.second);
		System.out.println("----Secondactivity activity---onCreates---");
	}
}
