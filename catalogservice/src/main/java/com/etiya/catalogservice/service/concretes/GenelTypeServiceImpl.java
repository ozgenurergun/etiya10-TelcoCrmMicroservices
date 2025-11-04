package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.repository.GenelTypeRepository;
import com.etiya.catalogservice.service.abstracts.GenelTypeService;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.CreateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.UpdateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.CreatedGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.GetListGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.UpdatedGenelTypeResponse;
import com.etiya.catalogservice.service.mappings.GenelTypeMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GenelTypeServiceImpl implements GenelTypeService {

    private final GenelTypeRepository genelTypeRepository;

    public GenelTypeServiceImpl(GenelTypeRepository genelTypeRepository) {
        this.genelTypeRepository = genelTypeRepository;
    }

    @Override
    public CreatedGenelTypeResponse add(CreateGenelTypeRequest createGenelTypeRequest) {
        GENELTYPE genelType = GenelTypeMapper.INSTANCE.genelTypeFromGenelTypeRequest(createGenelTypeRequest);
        GENELTYPE createdGenelType = genelTypeRepository.save(genelType);
        CreatedGenelTypeResponse response = GenelTypeMapper.INSTANCE.createdGenelTypeResponseFromGenelType(createdGenelType);
        return response;
    }

    @Override
    public UpdatedGenelTypeResponse update(UpdateGenelTypeRequest request) {
        GENELTYPE oldType = genelTypeRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("Genel Type not found."));

        GENELTYPE genelType = GenelTypeMapper.INSTANCE.genelTypeFromUpdateGenelTypeRequest(request, oldType);
        GENELTYPE updateGenelType = genelTypeRepository.save(genelType);

        UpdatedGenelTypeResponse response = GenelTypeMapper.INSTANCE.updatedGenelTypeResponseFromGenelType(updateGenelType);
        return response;
    }


    @Override
    public List<GetListGenelTypeResponse> getAll() {
        List<GENELTYPE> genelTypes = genelTypeRepository.findAll();
        List<GetListGenelTypeResponse> getListGenelTypeResponse = GenelTypeMapper.INSTANCE.getListGenelTypeResponseFromGenelType(genelTypes);
        return getListGenelTypeResponse;
    }

    @Override
    public void deleteById(int id) {

        GENELTYPE genelType = genelTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("Genel Type not found."));

        genelType.setDeletedDate(LocalDateTime.now());
        genelType.setIsActive(0);
        genelTypeRepository.save(genelType);
    }

    @Override
    public GENELTYPE findById(int id) {
        return genelTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("CampaignProduct not found"));
    }
}
