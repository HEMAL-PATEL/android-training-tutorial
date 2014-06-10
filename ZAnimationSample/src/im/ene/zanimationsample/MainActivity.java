package im.ene.zanimationsample;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends ActionBarActivity {

	private ImageView mImage1, mImage2;
	private Animation animation_fadein, animation_fadeout;

	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		mContext = this;

		mImage1 = (ImageView) findViewById(R.id.image_main);
		mImage2 = (ImageView) findViewById(R.id.image_sub);

		// set default image to imageviews
		Picasso.with(mContext).load(R.drawable.image_1).fit().centerCrop()
				.into(mImage1);
		Picasso.with(mContext).load(R.drawable.image_2).fit().centerCrop()
				.into(mImage2);

		// define fade animations
		animation_fadeout = AnimationUtils.loadAnimation(this,
				R.anim.abc_fade_out);
		animation_fadeout.setDuration(500);
		animation_fadeout.setAnimationListener(fadeout_animation_listener);

		animation_fadein = AnimationUtils.loadAnimation(this,
				R.anim.abc_fade_in);
		animation_fadein.setDuration(500);
		animation_fadein.setAnimationListener(fadein_animation_listener);

		mImage2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// start the fade out animation here
				mImage1.startAnimation(animation_fadeout);
				mImage2.startAnimation(animation_fadeout);
			}
		});
	}

	private AnimationListener fadeout_animation_listener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {

		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			mImage1.startAnimation(animation_fadein);
			mImage2.startAnimation(animation_fadein);
		}
	};

	int counter = 1;
	
	private AnimationListener fadein_animation_listener = new AnimationListener() {

		@Override
		public void onAnimationStart(Animation animation) {
			Log.d("counter", counter ++ + "");
			Picasso.with(mContext).load(R.drawable.image_2).fit().centerCrop()
					.into(mImage1);
			Picasso.with(mContext).load(R.drawable.image_1).fit().centerCrop()
					.into(mImage2);
		}

		@Override
		public void onAnimationRepeat(Animation animation) {

		}

		@Override
		public void onAnimationEnd(Animation animation) {
			
		}
	};
}
