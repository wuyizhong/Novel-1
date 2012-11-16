package com.hwm.novelmz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class BookpageActivity extends Activity {
	/** Called when the activity is first created. */
	private BookPageWidget mPageWidget;
	private String fname=null;
	private Intent intent;
	int i=0,j=0,k=0;
	private Bundle bl;
	SharedPreferences ferences ;
	//当前页、下一页图像
	Bitmap mCurPageBitmap, mNextPageBitmap;
	Canvas mCurPageCanvas, mNextPageCanvas;
	BookPageFactory pagefactory;


	

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		FileUtils fileUtils=new FileUtils();
		intent=this.getIntent();
		//获取Intent中的Bundle数据
		bl=intent.getExtras();
		 fname=bl.getString("fname");
		 i=bl.getInt("sha");
		 j=bl.getInt("no");
		try {
			//Return an AssetManager instance for your application's package
			InputStream is = getAssets().open(fname+".txt");
			fileUtils.write2SDFromInput("", "lingfen.txt", is);
//			int size = is.available();
//
//			// Read the entire asset into a local byte buffer.
//			byte[] buffer = new byte[size];
//			is.read(buffer);
//			is.close();
//
//			// Convert the buffer into a string.
//			String text1 = new String(buffer, "UTF-8");

			// Finally stick the string into the text view.
//			TextView tv = (TextView) findViewById(R.id.text);
//			tv.setText(text);
			
		} catch (IOException e) {
			// Should never happen!
			throw new RuntimeException(e);
		}
	//	String text=getResources().getString(R.string.changshi_1);

		
		//去掉标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		mPageWidget = new BookPageWidget(this);
		setContentView(mPageWidget);

		mCurPageBitmap = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		mNextPageBitmap = Bitmap.createBitmap(480, 800, Bitmap.Config.ARGB_8888);
		mCurPageCanvas = new Canvas(mCurPageBitmap);
		mNextPageCanvas = new Canvas(mNextPageBitmap);
		pagefactory = new BookPageFactory(480, 800);
		pagefactory.setBgBitmap(BitmapFactory.decodeResource(this.getResources(), R.drawable.cartoon_parchment_paper));

		try {
			
			
			
			pagefactory.openbook("/sdcard/lingfen.txt");
			pagefactory.noteHere(i);
			pagefactory.onDraw(mCurPageCanvas);
			
		} catch (IOException e1) {
			e1.printStackTrace();
			Toast.makeText(this, "电子书不存在,请将《test.txt》放在SD卡根目录下",
					Toast.LENGTH_SHORT).show();
		}

		mPageWidget.setBitmaps(mCurPageBitmap, mCurPageBitmap);
		mPageWidget.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent e) {
				boolean ret=false;
				int n=0;
				if (v == mPageWidget) {
					if (e.getAction() == MotionEvent.ACTION_DOWN) {
						mPageWidget.abortAnimation();
						mPageWidget.calcCornerXY(e.getX(), e.getY());

						pagefactory.onDraw(mCurPageCanvas);
						if (mPageWidget.DragToRight()) {
							try {
								pagefactory.prePage();
							} catch (IOException e1) {
								e1.printStackTrace();
							}						
							if(pagefactory.isfirstPage()) return false;
							pagefactory.onDraw(mNextPageCanvas);
						} else {
							try {
								pagefactory.nextPage();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							if(pagefactory.islastPage()) return false;
							pagefactory.onDraw(mNextPageCanvas);
						}
						mPageWidget.setBitmaps(mCurPageBitmap, mNextPageBitmap);
					}
					ret = mPageWidget.doTouchEvent(e);
					n=pagefactory.sento();
					System.out.println("ttt"+n);
					//k=send(n);
					ferences = getSharedPreferences("memory",0);
			        SharedPreferences.Editor editor = ferences.edit();
					editor.putInt("note"+j, n);
					editor.commit();
//					Intent intent = new Intent();
//					intent.putExtra("result",n);
//					BookpageActivity.this.setResult(-1, intent);
					return ret;
				}
				return false;
				
			}

		});
		
	}

	public int send(int t){
		int i=t;
		return i;
	}



//	@Override
//	protected void onPause() {
//		// TODO Auto-generated method stub
//		ferences = getSharedPreferences("memory",0);
//        SharedPreferences.Editor editor = ferences.edit();
//		editor.putInt("note"+j, k);
//		editor.commit();
//		super.onStop();
//	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
//		int n=i;
//		SharedPreferences ferences = getSharedPreferences("memory",Activity.MODE_PRIVATE);
//		SharedPreferences.Editor editor = ferences.edit();
//		editor.putInt("note", n);
//		editor.commit();
		super.onDestroy();
	}
	

}