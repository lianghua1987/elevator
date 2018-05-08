package com.nytimes.subscription.platform.interview;

import com.nytimes.subscription.platform.interview.pojo.Result;

import java.io.File;

public interface Model {

    public void operate(File file);

    public Result parse(String line);
}
