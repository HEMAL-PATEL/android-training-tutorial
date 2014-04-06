package im.ene.zintenttutorial.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CustomBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (mInterface != null)
			mInterface.onBroadcastReceiveUpdateUI(intent);
	}

	private OnBroadcastReceiveListener mInterface;

	public void setOnBroadcastReceiveListener(OnBroadcastReceiveListener intf) {
		this.mInterface = intf;
	}

	public interface OnBroadcastReceiveListener {
		public abstract void onBroadcastReceiveUpdateUI(Intent intent);
	}

}
