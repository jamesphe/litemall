package org.linlinjava.litemall.core.storage;

import com.qiniu.util.Json;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Base64;
import java.util.stream.Stream;

public class FtpStorage implements Storage {

    private final Log logger = LogFactory.getLog(FtpStorage.class);

    private String address;
    private String upload;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUpload() {
        return upload;
    }

    public void setUpload(String upload) {
        this.upload = upload;
    }

    @Override
    public void store(InputStream inputStream, long contentLength, String contentType, String keyName) {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String result = "";

        try {
            byte[] bytes = IOUtils.toByteArray(inputStream);
            String fileEncoded = Base64.getEncoder().encodeToString(bytes);

            //创建http请求
            HttpPost httpPost = new HttpPost(upload);
            httpPost.addHeader("Content-Type", "application/json");
            //创建请求内容

            String jsonStr = "{\"filedata\":\""+fileEncoded+"\",\"filename\":\""+keyName+"\"}";
            HttpEntity entity = new StringEntity(jsonStr);
            httpPost.setEntity(entity);

            response = httpClient.execute(httpPost);
            result = EntityUtils.toString(response.getEntity(),"utf-8");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            if(response != null){
                try {
                    response.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
            if(httpClient != null){
                try{
                    httpClient.close();
                }catch (IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public Path load(String keyName) {
        return null;
    }

    @Override
    public Resource loadAsResource(String keyName) {
        try {
            URL url = new URL(generateUrl(keyName));
            Resource resource = new UrlResource(url);
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public void delete(String keyName) {

    }

    @Override
    public String generateUrl(String keyName) {
        return address + keyName;
    }
}
