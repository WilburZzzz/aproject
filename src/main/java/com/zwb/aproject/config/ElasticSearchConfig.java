package com.zwb.aproject.config;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@Data
@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {

    @Value("${elasticsearch.host:192.168.101.17}")
    private String host;
    @Value("${elasticsearch.port:21005}")
    private Integer port;
    @Value("${elasticsearch.username}")
    private String username;
    @Value("${elasticsearch.password}")
    private String password;

    @Override
    public RestHighLevelClient elasticsearchClient() {
        if(StringUtils.isEmpty(username)&&StringUtils.isEmpty(password)){
            HttpHost httpHost = new HttpHost(host, port);
            RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(httpHost));
            return client;
        }else{
            final CredentialsProvider provider = new BasicCredentialsProvider();
            provider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(username, password));
            RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(host, port))
                    .setHttpClientConfigCallback(f -> f.setDefaultCredentialsProvider(provider));
            return new RestHighLevelClient(restClientBuilder);
        }
    }
}