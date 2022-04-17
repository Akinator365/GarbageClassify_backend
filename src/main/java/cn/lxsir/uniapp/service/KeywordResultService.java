package cn.lxsir.uniapp.service;

import cn.lxsir.uniapp.entity.ChallengeResult;
import cn.lxsir.uniapp.entity.KeywordResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关键词+结果记录表 服务类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
public interface KeywordResultService extends IService<KeywordResult> {

    List<KeywordResult> keywordHistory(Map<String, Object> useridMap);

}
