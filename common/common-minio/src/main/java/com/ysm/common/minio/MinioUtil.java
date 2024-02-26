package com.ysm.common.minio;

import cn.hutool.core.io.IoUtil;
import com.ysm.common.core.domain.CommonResult;
import com.ysm.common.core.utils.StringUtils;
import io.minio.*;
import io.minio.errors.MinioException;
import io.minio.messages.DeleteObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Component
@Slf4j
public class MinioUtil {


    private MinioClient minioClient;

    private static final String PATH_PREFIX = "10.21.32.243:9000/timelinefeed/";

    @Autowired
    public void setMinioClient(@Qualifier("MinioClient") MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Value("${minio.bucket1.bucket}")
    private String defaultBucketName;


    /**
     * 上传文件到MinIO桶
     *
     * @param objectName  存储对象的名称
     * @param inputStream 文件输入流
     * @param contentType 文件类型
     */
    public void uploadFile(String objectName, InputStream inputStream, String contentType) {
        this.uploadFile(defaultBucketName, objectName, inputStream, contentType);
    }

    /**
     * 使用MultipartFile进行文件上传
     *
     * @param file        文件名
     * @param objectName  对象名
     * @param contentType 类型
     * @return
     * @throws Exception
     */
    public String uploadFile(MultipartFile file, String objectName, String contentType) throws Exception {
        InputStream inputStream = file.getInputStream();
        String filename = null;
        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(defaultBucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(defaultBucketName).build());
            }
            String[] split = objectName.split("\\.");
            filename = new Date().getTime() + "." + split[split.length - 1];

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(defaultBucketName)
                    .object(filename)
                    .contentType(contentType)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();

            minioClient.putObject(putObjectArgs);
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            handleMinioException(e);
        }
        return PATH_PREFIX + filename;
    }

    /**
     * 上传文件到MinIO桶
     *
     * @param bucketName  MinIO桶的名称
     * @param objectName  存储对象的名称
     * @param inputStream 文件输入流
     * @param contentType 文件类型
     */
    public void uploadFile(String bucketName, String objectName, InputStream inputStream, String contentType) {

        try {
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!isExist) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .contentType(contentType)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();

            minioClient.putObject(putObjectArgs);
        } catch (MinioException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            handleMinioException(e);
        }
    }


    /**
     * 从MinIO桶下载文件
     *
     * @param objectName 存储对象的名称
     * @return 文件输入流
     */
    public InputStream downloadFile(String objectName) {
        return this.downloadFile(defaultBucketName, objectName);
    }

    public boolean downloadFile(String objectName, HttpServletResponse response) {
        InputStream inputStream = null;
        OutputStream outputStream = null;
        String filename = null;
        try {
            if (StringUtils.isBlank(objectName)) {
                response.setHeader("Content-type", "text/html;charset=UTF-8");
                String data = "文件下载失败";
                OutputStream ps = response.getOutputStream();
                ps.write(data.getBytes(StandardCharsets.UTF_8));
                return false;
            }

            outputStream = response.getOutputStream();
            // 获取文件对象

            inputStream = downloadFile(filename);
            byte[] buf = new byte[1024];
            int length = 0;
            response.reset();
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    URLEncoder.encode(objectName.substring(objectName.lastIndexOf("/") + 1), "UTF-8"));
            response.setContentType("application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            // 输出文件
            while ((length = inputStream.read(buf)) > 0) {
                outputStream.write(buf, 0, length);
            }
            inputStream.close();
        } catch (Throwable ex) {
            response.setHeader("Content-type", "text/html;charset=UTF-8");
            String data = "文件下载失败";
            try {
                OutputStream ps = response.getOutputStream();
                ps.write(data.getBytes(StandardCharsets.UTF_8));
                return false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;

    }


    /**
     * 从MinIO桶下载文件
     *
     * @param bucketName MinIO桶的名称
     * @param objectName 存储对象的名称
     * @return 文件输入流
     */
    public InputStream downloadFile(String bucketName, String objectName) {
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            // 读取文件内容到字节数组
            try (InputStream inputStream = minioClient.getObject(getObjectArgs)) {
                byte[] byteArray = IoUtil.readBytes(inputStream);
                return new ByteArrayInputStream(byteArray);
            }
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            handleMinioException(e);
            return null;
        }
    }


    /**
     * 从MinIO桶删除文件
     *
     * @param objectName 存储对象的名称
     */
    public void deleteFile(String objectName) {
        this.deleteFile(defaultBucketName, objectName);
    }

    /**
     * 从MinIO桶删除文件
     *
     * @param bucketName MinIO桶的名称
     * @param objectName 存储对象的名称
     */
    public void deleteFile(String bucketName, String objectName) {
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            minioClient.removeObject(removeObjectArgs);
        } catch (MinioException | IOException | InvalidKeyException | NoSuchAlgorithmException e) {
            handleMinioException(e);
        }
    }


    /**
     * 批量删除MinIO桶中的文件
     *
     * @param objectNames 存储对象的名称列表
     */
    public void deleteFiles(List<String> objectNames) {
        this.deleteFiles(defaultBucketName, objectNames);
    }

    /**
     * 批量删除MinIO桶中的文件
     *
     * @param bucketName  MinIO桶的名称
     * @param objectNames 存储对象的名称列表
     */
    public void deleteFiles(String bucketName, List<String> objectNames) {
        try {
            List<DeleteObject> objectsToDelete = new ArrayList<>();
            for (String objectName : objectNames) {
                objectsToDelete.add(new DeleteObject(objectName));
            }

            RemoveObjectsArgs removeObjectsArgs = RemoveObjectsArgs.builder()
                    .bucket(bucketName)
                    .objects(objectsToDelete)
                    .build();

            minioClient.removeObjects(removeObjectsArgs);
        } catch (Exception e) {
            handleMinioException(e);
        }
    }

    /**
     * 断点下载
     *
     * @param bucketName 存储桶
     * @param objectName 文件名称
     * @param offset     起始字节的位置
     * @param length     要读取的长度
     * @return 二进制流
     */
    public InputStream getObject(String bucketName, String objectName, long offset, long length) throws Exception {
        return minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .offset(offset)
                        .length(length)
                        .build());
    }


    /**
     * 判断MinIO桶中的对象是否存在
     *
     * @param objectName 存储对象的名称
     * @return 文件是否存在
     */
    public boolean doesObjectExist(String objectName) {
        return this.doesObjectExist(defaultBucketName, objectName);
    }

    /**
     * 判断MinIO桶中的对象是否存在
     *
     * @param bucketName MinIO桶的名称
     * @param objectName 存储对象的名称
     * @return 文件是否存在
     */
    public boolean doesObjectExist(String bucketName, String objectName) {
        boolean exist = true;
        try {
            minioClient.statObject(StatObjectArgs.builder().bucket(bucketName).object(objectName).build());
        } catch (Exception e) {
            exist = false;
        }
        return exist;
    }

    /**
     * 处理MinIO异常
     *
     * @param e MinIO异常
     */
    private void handleMinioException(Exception e) {
        log.error("minio操作出现异常，异常为:{}", e.toString());
        throw new RuntimeException(e);
    }
}