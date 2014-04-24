package im.ene.samplevideoplayer.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

public class CustomSurfaceView extends SurfaceView {

	private int mForceHeight = 0;
	private int mForceWidth = 0;

	public CustomSurfaceView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CustomSurfaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public CustomSurfaceView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public void setDimensions(int w, int h) {
		this.mForceHeight = h;
		this.mForceWidth = w;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.i("@@@@", "onMeasure");
		setMeasuredDimension(mForceWidth, mForceHeight);
	}

}
