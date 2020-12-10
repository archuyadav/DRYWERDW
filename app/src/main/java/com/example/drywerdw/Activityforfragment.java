package com.example.drywerdw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

public class Activityforfragment extends AppCompatActivity {
    private ViewPager pager;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activityforfragment);
        pager = findViewById(R.id.pager);
        SliderAdapter adapter=new SliderAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

    }
    class SliderAdapter extends FragmentStatePagerAdapter {

        public SliderAdapter(FragmentManager fm) {
            super(fm);
        }


        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new fragment_two();
                case 1:
                    return new fragment_three();
                case 2:
                    return new fragment_four();
            }
            return null;
        }

        public int getCount() {
            return 3;
        }
    }
}
