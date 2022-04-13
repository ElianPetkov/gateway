package com.etg.gateway.configurations;

import java.io.IOException;

import com.etg.gateway.dto.LatestExchangeSearchXmlDto;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class XmlDesSerializerForLatestExchange extends StdDeserializer<LatestExchangeSearchXmlDto> {
	public XmlDesSerializerForLatestExchange() {
		this(null);
	}

	public XmlDesSerializerForLatestExchange(Class<?> vc) {
		super(vc);
	}

	@Override
	public LatestExchangeSearchXmlDto deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		JsonNode productNode = jp.getCodec().readTree(jp);
		LatestExchangeSearchXmlDto dto = new LatestExchangeSearchXmlDto();
		dto.setRequestId(productNode.get("id").textValue());
		dto.setClient(productNode.get("get").get("consumer").asInt());
		dto.setCurrency(productNode.get("get").get("currency").textValue());
		return dto;
	}

}
