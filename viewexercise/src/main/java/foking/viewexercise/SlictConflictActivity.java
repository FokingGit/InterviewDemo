package foking.viewexercise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.Locale;

public class SlictConflictActivity extends AppCompatActivity {
    private ListView listview;
    private ScrollView scrollview;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_slict_conflict);

        listview = (ListView) findViewById(R.id.listview);
        scrollview = (ScrollView) findViewById(R.id.scrollview);

        final String[] listViewDatas = new String[10];
        for (int i = 0; i < listViewDatas.length; i++) {
            listViewDatas[i] = String.format(Locale.getDefault(), "我是第%d个条目", i);
        }

        listview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    listview.requestDisallowInterceptTouchEvent(true);
                }
                return listview.onTouchEvent(event);
            }
        });

        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listViewDatas);
        listview.setAdapter(stringArrayAdapter);
    }

}
