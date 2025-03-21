package com.airalo.api.pojo.sims;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id", "created_at", "iccid", "lpa", "imsis", "matching_id", "confirmation_code",
    "qrcode", "qrcode_url", "direct_apple_installation_url", "voucher_code",
    "airalo_code", "apn_type", "apn_value", "is_roaming", "brand_settings_name",
    "msisdn", "apn"
})
@Data
public class SimData {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("created_at")
    private String createdAt;
    @JsonProperty("iccid")
    private String iccid;
    @JsonProperty("lpa")
    private String lpa;
    @JsonProperty("imsis")
    private Object imsis;
    @JsonProperty("matching_id")
    private String matchingId;
    @JsonProperty("confirmation_code")
    private Object confirmationCode;
    @JsonProperty("qrcode")
    private String qrcode;
    @JsonProperty("qrcode_url")
    private String qrcodeUrl;
    @JsonProperty("direct_apple_installation_url")
    private String directAppleInstallationUrl;
    @JsonProperty("voucher_code")
    private Object voucherCode;
    @JsonProperty("airalo_code")
    private Object airaloCode;
    @JsonProperty("apn_type")
    private String apnType;
    @JsonProperty("apn_value")
    private String apnValue;
    @JsonProperty("is_roaming")
    private Boolean isRoaming;
    @JsonProperty("brand_settings_name")
    private Object brandSettingsName;
    @JsonProperty("msisdn")
    private Object msisdn;
    @JsonProperty("apn")
    private Apn apn;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}

