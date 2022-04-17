package cn.akinator.garbage.vo;

import lombok.Data;

/**
 * @描述
 */
@Data
public class SpeechResultVo {
    private String corpus_no;
    private String err_msg;
    private String err_no;
    private String[] result;
    private String sn;
}
