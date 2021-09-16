package br.com.petz.exception;

import java.time.LocalDateTime;

public record StandardError(Integer status, String message, LocalDateTime timestamp) {

}
