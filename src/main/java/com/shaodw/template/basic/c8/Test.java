package com.shaodw.template.basic.c8;

public class Test {

	public static int f(int N) {
		if(N == 1 || N ==2) {
			return 1;
		}
		return f(N-1) + f(N-2);
	}
	
}
