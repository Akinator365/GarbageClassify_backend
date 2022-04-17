package cn.akinator.garbage.service.impl;

import cn.akinator.garbage.entity.KeywordResult;
import cn.akinator.garbage.mapper.KeywordResultMapper;
import cn.akinator.garbage.service.KeywordSearchNumService;
import cn.akinator.garbage.service.KeywordResultService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关键词+结果记录表 服务实现类
 * </p>
 */
@Service
public class KeywordResultServiceImpl extends ServiceImpl<KeywordResultMapper, KeywordResult> implements KeywordResultService {

    @Autowired
    KeywordSearchNumService ksnService;

    @Autowired
    KeywordResultMapper keywordResultMapper;

    @Override
    public List<KeywordResult> keywordHistory(Map<String, Object> useridMap) {

        Integer userid = (Integer) useridMap.get("userid");

        List<KeywordResult> resultList = keywordResultMapper.findKeywordWithNum(userid);

        System.out.print(resultList);

        return resultList;
    }

}
