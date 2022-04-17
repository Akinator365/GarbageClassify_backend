package cn.akinator.garbage.service;

import cn.akinator.garbage.entity.ChallengeResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 挑战结果+详情记录表 服务类
 * </p>
 */
public interface ChallengeResultService extends IService<ChallengeResult> {

    Map<String ,Object> challengeResults(Map<String,Object> map);

    List<ChallengeResult> challengeHistory(Map<String,Object> useridMap);

}
