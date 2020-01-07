package me.rainstorm.algo4.sort;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.util.stream.Stream;

/**
 * @author baochen1.zhang
 * @date 2020.01.07
 */
public class TxtFileArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<TxtFileSource> {
    //    private final BiFunction<Class<?>, String, InputStream> inputStreamProvider;
//
//    private TxtFileSource annotation;
//    private String[] resources;
//
    @Override
    public void accept(TxtFileSource csvFileSource) {
        // todo 模拟 CsvFileArgumentsProvider 写出自己的参数解析 provider
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return null;
    }
}
