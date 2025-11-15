package com.etiya.catalogservice.service.concretes;

import com.etiya.catalogservice.domain.entities.CharValue;
import com.etiya.catalogservice.repository.CharValueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharValueLookupService {

    private final CharValueRepository charValueRepository;

    public CharValueLookupService(CharValueRepository charValueRepository) {
        this.charValueRepository = charValueRepository;
    }

    List<CharValue> getCharValListByCharId(int charId){
        List<CharValue> all = charValueRepository.findAll();
        List<CharValue> responses = new ArrayList<>();
        for (CharValue charValue : all) {
            if (charValue.getCharacteristic().getId() == charId){
                responses.add(charValue);
            }
        }
        return responses;
    }

}
