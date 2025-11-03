package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.repository.CharValueRepository;
import com.etiya.catalogservice.service.abstracts.CharValueService;
import com.etiya.catalogservice.service.dtos.requests.CharValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.CharValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.CharValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.UpdatedCharValueResponse;
import com.etiya.catalogservice.service.mappings.CharValueMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CharvalueServiceImpl implements CharValueService {

    private final CharValueRepository  charValueRepository;


    public CharvalueServiceImpl(CharValueRepository charValueRepository) {
        this.charValueRepository = charValueRepository;
    }


    @Override
    public CreatedCharValueResponse add(CreateCharValueRequest request) {
        CharValue charValue = CharValueMapper.INSTANCE.charValueFromCreateCharValueRequest(request);
        CharValue createdCharValue = charValueRepository.save(charValue);
        CreatedCharValueResponse response = CharValueMapper.INSTANCE.createdCharValueResponseFromCharValue(createdCharValue);
        return response;
    }

    @Override
    public UpdatedCharValueResponse update(UpdateCharValueRequest request) {
        CharValue oldCharValue = charValueRepository.findById(request.getId()).orElseThrow(() -> new RuntimeException("Char Value not found"));

        CharValue charValue = CharValueMapper.INSTANCE.charValueFromUpdateCharValueRequest(request, oldCharValue);
        CharValue updateCharValue = charValueRepository.save(charValue);

        UpdatedCharValueResponse response = CharValueMapper.INSTANCE.updatedCharValueResponseFromCharValue(updateCharValue);
        return response;
    }

    @Override
    public List<GetListCharValueResponse> getAll() {
        List<CharValue> charValues = charValueRepository.findAll();
        List<GetListCharValueResponse> responseList = CharValueMapper.INSTANCE.getListCharValueResponseFromCharvalue(charValues);
        return responseList;
    }

    @Override
    public void deleteById(int id) {
        CharValue charValue = charValueRepository.findById(id).orElseThrow(() -> new RuntimeException("Char value not found"));

        charValue.setDeletedDate(LocalDateTime.now());
        charValue.setIsActive(0);
        charValueRepository.save(charValue);

    }
}
