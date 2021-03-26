package com.oyf.test.test4;

import com.oyf.test.test5.Constraints;
import com.oyf.test.test5.Create;
import com.oyf.test.test5.Update;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

/**
 * @author ouyangfei
 * @date Created in 2021/3/24
 * @description
 */
@Data
public class User {

    @NotEmpty(groups = Update.class)
    private Long id;

    @NotEmpty(groups = Create.class)
    private String name;

    @NotNull(groups = {Create.class, Update.class})
    private Integer age;

    @Constraints(contains = "你好", message = "应该包含特定字符串[你好]")
    private String message;

    @NotEmpty(groups = Default.class)
    private String address;

    @Valid
    private Company company;

}
