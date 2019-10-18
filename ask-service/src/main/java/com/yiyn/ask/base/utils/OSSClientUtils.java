package com.yiyn.ask.base.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OSSClientUtils
{
  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("#{configProperties['oss.access_id']}")
  private String ACCESS_ID;

  @Value("#{configProperties['oss.access_key']}")
  private String ACCESS_KEY;

  @Value("#{configProperties['oss.bucketName']}")
  private String bucketName;

  @Value("#{configProperties['oss.end_point']}")
  private String END_POINT;
  private static final String contentType = "image/";

  public String uploadFile(String folderName, InputStream inputStream, String ofileName)
    throws Exception
  {
    String newFileName = folderName + "/" + String.valueOf(System.currentTimeMillis()) + ofileName.substring(ofileName.lastIndexOf("."));

    OSSClient client = new OSSClient(this.END_POINT, this.ACCESS_ID, this.ACCESS_KEY);
    uploadFile(client, this.bucketName, newFileName, inputStream, "image/");
    return generateFilePath(this.bucketName, newFileName);
  }

  public String uploadFileByBytes(String folderName, ByteArrayInputStream inputStream)
    throws Exception
  {
    String newFileName = folderName + "/" + String.valueOf(System.currentTimeMillis());

    OSSClient client = new OSSClient(this.END_POINT, this.ACCESS_ID, this.ACCESS_KEY);
    ObjectMetadata objectMeta = new ObjectMetadata();

    objectMeta.setContentType("image/");
    client.putObject(this.bucketName, newFileName, inputStream, objectMeta);
    client.shutdown();
    return generateFilePath(this.bucketName, newFileName);
  }

  private void uploadFile(OSSClient client, String bucketName, String newFileName, InputStream inputStream, String contentType)
    throws Exception
  {
    ObjectMetadata objectMeta = new ObjectMetadata();

    objectMeta.setContentType(contentType);
    client.putObject(bucketName, newFileName, inputStream, objectMeta);
  }

  /**
   * 删除文件
   * @param bucketName
   * @param filePath
   * @throws Exception
   */
  public void deleteFile(String filePath)
    throws Exception
  {
	  OSSClient ossClient = new OSSClient(this.END_POINT, this.ACCESS_ID, this.ACCESS_KEY);
      boolean exist = ossClient.doesObjectExist(this.bucketName, filePath);
      if (!exist) {
          logger.info("文件不存在,filePath={}", filePath);
      }
      
      logger.info("删除文件,filePath={}", filePath);
      ossClient.deleteObject(this.bucketName, filePath);
  }
  
  private String generateFilePath(String bucketName, String fileName) {
    return "https://" + bucketName + ".oss-cn-hangzhou.aliyuncs.com/" + fileName;
  }
  
  public static void main(String[] args){
	  OSSClient ossClient = new OSSClient("http//oss-cn-hangzhou.aliyuncs.com", "LTAIwtXK6M12ihFc", "zesu1Y7Eb1ZL9W7Z8XCXuW9nBf0cMy");
      boolean exist = ossClient.doesObjectExist("iesms-upload-test","http://iesms-upload-test.oss-cn-hangzhou.aliyuncs.com/用户证件1568278884151/pic.jpg");
      if (!exist) {
          System.out.println("文件不存在,filePath={}");
      }
      
      System.out.println("删除文件,filePath={}");
      ossClient.deleteObject("iesms-upload-test", "http://iesms-upload-test.oss-cn-hangzhou.aliyuncs.com/用户证件1568278884151/pic.jpg");
  }
}