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
    	 String a=execCMD("net use \\\\192.168.200.184\\ipc$ Qrst@2018/Administrator");
    	 System.out.println(a);
    	String back = execCMD("netstat -e");
    	   System.out.println("back:"+back);
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
//    	String back=execCMD("netstat -e");
    	String[] arr = back.split("\\s+");
     
        for(String ss : arr){
            System.out.println("ss:"+ss);
        }
        
        Long count=Long.parseLong(arr[4])+Long.parseLong(arr[5]);
//        String a=add(arr[4],arr[5]);
        System.out.println("带宽："+arr[3]+" "+count+"：：");
//        System.out.println("as:"+a);

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
