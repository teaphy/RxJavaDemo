package teaphy.rxjavademo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;

public class AtyFilter extends AppCompatActivity {

    @BindView(R.id.btn_filter)
    Button btnFilter;
    @BindView(R.id.btn_take)
    Button btnTake;
    @BindView(R.id.btn_takeFirst)
    Button btn_takeFirst;
    @BindView(R.id.btn_takeLast)
    Button btnTakeLast;
    @BindView(R.id.btn_distinct)
    Button btnDistinct;
    @BindView(R.id.btn_first)
    Button btnFirst;
    @BindView(R.id.btn_last)
    Button btnLast;
    @BindView(R.id.btn_skip)
    Button btnSkip;
    @BindView(R.id.btn_skipLst)
    Button btnSkipLst;
    @BindView(R.id.btn_elementAt)
    Button btnElementAt;
    @BindView(R.id.btn_sampling)
    Button btnSampling;
    @BindView(R.id.btn_timeout)
    Button btnTimeout;
    @BindView(R.id.btn_debounce)
    Button btnDebounce;
    @BindView(R.id.btn_distinctUntilChanged)
    Button btn_distinctUntilChanged;
    @BindView(R.id.rv_filter)
    RecyclerView rvFilter;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;

    List<Student> mLists;
    AdaStu mAdaStu;
    Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_filter);

        ButterKnife.bind(this);

        mHandler = new Handler();

        mLists = new ArrayList<>();
        mLists.add(new Student("A11", "20", "1101"));
        mLists.add(new Student("A12", "20", "1102"));
        mLists.add(new Student("A13", "20", "1103"));
        mLists.add(new Student("B11", "20", "1201"));
        mLists.add(new Student("B12", "30", "1202"));
        mLists.add(new Student("B13", "20", "1203"));
        mLists.add(new Student("S10", "28", "1301"));
        mLists.add(new Student("E10", "30", "1401"));
        mLists.add(new Student("F10", "26", "1501"));

        mAdaStu = new AdaStu(this);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvFilter.setLayoutManager(manager);
        rvFilter.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rvFilter.setAdapter(mAdaStu);

        mAdaStu.addData(mLists);
        manager.scrollToPosition(0);

        srl.setOnRefreshListener(() -> {
            mHandler.postDelayed(() -> {
                mAdaStu.addData(mLists);
                srl.setRefreshing(false);
            }, 1000);
        });
    }

    @OnClick({R.id.btn_filter, R.id.btn_take, R.id.btn_takeFirst, R.id.btn_takeLast, R.id.btn_distinct, R.id.btn_first,
            R.id.btn_last, R.id.btn_skip, R.id.btn_skipLst, R.id.btn_distinctUntilChanged})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_filter:
                doFilter();
                break;
            case R.id.btn_take:
                doTake();
                break;
            case R.id.btn_takeFirst:
                doTakeFirst();
                break;
            case R.id.btn_takeLast:
                doTakeLast();
                break;
            case R.id.btn_distinct:
                doDistinct();
                break;
            case R.id.btn_first:
                doFirst();
                break;
            case R.id.btn_last:
                doLast();
                break;
            case R.id.btn_skip:
                doSkip();
                break;
            case R.id.btn_skipLst:
                doSkipLast();
                break;
            case R.id.btn_distinctUntilChanged:
                Toast.makeText(this, "doDistinctUntilChanged", Toast.LENGTH_SHORT).show();
                doDistinctUntilChanged();
                break;
            default:
                break;
        }
    }

    private void doFilter() {
//        Observable.from(mLists)
//                .filter(student -> student.getName().startsWith("A"))
//                .subscribe(new Observer<Student>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Student student) {
//                        mAdaStu.addData(student);
//                    }
//                });
        mAdaStu.clearAll();
        Observable.from(mLists)
                .filter(new Func1<Student, Boolean>() {
                    @Override
                    public Boolean call(Student student) {
                        return student.getName().startsWith("A");
                    }
                })
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Student student) {
                        mAdaStu.addData(student);
                    }
                });
    }

    private void doTake() {
        mAdaStu.clearAll();
        Observable.from(mLists)
                .take(5)
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Student student) {
                        mAdaStu.addData(student);
                    }
                });
    }

    private void doTakeFirst() {
        mAdaStu.clearAll();
        Observable.from(mLists)
                .takeFirst(new Func1<Student, Boolean>() {
                    @Override
                    public Boolean call(Student student) {
                        return student.getName().startsWith("B");
                    }
                })
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {
                        Log.i("123", "doTakeFirst - onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("123", "doTakeFirst - onError");
                    }

                    @Override
                    public void onNext(Student student) {
                        Log.i("123", "doTakeFirst - onNext");
                        mAdaStu.addData(student);
                    }
                });
    }

    private void doTakeLast() {
        mAdaStu.clearAll();
        Observable.from(mLists)
                .takeLast(5)
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Student student) {
                        mAdaStu.addData(student);
                    }
                });
    }

    private void doDistinct() {
        mAdaStu.clearAll();
        Observable<Student> fullOfDuplicates = Observable.from(mLists)
                .take(3)
                .repeat(3);
        fullOfDuplicates.distinct()
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Student student) {
                        mAdaStu.addData(student);
                    }
                });
    }

    private void doFirst() {

    }

    private void doLast() {
    }

    private void doSkip() {
    }

    private void doSkipLast() {

    }

    private void doDistinctUntilChanged() {
        mAdaStu.clearAll();
        Observable.from(mLists)
                .distinctUntilChanged(new Func1<Student, String>() {
                    @Override
                    public String call(Student student) {
                        return student.getAge();
                    }
                })
                .subscribe(new Observer<Student>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Student student) {
                        mAdaStu.addData(student);
                    }
                });
    }
}
