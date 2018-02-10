package com.anlu.common.dao;

import com.anlu.common.model.UUser;
import com.anlu.common.model.bo.URoleBo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface UUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UUser record);

    int insertSelective(UUser record);

    UUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UUser record);

    int updateByPrimaryKey(UUser record);

    /**
     * @param
     * @return
     */
    UUser login();


	UUser findUserByEmail(String email);

	List<URoleBo> selectRoleByUserId(Long id);

}