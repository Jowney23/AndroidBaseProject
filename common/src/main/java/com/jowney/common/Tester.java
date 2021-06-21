package com.jowney.common;

import android.util.Log;

import com.jowney.common.net.RetrofitMaster;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class Tester {
    private static final String TAG = "Tester";

    public static void main(String[] args) {
        Student student = new Student();
        Class sc = student.getClass();
        System.out.println("getName->" + sc.getName());
        System.out.println("getSimpleName->" + sc.getSimpleName());
        System.out.println("getCanonicalName->" + sc.getCanonicalName());
        System.out.println("getTypeName->" + sc.getTypeName());
        System.out.println("getPackage->" + sc.getPackage());
    }
}

class Student {
    private String name;
    private String age;

    protected String study() {
        String subject = "Mathematics";
        return subject;
    }
}