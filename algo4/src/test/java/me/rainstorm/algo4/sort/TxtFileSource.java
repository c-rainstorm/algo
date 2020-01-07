package me.rainstorm.algo4.sort;

import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.lang.annotation.*;
import java.util.function.Function;

/**
 * @see CsvSource
 * @see ArgumentsSource
 * @see org.junit.jupiter.params.ParameterizedTest
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(TxtFileArgumentsProvider.class)
public @interface TxtFileSource {
    String[] resources();

    Class<? extends Function<String, ?>> elementConverter();
}
