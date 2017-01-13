package a501.itis.kpfu.ru.themoviedbapplication.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;

import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.List;

import a501.itis.kpfu.ru.themoviedbapplication.interfaces.TaskListenerInterface;
import a501.itis.kpfu.ru.themoviedbapplication.R;

public class MainActivity extends AppCompatActivity implements TaskListenerInterface {

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

    @Override
    public void onTaskFinish(List list, int id) {
        switch (id) {
            case 2:
                break;
            case 1:
                break;
            case 3:
                break;
        }
    }

    @Override
    public void onTaskStarted() {

    }
}
