package com.beadhouse.utils;

import java.io.File;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class FFMpegMusicUtil {

    private static final Logger logger = Logger.getLogger(FFMpegMusicUtil.class);
    private static String FFMPEG_PATH = "D:\\养老院项目\\ffmpeg-20181018-f72b990-win64-static\\bin\\";//ffMpeg解压的bin地址
                                         
    private static String TMP_PATH = "D:\\scratchFile\\";//转为音频后 存放的地址
    private static String MUSIC_ADDRESS;

    public static void main(String... args) throws Exception {
        videoToAudio("D:\\liuj.mp4","liuj.amr");
    }

    /**
     * 视频转音频 返回上传的音频地址
     * @param videoUrl视频地址
     */
    public static String videoToAudio(String videoUrl,String videoName){
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
