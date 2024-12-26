package com.laapicallbat.lostarkapicallbatchservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtConfigTests {

    @Autowired
    private JwtConfig jwtConfig;

    @Test
    public void jwtTokenTest() {
        Assertions.assertEquals("eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyIsImtpZCI6IktYMk40TkRDSTJ5NTA5NWpjTWk5TllqY2lyZyJ9.eyJpc3MiOiJodHRwczovL2x1ZHkuZ2FtZS5vbnN0b3ZlLmNvbSIsImF1ZCI6Imh0dHBzOi8vbHVkeS5nYW1lLm9uc3RvdmUuY29tL3Jlc291cmNlcyIsImNsaWVudF9pZCI6IjEwMDAwMDAwMDA1NTcwMzkifQ.StCIym3sU7i2UD2eFWusNf_h7BFifXwQFUdIilJ6rJFpOMo0y0UPkxH7zIfIKfKEf6-d-7UTcB1oIlcKF67iByUz0wcOMfGxABJmnCTt23uO86FDkBR3f5khnMawezuqkzSy1D_JdLlrIbZGJ8A7f8wZdPJH8YMLu_szg0U5Y5gaZKvuNihgaby2AWSh_ySDm4LJmSevj1lA8qV-Ldf7fHLgyhUOzppOpAFmLET7nAFKsN-vvWnYe7CTyFS1HN_HEgbTzzxKUiMdmfzmM3G-pCJW8GEv3m35X77zkj1mTNQoufqH7410_f0_wgFO2iGv2wetIo0kh1zVm1OOkmAycQ",jwtConfig.getToken());
    }

    @Test
    public void jwtPrefixTest(){
        Assertions.assertEquals("bearer",jwtConfig.getPrefix());
    }
}
