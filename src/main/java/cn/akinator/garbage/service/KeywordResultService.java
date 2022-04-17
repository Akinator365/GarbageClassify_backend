package cn.akinator.garbage.service;

import cn.akinator.garbage.entity.KeywordResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关键词+结果记录表 服务类
 * </p>
 */
public interface KeywordResultService extends IService<KeywordResult> {

    List<KeywordResult> keywordHistory(Map<String, Object> useridMap);

}
