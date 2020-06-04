package com.betterlxc.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author LXC
 * @date 2017/5/10
 */
public class ReadExcelTest {

    private static final File EXCEL_PATH = new File("/Users/lxc/Downloads/1.xlsx");
    private static final String ANXIN = "安心看";

    /**
     * https://pigeon.sankuai.com/#/tree/project/com.sankuai.maoyan.my.shcontent/test
     */
    @Test
    public void test() {
        List<RelievedTagDTO> relievedTagDTOList = JSONObject.parseArray("[ { \"id\" : 1, \"name\" : \"安心看\", \"status\" : null, \"sort\" : 11}, { \"id\" : 2, \"name\" : \"入场体温测量\", \"status\" : null, \"sort\" : 10}, { \"id\" : 3, \"name\" : \"入场佩戴口罩\", \"status\" : null, \"sort\" : 9}, { \"id\" : 4, \"name\" : \"提供洗手消毒设施\", \"status\" : null, \"sort\" : 8}, { \"id\" : 5, \"name\" : \"严格限流管控\", \"status\" : null, \"sort\" : 7}, { \"id\" : 6, \"name\" : \"每日消毒通风\", \"status\" : null, \"sort\" : 6}, { \"id\" : 7, \"name\" : \"推行无接触服务(电子票)\", \"status\" : null, \"sort\" : 3}, { \"id\" : 8, \"name\" : \"实名制观演\", \"status\" : null, \"sort\" : 5}, { \"id\" : 9, \"name\" : \"取消团体接待服务\", \"status\" : null, \"sort\" : 2}, { \"id\" : 10, \"name\" : \"退订保障\", \"status\" : null, \"sort\" : 4}, { \"id\" : 11, \"name\" : \"错排错位出票\", \"status\" : null, \"sort\" : 1} ]", RelievedTagDTO.class);
        Map<String, Integer> collect = relievedTagDTOList.stream().collect(Collectors.toMap(RelievedTagDTO::getName, RelievedTagDTO::getId));

        ImportParams params = new ImportParams();
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(EXCEL_PATH, Map.class, params);

        List<ProjectTagRequest> relievedProjectRequests = list.stream()
            .filter(obj -> obj.containsKey("项目ID") && !obj.containsValue(""))
            .map(obj -> {
                ProjectTagRequest relievedProjectRequest = new ProjectTagRequest();
                relievedProjectRequest.setProjectId(Double.valueOf(obj.get("项目ID").toString()).intValue());
                List<String> tagNames = Lists.newArrayList();
                obj.forEach((k, v) -> {
                    if (v instanceof Number && Double.parseDouble(v.toString()) > 0) {
                        tagNames.add(k.contains(ANXIN) ? ANXIN : k);
                    }
                });
                List<Integer> tagIds = tagNames.stream().map(collect::get).filter(Objects::nonNull).sorted().collect(Collectors.toList());
                relievedProjectRequest.setTagIds(tagIds);
                return relievedProjectRequest;
            }).collect(Collectors.toList());

        AddProjectTagRequest addProjectTagRequest = new AddProjectTagRequest();
        addProjectTagRequest.setProjectTagRequests(relievedProjectRequests);
        String s = JSON.toJSONString(addProjectTagRequest);
        System.out.println(addProjectTagRequest);
    }
}
