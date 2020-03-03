package com.betterlxc.mybatis.mapper;

import com.betterlxc.mybatis.domain.SQuestionProject;

/**
 * @author liuxincheng
 * @date 2019/12/27
 */
public interface SQuestionProjectMapper {
    int deleteByPrimaryKey(Integer iD);

    int insert(SQuestionProject record);

    int insertSelective(SQuestionProject record);

    SQuestionProject selectByPrimaryKey(Integer iD);

    int updateByPrimaryKeySelective(SQuestionProject record);

    int updateByPrimaryKey(SQuestionProject record);
}