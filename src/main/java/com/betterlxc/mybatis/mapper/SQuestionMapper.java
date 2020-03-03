package com.betterlxc.mybatis.mapper;

import com.betterlxc.mybatis.domain.SQuestion;

/**
 * @author liuxincheng
 * @date 2019/12/25
 */
public interface SQuestionMapper {
    int deleteByPrimaryKey(Integer iD);

    int insert(SQuestion record);

    int insertSelective(SQuestion record);

    SQuestion selectByPrimaryKey(Integer iD);

    int updateByPrimaryKeySelective(SQuestion record);

    int updateByPrimaryKey(SQuestion record);
}