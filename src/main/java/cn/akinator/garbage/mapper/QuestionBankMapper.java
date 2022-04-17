package cn.akinator.garbage.mapper;

import cn.akinator.garbage.entity.QuestionBank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 题库表 Mapper 接口
 * </p>
 */
public interface QuestionBankMapper extends BaseMapper<QuestionBank> {
    List<QuestionBank> qbRandOne(@Param("num") Integer num);
}
