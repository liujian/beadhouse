package com.beadhouse.utils;

import java.io.File;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class FFMpegMusicUtil {

    private static final Logger logger = Logger.getLogger(FFMpegMusicUtil.class);
    @Value("${ffmpeg.path}")
    private String FFMPEG_PATH;//ffMpeg解压的bin地址
    @Value("${temp.path}")
    private String TMP_PATH;//转为音频后 存放的地址

    /**
     * 视频转音频 返回上传的音频地址
     */
    public String videoToAudio(String videoUrl,String videoName){
        String musicUrl = "";
        try {
            String aacFile = TMP_PATH + videoName;
            String command = FFMPEG_PATH + "ffmpeg -i "+ videoUrl + " -c:a libopencore_amrnb -ac 1 -ar 8000 -b:a 12.20k -y "+ aacFile;
            logger.info("video to audio command : " + command);
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            musicUrl = aacFile;
        } catch (Exception e) {
            logger.error("音频提取失败，视频地址："+videoUrl, e);
        }
        return musicUrl;
    }
}
