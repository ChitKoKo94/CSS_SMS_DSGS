package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(com.example.demo.DemoApplication.class, args);
	}

	@Autowired
	private CodeRunnerService runnerService;

	@Override
	public void run(String... args) throws Exception {
		runnerService.testRun();






//		// Prepare source somehow.
//		String source = "package test; public class Test { static { System.out.println(\"hello\"); } public Test() { System.out.println(\"world\"); } }";
//
//// Save source in .java file.
//		File root = Files.createTempDirectory("java").toFile();
//		File sourceFile = new File(root, "test/Test.java");
//		System.out.println(sourceFile.getPath());
//		sourceFile.getParentFile().mkdirs();
//		Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
//
//// Compile source file.
//		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//		compiler.run(null, null, null, sourceFile.getPath());
//
//// Load and instantiate compiled class.
//		URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() });
//		Class<?> cls = Class.forName("test.Test", true, classLoader); // Should print "hello".
//		Object instance = cls.getDeclaredConstructor().newInstance(); // Should print "world".
//		System.out.println(instance); // Should print "test.Test@hashcode".
	}
}
