package com.hwm.novelmz;
import java.io.IOException;
import java.io.InputStream;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * This sample application shows how to use layout animation and various
 * transformations on views. The result is a 3D transition between a
 * ListView and an ImageView. When the user clicks the list, it flips to
 * show the picture. When the user clicks the picture, it flips to show the
 * list. The animation is made of two smaller animations: the first half
 * rotates the list by 90 degrees on the Y axis and the second half rotates
 * the picture by 90 degrees on the Y axis. When the first half finishes, the
 * list is made invisible and the picture is set visible.
 */
public class Transition3d extends Activity implements
        AdapterView.OnItemClickListener, View.OnClickListener {
    private ListView mPhotosList;
    private ViewGroup mContainer;
    private int i=0;
  //  private ImageView mImageView;
    private TextView mText;
    private ScrollView mScrollView;
    // Names of the photos we show in the list
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
	private String[] PHOTOS_NAMES = new String[]
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

    // Resource identifiers for the photos we want to display
//    private static final int[] PHOTOS_RESOURCES = new int[] {
//    	R.string.pingweishishang2009,
//    	R.string.pingweishishang2009_1,
//    	R.string.wandao,
//    	R.string.pingweishishang2009,
//    	R.string.changshi_1,
//    	R.string.pingweishishang2009_1
//  
////            R.drawable.photo1,
////            R.drawable.photo2,
////            R.drawable.photo3,
////            R.drawable.photo4,
////            R.drawable.photo5,
////            R.drawable.photo6
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

		

        setContentView(R.layout.animations_main_screen);

        mPhotosList = (ListView) findViewById(android.R.id.list);
       // mImageView = (ImageView) findViewById(R.id.picture);
        mContainer = (ViewGroup) findViewById(R.id.container);
        mText =(TextView)findViewById(R.id.text);
        mScrollView =(ScrollView)findViewById(R.id.scrollcView);
        // Prepare the ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, PHOTOS_NAMES);

        mPhotosList.setAdapter(adapter);
        mPhotosList.setOnItemClickListener(this);

        // Prepare the ImageView
//        mImageView.setClickable(true);
//        mImageView.setFocusable(true);
//        mImageView.setOnClickListener(this);
        mScrollView.setClickable(true);
        mScrollView.setFocusable(true);
        mScrollView.setOnClickListener(this);
        mText.setClickable(true);
        mText.setFocusable(true);
        mText.setOnClickListener(this);

        // Since we are caching large views, we want to keep their cache
        // between each animation
        mContainer.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
    }

    /**
     * Setup a new 3D rotation on the container view.
     *
     * @param position the item that was clicked to show a picture, or -1 to show the list
     * @param start the start angle at which the rotation must begin
     * @param end the end angle of the rotation
     */
    private void applyRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = mContainer.getWidth() / 2.0f;
        final float centerY = mContainer.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(position));

        mContainer.startAnimation(rotation);
    }

    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        // Pre-load the image then start the animation
try {
			
			//Return an AssetManager instance for your application's package
			InputStream is = getAssets().open(fileName[position]+".txt");
			int size = is.available();

			// Read the entire asset into a local byte buffer.
			byte[] buffer = new byte[size];
			is.read(buffer);
			is.close();

			// Convert the buffer into a string.
			String text = new String(buffer, "UTF-8");

			// Finally stick the string into the text view.
			mText.setText(text);
	        applyRotation(position, 0, 90);
		} catch (IOException e) {
			// Should never happen!
			throw new RuntimeException(e);
		}
    	
    }

    public void onClick(View v) {
        applyRotation(-1, 180, 90);
    }

    /**
     * This class listens for the end of the first half of the animation.
     * It then posts a new action that effectively swaps the views when the container
     * is rotated 90 degrees and thus invisible.
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        private DisplayNextView(int position) {
            mPosition = position;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            mContainer.post(new SwapViews(mPosition));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     */
    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            mPosition = position;
        }

        public void run() {
            final float centerX = mContainer.getWidth() / 2.0f;
            final float centerY = mContainer.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            
            if (mPosition > -1) {
                mPhotosList.setVisibility(View.GONE);
                mScrollView.setVisibility(View.VISIBLE);
                mScrollView.requestFocus();

                rotation = new Rotate3dAnimation(90, 360, centerX, centerY, 310.0f, false);
            } else {
            	mScrollView.setVisibility(View.GONE);
                mPhotosList.setVisibility(View.VISIBLE);
                mPhotosList.requestFocus();

                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            }

            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            mContainer.startAnimation(rotation);
        }
    }

}
