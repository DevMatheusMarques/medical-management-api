package com.devmatheusmarques.medicalManagement.service;

import com.devmatheusmarques.medicalManagement.dto.AddressRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "https://viacep.com.br/ws/")
public interface ViaCepClient {

    @GetMapping("{zip_code}/json")
    AddressRequestDTO getAddressByZipCode(@PathVariable("zip_code") String zip_code);
}
