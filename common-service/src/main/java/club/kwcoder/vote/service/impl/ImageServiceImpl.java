package club.kwcoder.vote.service.impl;

import club.kwcoder.vote.bean.ResultBean;
import club.kwcoder.vote.dataobject.ImageDO;
import club.kwcoder.vote.mapper.generate.ImageMapper;
import club.kwcoder.vote.service.ImageService;
import cn.hutool.crypto.digest.DigestUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@PropertySource({"classpath:config/cos.properties"})
public class ImageServiceImpl implements ImageService {

    @Value("${cos.secretId}")
    private String secretId;
    @Value("${cos.secretKey}")
    private String secretKey;
    @Value("${cos.region}")
    private String region;
    @Value("${cos.bucket}")
    private String bucket;

    @Value("${cos.prefix}")
    private String cosPrefix;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public ResultBean<ImageDO> upload(MultipartFile file) {
        if (file.isEmpty()) {
            return ResultBean.notAcceptable("文件为空！");
        }

        File localFile;
        String md5Hex;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            md5Hex = DigestUtil.md5Hex(localFile);
            ImageDO imageDO = imageMapper.selectByPrimaryKey(md5Hex);
            if (null != imageDO) {
                return ResultBean.success("上传成功！", imageDO);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResultBean.systemError();
        }

        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        Region regionConf = new Region(region);
        ClientConfig clientConfig = new ClientConfig(regionConf);
        clientConfig.setHttpProtocol(HttpProtocol.https);
        COSClient cosClient = new COSClient(cred, clientConfig);

        file.getName();
        file.getContentType();
        file.getOriginalFilename();

        String originName = file.getOriginalFilename();
        assert originName != null;
        String[] split = originName.split("\\.");


        String key = UUID.randomUUID().toString().split("-")[0] + System.currentTimeMillis() + "." + split[split.length - 1];
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, localFile);
        cosClient.putObject(putObjectRequest);

        ImageDO imgDO = new ImageDO(md5Hex, cosPrefix + key);
        imageMapper.insert(imgDO);
        cosClient.shutdown();
        return ResultBean.success("上传成功！", imgDO);
    }
}
