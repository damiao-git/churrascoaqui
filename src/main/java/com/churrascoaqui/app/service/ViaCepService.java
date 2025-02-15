package com.churrascoaqui.app.service;

import com.churrascoaqui.app.entity.ViaCepResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final String VIACEP_URL = "https://viacep.com.br/ws/%s/json/";

    public ViaCepResponseDTO verificarCep(String cep){
        String url = String.format(VIACEP_URL, cep);
        RestTemplate restTemplate = new RestTemplate();

        ViaCepResponseDTO responseDTO = restTemplate.getForObject(url, ViaCepResponseDTO.class);

        if(responseDTO != null && responseDTO.getErro() != null){
            throw new IllegalArgumentException("CEP inválido");
        }
        return responseDTO;
    }
}
