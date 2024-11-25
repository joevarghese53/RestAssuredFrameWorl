package com.automation.pojo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode
public class CreateUserResponsePojo {
    String name;
    String email;
    String gender;
    String status;
    int id;
}
