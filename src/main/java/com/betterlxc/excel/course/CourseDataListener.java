package com.betterlxc.excel.course;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.betterlxc.excel.CourseTypeEnum;
import com.betterlxc.excel.ProfessionTypeEnum;
import com.betterlxc.excel.SourceTypeEnum;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxincheng
 * @date 2021/8/19
 */
public class CourseDataListener extends AnalysisEventListener<CourseDTO> {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<CourseDTO> list = new ArrayList<>();


    @Override
    public void invoke(CourseDTO demoData, AnalysisContext analysisContext) {
        try {
            switch (demoData.getSourceStr()){
                case "校招":
                    demoData.setSource(SourceTypeEnum.SCHOOL_RECRUITMENT.code);
                    break;
                case "社招":
                    demoData.setSource(SourceTypeEnum.SOCIAL_RECRUITMENT.code);
                    break;
                default:
                    demoData.setSource(SourceTypeEnum.ALL.code);
                    break;
            }

            switch (demoData.getProfessionStr()){
                case "运营":
                    demoData.setProfession(ProfessionTypeEnum.OPERATION.code);
                    break;
                case "开发":
                    demoData.setProfession(ProfessionTypeEnum.DEVELOPMENT.code);
                    break;
                case "设计":
                    demoData.setProfession(ProfessionTypeEnum.DESIGN.code);
                    break;
                case "产品":
                    demoData.setProfession(ProfessionTypeEnum.PRODUCT.code);
                    break;
                case "数据分析":
                    demoData.setProfession(ProfessionTypeEnum.DATA_ANALYSIS.code);
                    break;
                default:
                    demoData.setProfession(ProfessionTypeEnum.NONE.code);
                    break;
            }

            switch (demoData.getTypeStr()){
                case "咨询课":
                    demoData.setType(CourseTypeEnum.ADVISORY.code);
                    break;
                case "1v1":
                    demoData.setType(CourseTypeEnum.ALONE.code);
                    break;
                case "小班":
                    demoData.setType(CourseTypeEnum.SMALL.code);
                    break;
                default:
                    break;
            }
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
        List<List<CourseDTO>> partition = Lists.partition(list, 500);
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