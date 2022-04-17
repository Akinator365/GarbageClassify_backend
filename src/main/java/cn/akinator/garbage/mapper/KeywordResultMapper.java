package cn.akinator.garbage.mapper;

import cn.akinator.garbage.entity.KeywordResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 关键词+结果记录表 Mapper 接口
 * </p>
 */
public interface KeywordResultMapper extends BaseMapper<KeywordResult> {

    List<KeywordResult> findKeywordWithNum(int userid);

}
