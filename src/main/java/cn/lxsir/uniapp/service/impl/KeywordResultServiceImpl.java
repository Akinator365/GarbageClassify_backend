package cn.lxsir.uniapp.service.impl;

import cn.lxsir.uniapp.entity.ChallengeResult;
import cn.lxsir.uniapp.entity.ImageClassify;
import cn.lxsir.uniapp.entity.KeywordResult;
import cn.lxsir.uniapp.entity.KeywordSearchNum;
import cn.lxsir.uniapp.mapper.KeywordResultMapper;
import cn.lxsir.uniapp.service.KeywordResultService;
import cn.lxsir.uniapp.service.KeywordSearchNumService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 关键词+结果记录表 服务实现类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
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
