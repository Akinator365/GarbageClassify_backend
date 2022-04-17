package cn.lxsir.uniapp.service;

import cn.lxsir.uniapp.entity.ImageClassify;
import cn.lxsir.uniapp.entity.QuestionBank;
import com.baomidou.mybatisplus.extension.service.IService;
import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图像识别记录 服务类
 * </p>
 *
 * @author luoxiang
 * @since 2019-07-13
 */
public interface ImageClassifyService extends IService<ImageClassify> {

    void imageHandle(String filename, JSONObject res, JSONObject resultVo, List<QuestionBank> questionBanks, QuestionBank questionBankOne);

    Map<String, Object> imageMatch(String fileName, String userid);

    String handleUploadFile(MultipartFile file, String path);

    List<ImageClassify> imageHistory(Map<String,Object> useridMap);

}
