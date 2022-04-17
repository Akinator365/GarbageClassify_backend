package cn.lxsir.uniapp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 关键词+结果记录表
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
@Data
@Builder
@TableName("keyword_result")
@NoArgsConstructor
@AllArgsConstructor
public class KeywordResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("keyword")
    private String keyword;

    @TableField("result")
    private String result;

    @TableField("times")
    private LocalDateTime times;

    @TableField("userid")
    private String userid;

    @TableField("num")
    private Integer num;
}
