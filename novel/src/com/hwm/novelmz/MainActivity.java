package com.hwm.novelmz;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
public class MainActivity extends Activity {
	
	private ListView myList;
	ImageView imageView1;
	BookPageFactory pagefactory;
	Canvas mCurPageCanvas;
	SharedPreferences ferences ;
	
	private List<Map<String, Object>> mData;
	private String[] fileName = new String[]
			{"zw3","zw4","zw5","zw6","zw7","zw8","zw9","zw10",
			"zw11","zw12","zw13","zw14","zw15","zw16","zw17","zw18","zw19","zw10",
			"zw21","zw22","zw23","zw24","zw25","zw26","zw27","zw28","zw29","zw20",
			"zw31","zw32","zw33","zw34","zw35","zw36","zw37","zw38","zw39","zw30",
			"zw41","zw42","zw43","zw44","zw45","zw46","zw47","zw48","zw49","zw40",
			"zw51","zw52","zw53","zw54","zw55","zw56","zw57","zw58","zw59","zw50",
			"zw61","zw62","zw63","zw64","zw65","zw66","zw67","zw68","zw69","zw60",
			"zw71","zw72","zw73","zw74","zw75","zw76","zw77","zw78","zw79","zw70",
			"zw81","zw82","zw83","zw84","zw85","zw86","zw87","zw88","zw89","zw90",
			"zw91","zw92","zw93","zw94","zw95","zw96","zw97","zw98","zw99","zw100"
			};
	private String[] title = new String[]
			{"2010年高考0分作文之湖南卷 ",
			"2011年高考0分作文《拒绝平庸》",
			"2009·湖南卷：踮起脚尖","2010年高考上海0分作文","2003·全国卷： 韩非子(2)","《北京的符号》零分作文之北京人民喜迎沙尘暴","辽宁卷：关于明星代言","2006·上海卷：我想握住你的手(2)",
			"上海零分作文《和大自然的悄悄话》","重庆卷：我与故事(2)","江苏卷：品味时尚(1)","2005·湖北卷：人间词话（1）","2009·北京卷：我有一双隐形的翅膀(1)","天津卷：我说九零后(2)","安徽：《时间在流逝（2）》","广东卷：常识(1)","广东卷：常识(2)","11年高考零分作文之四川卷","2010年广东高考零分作文 与你为邻","2010年高考零分作文（之十一）"
			,"2008·广东卷：不要轻易说不","四川0分作文：《总有一种期待》","  2011四川高考零分作文之《总有一种期待—–我想找媳妇》"
			,"江苏卷：品味时尚(4)","湖南卷：踮起脚尖(1)","江苏——《拒绝平庸》：","2010年高考零分作文：哥玩的不是游戏，是寂寞","2010年高考零分作文：哥玩的不是游戏，是寂寞","福建卷：这也是一种历史(2)","2009·江苏卷：品味时尚(2)","江苏零分作文：《品味时尚 》","北京卷：我有一双隐形的翅膀(2)","2010山东高考作文题：生活品质，靠什么来支撑?",
			"《北京的符号》零分作文之“圆环套圆环娱乐","河南高考零分作文：兔子 你傻啊？！","高考0分作文--品味时尚（江苏）","2009·江苏卷：品味时尚(1)",
			"2009·北京卷：我有一双隐形的翅膀(4)","10年普通高等学校招生考试上海卷作文题"
			,"北京卷2009年高考作文题","【2003·全国卷：韩非子】","湖南卷：踮起脚尖(2)","2005·重庆：筷子与自嘲","2006·广州卷：雕刻心中的天使","《春哥写的》","2006·江西卷：燕子减肥","北京的0分作文","2006·湖北卷：三的启示","天津卷：我说九零后(1)","辽宁：《如何看待高晓松酒驾案》","江苏卷：品味时尚(2)"," 站在____门口"," 好猫吃鼠，坏猫吃鱼",
			"2010年江西高考0分作文","最美的青春","福建卷：这也是一种历史(1)","叽叽和嘎嘎","安徽零分作文：《弯道超越》",
			"2010高考湖南卷《早》零分作文","安徽：《时间在流逝》","2005·山东卷：双赢的智慧(1)","2007·安徽卷：提篮春光看妈妈"
			,"2007高考北京作文 《北京符号》","全国卷1作文题目：《期待长大 》","重庆卷：我与故事(1)","2006·福建卷：半命题作文","2009·浙江卷：半命题作文","江苏卷：品味时尚(3)","北京高考0分作文《我有一双隐形的翅膀》","2008·湖南卷：半命题作文","广东：《回到原点》","2006·江苏卷：人与路","2009·北京卷：我有一双隐形的翅膀(2)",
			"2009·北京卷：我有一双隐形的翅膀(3)","北京卷：我有一双隐形的翅膀(1)",
			"2008·江苏卷：好奇心","11年零分作文 ：《神马都是浮云，神马又都不是浮云》","湖北高考0分作文《站在___的门口》","2006·上海卷：我想握住你的手（1）"," 不要轻易说不",
			"2007·北京卷：半命题作文","北京零分作文：《北京的符号》之“北京房价”","山东：《行走》","2007·辽宁卷：我能","湖北：《旧书》","广东2008零分作文《北庄中学逃校案》","2010高考北京卷“0”分作文：仰望星空与脚踏实地","2005·湖北卷：人间词话(2)","2008·山东卷：春来草自青","2007·湖北卷：母语"
			,"2008·江西卷：半命题作文","2010年高考0分作文湖南卷：早上好，判卷老师","2010年浙江高考0分作文：角色转换之间","《北京的符号》零分作文之“牛X的北京公交”","福建2004年高考语文作文题：","山东卷：见证","上海卷：材料作文字体与人体","2005·山东卷：双赢的智慧(2)"	
			};
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		myList = (ListView)findViewById(R.id.myListView);
		mData = getData();
		ferences = getSharedPreferences("memory",0);
	    SharedPreferences.Editor editor = ferences.edit();
		MyAdapter adapter = new MyAdapter(this);
		myList.setAdapter(adapter);
		myList.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub				
				Intent intent1 = new Intent();			
				intent1.setClass(MainActivity.this, BookpageActivity.class);
				int g=0;
				ferences = getSharedPreferences("memory",0);
				g=ferences.getInt("note"+arg2, g);
				Bundle bl = new Bundle();
				//				bl.putString("path", p[arg2]);
				//				bl.putString("imageUrl", u[arg2]);
				bl.putString("fname",fileName[arg2]);
				bl.putInt("sha", g);
				bl.putInt("no", arg2);
				System.out.println("gggg"+g);
				//				bl.putString("fpath", fp);				
				intent1.putExtras(bl);
				startActivityForResult(intent1,1);
				//startActivity(intent1);
			}


		});
		  
        SatelliteMenu menu = (SatelliteMenu) findViewById(R.id.menu);
        
//		  Set from XML, possible to programmatically set        
//        float distance = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, getResources().getDisplayMetrics());
//        menu.setSatelliteDistance((int) distance);
//        menu.setExpandDuration(500);
//        menu.setCloseItemsOnClick(false);
//        menu.setTotalSpacingDegree(60);
        
        List<SatelliteMenuItem> items = new ArrayList<SatelliteMenuItem>();
        items.add(new SatelliteMenuItem(6, R.drawable.ic_1));
        items.add(new SatelliteMenuItem(5, R.drawable.ic_3));
        items.add(new SatelliteMenuItem(4, R.drawable.ic_4));
        items.add(new SatelliteMenuItem(3, R.drawable.ic_5));
        items.add(new SatelliteMenuItem(2, R.drawable.ic_6));
        items.add(new SatelliteMenuItem(1, R.drawable.menu_ic3));
//        items.add(new SatelliteMenuItem(5, R.drawable.sat_item));
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
					Intent it_3 = new Intent(MainActivity.this,MyLocationOver.class);
					startActivity(it_3);
					break;
				case 4:
//					Uri uri_5 = Uri.parse("smsto:10086");   
//					Intent it_5 = new Intent(Intent.ACTION_SENDTO, uri_5);   
//					it_5.putExtra("sms_body", "cwj");   
//					startActivity(it_5); 
					ferences = getSharedPreferences("memory",0);
				    SharedPreferences.Editor editor = ferences.edit();
				    editor.clear();
					break; 
					
				case 5: 			        
					Intent it_4 =new Intent();
					it_4.setClass(MainActivity.this, Transition3d.class);										
					startActivity(it_4);
					break;
				case 6:
					Uri uri_6 = Uri.parse("http://www.baidu.com");
					Intent it  = new Intent(Intent.ACTION_VIEW,uri_6);
					startActivity(it);
					
				} 
				Log.i("sat", "Clicked on " + id);
			}
		});
        
		ferences = getSharedPreferences("memory",Activity.MODE_PRIVATE);
		

	}
//	 @Override
//	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	        int result = data.getExtras().getInt("result");
//	        //得到新Activity 关闭后返回的数据
//	        System.out.println("result"+result);
//	        ferences = getSharedPreferences("memory",0);
//	        SharedPreferences.Editor editor = ferences.edit();
//			editor.putInt("note", result);
//			editor.commit();
//	    }
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		//AlarmReceiver.sendGetAdMessage(MainActivity.this);
		super.onDestroy();
	}
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();


		for(int i=0;i<98;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			//			 File file1 = new File(p[i]);
			//	        	if(file1.exists()){
			//	        		map.put("img", R.drawable.guren);
			//	        	}else{
			map.put("img", R.drawable.cartoon_folder);
		//	map.put("imgR", R.drawable.chevron_default);
			//	        	}
			map.put("title", title[i]);

			list.add(map);

		}

		return list;
	}
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//		switch (keyCode) {
//		case KeyEvent.KEYCODE_BACK:{
//			AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//			builder.setMessage("确定退出程序吗?");
//			builder.setPositiveButton("确定",new DialogInterface.OnClickListener(){
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//					MainActivity.this.finish();
//					
//				}
//			});
//			builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//			builder.show();
//		}
//		return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	public final class ViewHolder{
		public ImageView img,imgR;
		public TextView title;
		public TextView info;

	}

	public class MyAdapter extends BaseAdapter{

		private LayoutInflater mInflater;


		public MyAdapter(Context context){
			this.mInflater = LayoutInflater.from(context);
		}
		public int getCount() {
			// TODO Auto-generated method stub
			return mData.size();
		}

		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = null;
			if (convertView == null) {

				holder=new ViewHolder();  

				convertView = mInflater.inflate(R.layout.vlist, null);
				holder.img = (ImageView)convertView.findViewById(R.id.img);
				//holder.imgR = (ImageView)convertView.findViewById(R.id.imgR);
				holder.title = (TextView)convertView.findViewById(R.id.title);			
				convertView.setTag(holder);

			}else {

				holder = (ViewHolder)convertView.getTag();
			}


			holder.img.setBackgroundResource((Integer)mData.get(position)
					.get("img"));
//			holder.imgR.setBackgroundResource((Integer)mData.get(position)
//			.get("imgR"));
			holder.title.setText((String)mData.get(position).get("title"));
			//holder.info.setText((String)mData.get(position).get("info"));

			//			holder.viewBtn.setOnClickListener(new View.OnClickListener() {
			//
			//				
			//			});


			return convertView;
		}

	}




}