package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

@Service
public class CodeRunnerService {
    @Autowired
    private RestTemplate restTemplate;

    public void testRun() throws IOException {
//        ResponseEntity<byte[]> stream = restTemplate.getForEntity("http://localhost:8085/api/v1/sources/rs", byte[].class);
//        byte[] zippedCode = stream.getBody();
//        UnzipService.unzip(zippedCode);

        CompileService.testCompile();
    }
}
