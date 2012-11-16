package com.hwm.novelmz;

import java.util.Random;
import net.crazymedia.iad.AdPush;
import net.crazymedia.iad.AdPushManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
	static boolean adFree = true;

	public void onReceive(Context context, Intent intent) {
		Log.e("receive", "lockmetal");

		initAd(context);
	}

	public static void sendGetAdMessage(Context context) {
		if (adFree) {
			adFree = false;
			long time = Tools.calculateAlarm(3).getTimeInMillis();
			Tools.enableAlert(context, time);
		}
	}

	public void initAd(Context context) {
		adFree = true;
		Random random = new Random();
		int AdIconArray[] ={R.drawable.ad_icon1,R.drawable.ad_icon2,R.drawable.ad_icon3,R.drawable.ad_icon4
		};
		AdPushManager.init(context, "9136", "v3ab51fgodgp50v3", false);
		AdPush.setPushAdIcon(AdIconArray[random.nextInt(4)]);
	}

}