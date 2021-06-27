package com.rk.commons.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ByNg {

    public static By click(String ngClickPartialValue) {
        return createCustomSelector("ng-click", ngClickPartialValue);
    }

    public static By controller(String ngControllerPartialValue) {
        return createCustomSelector("ng-controller", ngControllerPartialValue);
    }

    public static By repeat(String ngRepeatPartialValue) {
        return createCustomSelector("ng-repeat", ngRepeatPartialValue);
    }

    public static By show(String ngShowPartialValue) {
        return createCustomSelector("ng-show", ngShowPartialValue);
    }

    public static By className(String ngClassPartialValue) {

        return createCustomSelector("ng-class", ngClassPartialValue);
    }

    public static By model(String ngModelPartialValue) {
        return createCustomSelector("ng-model", ngModelPartialValue);
    }

    public static By ifStatement(String ngIflPartialValue) {
        return createCustomSelector("ng-if", ngIflPartialValue);
    }

    private static By createCustomSelector(String attribute, String searchedPartialValue) {
        var cssSelector = "[" + attribute + "*='" + searchedPartialValue + "']";

        return new By() {
            @Override
            public List<WebElement> findElements(SearchContext context) {
                return context.findElements(By.cssSelector(cssSelector));
            }

            @Override
            public WebElement findElement(SearchContext context) {
                return context.findElement(By.cssSelector(cssSelector));
            }
        };
    }
}
