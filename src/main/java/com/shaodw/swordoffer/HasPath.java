package com.shaodw.swordoffer;

/**
 * @Auther: shaodw
 * @Date: 2020/1/28 21:15
 * @Description: 回溯法的应用
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
 * 例如 a b c e
 *      s f c s
 *      a d e e
 *      矩阵中包含一条字符串"bcced"的路径，
 * 但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
 */
public class HasPath {
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
       if (matrix == null || rows < 1 || cols < 1 || str == null)
           return false;
       boolean[] visited = new boolean[rows * cols];
       int pathLen = 0;
       for (int i = 0; i < rows; i++){
           for (int j = 0; j < cols; j++){
               if (hasPathCore(matrix, rows, cols, i, j, str, pathLen, visited))
                   return true;
           }
       }
       return false;
    }

    private static boolean hasPathCore(char[] matrix, int rows, int cols, int i, int j, char[] str, int pathLen, boolean[] visited){
        if (pathLen == str.length)
            return true;
        boolean hasPath = false;
        if (i >= 0 && i < rows && j >= 0 && j < cols && matrix[i * cols + j] == str[pathLen] && !visited[i * cols + j]){
            pathLen++;
            visited[i * cols + j] = true;
            hasPath = hasPathCore(matrix, rows, cols, i , j - 1, str, pathLen, visited)
                    || hasPathCore(matrix, rows, cols, i, j + 1, str, pathLen, visited)
                    || hasPathCore(matrix, rows, cols, i + 1, j, str, pathLen, visited)
                    || hasPathCore(matrix, rows, cols, i - 1, j, str, pathLen, visited);
            if (!hasPath){
                pathLen--;
                visited[i * rows + j] = false;
            }
        }
        return hasPath;
    }

    public static void main(String[] args) {
        char[] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e','e'};
        int rows = 3;
        int cols = 4;
        char[] str = {'b','c','c','e','d'};
        char[] str1 = {'a','b','c','d'};
        System.out.println(new HasPath().hasPath(matrix, rows, cols, str));
        System.out.println(new HasPath().hasPath(matrix, rows, cols, str1));
    }

}
