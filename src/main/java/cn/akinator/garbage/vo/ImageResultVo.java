package cn.akinator.garbage.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc: 图片识别结果
 */
@Data
public class ImageResultVo  implements Serializable{
    private static final long serialVersionUID = 1L;

    private double score;
    private String root;
    private String keyword;
}
