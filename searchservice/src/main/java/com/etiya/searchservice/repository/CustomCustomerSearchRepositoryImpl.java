package com.etiya.searchservice.repository;

import com.etiya.searchservice.domain.CustomerSearch;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<CustomerSearch> searchDynamic(String id, String customerNumber, String nationalId, String firstName, String lastName, String value, String orderId, int page, int size) {
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
            bool.must(m -> m.matchPhrasePrefix(mpp -> mpp
                    .field("firstName")
                    .query(firstName)
            ));
        }
        if (StringUtils.hasText(lastName)) {
            bool.must(m -> m.matchPhrasePrefix(mpp -> mpp
                    .field("lastName")
                    .query(lastName)
            ));
        }

        if (StringUtils.hasText(orderId)) {
            // orderIds bir liste olsa bile, term sorgusu "bu listede bu değer var mı?" diye bakar.
            // Not: Eğer mapping'de orderIds text ise ve keyword subfield varsa "orderIds.keyword" kullanmalısın.
            // Ancak paylaştığın JSON'da fields altında direkt "orderIds" görünüyor.
            bool.must(m -> m.term(t -> t.field("orderIds").value(orderId)));
        }

        if (StringUtils.hasText(value)) {
            bool.must(m -> m.nested(n -> n
                    .path("contactMediums")
                    .query(q -> q.bool(nb -> nb
                            .must(mt -> mt.term(t -> t.field("contactMediums.type").value("PHONE")))
                            .must(mt -> mt.term(t -> t.field("contactMediums.value").value(value)))
                    ))
            ));
        }


        Query query = bool.build()._toQuery();
        Pageable pageable = PageRequest.of(page, size);
        NativeQuery nativeQuery = NativeQuery.builder()
                .withQuery(query)
                .withPageable(pageable)
                .build();

        SearchHits<CustomerSearch> hits =
                elasticsearchOperations.search(nativeQuery, CustomerSearch.class);

        return hits.stream().map(SearchHit::getContent).toList();
    }


}