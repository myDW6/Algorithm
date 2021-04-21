package com.shaodw.swordoffer;


/**
 * @Auther: shaodw
 * @Date: 2019-12-10 16:21
 * @Description:请实现一个函数，将一个字符串中的每个空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
 * 思路：先在末尾手动拼接" ",遇到空格字符时,先获取0-当前下标（前闭后开）的子串拼接到结果串，
 * 然后更新str,curChar置为0 重复 这样保证最后一个字符一定会是" " 那么结果会多拼接了一个"%20",截取子串返回即可；
 *
 *
 *
 * 另一种思路：既然上述思路在于处理最后一个字符，且仅需处理它最后一个不为" "的情况，那么我们单独判断一次即可
 *
 * 思路三比较简单
 */
public class ReplaceSpace {
    public String replaceSpaceFun1(StringBuffer str) {
        str = str.append(" ");
        int curChar = 0;
        StringBuffer res = new StringBuffer();
        while (curChar < str.length()){
            if (str.charAt(curChar) == ' ') {
                res.append(str.substring(0, curChar)).append("%20");
                str = new StringBuffer(str.substring(curChar + 1));
                curChar = 0;
                continue;
            }
            curChar++;
        }

        return res.substring(0, res.length() - 3);
    }

    public String replaceSpaceFun2(StringBuffer str) {
        int curChar = 0;
        StringBuffer res = new StringBuffer();
        while (curChar < str.length()){
            if (str.charAt(curChar) == ' ') {
                res.append(str.substring(0, curChar)).append("%20");
                str = new StringBuffer(str.substring(curChar + 1));
                curChar = 0;
                continue;
            }
            curChar++;
        }
        return str.toString().endsWith(" ") ? res.toString() : res.append(str).toString() ;
    }

    public String replaceSpaceFun3(StringBuffer str) {
        char[] strChar = str.toString().toCharArray();
        StringBuffer stb = new StringBuffer();
        for(int i=0;i<strChar.length;i++){
            if(strChar[i]==' '){
                stb.append("%20");
            }else{
                stb.append(strChar[i]);
            }
        }
        return stb.toString();
    }


    public static void main(String[] args) {
        System.out.println(new ReplaceSpace().replaceSpaceFun1(new StringBuffer("We Are Happy")));
        System.out.println(new ReplaceSpace().replaceSpaceFun1(new StringBuffer("helloworld ")));
        System.out.println(new ReplaceSpace().replaceSpaceFun1(new StringBuffer(" sss")));
        System.out.println(new ReplaceSpace().replaceSpaceFun1(new StringBuffer("   ")));

        System.out.println("===============");

        System.out.println(new ReplaceSpace().replaceSpaceFun2(new StringBuffer("We Are Happy")));
        System.out.println(new ReplaceSpace().replaceSpaceFun2(new StringBuffer("helloworld ")));
        System.out.println(new ReplaceSpace().replaceSpaceFun2(new StringBuffer(" sss")));
        System.out.println(new ReplaceSpace().replaceSpaceFun2(new StringBuffer("   ")));

        System.out.println("===============");

        System.out.println(new ReplaceSpace().replaceSpaceFun3(new StringBuffer("We Are Happy")));
        System.out.println(new ReplaceSpace().replaceSpaceFun3(new StringBuffer("helloworld ")));
        System.out.println(new ReplaceSpace().replaceSpaceFun3(new StringBuffer(" sss")));
        System.out.println(new ReplaceSpace().replaceSpaceFun3(new StringBuffer("   ")));
    }
}
