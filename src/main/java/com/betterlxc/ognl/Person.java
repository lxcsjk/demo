package com.betterlxc.ognl;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by liuxincheng on 2018/8/8.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    private Dog dog;

    public Person(String name) {
        this.name = name;
    }
}
