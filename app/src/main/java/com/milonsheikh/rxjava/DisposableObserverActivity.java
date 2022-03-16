package com.milonsheikh.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class DisposableObserverActivity extends AppCompatActivity {
    private Observable<Integer> myObservable1;
    private DisposableObserver<Integer> myObserver1;

    private Observable<Integer> myObservable2;
    private DisposableObserver<Integer> myObserver2;

    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disposable_observer);

        tvResult = findViewById(R.id.tv_result);

        myObservable1 = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        myObservable2 = Observable.just(21,22,23,24,25);

        myObserver1 = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                tvResult.setText(integer.toString());
                System.out.println("======= myObserver1 onNext =======");
                System.out.println("myObserver1 Integer=======>>> " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("======= myObserver1 onError =======");
            }

            @Override
            public void onComplete() {
                System.out.println("======= myObserver1 onComplete =======");
            }
        };
        myObserver2 = new DisposableObserver<Integer>() {
            @Override
            public void onNext(Integer integer) {
                System.out.println("======= myObserver2 onNext =======");
                System.out.println("myObserver2 Integer=======>>> " + integer);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("======= myObserver2 onError =======");
            }

            @Override
            public void onComplete() {
                System.out.println("======= myObserver2 onComplete =======");
            }
        };

        myObservable1.subscribe(myObserver1);
        myObservable2.subscribe(myObserver2);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        myObserver1.dispose();
        myObserver2.dispose();
    }
}