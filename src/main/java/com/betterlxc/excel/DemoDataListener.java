package com.betterlxc.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxincheng
 * @date 2021/8/19
 */
public class DemoDataListener extends AnalysisEventListener<DemoData> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<>();


    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        try {
            Integer dramaId = Integer.valueOf(demoData.getDramaIdStr());
            Integer projectId = Integer.valueOf(demoData.getProjectIdStr());
            demoData.setDramaId(dramaId);
            demoData.setProjectId(projectId);
            list.add(demoData);
        } catch (Exception ignored) {
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        List<List<DemoData>> partition = Lists.partition(list, 500);
        partition.forEach(l-> LOGGER.info("总数据条数:{}", JSON.toJSONString(l)));
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
//        demoDAO.save(list);
        LOGGER.info("存储数据库成功！");
    }


}