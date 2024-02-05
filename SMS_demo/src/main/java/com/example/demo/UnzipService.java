package com.example.demo;

import org.springframework.stereotype.Service;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class UnzipService {
    public static void unzip(byte[] zipByteArray) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(zipByteArray);
             ZipInputStream zis = new ZipInputStream(bis)) {

            File tempDir = createTempDirectory();

            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                String entryName = zipEntry.getName();
                File entryFile = new File(tempDir, entryName);

                if (zipEntry.isDirectory()) {
                    // Create directories
                    entryFile.mkdirs();
                } else {
                    // Create parent directories for files
                    entryFile.getParentFile().mkdirs();

                    // Write file content
                    try (FileOutputStream fos = new FileOutputStream(entryFile)) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                }

                System.out.println("Extracted: " + entryFile.getAbsolutePath());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static File createTempDirectory() throws IOException {
        Path tempDirPath = Files.createTempDirectory("extractedProject");
        return tempDirPath.toFile();
    }
}

