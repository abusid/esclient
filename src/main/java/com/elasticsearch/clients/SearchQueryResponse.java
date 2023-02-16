package com.elasticsearch.clients;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import co.elastic.clients.elasticsearch._types.aggregations.Aggregate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Generated;

public class SearchQueryResponse<T> {
    private int foundHits;
    private List<T> result = new ArrayList();
    private Map<String, Aggregate> aggregations;

    @Generated
    public SearchQueryResponse() {
    }

    @Generated
    public int getFoundHits() {
        return this.foundHits;
    }

    @Generated
    public List<T> getResult() {
        return this.result;
    }

    @Generated
    public Map<String, Aggregate> getAggregations() {
        return this.aggregations;
    }

    @Generated
    public void setFoundHits(int foundHits) {
        this.foundHits = foundHits;
    }

    @Generated
    public void setResult(List<T> result) {
        this.result = result;
    }

    @Generated
    public void setAggregations(Map<String, Aggregate> aggregations) {
        this.aggregations = aggregations;
    }

    @Generated
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof SearchQueryResponse)) {
            return false;
        } else {
            SearchQueryResponse<?> other = (SearchQueryResponse)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getFoundHits() != other.getFoundHits()) {
                return false;
            } else {
                Object this$result = this.getResult();
                Object other$result = other.getResult();
                if (this$result == null) {
                    if (other$result != null) {
                        return false;
                    }
                } else if (!this$result.equals(other$result)) {
                    return false;
                }

                Object this$aggregations = this.getAggregations();
                Object other$aggregations = other.getAggregations();
                if (this$aggregations == null) {
                    if (other$aggregations != null) {
                        return false;
                    }
                } else if (!this$aggregations.equals(other$aggregations)) {
                    return false;
                }

                return true;
            }
        }
    }

    @Generated
    protected boolean canEqual(Object other) {
        return other instanceof SearchQueryResponse;
    }

    @Generated
    public int hashCode() {
        int result = 1;
        result = result * 59 + this.getFoundHits();
        Object $result = this.getResult();
        result = result * 59 + ($result == null ? 43 : $result.hashCode());
        Object $aggregations = this.getAggregations();
        result = result * 59 + ($aggregations == null ? 43 : $aggregations.hashCode());
        return result;
    }

    @Generated
    public String toString() {
        int var10000 = this.getFoundHits();
        return "SearchQueryResponse(foundHits=" + var10000 + ", result=" + this.getResult() + ", aggregations=" + this.getAggregations() + ")";
    }
}
