package com.cy.platform.temp.server.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestInfo {
    private String id;
    private String name;
    private String desc;
}
