package com.hinsteny;

import java.io.File;
import java.io.FileReader;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import org.junit.Test;


/**
 * PracticeJavaScript.java
 * 
 * @author: Hinsteny
 * @date: 2015年10月9日
 * @copyright: 2015 All rights reserved.
 * 
 */
public class PracticeJavaScript {
	
	@Test
	public void testJS() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        System.out.println("begain testJS:");
        
        engine.eval("var i = 1;");
        engine.eval("i+='love';");
        engine.eval("print(i)");
        engine.eval("print(new Date());");

        ScriptEngine engine1 = engineManager.getEngineByName("nashorn");
        try {
            engine1.eval("print(i)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        engine.eval("function test(){print('love');}");
        engine.eval("test();");

        ScriptObjectMirror testFunc = (ScriptObjectMirror) engine.eval("test");
        testFunc.call(null);

        Object jsDate = engine.eval("new Date()");
        System.out.println("jsDate: "+jsDate);

        Invocable invocable = (Invocable) engine;
        invocable.invokeMethod(jsDate, "setDate", 1);
        System.out.println(jsDate);
        invocable.invokeFunction("test");

    }
    
    @Test
    public void testJava() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        File javaExtendJS = new File("src/test/resources/jstest/sample/sample.js");
        System.out.println("begain testJava:");
        engine.eval(new FileReader(javaExtendJS));
    }

    @Test
    public void testJavaType() throws Exception {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");
        File streamJS = new File("src/test/resources/jstest/sample/stream.js");
        System.out.println("begain testJavaType:");
        engine.eval(new FileReader(streamJS));
    }
}
