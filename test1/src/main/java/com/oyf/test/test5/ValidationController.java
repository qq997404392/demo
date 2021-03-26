package com.oyf.test.test5;

import com.oyf.test.test4.User;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

/**
 * @author ouyangfei
 * @date Created in 2021/3/25
 * @description
 */
@RestController
@RequestMapping("/validation")
public class ValidationController {

    @PostMapping("/test")
    public void test(@Validated @RequestBody User user) {
        /*if (bindingResult.hasErrors()) {
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError objectError : errorList) {
                System.out.println(objectError.getDefaultMessage());
            }
        }*/
        System.out.println(user.toString());
    }

    // 会触发[Create]和[Default]组的校验
    @PostMapping("/create")
    public void create(@Validated(value = Create.class) @RequestBody User user) {
        System.out.println(user.toString());
    }

    // 会触发[Update]和[Default]组的校验
    @PostMapping("/update")
    public void update(@Validated(value = Update.class)  @RequestBody User user) {
        System.out.println(user.toString());
    }

    // 会触发[Create]、[Update]和[Default]组的校验
    @PostMapping("/all")
    public void all(@Validated(value = {Create.class, Update.class}) @RequestBody User user) {
        System.out.println(user.toString());
    }

    // 会触发[Default]组的校验
    @PostMapping("/test1")
    public void test1(@Validated(value = Default.class) @RequestBody User user) {
        System.out.println(user.toString());
    }

    // 会触发[Default]组的校验
    @PostMapping("/test2")
    public void test2(@Validated @RequestBody User user) {
        System.out.println(user.toString());
    }

}
