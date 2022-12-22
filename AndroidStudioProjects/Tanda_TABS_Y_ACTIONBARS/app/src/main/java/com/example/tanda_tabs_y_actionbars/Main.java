package com.example.tanda_tabs_y_actionbars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignamos al ViewPager el PageAdapter
        ViewPager viewPager = findViewById(R.id.ViewPager);
        viewPager.setAdapter(new PageAdaprter());

        // Se asigna al TabLayout el ViewPager y configura el modo de las pestañas
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);

        //Ponemos el icono de la camara
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);

    }
        //CLASE PAGE ADAPTER
        class PageAdaprter extends PagerAdapter {
            private LinearLayout pestana1;
            private LinearLayout pestana2;
            private LinearLayout pestana3;
            private final int[] pestanas = {R.string.tab1, R.string.tab2};

            @Override
            public int getCount() {
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                String s = getString(pestanas[position]);
                return s;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                View page=null;
                switch (position) {
                    case 0:
                        if (pestana1 == null) {
                            pestana1 = (LinearLayout)
                                    LayoutInflater.from(Main.this)
                                            .inflate(R.layout.page0, container, false);
                        }
                        page = pestana1;
                        break;
                    default:
                        if (pestana2 == null) {
                            pestana2 = (LinearLayout)
                                    LayoutInflater.from(Main.this)
                                            .inflate(R.layout.page1, container, false);
                        }
                        page = pestana2;
                        break;
//                    default:
////                        if (pestana3 == null) {
////                            pestana3 = (LinearLayout)
////                                   // LayoutInflater.from(PestanasEj3.this)
////                                   //         .inflate(R.layout.tab3,container,false);
////
////                        }
//                        page = null;
//                        break;
                }
                container.addView(page, 0);
                return page;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
               // return false;
                return view == object;
            }
            @Override
            public void destroyItem(@NonNull ViewGroup container,
                                    int position, @NonNull Object object) {
                container.removeView((View)object);
            }

        }

}
