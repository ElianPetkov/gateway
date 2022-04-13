package com.etg.gateway.dto;

import com.etg.gateway.configurations.XmlDesSerializerForLatestExchange;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = XmlDesSerializerForLatestExchange.class)
public class LatestExchangeSearchXmlDto extends CurrencyExchangeSearchBaseDto {

}
