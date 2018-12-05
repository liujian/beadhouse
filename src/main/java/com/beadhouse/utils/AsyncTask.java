package com.beadhouse.utils;

import com.beadhouse.dao.ElderUserMapper;
import com.beadhouse.dao.MessageMapper;
import com.beadhouse.dao.UserMapper;
import com.beadhouse.domen.ChatHistory;
import com.beadhouse.domen.ElderUser;
import com.beadhouse.domen.User;
import com.beadhouse.in.AnswerQuestParam;
import com.beadhouse.in.SendMessageParam;
import com.beadhouse.out.BasicData;
import com.beadhouse.out.ChatHistoryOut;
import com.beadhouse.service.impl.SendMessageServiceImpl;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class AsyncTask {
    private static final Logger logger = LoggerFactory.getLogger(AsyncTask.class);

    @Resource
    private FFMpegMusicUtil ffMpegMusicUtil;
    @Resource
    private FireBaseUtil fireBaseUtil;
    @Value("${google.SERVER_IMAGE}")
    private String SERVER_IMAGE;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private ElderUserMapper elderUserMapper;
    @Autowired
    private UserMapper userMapper;

    @Async
    public void asyncToHandleSendMessage(SendMessageParam param, File file, User user, ChatHistory chatHistory) {
        if (file != null) {
            translateAndUpload(file, chatHistory, true);
        }

        ElderUser elderUser = elderUserMapper.selectById(param.getElderUserId());
        try {
            ChatHistoryOut chatHistoryOut = messageMapper.getQuestById(chatHistory);
            String body = user.getFirstName() + " " + user.getLastName() + ":" + chatHistoryOut.getQuest();
            fireBaseUtil.pushFCMNotification(Constant.PUSH_TYPE_SEND_MESSAGE, new Gson().toJson(chatHistoryOut), body, elderUser.getFireBaseToken());
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }


    @Async
    public void asyncToHandleAnswerQuestion(AnswerQuestParam param, File file, ElderUser elderUser, ChatHistory chatHistory) {
        if (file != null) {
            translateAndUpload(file, chatHistory, false);
        }
        ChatHistoryOut chatHistoryOut = messageMapper.getQuestById(chatHistory);
        try {
            String body = elderUser.getElderFirstName() + " " + elderUser.getElderLastName() + ":" + chatHistoryOut.getElderUserResponse();
            fireBaseUtil.pushFCMNotification(Constant.PUSH_TYPE_ANSWER_QUESTION, new Gson().toJson(chatHistoryOut), body, userMapper.selectById(chatHistoryOut.getLoginUserId()).getFireBaseToken());
        } catch (IOException e) {
            System.out.println("e = " + e);
        }
    }

    private void translateAndUpload(File file, ChatHistory chatHistory, boolean isSend) {
        String uploadFileName = null;
        String fileText = null;
        File audioFile = null;
        try {
            //语音转文字
            String fileName = file.getName();
            if (fileName.endsWith(".amr")) {
                fileText = GoogleSpeechUtil.translateAudio(file);
            } else {
                String audioPath = ffMpegMusicUtil.videoToAudio(file.getPath(), UUID.randomUUID() + ".amr");
                audioFile = new File(audioPath);
                if (audioFile.exists()) {
                    fileText = GoogleSpeechUtil.translateAudio(audioFile);
                }
            }
            logger.info("fileText-------" + fileText);
            //上传文件至谷歌
            logger.info("start google uploadFileToBucket");
            uploadFileName = GoogleStorageUtil.uploadFile(file);
            if (isSend) {
                if (fileText != null) chatHistory.setVoicequest(fileText);
                if (fileText != null) chatHistory.setUserVoiceUrl(SERVER_IMAGE + uploadFileName);
                messageMapper.updateQuestion(chatHistory);
            } else {
                if (fileText != null) chatHistory.setElderUserResponse(fileText);
                if (fileText != null) chatHistory.setElderUserVoiceUrl(SERVER_IMAGE + uploadFileName);
                messageMapper.updateAnswer(chatHistory);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            logger.info("uploadFileName-------" + uploadFileName);
            if (file != null && file.exists()) {
                file.delete();
            }

            if (audioFile != null && audioFile.exists()) {
                audioFile.delete();
            }
        }
    }


}
