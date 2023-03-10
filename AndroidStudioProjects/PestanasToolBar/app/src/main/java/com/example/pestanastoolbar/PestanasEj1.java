package com.example.pestanastoolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

public class PestanasEj1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pestanas_ej1);

        Resources res = getResources();
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("TAB1",
                res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAB2",
                res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
        tabs.setCurrentTab(0);
        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Log.i("AndroidTabsDemo","Pulsada pestaña: "+s);
                Toast.makeText(getApplicationContext(), "Pulsada pestaña: "+s, Toast.LENGTH_LONG).show();
            }
        });
    }
}