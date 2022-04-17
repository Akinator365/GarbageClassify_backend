package cn.akinator.garbage.service.impl;

import cn.akinator.garbage.entity.KeywordSearchNum;
import cn.akinator.garbage.mapper.KeywordSearchNumMapper;
import cn.akinator.garbage.service.KeywordSearchNumService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 搜索关键词+次数记录表 服务实现类
 * </p>
 */
@Service
public class KeywordSearchNumServiceImpl extends ServiceImpl<KeywordSearchNumMapper, KeywordSearchNum> implements KeywordSearchNumService {

}
