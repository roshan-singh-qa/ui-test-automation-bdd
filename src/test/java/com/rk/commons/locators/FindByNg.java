package com.rk.commons.locators;

import org.openqa.selenium.By;
import org.openqa.selenium.support.AbstractFindByBuilder;
import org.openqa.selenium.support.PageFactoryFinder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@PageFactoryFinder(FindByNg.FindByCustomBuilder.class)
public @interface FindByNg {

    String click() default "";

    String controller() default "";

    String repeat() default "";

    String show() default "";

    String className() default "";

    String model() default "";

    String ifStatement() default "";

    public static class FindByCustomBuilder extends AbstractFindByBuilder {

        public By buildIt(Object annotation, Field field) {
            var findBy = (FindByNg) annotation;
            return buildBy(findBy);
        }

        private By buildBy(FindByNg findByNg) {
            if (!"".equals(findByNg.className())) {
                return ByNg.className(findByNg.className());
            } else if (!"".equals(findByNg.click())) {
                return ByNg.click(findByNg.click());
            } else if (!"".equals(findByNg.controller())) {
                return ByNg.controller(findByNg.controller());
            } else if (!"".equals(findByNg.repeat())) {
                return ByNg.repeat(findByNg.repeat());
            } else if (!"".equals(findByNg.show())) {
                return ByNg.show(findByNg.show());
            } else if (!"".equals(findByNg.model())) {
                return ByNg.model(findByNg.model());
            } else if (!"".equals(findByNg.ifStatement())) {
                return ByNg.ifStatement(findByNg.ifStatement());
            } else {
                throw new IllegalArgumentException("Incorrect findByNg selector");
            }
        }
    }
}
