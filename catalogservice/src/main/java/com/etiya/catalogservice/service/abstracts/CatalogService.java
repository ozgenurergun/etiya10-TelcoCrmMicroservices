package com.etiya.catalogservice.service.abstracts;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.service.dtos.requests.Catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.requests.Catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.Catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.UpdatedCatalogResponse;

import java.util.List;

public interface CatalogService {
    CreatedCatalogResponse add(CreateCatalogRequest request);
    UpdatedCatalogResponse update(UpdateCatalogRequest request);
    List<GetListCatalogResponse> getListCatalogResponse();
    void delete(int id);
    void softDelete(int id);

    // --- Servisler Arası İletişim Metodu ---
    // ProductService'in ve bu servisin kendisinin (parent'ı bulmak için)
    // Catalog entity'sine erişmesi için.
    Catalog findById(int id);
}
