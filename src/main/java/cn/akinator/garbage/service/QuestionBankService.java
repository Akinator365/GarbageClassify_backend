package cn.akinator.garbage.service;

import cn.akinator.garbage.entity.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 题库表 服务类
 * </p>
 */
public interface QuestionBankService extends IService<QuestionBank> {

    Map<String,Object> searchQuestionByUniName(String name, String userid);

    Map<String,Object> uploadExcel(String filePath);


}
