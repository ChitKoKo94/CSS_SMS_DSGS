package com.example.demo;

import org.apache.maven.shared.invoker.*;
import java.io.File;
import java.util.Collections;

public class CompileService {

    public static void testCompile() {
        File projectDirectory = new File("/var/folders/15/qlmx9gkx4_n38g68rz4nvzyh0000gn/T/extractedProject2287153943762924118/rs-service-main");
        buildWithMaven(projectDirectory);
    }

    private static void buildWithMaven(File projectDirectory) {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setBaseDirectory(projectDirectory);
        request.setGoals(Collections.singletonList("clean install")); // You can customize the Maven goals here

        Invoker invoker = new DefaultInvoker();

        try {
            InvocationResult result = invoker.execute(request);
            if (result.getExitCode() == 0) {
                System.out.println("Build successful");
            } else {
                System.out.println("Build failed");
            }
        } catch (MavenInvocationException e) {
            e.printStackTrace();
        }
    }
}
