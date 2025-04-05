package cn.zimeedu.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

// 阿里云OSS上传文件工具类
@Component //  @Controller（将类交给IOC容器）  @Component 声明bean的基础注解 不属于三层架构，用此注解
public class AliyunOSSperator {
//    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。 使用阿里云OSS这个bucket对应的域名
//    @Value("${aliyun.oss.endopoint}")  // 从配置文件中获取值
//    private String endpoint;
//    // 填写Bucket名称
//    @Value("${aliyun.oss.bucketName}")
//    private String bucketName;
//    @Value("${aliyun.oss.reqion}")
//    // 填写Bucket所在地域
//    private String region;
    @Autowired
    private AliyunOSSProperties aliyunOSSperator;
    // 填写Object完整路径
    private String objectName = "";

    public String upload(byte[] content, String originalFilename) throws Exception {
        String endpoint = aliyunOSSperator.getEndpoint();
        String bucketName = aliyunOSSperator.getBucketName();
        String region = aliyunOSSperator.getReqion();
        // 从环境变量中获取访问凭证。运行本代码示例之前，请确保已设置环境变量OSS_ACCESS_KEY_ID和OSS_ACCESS_KEY_SECRET。
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();

        // 填写Object完整路径，例如202406/1.png(每个月一个文件夹方便维护)。Object完整路径中不能包含Bucket名称。
        //获取当前系统日期的字符串,格式为 yyyy/MM
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
        //生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
        // 把图片放到指定文件夹下
        this.objectName = dir + "/" + newFileName;

        // 创建OSSClient实例。 创建OSS客户端对象
        ClientBuilderConfiguration clientBuilderConfiguration = new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();

        try {
            //此方法允许你将本地文件、字节数组、输入流等数据上传到指定的 OSS Bucket 中。存储空间 上传文件路径与文件名 具体文件的字节数组
            // 进行文件上传 将文件内容content上传到对应的bucketName这个存储空间中 文件路径和文件名为objectName
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        }finally {
            ossClient.shutdown();
        }

        // 返回值为OSS当中的访问路径  访问路劲组装  https://javaailearn.oss-cn-beijing.aliyuncs.com/imges/001.jpg
        return endpoint.split("//")[0] + "//" + bucketName + "." + endpoint.split("//")[1] + "/" + objectName;
    }

}
