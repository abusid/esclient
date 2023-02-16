package com.elasticsearch.clients.utils;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery.Builder;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Operator;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.cluster.HealthResponse;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.ElasticsearchTransport;
import com.elasticsearch.clients.Person;
import com.elasticsearch.clients.SearchQueryResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

public class EsUtils {

    private EsUtils() {
    }

    public static boolean isNotBlank(Object object, String... values) {
        if (object == null) {
            return false;
        } else {
            String[] var2 = values;
            int var3 = values.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String value = var2[var4];
                if (StringUtils.isBlank(value)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static Builder addShouldTerm(Builder boolQueryBuilder, String name, String value) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.should((boolBuilder) -> {
            return boolBuilder.term((termQueryBuilder) -> {
                return termQueryBuilder.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addShouldTerm(Builder boolQueryBuilder, String name, Long value) {
        return boolQueryBuilder != null && StringUtils.isNotBlank(name) && value != null ? boolQueryBuilder.should((boolBuilder) -> {
            return boolBuilder.term((termQueryBuilder) -> {
                return termQueryBuilder.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addShouldTerm(Builder boolQueryBuilder, String name, Boolean value) {
        return boolQueryBuilder != null && StringUtils.isNotBlank(name) && value != null ? boolQueryBuilder.should((boolBuilder) -> {
            return boolBuilder.term((termQueryBuilder) -> {
                return termQueryBuilder.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addShouldMatch(Builder boolQueryBuilder, String name, String value, Operator operator) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.should((mu) -> {
            return mu.match(MatchQuery.of((m) -> {
                return m.field(name).query(value).operator(operator);
            }));
        }) : boolQueryBuilder;
    }

    public static Builder addShouldMatchWithLenient(Builder boolQueryBuilder, String name, String value, Operator operator) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.should((mu) -> {
            return mu.match(MatchQuery.of((m) -> {
                return m.field(name).query(value).operator(operator).lenient(true);
            }));
        }) : boolQueryBuilder;
    }

    public static Builder addMustTerm(Builder boolQueryBuilder, String fieldName, String fieldValue) {
        return isNotBlank(boolQueryBuilder, fieldName, fieldValue) ? boolQueryBuilder.must((mu) -> {
            return mu.term(TermQuery.of((m) -> {
                return m.field(fieldName).value(fieldValue);
            }));
        }) : boolQueryBuilder;
    }

    public static Builder addMustTerm(Builder boolQueryBuilder, String name, Boolean value) {
        return name != null && value != null ? boolQueryBuilder.must((boolBuilder) -> {
            return boolBuilder.term((termQueryBuilder) -> {
                return termQueryBuilder.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }


    public static Builder addMustMatch(Builder boolQueryBuilder, String fieldName, String fieldValue, Operator operator) {
        return isNotBlank(boolQueryBuilder, fieldName, fieldValue) ? boolQueryBuilder.must((mu) -> {
            return mu.match(MatchQuery.of((m) -> {
                return m.field(fieldName).query(fieldValue).operator(operator);
            }));
        }) : boolQueryBuilder;
    }

    public static Builder addMustNotMatch(Builder boolQueryBuilder, String name, String value) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.mustNot((mustQueryBuilder) -> {
            return mustQueryBuilder.match((matchQueryBuilder) -> {
                return matchQueryBuilder.field(name).query(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addFilterTerm(Builder boolQueryBuilder, String name, Boolean value) {
        return isNotBlank(boolQueryBuilder, name) && value != null ? boolQueryBuilder.filter((b) -> {
            return b.term((tqb) -> {
                return tqb.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addFilterTerm(Builder boolQueryBuilder, String name, int value) {
        return boolQueryBuilder.filter((b) -> {
            return b.term((tqb) -> {
                return tqb.field(name).value((long)value);
            });
        });
    }

    public static Builder addFilterTerm(Builder boolQueryBuilder, String name, long value) {
        return boolQueryBuilder.filter((b) -> {
            return b.term((tqb) -> {
                return tqb.field(name).value(value);
            });
        });
    }

    public static Builder addFilterTerm(Builder boolQueryBuilder, String name, String value) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.filter((boolBuilder) -> {
            return boolBuilder.term((termQueryBuilder) -> {
                return termQueryBuilder.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addFilterMatch(Builder boolQueryBuilder, String name, String value, Operator operator) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.filter((mu) -> {
            return mu.match(MatchQuery.of((m) -> {
                return m.field(name).query(value).operator(operator);
            }));
        }) : boolQueryBuilder;
    }



    public static Builder addFilterBool(Builder parentBooleanQuery, Builder filterBooleanQuery) {
        return filterBooleanQuery != null ? parentBooleanQuery.filter(filterBooleanQuery.build()._toQuery(), new Query[0]) : parentBooleanQuery;
    }

    public static Builder addMustBool(Builder parentBooleanQuery, Builder filterBooleanQuery) {
        return filterBooleanQuery != null ? parentBooleanQuery.must(filterBooleanQuery.build()._toQuery(), new Query[0]) : parentBooleanQuery;
    }

    public static Builder addShouldBool(Builder parentBooleanQuery, Builder filterBooleanQuery) {
        return filterBooleanQuery != null ? parentBooleanQuery.should(filterBooleanQuery.build()._toQuery(), new Query[0]) : parentBooleanQuery;
    }

    public static Builder addShouldBoolTermQuery(Builder boolQueryBuilder, String name, String value) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.should((bqb) -> {
            return bqb.term((tqb) -> {
                return tqb.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }



    public static Builder addMustBoolTermQuery(Builder boolQueryBuilder, String name, String value) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.must((bqb) -> {
            return bqb.term((tqb) -> {
                return tqb.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addBoolQueryFilter(Builder boolQueryBuilder, String name, Long value) {
        return isNotBlank(boolQueryBuilder, name) && value != null ? boolQueryBuilder.filter((bqb) -> {
            return bqb.term((tqb) -> {
                return tqb.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addBoolQueryFilter(Builder boolQueryBuilder, String name, String value) {
        return isNotBlank(boolQueryBuilder, name, value) ? boolQueryBuilder.filter((bqb) -> {
            return bqb.term((tqb) -> {
                return tqb.field(name).value(value);
            });
        }) : boolQueryBuilder;
    }

    public static Builder addRangeQuery(Builder boolQueryBuilder, String name, Long lessThanValue, Long greaterThanValue) {
        return isNotBlank(boolQueryBuilder, name) && lessThanValue != null && greaterThanValue != null ? boolQueryBuilder.must((bqb) -> {
            return bqb.range((rqb) -> {
                return rqb.field(name).gte(JsonData.of(lessThanValue)).lte(JsonData.of(greaterThanValue));
            });
        }) : boolQueryBuilder;
    }

    public static int getNumberofNodes(ElasticsearchTransport transport) throws IOException {
        ElasticsearchClient elasticsearchClient = new ElasticsearchClient(transport);
        HealthResponse response = elasticsearchClient.cluster().health((b)-> {
            return b;
        });
        return response.numberOfNodes();
    }

    public static void handleResponse(SearchQueryResponse<Person> profileSearchQueryResponse) {
        profileSearchQueryResponse.getResult().stream().forEach(System.out::println);
    }

}

