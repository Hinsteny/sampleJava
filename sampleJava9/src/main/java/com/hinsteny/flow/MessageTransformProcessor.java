package com.hinsteny.flow;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

/**
 * 消息转化处理器
 *
 * @author Hinsteny
 * @version $ID: MessageTransformProcessor 2018-09-28 13:44 All rights reserved.$
 */
public class MessageTransformProcessor<T, R> extends SubmissionPublisher<R> implements Processor<T, R> {

    private Function function;

    private Subscription subscription;

    public MessageTransformProcessor(Function<? super T, ? extends R> function) {
        super();
        this.function = function;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(T item) {
        submit((R) function.apply(item));
        subscription.request(1);
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    @Override
    public void onComplete() {
        close();
    }

}
