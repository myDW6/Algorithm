package com.practice.shaodw.leetcode.stack;

import com.practice.shaodw.anno.Passed;

import java.util.Stack;

/**
 * @author shaodw
 * @date 2021/2/19 13:40
 * @description
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 */
public class ValidCharacter {
    @Passed(complex = "N", note = "没什么技巧 用栈")
    public static boolean isValid(String s){
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char cur : chars) {
            if (cur == '(' || cur == '{' || cur == '[') {
                stack.push(cur);
            } else {
                if (cur == ')' && (stack.isEmpty() || stack.pop() != '(')) {
                    return false;
                }
                if (cur == ']' && (stack.isEmpty() || stack.pop() != '[')) {
                    return false;
                }
                if (cur == '}' && (stack.isEmpty() || stack.pop() != '{')) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }


    @Passed(complex = "N", note = "使用ascii 但是运行效率比上面慢")
    public static boolean isValid1(String s){
        if (s == null || "".equals(s)){
            return true;
        }
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (stack.isEmpty()){
                stack.push(cur);
            }else if (cur - stack.peek() == 1 || cur - stack.peek() == 2){
                stack.pop();
            }else {
                stack.push(cur);
            }
        }
        return stack.isEmpty();
    }


    public static void main(String[] args) {
        String s1 = "";
        String s2 = "()";
        String s3 = "()[]{}";
        String s4 = "(]";
        String s5 = "([)]";
        String s6 = "{[]}";
        System.out.println(isValid(s1));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
        System.out.println(isValid(s4));
        System.out.println(isValid(s5));
        System.out.println(isValid(s6));

        System.out.println("---------");
        System.out.println(isValid1(s1));
        System.out.println(isValid1(s2));
        System.out.println(isValid1(s3));
        System.out.println(isValid1(s4));
        System.out.println(isValid1(s5));
        System.out.println(isValid1(s6));
    }
}
