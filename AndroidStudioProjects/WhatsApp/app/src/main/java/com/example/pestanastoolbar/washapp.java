package com.example.pestanastoolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

public class washapp extends AppCompatActivity {
    private chats[] lista;

    private ListView vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_washapp);
        //Asignamos al ViewPager el PageAdapter
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new PageAdaprter());
// Se asigna al TabLayout el ViewPager y configura el modo de las pestañas
        TabLayout tabLayout= findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
//Añadimos iconos a las pestañas
        tabLayout.getTabAt(0).setIcon(R.drawable.camara);



    }

    class PageAdaprter extends PagerAdapter {
        private LinearLayout pestana0;
        private LinearLayout pestana1;
        private LinearLayout pestana2;
        private LinearLayout pestana3;

        private final int[] pestanas ={R.string.tab0,R.string.tab1, R.string.tab2,
                R.string.tab3 };

        @Override
        public int getCount() {
            return 4;
        }
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            String s = getString(pestanas[position]);
            return s;
        }
        @NonNull
        @Override

        public Object instantiateItem(@NonNull ViewGroup container, int position){
            View page;

            switch (position){
                case 0:
                    if (pestana0 == null){
                        pestana0 = (LinearLayout)
                                LayoutInflater.from(washapp.this)
                                        .inflate(R.layout.tab0, container,false );
                    }
                    page= pestana0;
                    break;
                case 1:
                    if (pestana1 == null){
                        pestana1 = (LinearLayout)
                                LayoutInflater.from(washapp.this)
                                        .inflate(R.layout.tab1, container,false );

                    }
                    page= pestana1;
                    break;
                case 2:
                    if (pestana2 == null) {
                        pestana2 = (LinearLayout)
                                LayoutInflater.from(washapp.this)
                                        .inflate(R.layout.tab2,container,false);


                    }
                    page=pestana2;
                    break;
                default:
                    if (pestana3 == null) {
                        pestana3 = (LinearLayout)
                                LayoutInflater.from(washapp.this)
                                        .inflate(R.layout.tab3,container,false);

                    }
                    page=pestana3;
                    break;

            }
            container.addView(page, 0);
            return page;

        }
        @Override
        public boolean isViewFromObject(@NonNull View view,
                                        @NonNull Object object) {
//return false;
            return view == object;
        }
        @Override
        public void destroyItem(@NonNull ViewGroup container,
                                int position, @NonNull Object object) {
            container.removeView((View)object);
        }
    }

}

