package cn.akinator.garbage.service.impl;

import cn.akinator.garbage.entity.ChallengeDetail;
import cn.akinator.garbage.mapper.ChallengeResultMapper;
import cn.akinator.garbage.service.ChallengeDetailService;
import cn.akinator.garbage.entity.ChallengeResult;
import cn.akinator.garbage.service.ChallengeResultService;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 挑战结果+详情记录表 服务实现类
 * </p>
 */
@Service
@Slf4j
public class ChallengeResultServiceImpl extends ServiceImpl<ChallengeResultMapper, ChallengeResult> implements ChallengeResultService {

    @Autowired
    ChallengeDetailService detailService;

    @Override
    @Async
    public Map<String, Object> challengeResults(Map<String, Object> map) {
        System.out.println(map);
        Integer score = (Integer) map.get("score");
        Integer userid = (Integer) map.get("userid");
        List<LinkedHashMap> list = (List<LinkedHashMap>) map.get("list");
        List<ChallengeDetail> detailArrayList = new ArrayList<>();
        for (LinkedHashMap linkedHashMap : list) {
            ChallengeDetail detail = ChallengeDetail.builder().questionId((Integer) linkedHashMap.get("questionId"))
                    .garbageName((String) linkedHashMap.get("garbageName"))
                    .garbageType((Integer) linkedHashMap.get("garbageType"))
                    .selectedType((Integer) linkedHashMap.get("selectedType")).build();
            detailArrayList.add(detail);
        }
        boolean save = this.save(ChallengeResult.builder().score(score).userid(userid.toString()).result(JSON.toJSONString(detailArrayList)).build());
        boolean saveBatch = detailService.saveBatch(detailArrayList);
        map.clear();
        map.put("handle", "ok");
        return map;
    }

    @Override
    public List<ChallengeResult> challengeHistory(Map<String, Object> useridMap) {

        Integer userid = (Integer) useridMap.get("userid");

        QueryWrapper<ChallengeResult> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("times");
        queryWrapper.eq("userid", userid);

        List<ChallengeResult> resultList = list(queryWrapper);

        System.out.println(resultList);

        return resultList;
    }
}
