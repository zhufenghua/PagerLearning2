package com.swsc.pagerlearning2;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends Activity {
    private ViewPager viewPager;
    private LinearLayout ll;
    private View redPoint;

    private List<View> listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        //注入要应的值 view
        viewPager=(ViewPager) findViewById(R.id.viewpager);
        ll = (LinearLayout) findViewById(R.id.ll);
        redPoint = (View) findViewById(R.id.red_point);
        onInit();
    }
    //初始化pager
    private void onInit(){
        viewPager.setAdapter(new WecomePagerAdpater());
        //加入小圆点
        for(int i = 0 ; i<listView.size();i++){
            View view= new View(this);
            view.setBackgroundResource(R.drawable.welcome_point_gray);
            int px = DensityUtils.dpi2px(this,20);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(px,px);
            view.setLayoutParams(param);


            if (i>0){
                param.leftMargin = px;
            }
            ll.addView(view);
            Log.i("first",view.getLeft()+"");

            Log.i("red",redPoint.getLeft()+"");
        }

    }

    public  void startBtnClick(View view){

    }
    private int[] ids = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
    private class WecomePagerAdpater extends PagerAdapter{

        public WecomePagerAdpater() {

            listView = new ArrayList<View>();
            for (int i =0; i<ids.length;i++){
                ImageView lview=new ImageView(WelcomeActivity.this);
                lview.setBackgroundResource(ids[i]);
                listView.add(lview);
            }

            //第四个
            View btnView=    View.inflate(WelcomeActivity.this,R.layout.start_btn_page,null);
                        listView.add(btnView);




       }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = listView.get(position);
            container.addView(view);
            Log.i("instantiate",position+" ");
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.i("destroy",position+""+object+"");
          //  super.destroyItem(container, position, object);

            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public int getCount() {
            return listView.size();
        }
    }
}
