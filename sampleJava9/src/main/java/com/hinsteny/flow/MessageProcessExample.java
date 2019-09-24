package com.hinsteny.flow;

import java.util.Arrays;
import java.util.concurrent.SubmissionPublisher;

/**
 * 消息处理案例
 * @author Hinsteny
 * @version $ID: MessageProcessExample 2018-09-28 13:46 All rights reserved.$
 */
public class MessageProcessExample {

    public static void main(String[] args) {
        simplePublicAndSubscriber();
    }

    /**
     * 简单的消息发布与订阅处理案例
     */
    private static void simplePublicAndSubscriber() {
        //Create Publisher
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        //Register Subscriber
        MessageSubscriber<String> subscriber = new MessageSubscriber<>();
        publisher.subscribe(subscriber);

        //Publish items
        System.out.println("Publishing Items...");
        String[] items = {"1", "x", "2", "x", "3", "x"};
        Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
        publisher.close();
    }

    private static void processPublicAndSubscriber() {
        //Create Publisher
        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        //Create Processor and Subscriber
        MessageFilterTransformProcessor<String, String> filterProcessor = new MessageFilterTransformProcessor<>(s -> s.equals("x"));

        MessageTransformProcessor<String, Integer> transformProcessor = new MessageTransformProcessor<>(s -> Integer.parseInt(s));

        MessageSubscriber<Integer> subscriber = new MessageSubscriber<>();

        //Chain Processor and Subscriber
        publisher.subscribe(filterProcessor);
        filterProcessor.subscribe(transformProcessor);
        transformProcessor.subscribe(subscriber);

        System.out.println("Publishing Items...");
        String[] items = {"1", "x", "2", "x", "3", "x"};
        Arrays.asList(items).stream().forEach(i -> publisher.submit(i));
        publisher.close();
    }
}
