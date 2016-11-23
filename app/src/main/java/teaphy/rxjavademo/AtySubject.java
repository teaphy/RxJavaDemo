package teaphy.rxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class AtySubject extends AppCompatActivity {

    @BindView(R.id.btn_publishSubject)
    Button btn_publishSubject;
    @BindView(R.id.btn_behaviorSubject)
    Button btn_behaviorSubject;
    @BindView(R.id.btn_replaySubjectt)
    Button btn_replaySubjectt;
    @BindView(R.id.btn_asyncSubject)
    Button btn_asyncSubject;

    PublishSubject<String> publishSubject;
    BehaviorSubject<String> behaviorSubject;
    ReplaySubject<Integer> replaySubject;
    AsyncSubject<Integer> asyncSubject;

    Subscription subReplay;

    int cout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_subject);

        ButterKnife.bind(this);

        createPublishSubject();

        createBehaviorSubject();

        createReplaySubject();

        createAsyncSubject();
    }

    @OnClick(R.id.btn_publishSubject)
    protected void doPublish() {

        publishSubject.onNext("Hello RxJava");

        //publishSubject.onCompleted();
        publishSubject.doOnCompleted(new Action0() {
            @Override
            public void call() {
                Log.i("123", "publishSubject - doOnCompleted");
            }
        });

        publishSubject.onNext("Hello RxJava");
    }

    private void createPublishSubject() {
        publishSubject = PublishSubject.create();
        Subscription subscription = publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.i("123", "PublishSubject - onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.i("123", "PublishSubject: " + s);
            }
        });
    }

    @OnClick(R.id.btn_behaviorSubject)
    protected void doBehavior() {

        behaviorSubject.onNext("Hello RxJava");
    }

    private void createBehaviorSubject() {
        behaviorSubject = BehaviorSubject.create("First");

        Subscription subscription = behaviorSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                cout++;
                Log.i("123", s + " | " + cout);
            }
        });
    }

    private void createReplaySubject() {
        replaySubject = ReplaySubject.create();
        replaySubject.onNext(1);
        replaySubject.onNext(2);
        replaySubject.onNext(3);
        replaySubject.onNext(4);
        replaySubject.onCompleted();

    }

    @OnClick(R.id.btn_replaySubjectt)
    protected void doReplaySubject() {
        Observer<Integer> observer_1 = new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("123", "observer_1" + " - " + integer);
            }
        };

        Observer<Integer> observer_2 = new Observer<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i("123", "observer_2" + " - " + integer);
            }
        };

        replaySubject.subscribe(observer_1);
        replaySubject.subscribe(observer_2);
    }

    private void createAsyncSubject() {
        asyncSubject = AsyncSubject.create();
    }


    @OnClick(R.id.btn_asyncSubject)
    protected void doAsyncSubject() {
        Observer<Integer> observer = new Observer<Integer>() {

            public void onCompleted() {

            }


            public void onError(Throwable e) {

            }

            public void onNext(Integer integer) {
                Log.i("123", "AsyncSubject - observer:" + integer);
            }
        };

        asyncSubject.subscribe(observer);
        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);
        asyncSubject.onNext(4);
        asyncSubject.onCompleted();

        Toast.makeText(this, "doAsyncSubject", Toast.LENGTH_SHORT).show();
    }
}
