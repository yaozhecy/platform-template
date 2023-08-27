package com.cy.generate.domain.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author 56807
 */
@Component
@Scope("prototype")
@Getter
@Setter
public class User extends AbstractModel {
    private String id;
    private String name;
    private String group;

    // the user configuration parameters
    public void configure(int id, String name, String group) {
        this.id = "uid-" + id;  // the user id
        this.name = name;  // the user name
        this.group = group;  // the group the user belongs to
    }
}
