package top.hunaner.lol.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ResourceUtil {
    //列出指定目录（包括其子目录）下所有文件
    public static List<String> listDirectory(File dir,String filter) {
        if(!dir.exists()){
            throw new IllegalArgumentException("Directory:"+dir+" doesn`t exists!");
        }
        if(!dir.isDirectory()){
            throw new IllegalArgumentException(dir+" is now a directory!");
        }
        List<String> fileList = new ArrayList<>();
        //System.out.println(dir.list());
        // String[] filenames = dir.list();
        // for(String string:filenames){
        // 	System.out.println(dir+"/"+string);
        // }
        //返回直接子目录的File对象
        File[] files = dir.listFiles();
        if(files!=null && files.length>0){
            for(File file:files){
                String filename = file.getName();
                if(file.isDirectory()){
                    //递归操作
//                    System.out.println(filename+"-");
                    listDirectory(file,filter);
                }else if(ResourceUtil.filterFile(filename,filter)){
//                    System.out.println(filename);
                    fileList.add(filename);
                }
            }
        }
        return  fileList;
    }
    private static boolean filterFile(String fileName,String filter){
        if(fileName.contains(filter)){
            return  true;
        }
        return false;
    }
}
