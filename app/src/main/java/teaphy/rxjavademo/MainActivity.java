package teaphy.rxjavademo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_create)
    Button btn_create;
    @BindView(R.id.btn_subject)
    Button btn_subject;
    @BindView(R.id.btn_filter)
    Button btn_filter;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Log.i("123", "dip2px: " + DensityUtil.dip2px(this, 1));
        DensityUtil.px2dip(this, 9);
        Log.i("123", "px2dip: " + DensityUtil.px2dip(this, 9));
    }

    @OnClick(R.id.btn_subject)
    protected void doSubject() {
        Intent intent = new Intent(this, AtySubject.class);
        startActivity(intent);
    }


    @OnClick(R.id.btn_filter)
    protected void doFilter() {
        Intent intent = new Intent(this, AtyFilter.class);
        startActivity(intent);
    }
}
