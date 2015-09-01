package com.ryan.gengine.Version1.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by a689638 on 7/20/2015.
 * Copyright (C) 2015 HEB
 *
 * @author Ryan Anders
 *         This software is the confidential and proprietary information
 *         of HEB
 */
public class LanguageParser {
    Stack<String> stack = new Stack<String>();
    Map<String, Object> variables = new HashMap<String, Object>();

    class FunctionCall{
        String name;
        String[] arguments;
        String function;
        FunctionCall (String n, String[] a, String f){
            this.name = n; this.arguments = a; this.function = f;
        }
    }

    public  void Test(){
        String langSample = "func[arg1, arg2]:= arg1 + arg2\n" +
                "this=func[arg1, arg2]";


        for (String s: langSample.split("\\b")){
            stack.push(s);
        }

    }



}
