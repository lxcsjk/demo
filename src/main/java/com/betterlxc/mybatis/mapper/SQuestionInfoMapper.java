package com.betterlxc.mybatis.mapper;

import com.betterlxc.mybatis.domain.SQuestionInfo;

/**
 * @author liuxincheng
 * @date 2019/12/25
 */
public interface SQuestionInfoMapper {
    int deleteByPrimaryKey(Integer iD);

    int insert(SQuestionInfo record);

    int insertSelective(SQuestionInfo record);

    SQuestionInfo selectByPrimaryKey(Integer iD);

    int updateByPrimaryKeySelective(SQuestionInfo record);

    int updateByPrimaryKey(SQuestionInfo record);
}