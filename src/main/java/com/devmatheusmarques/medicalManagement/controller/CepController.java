package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.dto.AddressRequestDTO;
import com.devmatheusmarques.medicalManagement.service.ViaCepClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cep")
public class CepController {

    @Autowired
    private ViaCepClient viaCepClient;

    @GetMapping("/{cep}")
    public ResponseEntity<AddressRequestDTO> getAddressByCep(@PathVariable String cep) {
        AddressRequestDTO address = viaCepClient.getAddressByZipCode(cep);
        return ResponseEntity.ok(address);
    }
}
