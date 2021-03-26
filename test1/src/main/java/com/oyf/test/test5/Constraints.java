package com.oyf.test.test5;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *
 * @description 自定义约束注解
 * message、contains、payload是必须要写的
 * 当没有指定默认值时，那么在使用此注解时，就必须输入对应的属性值
 *
 * @author ouyangfei
 * @date Created in 2021/3/25
 */
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
@Documented
// 指定此注解的实现，即:验证器
@Constraint(validatedBy ={Validator.class})
public @interface Constraints {

    // 当验证不通过时的提示信息
    String message() default "param value must contains specified value!";

    // 根据实际需求定的方法
    String contains() default "";

    // 约束注解在验证时所属的组别
    Class<?>[] groups() default { };

    // 负载
    Class<? extends Payload>[] payload() default { };

}
