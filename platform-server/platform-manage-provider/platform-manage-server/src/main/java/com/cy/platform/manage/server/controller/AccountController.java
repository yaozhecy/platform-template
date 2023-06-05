package com.cy.platform.manage.server.controller;

import com.cy.platform.common.http.R;
import com.cy.platform.manage.domain.vo.AccountInfoVO;
import com.cy.platform.manage.domain.vo.AccountLoginParams;
import com.cy.platform.manage.domain.vo.LoginInfoVO;
import com.cy.platform.manage.server.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private IAccountService accountService;

    @PostMapping("/login")
    public String login() {
        return "{\n" +
            "\t\"code\": \"00000\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"accessToken\": \"Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInVzZXJJZCI6MiwidXNlcm5hbWUiOiJhZG1pbiIsImRlcHRJZCI6MiwiZGF0YVNjb3BlIjoxLCJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sImV4cCI6MTY3Nzc3ODY3OX0.05EXTy4784tG7Pz1yVvyC6pY3ejSOW0vwgC9BYUH7Qc\",\n" +
            "\t\t\"refreshToken\": null,\n" +
            "\t\t\"expires\": null\n" +
            "\t},\n" +
            "\t\"msg\": \"一切ok\"\n" +
            "}";
    }

    /**
     * 查询登录信息
     *
     * @return 登录信息
     */
    @PostMapping("/info")
    public String getAccountInfo() {
        return "{\n" +
            "\t\"code\": \"00000\",\n" +
            "\t\"data\": {\n" +
            "\t\t\"userId\": \"2\",\n" +
            "\t\t\"nickname\": \"系统管理员\",\n" +
            "\t\t\"avatar\": \"https://s2.loli.net/2022/04/07/gw1L2Z5sPtS8GIl.gif\",\n" +
            "\t\t\"roles\": [\n" +
            "\t\t\t\"ADMIN\"\n" +
            "\t\t],\n" +
            "\t\t\"perms\": [\n" +
            "\t\t\t\"sys:user:edit\",\n" +
            "\t\t\t\"sys:user:delete\",\n" +
            "\t\t\t\"sys:user:add\"\n" +
            "\t\t]\n" +
            "\t},\n" +
            "\t\"msg\": \"一切ok\"\n" +
            "}";
    }

    @PostMapping("/menus")
    public String getMenu() {
        return "{\n" +
            "\t\"code\": \"00000\",\n" +
            "\t\"data\": [\n" +
            "\t\t{\n" +
            "\t\t\t\"path\": \"/system\",\n" +
            "\t\t\t\"component\": \"Layout\",\n" +
            "\t\t\t\"redirect\": \"/system/user\",\n" +
            "\t\t\t\"meta\": {\n" +
            "\t\t\t\t\"title\": \"系统管理\",\n" +
            "\t\t\t\t\"icon\": \"system\",\n" +
            "\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\"alwaysShow\": true,\n" +
            "\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t],\n" +
            "\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t},\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"user\",\n" +
            "\t\t\t\t\t\"component\": \"system/user/index\",\n" +
            "\t\t\t\t\t\"name\": \"user\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"用户管理\",\n" +
            "\t\t\t\t\t\t\"icon\": \"user\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"role\",\n" +
            "\t\t\t\t\t\"component\": \"system/role/index\",\n" +
            "\t\t\t\t\t\"name\": \"role\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"角色管理\",\n" +
            "\t\t\t\t\t\t\"icon\": \"role\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"cmenu\",\n" +
            "\t\t\t\t\t\"component\": \"system/menu/index\",\n" +
            "\t\t\t\t\t\"name\": \"cmenu\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"菜单管理\",\n" +
            "\t\t\t\t\t\t\"icon\": \"menu\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"dept\",\n" +
            "\t\t\t\t\t\"component\": \"system/dept/index\",\n" +
            "\t\t\t\t\t\"name\": \"dept\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"部门管理\",\n" +
            "\t\t\t\t\t\t\"icon\": \"tree\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"dict\",\n" +
            "\t\t\t\t\t\"component\": \"system/dict/index\",\n" +
            "\t\t\t\t\t\"name\": \"dict\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"字典管理\",\n" +
            "\t\t\t\t\t\t\"icon\": \"dict\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"path\": \"/api\",\n" +
            "\t\t\t\"component\": \"Layout\",\n" +
            "\t\t\t\"meta\": {\n" +
            "\t\t\t\t\"title\": \"接口\",\n" +
            "\t\t\t\t\"icon\": \"api\",\n" +
            "\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\"alwaysShow\": true,\n" +
            "\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t],\n" +
            "\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t},\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"apidoc\",\n" +
            "\t\t\t\t\t\"component\": \"demo/apidoc\",\n" +
            "\t\t\t\t\t\"name\": \"apidoc\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"接口文档\",\n" +
            "\t\t\t\t\t\t\"icon\": \"api\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"path\": \"/external-link\",\n" +
            "\t\t\t\"component\": \"Layout\",\n" +
            "\t\t\t\"redirect\": \"noredirect\",\n" +
            "\t\t\t\"meta\": {\n" +
            "\t\t\t\t\"title\": \"外部链接\",\n" +
            "\t\t\t\t\"icon\": \"link\",\n" +
            "\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\"alwaysShow\": true,\n" +
            "\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t],\n" +
            "\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t},\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"https://www.cnblogs.com/haoxianrui/p/16090029.html\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"document\",\n" +
            "\t\t\t\t\t\t\"icon\": \"document\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"path\": \"/multi-level-menu\",\n" +
            "\t\t\t\"component\": \"Layout\",\n" +
            "\t\t\t\"redirect\": \"/nested/level1/level2\",\n" +
            "\t\t\t\"meta\": {\n" +
            "\t\t\t\t\"title\": \"多级菜单\",\n" +
            "\t\t\t\t\"icon\": \"multi_level\",\n" +
            "\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\"alwaysShow\": true,\n" +
            "\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t],\n" +
            "\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t},\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"nested_level1_index\",\n" +
            "\t\t\t\t\t\"component\": \"nested/level1/index\",\n" +
            "\t\t\t\t\t\"redirect\": \"/nested/level1/level2\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"菜单一级\",\n" +
            "\t\t\t\t\t\t\"icon\": \"\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": true,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t},\n" +
            "\t\t\t\t\t\"children\": [\n" +
            "\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\"path\": \"nested_level1_level2_index\",\n" +
            "\t\t\t\t\t\t\t\"component\": \"nested/level1/level2/index\",\n" +
            "\t\t\t\t\t\t\t\"redirect\": \"/nested/level1/level2/level3\",\n" +
            "\t\t\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\t\t\"title\": \"菜单二级\",\n" +
            "\t\t\t\t\t\t\t\t\"icon\": \"\",\n" +
            "\t\t\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\t\t\"alwaysShow\": true,\n" +
            "\t\t\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\"children\": [\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"path\": \"nested_level1_level2_level3_index1\",\n" +
            "\t\t\t\t\t\t\t\t\t\"component\": \"nested/level1/level2/level3/index1\",\n" +
            "\t\t\t\t\t\t\t\t\t\"name\": \"nested_level1_level2_level3_index1\",\n" +
            "\t\t\t\t\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\t\t\t\t\"title\": \"菜单三级-1\",\n" +
            "\t\t\t\t\t\t\t\t\t\t\"icon\": \"\",\n" +
            "\t\t\t\t\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t\t},\n" +
            "\t\t\t\t\t\t\t\t{\n" +
            "\t\t\t\t\t\t\t\t\t\"path\": \"nested_level1_level2_level3_index2\",\n" +
            "\t\t\t\t\t\t\t\t\t\"component\": \"nested/level1/level2/level3/index2\",\n" +
            "\t\t\t\t\t\t\t\t\t\"name\": \"nested_level1_level2_level3_index2\",\n" +
            "\t\t\t\t\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\t\t\t\t\"title\": \"菜单三级-2\",\n" +
            "\t\t\t\t\t\t\t\t\t\t\"icon\": \"\",\n" +
            "\t\t\t\t\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t\t\t]\n" +
            "\t\t\t\t\t\t}\n" +
            "\t\t\t\t\t]\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t},\n" +
            "\t\t{\n" +
            "\t\t\t\"path\": \"/demo\",\n" +
            "\t\t\t\"component\": \"Layout\",\n" +
            "\t\t\t\"meta\": {\n" +
            "\t\t\t\t\"title\": \"组件封装\",\n" +
            "\t\t\t\t\"icon\": \"menu\",\n" +
            "\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\"alwaysShow\": true,\n" +
            "\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t],\n" +
            "\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t},\n" +
            "\t\t\t\"children\": [\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"editor\",\n" +
            "\t\t\t\t\t\"component\": \"demo/editor\",\n" +
            "\t\t\t\t\t\"name\": \"editor\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"富文本编辑器\",\n" +
            "\t\t\t\t\t\t\"icon\": \"\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"uploader\",\n" +
            "\t\t\t\t\t\"component\": \"demo/uploader\",\n" +
            "\t\t\t\t\t\"name\": \"uploader\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"上传组件\",\n" +
            "\t\t\t\t\t\t\"icon\": \"\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t},\n" +
            "\t\t\t\t{\n" +
            "\t\t\t\t\t\"path\": \"icon-selector\",\n" +
            "\t\t\t\t\t\"component\": \"demo/icon-selector\",\n" +
            "\t\t\t\t\t\"name\": \"icon-selector\",\n" +
            "\t\t\t\t\t\"meta\": {\n" +
            "\t\t\t\t\t\t\"title\": \"图标选择器\",\n" +
            "\t\t\t\t\t\t\"icon\": \"\",\n" +
            "\t\t\t\t\t\t\"hidden\": false,\n" +
            "\t\t\t\t\t\t\"alwaysShow\": false,\n" +
            "\t\t\t\t\t\t\"roles\": [\n" +
            "\t\t\t\t\t\t\t\"ADMIN\"\n" +
            "\t\t\t\t\t\t],\n" +
            "\t\t\t\t\t\t\"keepAlive\": true\n" +
            "\t\t\t\t\t}\n" +
            "\t\t\t\t}\n" +
            "\t\t\t]\n" +
            "\t\t}\n" +
            "\t],\n" +
            "\t\"msg\": \"一切ok\"\n" +
            "}";
    }
}
