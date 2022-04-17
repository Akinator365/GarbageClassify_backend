package cn.akinator.garbage.service;

import cn.akinator.garbage.entity.QuestionBank;
import cn.akinator.garbage.entity.SpeechClassify;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 语音识别记录 服务类
 * </p>
 */
public interface SpeechClassifyService extends IService<SpeechClassify> {

    void speechHandle(String fileName, String speechResult, List<QuestionBank> list, String speechStr, QuestionBank questionBankOne);
}
