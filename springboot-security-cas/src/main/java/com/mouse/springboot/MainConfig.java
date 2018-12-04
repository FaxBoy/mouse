package com.mouse.springboot;

import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.catalina.connector.Connector;

import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@RestController
@SpringBootApplication
public class MainConfig
{
    public static void main(String[] args)
    {
        disableSslVerification();
        SpringApplication.run(MainConfig.class, args);
    }

    /**
     * @Description TODO(单向认证，忽略https证书：No subject alternative names present)
     * @author shil <sl166199@163.com>
     * @date 2018/12/4 14:18
     * @param []
     * @return void
     */
    private static void disableSslVerification() {
        try
        {
            // Create a trust manager that does not validate certificate chains
            TrustManager[] trustAllCerts = new TrustManager[] {new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
            };

            // Install the all-trusting trust manager
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping("/")
    public String index()
    {
        return "访问了首页哦";
    }
    @RequestMapping("/login")
    public String login()
    {
        return "访问了首页哦";
    }
    @RequestMapping("/hello")
    public String hello()
    {
        return "不验证哦";
    }

    @PreAuthorize("hasAuthority('TEST')") // 有TEST权限的才能访问
    @RequestMapping("/security")
    public String security()
    {
        return "hello world security";
    }

    @PreAuthorize("hasAuthority('ADMIN')") // 必须要有ADMIN权限的才能访问
    @RequestMapping("/authorize")
    public String authorize()
    {
        return "有权限访问";
    }

}