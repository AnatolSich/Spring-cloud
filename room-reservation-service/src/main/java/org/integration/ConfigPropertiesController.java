package org.integration;

import com.netflix.config.DynamicPropertyFactory;
import com.netflix.config.DynamicStringProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigPropertiesController {

    @Value("${archaius.properties.one:not found!}")
    private String propertyOneWithValue;

    @Value("${archaius.properties.two:not found!}")
    private String propertyTwoWithValue;

    @Value("${archaius.properties.three:not found!}")
    private String propertyThreeWithValue;

    @Value("${archaius.properties.four:not found!}")
    private String propertyFourWithValue;

    private DynamicStringProperty propertyOneWithDynamic = DynamicPropertyFactory.getInstance()
        .getStringProperty("archaius.properties.one", "not found!");

    private DynamicStringProperty propertyTwoWithDynamic = DynamicPropertyFactory.getInstance()
        .getStringProperty("archaius.properties.two", "not found!");

    private DynamicStringProperty propertyThreeWithDynamic = DynamicPropertyFactory.getInstance()
        .getStringProperty("archaius.properties.three", "not found!");

    private DynamicStringProperty propertyFourWithDynamic = DynamicPropertyFactory.getInstance()
        .getStringProperty("archaius.properties.four", "not found!");

    @GetMapping("/properties-from-value")
    public Map<String, String> getPropertiesFromValue() {
        Map<String, String> properties = new HashMap<>();
        properties.put("archaius.properties.one", propertyOneWithValue);
        properties.put("archaius.properties.two", propertyTwoWithValue);
        properties.put("archaius.properties.three", propertyThreeWithValue);
        properties.put("archaius.properties.four", propertyFourWithValue);
        return properties;
    }

    @GetMapping("/properties-from-dynamic")
    public Map<String, String> getPropertiesFromDynamic() {
        Map<String, String> properties = new HashMap<>();
        properties.put("archaius.properties.one", propertyOneWithDynamic.get());
        properties.put("archaius.properties.two", propertyTwoWithDynamic.get());
        properties.put("archaius.properties.three", propertyThreeWithDynamic.get());
        properties.put("archaius.properties.four", propertyFourWithDynamic.get());
        return properties;
    }

}
