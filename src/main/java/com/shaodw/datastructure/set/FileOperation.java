package com.shaodw.datastructure.set;

import com.shaodw.datastructure.array.Array;

import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileOperation {
    public static boolean readFile(String fileName, Array<String> array){
        if (fileName == null || array == null){
            System.out.println("filename is null or words is null");
            return false;
        }
        Scanner scanner;

        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fileInputStream), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }else {
                return false;
            }
        }catch (IOException ioe){
            System.out.println("cannot open "+ fileName);
            return false;
        }



        //简单分词
        if (scanner.hasNextLine()){
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); )
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    array.addLast(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else
                    i++;
        }
        return true;
    }
    // 寻找字符串s中，从start的位置开始的第一个字母字符的位置
    private static int firstCharacterIndex(String s, int start){

        for( int i = start ; i < s.length() ; i ++ )
            if( Character.isLetter(s.charAt(i)) )
                return i;
        return s.length();
    }

    public static boolean readFile(String fileName, ArrayList<String> array){
        if (fileName == null || array == null){
            System.out.println("filename is null or words is null");
            return false;
        }
        Scanner scanner;

        try {
            File file = new File(fileName);
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                scanner = new Scanner(new BufferedInputStream(fileInputStream), "UTF-8");
                scanner.useLocale(Locale.ENGLISH);
            }else {
                return false;
            }
        }catch (IOException ioe){
            System.out.println("cannot open "+ fileName);
            return false;
        }



        //简单分词
        if (scanner.hasNextLine()){
            String contents = scanner.useDelimiter("\\A").next();

            int start = firstCharacterIndex(contents, 0);
            for (int i = start + 1; i <= contents.length(); )
                if (i == contents.length() || !Character.isLetter(contents.charAt(i))) {
                    String word = contents.substring(start, i).toLowerCase();
                    array.add(word);
                    start = firstCharacterIndex(contents, i);
                    i = start + 1;
                } else
                    i++;
        }
        return true;
    }
}
