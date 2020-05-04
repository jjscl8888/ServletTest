package com.jjs.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class JavaScriptTest {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine javaScript = (ScriptEngine)manager.getEngineByName("JavaScript");
        Integer result = (Integer) javaScript.eval("1*3-9");
        System.out.println(result);
    }

}
