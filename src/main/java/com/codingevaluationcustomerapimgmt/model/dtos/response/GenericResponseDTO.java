package com.codingevaluationcustomerapimgmt.model.dtos.response;

import java.time.LocalDateTime;

public record GenericResponseDTO(String status, String message, LocalDateTime time){
}
