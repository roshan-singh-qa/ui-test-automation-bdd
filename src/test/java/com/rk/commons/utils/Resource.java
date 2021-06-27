package com.rk.commons.utils;

public class Resource {

    public String getPathToFile(String fileName) {
        var classLoader = getClass().getClassLoader();
        var path = classLoader.getResource(fileName).getPath();
        var numberOfCharacters = path.length();
        return path.substring(1, numberOfCharacters);
    }

}
