package com.rk.commons.configuration.sections;

import com.rk.commons.configuration.enums.BrowserType;
import com.rk.commons.configuration.enums.OperatingSystem;

public class DriverDetails {
    public static boolean isHeadless;
    public static boolean shouldHighlightElement;
    public static String highlightColor;
    public static BrowserType browserType;
    public static OperatingSystem os;
    public static boolean isIncognito;
    public static boolean verbose;
}
