package by.zemich.bargainms.application.mapper;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DtoToMapMapper {
    public Map<String, Object> map(Object dto) {
        return Arrays.stream(dto.getClass().getDeclaredFields())
                .peek(field -> field.setAccessible(true))
                .filter(field -> {
                    try {
                        return field.get(dto) != null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toMap(
                        Field::getName,
                        field -> {
                            try {
                                return field.get(dto);
                            } catch (IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }));
    }
}
