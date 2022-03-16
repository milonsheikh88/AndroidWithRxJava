package com.milonsheikh.rxjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SchedulersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedulers);

        TextView tvResult = findViewById(R.id.tv_result);

        Observable.just("RxJava", "Schedulers")
                .doOnNext(item -> {
                    System.out.println("Emitting item: " + item + " => " + Thread.currentThread().getName());
                    tvResult.setText(item);
                })
                .subscribeOn(Schedulers.io())
                .map(item -> {
                    System.out.println("Mapping item: " + item + " => " + Thread.currentThread().getName());
                    return item.length();
                })
                .observeOn(Schedulers.computation())
                .filter(item -> {
                    System.out.println("Filtering item: " + item + " => " + Thread.currentThread().getName());
                    return item == 10;
                })
                .subscribe(item -> System.out.println("Consuming item ===>>> " + item + " => " + Thread.currentThread().getName()));
    }
}