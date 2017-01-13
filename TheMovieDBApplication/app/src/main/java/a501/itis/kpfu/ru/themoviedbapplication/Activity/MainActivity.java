package a501.itis.kpfu.ru.themoviedbapplication.Activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import a501.itis.kpfu.ru.themoviedbapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_movies) {

                }
                if (tabId == R.id.tab_main) {

                }
                if (tabId == R.id.tab_series) {

                }
            }
        });
        bottomBar.setDefaultTab(R.id.tab_main);
    }
}
