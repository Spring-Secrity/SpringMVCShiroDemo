package com.anlu.permission.controller;

import com.anlu.common.controller.BaseController;
import com.anlu.common.model.bo.UPermissionBo;
import com.anlu.core.mybatis.page.Pagination;
import com.anlu.permission.bo.RolePermissionAllocationBo;
import com.anlu.permission.service.PermissionService;
import com.anlu.permission.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public class PermissionAllocationController extends BaseController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;

    /**
     * 权限分配
     * @param modelMap
     * @param pageNo
     * @param findContent
     * @return
     */
    @RequestMapping(value="allocation")
    public ModelAndView allocation(ModelMap modelMap, Integer pageNo, String findContent){
        modelMap.put("findContent", findContent);
        Pagination<RolePermissionAllocationBo> boPage = roleService.findRoleAndPermissionPage(modelMap,pageNo,pageSize);
        modelMap.put("page", boPage);
        return new ModelAndView("permission/allocation");
    }

    /**
     * 根据角色ID查询权限
     * @param id
     * @return
     */
    @RequestMapping(value="selectPermissionById")
    @ResponseBody
    public List<UPermissionBo> selectPermissionById(Long id){
        List<UPermissionBo> permissionBos = permissionService.selectPermissionById(id);
        return permissionBos;
    }

    /**
     * 操作角色的权限
     * @param roleId 	角色ID
     * @param ids		权限ID，以‘,’间隔
     * @return
     */
    @RequestMapping(value="addPermission2Role")
    @ResponseBody
    public Map<String,Object> addPermission2Role(Long roleId, String ids){
        return permissionService.addPermission2Role(roleId,ids);
    }

    /**
     * 根据角色id清空权限。
     * @param roleIds	角色ID ，以‘,’间隔
     * @return
     */
    @RequestMapping(value="clearPermissionByRoleIds")
    @ResponseBody
    public Map<String,Object> clearPermissionByRoleIds(String roleIds){
        return permissionService.deleteByRids(roleIds);
    }
}