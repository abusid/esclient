package com.elasticsearch.clients.provider;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

import java.io.IOException;

import static com.elasticsearch.clients.utils.EsUtils.getNumberofNodes;

@Slf4j
public class ESClientManager {
    private static final String HOST = "localhost";
    private static final int PORT_ONE = 9200;
    private static final int PORT_TWO = 9201;
    private static final String SCHEME = "http";

    static ElasticsearchTransport transport;
    public  ElasticsearchClient makeESClient()  {
        RestClientBuilder builder = RestClient.builder(new HttpHost(HOST, PORT_ONE, SCHEME),
                new HttpHost(HOST, PORT_TWO, SCHEME));
        // Create the low-level client
        RestClient restClient = builder.build();
        // Create the transport with a Jackson mapper
        transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        int numberOfNodes = 0;
        try {
            numberOfNodes = getNumberofNodes(transport);
        } catch (IOException e) {
            log.error("Exception occuerd {}", e.getMessage(), e);
            throw new RuntimeException(e);
        }
        log.info("number of nodes running {}", numberOfNodes );
        // And create the API client
        return new ElasticsearchClient(transport);
    }

    public  void closeConnection() throws IOException {
        transport.close();
    }
}
