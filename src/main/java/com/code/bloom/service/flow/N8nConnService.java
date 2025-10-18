package com.code.bloom.service.flow;

import com.code.bloom.dto.flow.N8nResponse;
import com.code.bloom.exceptions.webhook.WebhookException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.UnknownContentTypeException;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class N8nConnService {

    private final RestTemplate restTemplate;

    @Value("${n8n.webhook.url}")
    private String webHook;

    public N8nResponse sendToN8n(String message, String username) {
        try {
            Map<String, String> body = Map.of(
                "message", message,
                "username", username
            );
            
            ResponseEntity<N8nResponse> response = restTemplate.postForEntity(webHook, body, N8nResponse.class);
            return response.getBody();
            
        } catch (UnknownContentTypeException ex) {
            log.error("Webhook retornou conteúdo inesperado (HTML ao invés de JSON): {}", ex.getMessage());
            throw new WebhookException(
                "O serviço de processamento retornou uma resposta inválida. " +
                "Verifique se o webhook está configurado corretamente.", ex
            );
            
        } catch (HttpClientErrorException.NotFound ex) {
            log.error("Webhook não encontrado: {}", ex.getMessage());
            throw new WebhookException(
                "O serviço de processamento está temporariamente indisponível. " +
                "Por favor, tente novamente em alguns instantes.", ex
            );
            
        } catch (HttpClientErrorException ex) {
            log.error("Erro HTTP ao chamar webhook: Status {} - {}", ex.getStatusCode(), ex.getMessage());
            throw new WebhookException(
                "Erro ao processar sua mensagem. Por favor, tente novamente.", ex
            );
            
        } catch (RestClientException ex) {
            log.error("Erro de comunicação com o webhook: {}", ex.getMessage());
            throw new WebhookException(
                "Não foi possível conectar ao serviço de processamento. " +
                "Verifique sua conexão e tente novamente.", ex
            );
        }
    }
}
