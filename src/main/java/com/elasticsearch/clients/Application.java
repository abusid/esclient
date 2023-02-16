package com.elasticsearch.clients;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.elasticsearch.clients.provider.ESClientManager;
import com.elasticsearch.clients.search.ESDao;
import com.elasticsearch.clients.utils.EsUtils;

import java.io.IOException;
import java.util.UUID;

public class Application {


    static ElasticsearchClient client ;

    private static final String INDEX = "persondata";

    static ESClientManager esClientManager = new ESClientManager();
    static ESDao esDao = new ESDao();

    public static void main(String... args) throws IOException {

        client = esClientManager.makeESClient();
        System.out.println("ES Connection established successfully");
        Person person = new Person(UUID.randomUUID().toString(), "Suma", "26", "Pusapati");
        esDao.insertData(person, client, INDEX);

        EsUtils.handleResponse(esDao.searchCluster("name", "Shubham Aggarwal",  client, INDEX));
        esClientManager.closeConnection();
        System.out.println("ES Connection closed successfully");
    }

}
