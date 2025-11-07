package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import java.util.List;

@Repository
public class CustomCustomerSearchRepositoryImpl implements CustomCustomerSearchRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public CustomCustomerSearchRepositoryImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<CustomerSearch> searchDynamic(String id, String customerNumber, String nationalId, String firstName, String lastName, String value) {
        BoolQuery.Builder bool = QueryBuilders.bool();

        if (StringUtils.hasText(id)) {
            bool.must(m -> m.term(t -> t.field("id.keyword").value(id)));
        }
        if (StringUtils.hasText(customerNumber)) {
            bool.must(m -> m.term(t -> t.field("customerNumber.keyword").value(customerNumber)));
        }
        if (StringUtils.hasText(nationalId)) {
            bool.must(m -> m.term(t -> t.field("nationalId.keyword").value(nationalId)));
        }
        if (StringUtils.hasText(firstName)) {
            // matchPhrase DEĞİL, matchPhrasePrefix KULLAN
            bool.must(m -> m.matchPhrasePrefix(mpp -> mpp
                    .field("firstName") // .keyword değil, analiz edilmiş 'text' alanı olmalı
                    .query(firstName)
            ));
        }
        if (StringUtils.hasText(lastName)) {
            // matchPhrase DEĞİL, matchPhrasePrefix KULLAN
            bool.must(m -> m.matchPhrasePrefix(mpp -> mpp
                    .field("lastName") // .keyword değil, analiz edilmiş 'text' alanı olmalı
                    .query(lastName)
            ));
        }
        if (StringUtils.hasText(value)) {
            bool.must(m -> m.nested(n -> n
                    .path("contactMediums")
                    .query(q -> q.bool(nb -> nb
                            .must(mt -> mt.term(t -> t.field("contactMediums.type").value("PHONE"))) // sadece PHONE
                            .must(mt -> mt.term(t -> t.field("contactMediums.value").value(value)))
                    ))
            ));
        }

        Query query = bool.build()._toQuery();
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .build();

        SearchHits<CustomerSearch> hits =
                elasticsearchOperations.search(nativeQuery, CustomerSearch.class);

        return hits.stream().map(SearchHit::getContent).toList();
    }

}