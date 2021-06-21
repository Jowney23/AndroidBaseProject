package com.jowney.common;

import org.junit.Test;
import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        });

        Observable.just("爸爸打我").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Exception {
                return s.length();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });

    }
}