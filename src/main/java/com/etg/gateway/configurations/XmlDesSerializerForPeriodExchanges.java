package com.etg.gateway.configurations;

import java.io.IOException;

import com.etg.gateway.dto.PeriodExchangeSearchXmlDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class XmlDesSerializerForPeriodExchanges extends StdDeserializer<PeriodExchangeSearchXmlDto> {

	public XmlDesSerializerForPeriodExchanges() {
		this(null);
	}

	public XmlDesSerializerForPeriodExchanges(Class<?> vc) {
		super(vc);
	}

	@Override
	public PeriodExchangeSearchXmlDto deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		JsonNode productNode = jp.getCodec().readTree(jp);
		PeriodExchangeSearchXmlDto dto = new PeriodExchangeSearchXmlDto();
		dto.setRequestId(productNode.get("id").textValue());
		dto.setClient(productNode.get("history").get("consumer").asInt());
		dto.setCurrency(productNode.get("history").get("currency").textValue());
		dto.setPeriod(productNode.get("history").get("period").asInt());
		return dto;
	}
}
