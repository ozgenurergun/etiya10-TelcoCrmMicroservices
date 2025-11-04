package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.domain.entities.GENELSTATUS;
import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.repository.GenelStatusRepository;
import com.etiya.catalogservice.service.abstracts.GenelStatusService;
import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.CreateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.UpdateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.CreatedGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.GetListGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.UpdatedGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.GetListGenelTypeResponse;
import com.etiya.catalogservice.service.mappings.GenelStatusMapper;
import com.etiya.catalogservice.service.mappings.GenelTypeMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class GenelStatusServiceImpl implements GenelStatusService {

    private final GenelStatusRepository genelStatusRepository;

    public GenelStatusServiceImpl(GenelStatusRepository genelStatusRepository) {
        this.genelStatusRepository = genelStatusRepository;
    }


    @Override
    public CreatedGenelStatusResponse add(CreateGenelStatusRequest createGenelStatusRequest) {
        GENELSTATUS genelstatus = GenelStatusMapper.INSTANCE.genelStatusFromGenelStatusRequest(createGenelStatusRequest);
        GENELSTATUS createdGenelStatus = genelStatusRepository.save(genelstatus);
        CreatedGenelStatusResponse response = GenelStatusMapper.INSTANCE.createdGenelStatusResponseFromGenelStatus(createdGenelStatus);
        return response;
    }

    @Override
    public UpdatedGenelStatusResponse update(UpdateGenelStatusRequest request) {
        GENELSTATUS oldStatus = genelStatusRepository.findById(request.getId()).orElseThrow(()-> new RuntimeException("Genel Status not found."));

        GENELSTATUS genelStatus = GenelStatusMapper.INSTANCE.genelStatusFromUpdateGenelStatusRequest(request, oldStatus);
        GENELSTATUS updatedGenelStatus = genelStatusRepository.save(genelStatus);

        UpdatedGenelStatusResponse response = GenelStatusMapper.INSTANCE.updatedGenelStatusResponseFromGenelStatus(updatedGenelStatus);
        return response;
    }

    @Override
    public List<GetListGenelStatusResponse> getAll() {
        List<GENELSTATUS> genelStatuses = genelStatusRepository.findAll();
        List<GetListGenelStatusResponse> getListGenelStatusResponses = GenelStatusMapper.INSTANCE.getListGenelStatusResponseFromGenelStatus(genelStatuses);
        return getListGenelStatusResponses;
    }

    @Override
    public void deleteById(int id) {
        GENELSTATUS genelStatus = genelStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("Genel Status not found."));

        genelStatus.setDeletedDate(LocalDateTime.now());
        genelStatus.setIsActive(0);
        genelStatusRepository.save(genelStatus);
    }

    @Override
    public GENELSTATUS findById(int id) {
        return genelStatusRepository.findById(id).orElseThrow(() -> new RuntimeException("GenelStatus not found"));
    }
}
