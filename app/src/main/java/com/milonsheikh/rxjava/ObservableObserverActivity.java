package com.milonsheikh.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class ObservableObserverActivity extends AppCompatActivity {

    private String getStr = "RxJava Observable Observer";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observable_observer);


        tvResult=findViewById(R.id.tv_result);

        myObservable=Observable.just(getStr);

        myObserver=new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("======= onSubscribe =======");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("======= onNext =======");
                System.out.println("=======>>> "+s);
                tvResult.setText(s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("======= onError =======");
            }

            @Override
            public void onComplete() {
                System.out.println("======= onComplete =======");
            }
        };
        myObservable.subscribe(myObserver);
    }
}