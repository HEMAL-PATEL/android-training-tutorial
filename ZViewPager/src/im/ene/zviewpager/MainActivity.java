package im.ene.zviewpager;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	private ViewPager mViewPager;
	private List<View> mViews;
	private ENECustomPagerAdapter mPagerAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViews = new ArrayList<View>();
		
		TextView view = new TextView(this);
		view.setText("sample text view 1");
		view.setTextColor(0x000000);
		mViews.add(view);
		
		view = new TextView(this);
		view.setText("sample text view 2");
		view.setTextColor(0x000000);
		mViews.add(view);
		
		view = new TextView(this);
		view.setText("sample text view 3");
		view.setTextColor(0x000000);
		mViews.add(view);
		
		mPagerAdapter = new ENECustomPagerAdapter(this, mViews);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), arg0 + "", Toast.LENGTH_SHORT).show();
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});

		mViewPager.setCurrentItem(0);
		
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
	
	private class ENECustomPagerAdapter extends PagerAdapter {

		private List<View> list;
		
		public ENECustomPagerAdapter(Context context, List<View> list) {
			this.list = list;
		}
		
		@Override
		public View instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			Toast.makeText(getBaseContext(), "item instantiated", Toast.LENGTH_SHORT).show();
			return list.get(position);
		}
		
		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
			Toast.makeText(getBaseContext(), "item destroyed", Toast.LENGTH_SHORT).show();
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
