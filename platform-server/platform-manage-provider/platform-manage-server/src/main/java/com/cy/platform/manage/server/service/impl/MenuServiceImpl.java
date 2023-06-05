package com.cy.platform.manage.server.service.impl;

import com.cy.platform.manage.server.domain.mapper.MenuInfoMapper;
import com.cy.platform.manage.server.domain.mapper.MenuMetaMapper;
import com.cy.platform.manage.server.domain.vo.MenuInfoVO;
import com.cy.platform.manage.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单服务实现类
 */
@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuInfoMapper menuInfoMapper;
    @Autowired
    private MenuMetaMapper menuMetaMapper;

    @Override
    public String recordMenuInfo(MenuInfoVO vo) {
        return null;
    }

    @Override
    public List<MenuInfoVO> queryMenuInfo() {
        return null;
    }
}
