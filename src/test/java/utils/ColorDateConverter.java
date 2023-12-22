package utils;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class ColorDateConverter implements ArgumentConverter {
    @Override
    public Object convert(Object source, ParameterContext parameterContext) throws ArgumentConversionException {
        if (!(source instanceof String)) {
            throw new IllegalArgumentException(
                    "The argument should be a string: " + source);
        }
        try {
            String[] parts = ((String) source).split(",\\s*");
            int red = Integer.parseInt(parts[0]);
            int green = Integer.parseInt(parts[1]);
            int blue = Integer.parseInt(parts[2]);

            return String.format("rgb(%d, %d, %d)", red, green, blue);
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert", e);
        }
    }
}
