package cn.lxsir.uniapp.mapper;

import cn.lxsir.uniapp.entity.KeywordResult;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 关键词+结果记录表 Mapper 接口
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
public interface KeywordResultMapper extends BaseMapper<KeywordResult> {

    List<KeywordResult> findKeywordWithNum(int userid);

}
