package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CharValueService;
import com.etiya.catalogservice.service.dtos.requests.CharValue.CreateCharValueRequest;
import com.etiya.catalogservice.service.dtos.requests.CharValue.UpdateCharValueRequest;
import com.etiya.catalogservice.service.dtos.responses.CharValue.CreatedCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.GetListCharValueResponse;
import com.etiya.catalogservice.service.dtos.responses.CharValue.UpdatedCharValueResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/char-values")
public class CharValueController {

    private final CharValueService charvalueService;

    public CharValueController(CharValueService charvalueService) {
        this.charvalueService = charvalueService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCharValueResponse add(@RequestBody CreateCharValueRequest request) {
        return charvalueService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCharValueResponse update(@RequestBody UpdateCharValueRequest request) {
        return charvalueService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCharValueResponse> getAll() {
        return charvalueService.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        charvalueService.deleteById(id);
    }
}
