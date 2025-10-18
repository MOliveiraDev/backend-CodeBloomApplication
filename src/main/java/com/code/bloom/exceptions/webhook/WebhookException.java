// java
package com.code.bloom.exceptions.webhook;

public class WebhookException extends RuntimeException {

    public WebhookException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebhookException(String message) {
        super(message);
    }

}
