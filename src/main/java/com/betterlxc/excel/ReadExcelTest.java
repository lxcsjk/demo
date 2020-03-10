package com.betterlxc.excel;

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

    private static final File EXCEL_PATH = new File("/Users/lxc/Downloads/test.xlsx");
    private static final String ANXIN = "安心看";

    @Test
    public void test1() {
        List<RelievedTagDTO> relievedTagDTOList = JSONObject.parseArray("[{\"id\":10,\"name\":\"错排错位\",\"status\":0,\"sort\":10},{\"id\":9,\"name\":\"退订保障\",\"status\":0,\"sort\":9},{\"id\":8,\"name\":\"取消团体接待服务\",\"status\":0,\"sort\":8},{\"id\":7,\"name\":\"实名制观演\",\"status\":0,\"sort\":7},{\"id\":6,\"name\":\"推行无接触服务（电子票）\",\"status\":0,\"sort\":6},{\"id\":5,\"name\":\"消毒通风\",\"status\":0,\"sort\":5},{\"id\":4,\"name\":\"严格限流管控\",\"status\":0,\"sort\":4},{\"id\":3,\"name\":\"设置洗手消毒设施\",\"status\":0,\"sort\":3},{\"id\":2,\"name\":\"入场佩戴口罩\",\"status\":0,\"sort\":2},{\"id\":1,\"name\":\"入场体温测量\",\"status\":0,\"sort\":1},{\"id\":0,\"name\":\"安心看\",\"status\":0,\"sort\":0}]\n", RelievedTagDTO.class);
        Map<String, Integer> collect = relievedTagDTOList.stream().collect(Collectors.toMap(RelievedTagDTO::getName, RelievedTagDTO::getId));

        ImportParams params = new ImportParams();
        List<Map<String, Object>> list = ExcelImportUtil.importExcel(EXCEL_PATH, Map.class, params);

        List<RelievedProjectRequest> relievedProjectRequests = list.stream().filter(obj -> obj.containsKey("项目ID")).map(obj -> {
            RelievedProjectRequest relievedProjectRequest = new RelievedProjectRequest();
            relievedProjectRequest.setProjectId(Double.valueOf(obj.get("项目ID").toString()).intValue());
            List<String> tagNames = Lists.newArrayList();
            obj.forEach((k, v) -> {
                if (v instanceof Number && Double.parseDouble(v.toString()) > 0) {
                    tagNames.add(k.contains(ANXIN) ? ANXIN : k);
                }
            });
            List<Integer> tagIds = tagNames.stream().map(collect::get).filter(Objects::nonNull).collect(Collectors.toList());
            relievedProjectRequest.setTagIds(tagIds);
            return relievedProjectRequest;
        }).collect(Collectors.toList());

        System.out.println(relievedProjectRequests);
    }
}
