package com.etiya.catalogservice.controller;

import com.etiya.catalogservice.service.abstracts.CampaignService;
import com.etiya.catalogservice.service.dtos.requests.Campaign.CreateCampaignRequest;
import com.etiya.catalogservice.service.dtos.requests.Campaign.UpdateCampaignRequest;
import com.etiya.catalogservice.service.dtos.responses.Campaign.CreatedCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.GetListCampaignResponse;
import com.etiya.catalogservice.service.dtos.responses.Campaign.UpdatedCampaignResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {
    private final CampaignService campaignService;

    public CampaignController(CampaignService campaignService) {
        this.campaignService = campaignService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCampaignResponse add(@RequestBody CreateCampaignRequest request)
    {
        return campaignService.add(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public UpdatedCampaignResponse update(@RequestBody UpdateCampaignRequest request) {
        return campaignService.update(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GetListCampaignResponse> getListCampaignResponsesFromCampaign(){
        return campaignService.getListCampaignResponse();
    }

    @DeleteMapping("{id}")//pathvariable ile anlaşsın diye, mapping yapsın diye
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable int id) {
        campaignService.delete(id);
    }

    @DeleteMapping("{id}/soft")
    @ResponseStatus(HttpStatus.OK)
    public void softDelete(@PathVariable int id){
        campaignService.softDelete(id);
    }


}

