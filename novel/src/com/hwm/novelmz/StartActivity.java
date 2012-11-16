package com.hwm.novelmz;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.start);
		 SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);
	        
//		  Set from XML, possible to programmatically set        
//       float distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics());
//       menu.setSatelliteDistance((int) distance);
//       menu.setExpandDuration(500);
//       menu.setCloseItemsOnClick(false);
//       menu.setTotalSpacingDegree(60);
       
       List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
       items.add(new SatelliteMenuItem(4, R.drawable.menu_ic3));
       items.add(new SatelliteMenuItem(4, R.drawable.ic_3));
       items.add(new SatelliteMenuItem(4, R.drawable.ic_4));
       items.add(new SatelliteMenuItem(3, R.drawable.ic_5));
       items.add(new SatelliteMenuItem(2, R.drawable.ic_6));
       items.add(new SatelliteMenuItem(1, R.drawable.ic_2));
//       items.add(new SatelliteMenuItem(5, R.drawable.sat_item));
       menu.addItems(items);        
       
       menu.setOnItemClickedListener(new com.hwm.novelmz.SatelliteMenu.SateliteClickedListener() {
			
			public void eventOccured(int id) {
				switch (id) { 
				case 1:
					Intent it_1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					startActivity(it_1);
					break;
				case 2: 					
					Intent it_2 = new Intent(Intent.ACTION_DIAL);
					startActivity(it_2);
					break; 
				case 3:
					Intent it_3 = new Intent(StartActivity.this,MyLocationOver.class);
					startActivity(it_3);
					break;
				case 4:
					Intent it_4 = new Intent(StartActivity.this,Transition3d.class);
					startActivity(it_4);
					break;
				case 5: 			        
					Uri uri_5 = Uri.parse("smsto:10086");   
					Intent it_5 = new Intent(Intent.ACTION_SENDTO, uri_5);   
					it_5.putExtra("sms_body", "cwj");   
					startActivity(it_5); 
					break; 
				case 6:
					Uri uri_6 = Uri.parse("http://www.baidu.com");
					Intent it  = new Intent(Intent.ACTION_VIEW,uri_6);
					startActivity(it);
					
				} 
				Log.i("sat", "Clicked on " + id);
			}
		});
       
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:{
			AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
			builder.setMessage("确定退出程序吗?");
			builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
					StartActivity.this.finish();					
				}
			});
			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
			builder.show();
		}
		return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		AlarmReceiver.sendGetAdMessage(StartActivity.this);
		super.onDestroy();
	}
	
	

}
