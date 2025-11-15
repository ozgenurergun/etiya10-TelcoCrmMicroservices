package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CharacteristicService;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.CreateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.Characteristic.UpdateCharacteristicRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.CreateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.UpdateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.CreatedCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.GetListCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.GetListCharacteristicWithCharValResponse;
import com.etiya.catalogservice.service.dtos.responses.Characteristic.UpdatedCharacteristicResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.CreatedGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.GetListGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.UpdatedGenelStatusResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/characteristics")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCharacteristicResponse add(@RequestBody CreateCharacteristicRequest request) {
        return characteristicService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCharacteristicResponse update(@RequestBody UpdateCharacteristicRequest request) {
        return characteristicService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCharacteristicResponse> getAll() {
        return characteristicService.getAll();
    }

    @GetMapping("/getCharacteristicsByProdSpecId/{prodSpecId}")
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCharacteristicWithCharValResponse> getAllByProdSpecId(@PathVariable int prodSpecId) {
        return characteristicService.getAllByProdSpecId(prodSpecId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        characteristicService.deleteById(id);
    }
}
