package com.cy.platform.common.template;

import com.google.common.collect.Lists;

import java.util.List;

public class GuavaTemplate {
    public void collection() {
        //创建队列
        List<Integer> list = Lists.newArrayList(1, 2, 3);
        //列表分组
        List<List<Integer>> lists = Lists.partition(list, 2);
    }
}
