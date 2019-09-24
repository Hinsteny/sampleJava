package com.hinsteny.flow;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * 消息订阅处理器
 *
 * @author Hinsteny
 * @version $ID: MessageSubscriber 2018-09-28 11:39 All rights reserved.$
 */
public class MessageSubscriber<T> implements Subscriber<T> {

    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        //a value of  Long.MAX_VALUE may be considered as effectively unbounded
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        System.out.println("Got : " + item);
        //a value of  Long.MAX_VALUE may be considered as effectively unbounded
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {

    }
}
