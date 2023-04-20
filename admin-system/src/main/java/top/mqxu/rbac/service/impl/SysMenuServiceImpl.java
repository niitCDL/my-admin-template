package top.mqxu.rbac.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.mqxu.common.constant.Constant;
import top.mqxu.common.utils.TreeUtils;
import top.mqxu.mybatis.service.impl.BaseServiceImpl;
import top.mqxu.rbac.convert.SysMenuConvert;
import top.mqxu.rbac.dao.SysMenuDao;
import top.mqxu.rbac.entity.SysMenuEntity;
import top.mqxu.rbac.enums.SuperAdminEnum;
import top.mqxu.rbac.service.SysMenuService;
import top.mqxu.rbac.vo.SysMenuVO;
import top.mqxu.security.user.UserDetail;

import java.util.List;


/**
 * 菜单管理服务
 *
 * @author mqxu
 */
@Service
@AllArgsConstructor
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {

    @Override
    public List<SysMenuVO> getMenuList(Integer type) {
        List<SysMenuEntity> menuList = baseMapper.getMenuList(type);
        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList), Constant.ROOT);
    }

    @Override
    public List<SysMenuVO> getUserMenuList(UserDetail user, Integer type) {
        List<SysMenuEntity> menuList;
        // 系统管理员，拥有最高权限
        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
            menuList = baseMapper.getMenuList(type);
        } else {
            menuList = baseMapper.getUserMenuList(user.getId(), type);
        }
        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList));
    }

}