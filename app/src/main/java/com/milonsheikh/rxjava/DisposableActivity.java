package com.milonsheikh.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class DisposableActivity extends AppCompatActivity {

    private Observable<Integer> myObservable;
    private Observer<Integer> myObserver;
    private Disposable disposable;

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposable);

         tvResult=findViewById(R.id.tv_result);

        myObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        myObserver = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                System.out.println("======= onSubscribe =======");
            }

            @Override
            public void onNext(Integer integer) {
                tvResult.setText(integer.toString());
                System.out.println("======= onNext =======");
                System.out.println("Integer=======>>> " + integer);
                if (integer == 8) {
                    System.out.println("======= Disposed =======");
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {
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