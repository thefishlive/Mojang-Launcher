package io.github.thefishlive.minecraft.profiles;

import org.junit.After;
import org.junit.Before;

public class Profiliable {

	private String currentTest;
	
	private long start;
	private long end;
	
	private long testStart;
	private long testEnd;
	
	private int test;
	
	protected long now() {
		return System.nanoTime();
	}

	protected void end() {
		end = now();
	}

	protected void start() {
		if (currentTest == null) {
			currentTest = getCallingMethod();
		}
		
		start = now();
	}
	
	private String getCallingMethod() {
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		return stack[3].getMethodName();
	}

	protected void resetTestCount() {
		test = 1;
	}
	
	protected void printProfile() {
		end();

		System.out.println(String.format("[%1$s] Test %3$s took %2$s ns", currentTest, calculateElaspsedTime(), test++));
	}

	protected long calculateElaspsedTime() {
		return end - start;
	}

	@Before
	public void before() {
		resetTestCount();
		currentTest = null;
		testStart = now();
	}
	
	@After
	public void after() {
		testEnd = now();
		System.err.println(String.format("[%1$s] Test took %2$s ns", currentTest, testEnd - testStart, test++)); // Print to error to show distinction between tests
	}
}
