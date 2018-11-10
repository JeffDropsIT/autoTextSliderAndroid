package com.devdesign.jeffdropsit.autoslider;


import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.devdesign.jeffdropsit.autoslider.adapters.SliderAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    List<String> colorName;

    ViewPager viewPager;
    TabLayout indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager =  findViewById(R.id.viewPager);
        indicator = findViewById(R.id.indicator);


        colorName = new ArrayList<>();
        colorName.add("Join Us Here At ZipTown And Become Part Of Safe Community Of Daily Commuters, That Use Lifts Clubs To Travel To And From Work.");
        colorName.add("ZipTown Is Safe Free, And Easy to use even your Grandma Can Use It.");
        colorName.add("In Fact Share ZipTown With Her And All Your Friends To Help Grow The Platform, For All People To Use.");

        viewPager.setAdapter(new SliderAdapter(this, colorName));
        indicator.setupWithViewPager(viewPager, true);

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 4000);
    }


    private class SliderTimer extends TimerTask {

        private Handler handler = new Handler();
        private Runnable runnable;
        private void reverseSlider(){
            if (viewPager.getCurrentItem() > 0){
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }else{
                viewPager.setCurrentItem(0);
            }


        }
        @Override
        public void run() {
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager.getCurrentItem() < colorName.size() - 1) {

                        handler.removeCallbacks(runnable);
                        handler.removeMessages(0);
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    } else {

                        handler.postDelayed( runnable = new Runnable() {
                            public void run() {
                                reverseSlider();
                                handler.postDelayed(runnable, 500);
                            }
                        }, 500);

                        }

                    }

            });
        }
    }
}
