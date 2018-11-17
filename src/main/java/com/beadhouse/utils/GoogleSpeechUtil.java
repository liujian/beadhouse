package com.beadhouse.utils;

import com.google.cloud.speech.v1p1beta1.*;
import com.google.cloud.speech.v1p1beta1.RecognitionConfig.AudioEncoding;
import com.google.protobuf.ByteString;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class GoogleSpeechUtil {

    /**
     * Demonstrates using the Speech API to transcribe an audio file.
     */
    public static void main(String... args) throws Exception {
      String str = translateAudio(new File("D:\\scratchFile\\20181031102523041800.amr"));
      
      System.out.println(str);
    }

    public static String translateAudio(File file) throws Exception {
        // Instantiates a client
        try (SpeechClient speechClient = SpeechClient.create()) {

            // The path to the audio file to transcribe
//            String fileName = "D://scratchFile//4840fc89-48d9-4dfc-8805-01f2a88db4d3.amr";
//            Path path = Paths.get(fileName);
            // Reads the audio file into memory
            Path path = Paths.get(file.getPath());
            byte[] data = Files.readAllBytes(path);

            ByteString audioBytes = ByteString.copyFrom(data);

            // Builds the sync recognize request
            RecognitionConfig config = RecognitionConfig.newBuilder()
                    .setEncoding(AudioEncoding.AMR)
                    .setSampleRateHertz(8000)
                    .setLanguageCode("en-US")
                    .setEnableAutomaticPunctuation(true)
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
//                    .setContent(ByteString.copyFrom(byteMerger(data)))
                    .setContent(audioBytes)
                    .build();

            // Performs speech recognition on the audio file
            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

            StringBuffer stringBuffer = new StringBuffer();
            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s%n", alternative.getTranscript());
                stringBuffer.append(alternative.getTranscript());
            }
            return stringBuffer.toString();
        }
    }
}