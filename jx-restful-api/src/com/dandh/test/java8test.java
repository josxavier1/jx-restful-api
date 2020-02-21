package com.dandh.test;

import static java.lang.System.out;

import java.util.Arrays;

public class java8test {

	Runnable r1 = () -> out.println(this);
	Runnable r2 = () -> out.println(toString());

	
	public void testarrays(String strArray, String s1, String s2) {

	}

	public String toString() {
		return "Hello, world!";
	}

	public static void main(String... args) {
		new java8test().r1.run(); // Hello, world!
		new java8test().r2.run(); // Hello, world!
	}
}