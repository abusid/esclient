package com.elasticsearch.clients.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.SearchType;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TrackHits;
import com.elasticsearch.clients.Person;
import com.elasticsearch.clients.SearchQueryResponse;
import com.elasticsearch.clients.utils.EsUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ESDao {

    public SearchQueryResponse<Person> searchCluster(String fieldName, String fieldValue, ElasticsearchClient client, String index) {

        SearchRequest request = createRequestBuilder(fieldName, fieldValue, index);
        try {
            SearchResponse<Person> searchResponse = client.search(request, Person.class);
            return buildQueryResponse(searchResponse);
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }

    public void insertData(Person person, ElasticsearchClient client, String index) throws IOException {
        IndexRequest<Person> request = IndexRequest.of(i -> i
                .index(index)

                .id(person.getPersonId())
                .document(person)
        );

       client.index(request);

    }

    private   <T> SearchQueryResponse buildQueryResponse(SearchResponse<T> searchResponse) throws IOException {
        SearchQueryResponse<T> response = new SearchQueryResponse();
        if (searchResponse.hits() != null && searchResponse.hits().total() != null) {
            response.setFoundHits(Math.toIntExact(searchResponse.hits().total().value()));
        } else {
            response.setFoundHits(0);
        }

        response.setResult(buildResultList(searchResponse));
        response.setAggregations(searchResponse.aggregations());
        System.out.println("Response size "+ response.getResult().size());
        return response;
    }

    private  <T> List<T> buildResultList(SearchResponse<T> response) throws IOException {
        List<T> resultSet = new ArrayList();
        Iterator var3 = response.hits().hits().iterator();

        while(var3.hasNext()) {
            Hit<T> hit = (Hit)var3.next();
            resultSet.add(hit.source());
        }

        return Collections.unmodifiableList(resultSet);
    }





    private  SearchRequest createRequestBuilder(String fieldName, String fieldValue, String index) {
        SearchRequest.Builder searchRequestBuilder = new SearchRequest.Builder()
                .index(index)
                .searchType(SearchType.DfsQueryThenFetch)
                .query(q -> q.bool(
                        bq -> EsUtils.addFilterMatch(bq, fieldName, fieldValue, Operator.And)
                )).trackTotalHits(TrackHits.of(b -> b.enabled(true)));
        return searchRequestBuilder.build();
    }


}
