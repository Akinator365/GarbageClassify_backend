package cn.akinator.garbage.service;

import cn.akinator.garbage.entity.ImageClassify;
import cn.akinator.garbage.entity.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图像识别记录 服务类
 * </p>
 */
public interface ImageClassifyService extends IService<ImageClassify> {

    void imageHandle(String filename, JSONObject res, JSONObject resultVo, List<QuestionBank> questionBanks, QuestionBank questionBankOne);

    Map<String, Object> imageMatch(String fileName, String userid);

    String handleUploadFile(MultipartFile file, String path);

    List<ImageClassify> imageHistory(Map<String,Object> useridMap);

}
