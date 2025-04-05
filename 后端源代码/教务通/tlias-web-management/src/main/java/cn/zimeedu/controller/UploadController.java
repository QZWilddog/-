package cn.zimeedu.controller;


import cn.zimeedu.pojo.Result;
import cn.zimeedu.utils.AliyunOSSperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

// 实现文件上传功能  上传到阿里云oss 对象存储
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Autowired  // 注入阿里云OSS工具类
    private AliyunOSSperator aliyunOSSperator;

    // 阿里云OSS方案 存储方案
    @PostMapping
    public Result upload(MultipartFile file) throws Exception{
        // 输出日志 将文件原始名输出到日志
        log.info("文件上传：{}", file.getOriginalFilename());
        // 将文件交给OSS存储管理
        String url = aliyunOSSperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("文件上传OSS成功{}", url);

        return Result.success(url);
    }


    /**
     * 本地磁盘存储方案 项目中不推荐
     * */
//    @PostMapping()   //  如果前端传递的参数名与服务器方法名一直就可以省略（省略@RequestParam注解）
//    public Result upload(String name, String age, MultipartFile file) throws Exception{ // Spring中提供了一个API：MultipartFile，使用这个API就可以来接收到上传的文件
//
//        log.info("文件上传：{}，{}，{}", name, age, file);
//        // 获取原始文件名  可能重复 会被覆盖
//        String originalFilename = file.getOriginalFilename();
//        // 生成新不重复的文件名
//        String  newFileNmae = UUID.randomUUID() +  originalFilename.substring(originalFilename.lastIndexOf("."));
//
//        // 保存文件到本地  transferTo 方法用于将上传的文件保存到指定的目标位置。方法会将上传文件的内容从临时存储位置（通常是服务器的临时目录）复制到目标文件中。
//        //清理临时文件：文件复制完成后，方法会删除上传文件的临时副本
//        file.transferTo(new File("E:\\系统文件\\桌面\\code\\后端学习项目\\wen-ai-project02\\tlias-web-management\\src\\main\\resources\\static\\imges\\" + newFileNmae));
//        return Result.success();
//    }
}
