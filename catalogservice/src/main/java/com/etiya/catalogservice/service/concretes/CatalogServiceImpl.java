package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.Catalog;
import com.etiya.catalogservice.repository.CatalogRepository;
import com.etiya.catalogservice.service.abstracts.CatalogService;
import com.etiya.catalogservice.service.dtos.requests.Catalog.CreateCatalogRequest;
import com.etiya.catalogservice.service.dtos.requests.Catalog.UpdateCatalogRequest;
import com.etiya.catalogservice.service.dtos.responses.Catalog.CreatedCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.GetListCatalogResponse;
import com.etiya.catalogservice.service.dtos.responses.Catalog.UpdatedCatalogResponse;
import com.etiya.catalogservice.service.mappings.CatalogMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {

    private final CatalogRepository catalogRepository; // SADECE kendi reposu

    // Sadece kendi reposunu enjekte ediyoruz
    public CatalogServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public CreatedCatalogResponse add(CreateCatalogRequest request) {
        // 1. Mapper ile temel alanları map'le
        Catalog catalog = CatalogMapper.INSTANCE.getCatalogFromCreateRequest(request);

        // 2. Parent (üst kategori) ilişkisini kur
        // Eğer bir parentId geldiyse (null değilse), o parent'ı bul ve set et.
        if (request.getParentId() != null && request.getParentId() > 0) {
            Catalog parentCatalog = findById(request.getParentId()); // Kendi findById metodumuzu kullanıyoruz
            catalog.setParent(parentCatalog);
        }
        // else: parentId null ise, catalog.setParent(null) olur (zaten varsayılan).
        // Bu, bir kök kategoridir.

        // 3. Kaydet
        catalogRepository.save(catalog);

        // 4. Response DTO'ya map'le ve dön
        return CatalogMapper.INSTANCE.getCreatedResponseFromCatalog(catalog);
    }

    @Override
    public UpdatedCatalogResponse update(UpdateCatalogRequest request) {
        // 1. Güncellenecek kataloğu bul
        Catalog catalogToUpdate = findById(request.getId());

        // 2. Parent ilişkisini güncelle
        if (request.getParentId() != null && request.getParentId() > 0) {
            // Yeni bir parent atanmış
            Catalog parentCatalog = findById(request.getParentId());
            catalogToUpdate.setParent(parentCatalog);
        } else {
            // Parent null olarak setlenmiş (kök kategori yapılmış)
            catalogToUpdate.setParent(null);
        }

        // 3. Mapper ile diğer temel alanları güncelle
        CatalogMapper.INSTANCE.updateCatalogFromUpdateRequest(request, catalogToUpdate);

        // 4. Kaydet
        catalogRepository.save(catalogToUpdate);

        return CatalogMapper.INSTANCE.getUpdatedResponseFromCatalog(catalogToUpdate);
    }

    @Override
    public List<GetListCatalogResponse> getListCatalogResponse() {
        // List<Catalog> catalogs = catalogRepository.findAllByIsActive(1); // Sadece aktifleri de alabilirsin
        List<Catalog> catalogs = catalogRepository.findAll();
        return CatalogMapper.INSTANCE.getListResponseFromCatalogList(catalogs);
    }

    @Override
    public void delete(int id) {
        Catalog catalog = findById(id);
        catalogRepository.delete(catalog);
    }

    @Override
    public void softDelete(int id) {
        Catalog catalog = findById(id);

        // BaseEntity'den gelen alanlar
        catalog.setDeletedDate(LocalDateTime.now());
        catalog.setIsActive(0);

        catalogRepository.save(catalog);
    }

    // --- Servis İçi ve Servisler Arası Metot ---
    @Override
    public Catalog findById(int id) {
        return catalogRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catalog not found with id: " + id));
    }
}