package com.cy.platform.manage.server.service;

import com.cy.platform.manage.server.domain.vo.MenuInfoVO;

import java.util.List;

/**
 * 菜单服务类
 */
public interface IMenuService {
    /**
     * 记录菜单信息
     *
     * @param vo 菜单信息VO
     * @return 菜单信息ID
     */
    String recordMenuInfo(MenuInfoVO vo);

    List<MenuInfoVO> queryMenuInfo();
}
