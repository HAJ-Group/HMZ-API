package com.hmz.config;

@FunctionalInterface
public interface ConfigLoader {

    String getProperty(String name);

}
