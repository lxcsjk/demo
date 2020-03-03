package com.betterlxc.mybatis.mapper;

import com.betterlxc.mybatis.domain.SQuestionProjectRecord;

/**
 * @author liuxincheng
 * @date 2019/12/27
 */
public interface SQuestionProjectRecordMapper {
    int deleteByPrimaryKey(Integer iD);

    int insert(SQuestionProjectRecord record);

    int insertSelective(SQuestionProjectRecord record);

    SQuestionProjectRecord selectByPrimaryKey(Integer iD);

    int updateByPrimaryKeySelective(SQuestionProjectRecord record);

    int updateByPrimaryKey(SQuestionProjectRecord record);
}