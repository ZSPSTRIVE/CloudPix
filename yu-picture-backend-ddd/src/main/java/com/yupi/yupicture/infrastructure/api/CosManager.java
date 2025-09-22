package com.yupi.yupicture.infrastructure.api;

import cn.hutool.core.io.FileUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.*;
import com.qcloud.cos.model.ciModel.persistence.PicOperations;
import com.yupi.yupicture.infrastructure.config.CosClientConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;

    /**
     * 上传对象并设置公有读
     *
     * @param key  唯一键
     * @param file 文件
     */
    public PutObjectResult putObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);
        // 设置公有读
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult result = cosClient.putObject(putObjectRequest);
        // 打印底层返回信息，方便调试
        System.out.println("ETag: " + result.getETag());
        System.out.println("RequestId: " + result.getRequestId());
        return result;
    }

    /**
     * 上传对象（附带图片信息）并设置公有读
     *
     * @param key  唯一键
     * @param file 文件
     */
    public PutObjectResult putPictureObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key, file);
        // 设置公有读
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);

        // 图片处理
        PicOperations picOperations = new PicOperations();
        picOperations.setIsPicInfo(1);

        List<PicOperations.Rule> rules = new ArrayList<>();
        // 压缩成 webp
        String webpKey = FileUtil.mainName(key) + ".webp";
        PicOperations.Rule compressRule = new PicOperations.Rule();
        compressRule.setFileId(webpKey);
        compressRule.setBucket(cosClientConfig.getBucket());
        compressRule.setRule("imageMogr2/format/webp");
        rules.add(compressRule);

        // 缩略图
        if (file.length() > 2 * 1024) {
            PicOperations.Rule thumbnailRule = new PicOperations.Rule();
            String thumbnailKey = FileUtil.mainName(key) + "_thumbnail." + FileUtil.getSuffix(key);
            thumbnailRule.setFileId(thumbnailKey);
            thumbnailRule.setBucket(cosClientConfig.getBucket());
            thumbnailRule.setRule(String.format("imageMogr2/thumbnail/%sx%s>", 256, 256));
            rules.add(thumbnailRule);
        }

        picOperations.setRules(rules);
        putObjectRequest.setPicOperations(picOperations);

        PutObjectResult result = cosClient.putObject(putObjectRequest);

        System.out.println("ETag: " + result.getETag());
        System.out.println("RequestId: " + result.getRequestId());

        return result;
    }

    /**
     * 下载对象
     */
    public COSObject getObject(String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        return cosClient.getObject(getObjectRequest);
    }

    /**
     * 删除对象
     */
    public void deleteObject(String key) {
        cosClient.deleteObject(cosClientConfig.getBucket(), key);
    }

    /**
     * 获取公网访问 URL
     */
    public String getPublicUrl(String key) {
        return String.format("https://%s-%s.cos.%s.myqcloud.com/%s",
                cosClientConfig.getBucket(),
                cosClientConfig.getAppId(),
                cosClientConfig.getRegion(),
                key);
    }
}
