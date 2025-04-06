package com.devmatheusmarques.medicalManagement.controller;

import com.devmatheusmarques.medicalManagement.config.OpenAIConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/faq")
public class FaqController {

    @Autowired
    private OpenAIConfig openAIConfig;

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions";

    @PostMapping("/ask")
    public ResponseEntity<String> askAI(@RequestBody String question) {
        try {
            String response = getAnswerFromOpenAI(question);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao processar a pergunta: " + e.getMessage());
        }
    }

    private String getAnswerFromOpenAI(String question) {
        // Configura a requisição HTTP para a API da OpenAI
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = getStringHttpEntity(question);
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(OPENAI_API_URL);

        // Realiza a requisição POST para a OpenAI
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(), HttpMethod.POST, entity, String.class);

        // Processa a resposta da API
        String responseBody = responseEntity.getBody();
        if (responseBody != null && responseBody.contains("\"choices\"")) {
            // A resposta estará no campo "choices", então vamos pegar o primeiro texto da escolha
            return responseBody.split("\"text\":\"")[1].split("\"")[0];
        } else {
            return "Erro na resposta da API da OpenAI";
        }
    }

    private HttpEntity<String> getStringHttpEntity(String question) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + openAIConfig.getKey());
        headers.set("Content-Type", "application/json");

        // Corpo da requisição atualizado para o formato correto (escapando as aspas corretamente)
        String body = "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [{\"role\": \"user\", \"content\": \"" + question.replace("\"", "\\\"") + "\"}],\n" +  // Escapando as aspas
                "  \"max_tokens\": 100,\n" +
                "  \"temperature\": 0.7\n" +
                "}";

        return new HttpEntity<>(body, headers);
    }

}
