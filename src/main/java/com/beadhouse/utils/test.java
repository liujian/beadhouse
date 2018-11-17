package com.beadhouse.utils;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @Author: liujian
 * @Description
 * @Date:Created in ${Time} ${Date}
 */
public class test {
    public static void main( String[] args ) {
    	// String back=execCMD("net use \\\\192.168.200.72\\ipc$ 111111/Administrator");
    	 
    //	String a = execCMD("netstat -e -m \\\\192.168.200.72");
     //   String as=execCMD("ipconfig");

//        String command1="shutdown -m \\192.168.1.195 -r";
//        String command2="net view \\192.168.1.195";
//        String command3="cmd /c start e:/blog";
//        String command4="cmd /c start f:/视频";
//        StringBuilder builder = new StringBuilder();
//        builder.append("net use ")
//                .append("\\\192.168.200.190")
//                .append("\\ipc");
//        String[] arrs={command1,command2,command3,command4};
//        try {
//            Process process = Runtime.getRuntime().exec(builder.toString());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    	String back=execCMD("netstat -e");
        System.out.println("back:"+back);
    //    System.out.println("as:"+a);

    }

    public static String execCMD(String command) {
        StringBuilder sb =new StringBuilder();
        try {
            Process process=Runtime.getRuntime().exec(command);
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream(),"GBK"));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                sb.append(line+"\n");
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }

}
