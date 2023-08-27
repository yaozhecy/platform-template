package com.cy.generate.service.impl;

import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cy.generate.domain.mapper.TempInfoMapper;
import com.cy.generate.domain.model.TempInfoPO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TempInfoServiceImpl extends ServiceImpl<TempInfoMapper, TempInfoPO> {

    @Transactional(rollbackFor = Exception.class)
    public void test() {
        long start = System.currentTimeMillis();
        List<TempInfoPO> list = new ArrayList<>(2000);
        for (int i = 0; i < 2 * 1000 * 10000; i++) {
            list.add(new TempInfoPO(IdWorker.getId(), "test", "test"));
            if (i % 10000 == 0) {
                long start2 = System.currentTimeMillis();
                saveBatch(list);
                System.out.println("插入耗时：" + (System.currentTimeMillis() - start2));
                list.clear();
            }
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));
    }
}
