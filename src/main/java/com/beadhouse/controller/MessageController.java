package com.beadhouse.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import com.beadhouse.in.AnswerQuestParam;
import com.beadhouse.in.GetMessageParam;
import com.beadhouse.utils.AWSClient;
import com.beadhouse.utils.FFMpegMusicUtil;
import com.beadhouse.utils.GoogleSpeechUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.beadhouse.in.SendMessageParam;
import com.beadhouse.in.UpdateChatParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.service.SendMessageService;
import com.beadhouse.service.impl.ElderServiceImpl;
import com.beadhouse.utils.UploadUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class MessageController {
    @Value("${aws.AWS_ACCESS_KEY}")
    private String AWS_ACCESS_KEY;

    @Value("${aws.AWS_SECRET_KEY}")
    private String AWS_SECRET_KEY;

    @Value("${aws.regionName}")
    private String regionName;

    @Value("${aws.bucketName}")
    private String bucketName;
    
    @Value("${aws.prifix}")
    private String prifix;

    @Autowired
    SendMessageService sendMessageService;
    
	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);


    /**
     * 发送消息
     *
     * @param request
     * @return
     */
    @RequestMapping("sendMessage")
    @ResponseBody
    public BasicData sendMessage(SendMessageParam param, HttpServletRequest request) {
        if(param.getToken() == null) param = new Gson().fromJson(request.getParameter("json"), SendMessageParam.class);
        //上传文件至aws
        MultipartFile file = UploadUtil.getFile(request);
        
        String uploadFileName = null;
        String fileText = null;
        File scratchFile=null;
        File audioFile = null;
        if (file != null) {
        	 try {
        	    String fileName = file.getOriginalFilename();
                String suffic = fileName.substring(fileName.lastIndexOf("."));
                scratchFile = File.createTempFile("scratchFile", suffic, new File(prifix));
                InputStream inputStream = file.getInputStream();
                FileUtils.copyInputStreamToFile(inputStream, scratchFile);
                //语音转文字
                if (fileName.endsWith(".amr")) {
                    fileText = GoogleSpeechUtil.translateAudio(scratchFile);
                } else {
                    String audioPath = FFMpegMusicUtil.videoToAudio(scratchFile.getPath(), UUID.randomUUID() + ".amr");
                    audioFile = new File(audioPath);
                    if (audioFile.exists()) {
                        fileText = GoogleSpeechUtil.translateAudio(audioFile);
                    }
                }
                logger.info("fileText-------"+fileText);
                //上传文件至亚马逊
                logger.info("start aws uploadFileToBucket");
                AWSClient.init_with_key(AWS_ACCESS_KEY, AWS_SECRET_KEY, regionName);
                uploadFileName = AWSClient.uploadFileToBucket(scratchFile, AWSClient.getFileName() + suffic, bucketName);
                if (uploadFileName == null) return BasicData.CreateErrorMsg("文件上传失败");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
            	 logger.info("uploadFileName-------"+uploadFileName);
                if (scratchFile != null &&scratchFile.exists()) {
                    scratchFile.delete();
                }
                
                if (audioFile != null && audioFile.exists()) {
                    audioFile.delete();
                }
            }
        }
        return sendMessageService.sendMessage(param, uploadFileName,fileText);
    }

    /**
     * 获取聊天记录
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("getChatHistory")
    @ResponseBody
    public BasicData getChatHistory(@Valid @RequestBody GetMessageParam param, HttpServletRequest request){
        return sendMessageService.getChatHistory(param);
    }
    
    /**
     * 获取老人端聊天记录
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("elder/getChatHistory")
    @ResponseBody
    public BasicData getElderChatHistory(@Valid @RequestBody GetMessageParam param, HttpServletRequest request){
        return sendMessageService.getElderChatHistory(param);
    }
    
    
    
    
    /**
     * 修改聊天记录
     */
    @RequestMapping("updateChatHistory")
    @ResponseBody
    public BasicData updateChatHistory(@Valid @RequestBody UpdateChatParam param, HttpServletRequest request){
    	
        return sendMessageService.updateChatHistory(param);
    }

    /**
     * 回答问题
     *
     * @param request
     * @return
     */
    @RequestMapping("answerQuestion")
    @ResponseBody
    public BasicData answerQuestion(AnswerQuestParam param, HttpServletRequest request){
        if(param.getToken() == null) param = new Gson().fromJson(request.getParameter("json"), AnswerQuestParam.class);
        //上传文件至aws
        MultipartFile file = UploadUtil.getFile(request);
        String uploadFileName = null;
        String fileText = null;
        File scratchFile= null;
        File audioFile = null;
        if (file != null) {
        	 try {
                String fileName = file.getOriginalFilename();
                String suffic = fileName.substring(fileName.lastIndexOf("."));
                scratchFile = File.createTempFile("scratchFile", suffic, new File(prifix));
                InputStream inputStream = file.getInputStream();
                FileUtils.copyInputStreamToFile(inputStream, scratchFile);

                if (fileName.endsWith(".amr")) {
                    fileText = GoogleSpeechUtil.translateAudio(scratchFile);
                } else {
                    String audioPath = FFMpegMusicUtil.videoToAudio(scratchFile.getPath(), UUID.randomUUID() + ".amr");
                    audioFile = new File(audioPath);
                    if (audioFile.exists()) {
                        fileText = GoogleSpeechUtil.translateAudio(audioFile);
                    }
                }
                AWSClient.init_with_key(AWS_ACCESS_KEY, AWS_SECRET_KEY, regionName);
                System.out.println(AWSClient.getFileName());
                uploadFileName = AWSClient.uploadFileToBucket(scratchFile, AWSClient.getFileName() + suffic, bucketName);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (scratchFile !=null&&scratchFile.exists()) {
                    scratchFile.delete();
                }
                if (audioFile != null && audioFile.exists()) {
                    audioFile.delete();
                }
            }
        }
        return sendMessageService.answerQuestion(param, uploadFileName, fileText);
    }
    

}
