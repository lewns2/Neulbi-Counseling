package com.ssafy.db.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MaskAttributeConverter implements AttributeConverter<String, Integer> {

    @Override
    public Integer convertToDatabaseColumn(String s) {
        if ("DEFAULT_MASK".equals(s)) {
            return 1;
        } else if ("WHITE_MASK".equals(s)) {
            return 2;
        }
        return 0;
    }

    @Override
    public String convertToEntityAttribute(Integer code) {
        if (1 == code) {
            return "DEFAULT_MASK";
        } else if (2 == code) {
            return "WHITE_MASK";
        }
        return "UNKNOWN_MASK";
    }
}
