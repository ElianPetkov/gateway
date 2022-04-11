package com.etg.gateway.configurations;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomLocalDateTimeDesSerializer extends StdDeserializer<LocalDateTime> {

	private static final long serialVersionUID = 7456772009321486311L;
	private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of("UTC");

	public CustomLocalDateTimeDesSerializer() {
		super(LocalDateTime.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser jsonparser, DeserializationContext context) throws IOException {
		Long timestamp = Long.parseLong(jsonparser.getText());
		return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), DEFAULT_ZONE_ID);
	}
}