package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignProductOfferService;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.CreateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.requests.CampaignProductOffer.UpdateCampaignProductOfferRequest;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.CreatedCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.GetListCampaignProductOfferResponse;
import com.etiya.catalogservice.service.dtos.responses.CampaignProductOffer.UpdatedCampaignProductOfferResponse;
import com.etiya.common.responses.CampaignProductOfferResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaign-product-offers") // Endpoint'i değiştiriyoruz
public class CampaignProductOfferController {

    private final CampaignProductOfferService campaignProductOfferService;

    public CampaignProductOfferController(CampaignProductOfferService campaignProductOfferService) {
        this.campaignProductOfferService = campaignProductOfferService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCampaignProductOfferResponse add(@RequestBody CreateCampaignProductOfferRequest request) {
        return campaignProductOfferService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCampaignProductOfferResponse update(@RequestBody UpdateCampaignProductOfferRequest request) {
        return campaignProductOfferService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCampaignProductOfferResponse> getListCampaignProductResponses() {
        return campaignProductOfferService.getListCampaignProductResponse();
    }

    @DeleteMapping("/{id}") // {id} daha standart
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        campaignProductOfferService.delete(id);
    }

    @DeleteMapping("/{id}/soft") // {id}/soft daha standart
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id) {
        campaignProductOfferService.softDelete(id);
    }

    // YENİ ENDPOINT'İ EKLE
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CampaignProductOfferResponse getById(@PathVariable int id) {
        return campaignProductOfferService.getByIdForClient(id);
    }
}
