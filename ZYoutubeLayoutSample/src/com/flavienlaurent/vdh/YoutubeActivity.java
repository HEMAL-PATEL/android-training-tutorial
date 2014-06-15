package com.flavienlaurent.vdh;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flavienlaurent.vdh.YoutubeLayout.YoutubeLayoutListener;

/**
 * Created by Flavien Laurent (flavienlaurent.com) on 23/08/13.
 */
public class YoutubeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_youtube);

		final TextView viewHeader = (TextView) findViewById(R.id.header);
		final YoutubeLayout youtubeLayout = (YoutubeLayout) findViewById(R.id.dragLayout);
		youtubeLayout.setYoutubeLayoutListener(new YoutubeLayoutListener() {

			@Override
			public void onHeadViewClickListener(View view) {
				Toast.makeText(getBaseContext(), "clicked", Toast.LENGTH_SHORT)
						.show();
			}
		});

		final ListView listView = (ListView) findViewById(R.id.listView);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view,
					int i, long l) {
				viewHeader.setText(listView.getAdapter().getItem(i).toString());
				youtubeLayout.setVisibility(View.VISIBLE);
				youtubeLayout.maximize();
			}
		});

		listView.setAdapter(new BaseAdapter() {
			@Override
			public int getCount() {
				return 50;
			}

			@Override
			public String getItem(int i) {
				return "object" + i;
			}

			@Override
			public long getItemId(int i) {
				return i;
			}

			@Override
			public View getView(int i, View rView, ViewGroup viewGroup) {
				View view = rView;
				if (view == null) {
					view = getLayoutInflater().inflate(
							android.R.layout.simple_list_item_1, viewGroup,
							false);
				}
				((TextView) view.findViewById(android.R.id.text1))
						.setText(getItem(i));
				return view;
			}
		});
	}
}
