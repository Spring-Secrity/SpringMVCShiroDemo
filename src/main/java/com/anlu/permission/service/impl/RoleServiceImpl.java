package com.anlu.permission.service.impl;

import com.anlu.common.dao.URoleMapper;
import com.anlu.common.dao.URolePermissionMapper;
import com.anlu.common.dao.UUserMapper;
import com.anlu.common.model.URole;
import com.anlu.core.mybatis.BaseMybatisDao;
import com.anlu.core.mybatis.page.Pagination;
import com.anlu.core.shiro.token.manager.TokenManager;
import com.anlu.core.utils.LoggerUtils;
import com.anlu.permission.bo.RolePermissionAllocationBo;
import com.anlu.permission.service.RoleService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
@Service
public class RoleServiceImpl extends BaseMybatisDao<URoleMapper> implements RoleService {
    @Autowired
    URoleMapper roleMapper;
    @Autowired
    UUserMapper userMapper;
    @Autowired
    URolePermissionMapper rolePermissionMapper;


    public int deleteByPrimaryKey(Long id) {
        return roleMapper.deleteByPrimaryKey(id);
    }

    public int insert(URole record) {
        return roleMapper.insert(record);
    }

    public int insertSelective(URole record) {
        return roleMapper.insertSelective(record);
    }

    public URole selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(URole record) {
        return roleMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(URole record) {
        return roleMapper.updateByPrimaryKey(record);
    }

    public Map<String, Object> deleteRoleById(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String resultMsg = "删除成功。";
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            c:
            for (String idx : idArray) {
                Long id = new Long(idx);
                if (new Long(1).equals(id)) {
                    resultMsg = "操作成功，But'系统管理员不能删除。";
                    continue c;
                } else {
                    count += this.deleteByPrimaryKey(id);
                }
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
            resultMap.put("resultMsg", resultMsg);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除用户出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

    public Pagination<RolePermissionAllocationBo> findRoleAndPermissionPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findRoleAndPermission", "findCount", resultMap, pageNo, pageSize);

    }

    public Set<String> findRoleByUserId(Long userId) {
        return roleMapper.findRoleByUserId(userId);
    }

    public List<URole> findNowAllPermission() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", TokenManager.getUserId());
        return roleMapper.findNowAllPermission(map);
    }

    public void initData() {
        roleMapper.initData();
    }
}
