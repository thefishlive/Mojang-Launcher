package io.github.thefishlive.minecraft.profiles;

import static org.junit.Assert.*;
import io.github.thefishlive.minecraft.profiles.JavaArgs;

import org.junit.Test;

public class JavaArgsTest extends Profiliable {

	@Test
	public void testFromString() {		
		String command = "-Xms512M -XX:MaxPermSize=128M -Xmx1536M -RandomArgument=\"Random Value\"";
		
		start(); // Test 1
			JavaArgs args = new JavaArgs(command);
		printProfile();

		start(); // Test 2
			String commandForm = args.toCommandForm();
		printProfile();
		
		assertEquals(command, commandForm);
	}
	
	@Test
	public void testToString() {
		String command = "-Xms512M -XX:MaxPermSize=128M -Xmx1536M -RandomArgument=\"Random Value\"";
		
		start(); // Test 1
			JavaArgs args = new JavaArgs();
			args.setInitialMemory("512M");
			args.setPermGen("128M");
			args.setMaxMemory("1536M");
			args.addJavaArg("RandomArgument", "Random Value");
		printProfile();
		
		start(); // Test 2
			String commandForm = args.toCommandForm();
		printProfile();
		
		assertEquals(command, commandForm);
	}
	
	@Test
	public void testHashCode() {
		JavaArgs args = new JavaArgs();
		args.setInitialMemory("512M");
		args.setPermGen("128M");
		args.setMaxMemory("1536M");
		args.addJavaArg("RandomArgument", "Random Value");

		JavaArgs args2 = new JavaArgs();
		args2.setInitialMemory("512M");
		args2.setPermGen("128M");
		args2.setMaxMemory("1536M");
		args2.addJavaArg("RandomArgument", "Random Value");

		JavaArgs args3 = new JavaArgs();
		args3.setInitialMemory("256M");
		args3.setPermGen("256M");
		args3.setMaxMemory("1024M");
		args3.addJavaArg("RandomArgument", "Random Other Value");
		
		start(); // Test 1
			assertEquals(args.hashCode(), args.hashCode());
		printProfile();

		start(); // Test 2
			assertEquals(args2.hashCode(), args2.hashCode());
		printProfile();

		start(); // Test 3
			assertEquals(args.hashCode(), args2.hashCode());
		printProfile();

		start(); // Test 4
			assertEquals(args2.hashCode(), args.hashCode());
		printProfile();

		start(); // Test 5
			assertNotEquals(args.hashCode(), args3.hashCode());
		printProfile();

		start(); // Test 6
			assertNotEquals(args3.hashCode(), args.hashCode());
		printProfile();
	}

	@Test
	public void testEquals() {
		JavaArgs args = new JavaArgs();
		args.setInitialMemory("512M");
		args.setPermGen("128M");
		args.setMaxMemory("1536M");
		args.addJavaArg("RandomArgument", "Random Value");

		JavaArgs args2 = new JavaArgs();
		args2.setInitialMemory("512M");
		args2.setPermGen("128M");
		args2.setMaxMemory("1536M");
		args2.addJavaArg("RandomArgument", "Random Value");
		
		JavaArgs args3 = new JavaArgs();
		args3.setInitialMemory("256M");
		args3.setPermGen("256M");
		args3.setMaxMemory("1024M");
		args3.addJavaArg("RandomArgument", "Random Other Value");
		
		start(); // Test 1
			assertEquals(args, args);
		printProfile();

		start(); // Test 2
			assertEquals(args2, args2);
		printProfile();
		
		start(); // Test 3
			assertEquals(args, args2);
		printProfile();
		
		start(); // Test 4
			assertEquals(args2, args);
		printProfile();
		
		start(); // Test 5
			assertNotEquals(args, args3);
		printProfile();
		
		start(); // Test 6
			assertNotEquals(args3, args);
		printProfile();
	}

}
