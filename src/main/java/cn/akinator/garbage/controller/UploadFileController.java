package cn.akinator.garbage.controller;

import cn.akinator.garbage.service.BaiduService;
import cn.akinator.garbage.service.QuestionBankService;
import cn.akinator.garbage.service.CommonService;
import cn.akinator.garbage.service.ImageClassifyService;
import com.baomidou.mybatisplus.extension.api.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Desc: 上传文件接口
 */
@Slf4j
@RestController
@RequestMapping("/upload")
@Api(value = "上传 Controller",tags = {"上传文件处理接口"})

public class UploadFileController {

    @Autowired
    BaiduService baiduService;

    @Autowired
    CommonService commonService;

    @Autowired
    ImageClassifyService imageClassifyService;

    @Autowired
    QuestionBankService qbService;

    @Autowired
    RestTemplate restTemplate;

    @Value("${upload.image.path}")
    String imagePath;
    @Value("${upload.record.path}")
    String recordPath;
    @Value("${upload.excel.path}")
    String excelPath;

    @GetMapping("/test")
    public R test(@RequestParam String testLog){

            log.info("testLog:"+testLog);
            return R.ok(testLog);
        }

    @PostMapping("/image")
    @ApiOperation(value = " 上传图像接口")
    public R uploadImage(@RequestParam("file") MultipartFile file,@RequestParam("userid") String userid) {
        if (file.isEmpty()) {
            return R.failed("文件为空");
        }
        String path = imageClassifyService.handleUploadFile(file,imagePath);
        //Map<String, Object> map = baiduService.imageClassify(path);
        Map<String, Object> map = imageClassifyService.imageMatch(path,userid);
        return R.ok(map);
    }

    @PostMapping("/record")
    @ApiOperation(value = " 通过上传音频进行语音识别其垃圾分类")
    public R uploadRecord(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return R.failed("文件为空");
        }
        String path = commonService.handleUploadFile(file,recordPath);
        Map<String, Object> map = baiduService.apiSpecch(path);
        return R.ok(map);
    }

    @PostMapping("/excel")
    @ApiOperation(value = " 通过上传excel 文件进行 垃圾总库的完善")
    public R uploadExcel(@RequestParam("excel") MultipartFile file){
        String filepath = commonService.handleUploadFile(file, excelPath);
        Map<String, Object> map = qbService.uploadExcel(filepath);
        return R.ok(map);

    }

}
