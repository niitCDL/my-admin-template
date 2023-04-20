package top.mqxu.rbac.service;


import top.mqxu.mybatis.service.BaseService;
import top.mqxu.rbac.entity.SysMenuEntity;
import top.mqxu.rbac.vo.SysMenuVO;
import top.mqxu.security.user.UserDetail;

import java.util.List;

/**
 * 菜单管理
 *
 * @author mqxu
 */
public interface SysMenuService extends BaseService<SysMenuEntity> {

    /**
     * 菜单列表
     *
     * @param type 菜单类型
     */
    List<SysMenuVO> getMenuList(Integer type);

    /**
     * 用户菜单列表
     *
     * @param user 用户
     * @param type 菜单类型
     */
    List<SysMenuVO> getUserMenuList(UserDetail user, Integer type);


}
