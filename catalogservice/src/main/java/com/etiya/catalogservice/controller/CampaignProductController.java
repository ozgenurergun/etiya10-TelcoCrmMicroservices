package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignProductService;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.CreateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProduct.UpdateCampaignProductRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.CreatedCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.GetListCampaignProductResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProduct.UpdatedCampaignProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaign-products") // Endpoint'i değiştiriyoruz
public class CampaignProductController {

    private final CampaignProductService campaignProductService;

    public CampaignProductController(CampaignProductService campaignProductService) {
        this.campaignProductService = campaignProductService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCampaignProductResponse add(@RequestBody CreateCampaignProductRequest request) {
        return campaignProductService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCampaignProductResponse update(@RequestBody UpdateCampaignProductRequest request) {
        return campaignProductService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCampaignProductResponse> getListCampaignProductResponses() {
        return campaignProductService.getListCampaignProductResponse();
    }

    @DeleteMapping("/{id}") // {id} daha standart
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        campaignProductService.delete(id);
    }

    @DeleteMapping("/{id}/soft") // {id}/soft daha standart
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id) {
        campaignProductService.softDelete(id);
    }
}
