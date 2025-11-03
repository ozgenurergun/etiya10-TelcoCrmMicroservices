package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.domain.entities.GENELTYPE;
import com.etiya.catalogservice.service.abstracts.GenelTypeService;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.CreateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELTYPE.UpdateGenelTypeRequest;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.CreatedGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.GetListGenelTypeResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELTYPE.UpdatedGenelTypeResponse;
import com.etiya.customerservice.domain.entities.City;
import com.etiya.customerservice.service.abstracts.CityService;
import com.etiya.customerservice.service.responses.city.GetListCityResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genel-types")
public class GenelTypeController {

    private final GenelTypeService  genelTypeService;

    public GenelTypeController(GenelTypeService genelTypeService) {
        this.genelTypeService = genelTypeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedGenelTypeResponse add(@RequestBody CreateGenelTypeRequest request) {
        return genelTypeService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedGenelTypeResponse update(@RequestBody UpdateGenelTypeRequest request) {
        return genelTypeService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListGenelTypeResponse> getAll() {
        return genelTypeService.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        genelTypeService.deleteById(id);
    }
}
