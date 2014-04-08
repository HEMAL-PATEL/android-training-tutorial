package im.ene.pinnedheaderlistviewsample;

import java.util.ArrayList;
import java.util.List;

import com.hb.views.PinnedSectionListView.PinnedSectionListAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ListView mListView;
	private Context mContext;

	private List<Items> mItemList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		mContext = this;
		mListView = (ListView) findViewById(android.R.id.list);
		mItemList = new ArrayList<MainActivity.Items>();

		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));
		mItemList.add(new Items("top", "bot"));

		mListView.setAdapter(new CustomListAdapter(mContext,
				android.R.layout.simple_list_item_1, mItemList));
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

	public class Items {
		public String top;
		public String bot;

		public Items(String top, String bot) {
			this.top = top;
			this.bot = bot;
		}
	}

	public class ListItemViewHolder {
		public TextView mTopText;
		public TextView mBotText;
	}

	public class CustomListAdapter extends ArrayAdapter<Items> implements
			PinnedSectionListAdapter {

		private Context mContext;
		private LayoutInflater mInflater;
		private List<Items> mList;

		public CustomListAdapter(Context context, int resource, List<Items> list) {
			super(context, resource, list);
			mContext = context;
			mInflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			mList = list;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ListItemViewHolder mViewHolder;
			View view;

			if (position % 5 == 0) {
				view = mInflater.inflate(android.R.layout.simple_list_item_1,
						parent, false);
				
				TextView mText = (TextView) view;
				mText.setText("section " + position / 5);
				mText.setTextColor(Color.YELLOW);
				mText.setGravity(Gravity.CENTER);
				
				view.setBackgroundColor(Color.BLACK);
				view.setTag(-1, -1);
			} else {
				view = convertView;

				if (convertView == null) {
					view = mInflater.inflate(R.layout.list_item_layout, parent,
							false);
					mViewHolder = new ListItemViewHolder();
					mViewHolder.mTopText = (TextView) view
							.findViewById(R.id.item_top_text);
					mViewHolder.mBotText = (TextView) view
							.findViewById(R.id.item_bot_text);

					view.setTag(mViewHolder);

				} else {
					mViewHolder = (ListItemViewHolder) view.getTag();
				}

				final Items item = mList.get(position);
				mViewHolder.mTopText.setText(item.top);
				mViewHolder.mBotText.setText(item.bot);

			}

			return view;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			if (position % 5 == 0)
				return -1;
			else
				return 0;
		}

		@Override
		public boolean isItemViewTypePinned(int viewType) {
			return viewType == -1;
		}

	}
}
