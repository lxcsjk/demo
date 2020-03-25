package com.betterlxc.excel;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuxincheng
 * @date 2020/3/12
 */
@Data
public class AddProjectTagRequest implements Serializable {

    private List<ProjectTagRequest> projectTagRequests;
}
