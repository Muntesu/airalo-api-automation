package com.airalo.api.pojo.orders;

import com.airalo.api.pojo.sims.SimData;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "code",
    "currency",
    "package_id",
    "quantity",
    "type",
    "description",
    "esim_type",
    "validity",
    "package",
    "data",
    "price",
    "created_at",
    "manual_installation",
    "qrcode_installation",
    "installation_guides",
    "text",
    "voice",
    "net_price",
    "brand_settings_name",
    "sims"
})
@Data
public class SimPurchaseData {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("package_id")
    private String packageId;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private String description;

    @JsonProperty("esim_type")
    private String esimType;

    @JsonProperty("validity")
    private Integer validity;

    @JsonProperty("package")
    private String packageName;

    @JsonProperty("data")
    private String data;

    @JsonProperty("price")
    private Double price;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("manual_installation")
    private String manualInstallation;

    @JsonProperty("qrcode_installation")
    private String qrcodeInstallation;

    @JsonProperty("installation_guides")
    private Map<String, String> installationGuides;

    @JsonProperty("text")
    private Object text;

    @JsonProperty("voice")
    private Object voice;

    @JsonProperty("net_price")
    private Double netPrice;

    @JsonProperty("brand_settings_name")
    private Object brandSettingsName;

    @JsonProperty("sims")
    private List<SimData> sims;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

