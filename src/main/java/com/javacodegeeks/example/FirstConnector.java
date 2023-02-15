package com.javacodegeeks.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpHost;
import org.apache.lucene.index.Terms;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.index.mapper.ObjectMapper;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.builder.SearchSourceBuilder;
public class FirstConnector {
    private static final String hst = "localhost";
    private static final int prt1 = 9200;
    private static final int prt2 = 9201;
    private static final String sch = "http";
    public static void main(String[] args) throws Exception{

    }

    private static ElasticsearchClient getElasticSearchClient() {
        RestClientBuilder clientBuilder = RestClient.builder(new HttpHost("localhost", 9200));

        return null;
    }
}