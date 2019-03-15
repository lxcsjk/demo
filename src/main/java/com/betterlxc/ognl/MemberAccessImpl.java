package com.betterlxc.ognl;

import ognl.MemberAccess;

import java.lang.reflect.Member;
import java.util.Map;

/**
 * Created by liuxincheng on 2018/8/8.
 */
public class MemberAccessImpl implements MemberAccess {

    public MemberAccessImpl() {
    }

    @Override
    public Object setup(Map context, Object target, Member member, String propertyName) {
        return null;
    }

    @Override
    public void restore(Map context, Object target, Member member, String propertyName, Object state) {

    }

    @Override
    public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
        return false;
    }
}
