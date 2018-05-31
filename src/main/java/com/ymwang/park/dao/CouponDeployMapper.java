package com.ymwang.park.dao;

import com.ymwang.park.model.CouponDeploy;
import com.ymwang.park.model.CouponDeployKey;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Date;
import java.util.List;

@Repository
public interface CouponDeployMapper {
    int deleteByPrimaryKey(CouponDeployKey key);

    int insert(CouponDeploy record);

    int insertSelective(CouponDeploy record);

    CouponDeploy selectByPrimaryKey(CouponDeployKey key);
    CouponDeploy selectById(Integer  id);
    int updateByPrimaryKeySelective(CouponDeploy record);

    int updateByPrimaryKey(CouponDeploy record);
    CouponDeploy queryCouponByCommentary( HashMap map);
    List<CouponDeploy> queryAllDeployCoupon();
}