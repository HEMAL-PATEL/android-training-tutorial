package im.ene.dev.zbroadcastreceiver.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CustomBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (mInterface != null)
			mInterface.updateTextView(intent);
	}
	
	private ReceiverInterface mInterface;
	
	public void setReceiverInterface(ReceiverInterface intf) {
		this.mInterface = intf;
	}
	
	public interface ReceiverInterface {
		public abstract void updateTextView(Intent intent);
	}

}
