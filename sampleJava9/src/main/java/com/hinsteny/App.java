package com.hinsteny;

/**
 * @author Hinsteny
 * @date 2017-09-28
 * @copyright: 2017 All rights reserved.
 */
public class App {

    public static void main(String[] args) {
        System.err.println("Hello Java-9!");
        System.err.println("java.home: " + System.getProperty("java.home"));
        System.err.println("java.specification.version: " + System.getProperty("java.specification.version"));
        System.err.println("java.vm.version: " + System.getProperty("java.vm.version"));
        System.err.println("java.vm.info: " + System.getProperty("java.vm.info"));
    }
}
