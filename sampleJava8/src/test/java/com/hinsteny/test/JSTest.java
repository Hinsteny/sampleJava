package com.hinsteny.test;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Test;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.newBufferedReader;

public class JSTest {
	private final String baseDirStr = "src/test/resources/jstest/";
	private final Path basePath = Paths.get("src/test/resources/jstest/");


	@Test
	public void testGlobal() throws Exception{
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine1 = engineManager.getEngineByName("nashorn");

		engine1.eval("var i = 0");
		engine1.eval("i += 3");
		engine1.eval("print(i)");

		ScriptEngine engine2 = engineManager.getEngineByName("nashorn");
		engine2.eval("var x = \"World\"");
		engine2.eval("var str = \"Hello, ${x}\"");
		engine2.eval("print(str)");

	}


	@Test
	public void testMustache() throws Exception {
		String mustacheBaseDir = baseDirStr + "mustache/";

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Invocable invocable = (Invocable) engine;

		File mustacheJS = new File(mustacheBaseDir,"mustache.js");

		engine.eval(new FileReader(mustacheJS));

		String dataJSON = new String(Files.readAllBytes(Paths.get(mustacheBaseDir, "data.json")));
		String template = new String(Files.readAllBytes(Paths.get(mustacheBaseDir, "template.tmpl")));

		Object json = engine.eval("JSON");
		Object data = invocable.invokeMethod(json, "parse", dataJSON);

		Object mustache = engine.eval("Mustache");
		System.out.println(invocable.invokeMethod(mustache, "render", template, data));
	}


	@Test
	public void testHandlebars() throws Exception {

		String handlebarsBaseDir = baseDirStr + "handlebars/";

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Invocable invocable = (Invocable) engine;

		engine.eval(newBufferedReader(Paths.get(handlebarsBaseDir, "handlebars.js"), UTF_8));


		String dataJSON = new String(Files.readAllBytes(Paths.get(handlebarsBaseDir, "data.json")));
		String templateTMPL = new String(Files.readAllBytes(Paths.get(handlebarsBaseDir, "template.tmpl")));

		Object json = engine.eval("JSON");
		Object data = invocable.invokeMethod(json, "parse", dataJSON);

		Object handlebars = engine.eval("Handlebars");
		ScriptObjectMirror templateFunc = (ScriptObjectMirror) invocable.invokeMethod(handlebars, "compile", templateTMPL);
		Object result = templateFunc.call(null,data);

		System.out.println("result: " + result);

	}

	@Test
	public void testHandlebarsPrecompile() throws Exception {
		Path baseHandlebarsPath = basePath.resolve("handlebars/");

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Invocable invocable = (Invocable) engine;

		engine.eval(newBufferedReader(baseHandlebarsPath.resolve("handlebars.js"), UTF_8));

		String templateTMPL = new String(Files.readAllBytes(baseHandlebarsPath.resolve("template.tmpl")));
		Object jsHandlebars = engine.eval("Handlebars");

		Object templateFunc = invocable.invokeMethod(jsHandlebars, "precompile", templateTMPL);
		System.out.println("templateFunc: \n" + templateFunc.toString());
	}

	@Test
	public void sampleJS() throws Exception{
		Path baseSamplePath = basePath.resolve("sample/");

		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		Invocable invocable = (Invocable) engine;

		engine.eval(newBufferedReader(baseSamplePath.resolve("sample.js"), UTF_8));

	}
}
