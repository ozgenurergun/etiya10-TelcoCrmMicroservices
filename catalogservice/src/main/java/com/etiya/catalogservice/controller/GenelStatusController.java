package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.GenelStatusService;
import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.CreateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.requests.GENELSTATUS.UpdateGenelStatusRequest;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.CreatedGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.GetListGenelStatusResponse;
import com.etiya.catalogservice.service.dtos.responses.GENELSTATUS.UpdatedGenelStatusResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genel-statuses")
public class GenelStatusController {

    private final GenelStatusService genelStatusService;

    public GenelStatusController(GenelStatusService genelStatusService) {
        this.genelStatusService = genelStatusService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedGenelStatusResponse add(@RequestBody CreateGenelStatusRequest request) {
        return genelStatusService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedGenelStatusResponse update(@RequestBody UpdateGenelStatusRequest request) {
        return genelStatusService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListGenelStatusResponse> getAll() {
        return genelStatusService.getAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@Valid @PathVariable int id) {
        genelStatusService.deleteById(id);
    }
}
