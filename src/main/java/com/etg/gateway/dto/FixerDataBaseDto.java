package com.etg.gateway.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.etg.gateway.configurations.CustomLocalDateTimeDesSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FixerDataBaseDto {
	private String base;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime timestamp;

}
