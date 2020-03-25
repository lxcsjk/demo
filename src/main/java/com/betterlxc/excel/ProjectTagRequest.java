package com.betterlxc.excel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuxincheng
 * @date 2020/3/9
 */
@Data
public class ProjectTagRequest implements Serializable {

    private Integer projectId;

    private List<Integer> tagIds;

}