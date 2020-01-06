package me.rainstorm.algo4.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author baochen1.zhang
 * @date 2020.01.06
 */
public class FileIn implements AutoCloseable {

    private BufferedReader bufferedReader;

    public FileIn(File file) throws FileNotFoundException {
        bufferedReader = new BufferedReader(new FileReader(file));
    }

    public FileIn(String filename) throws FileNotFoundException {
        this(new File(filename));
    }

    public String[] readAllStrings() {
        return bufferedReader.lines().toArray(String[]::new);
    }

    public Integer[] readAllInts() {
        String[] strings = readAllStrings();
        return Arrays.stream(strings)
                .map(String::trim)
                .map(Integer::valueOf)
                .toArray(Integer[]::new);
    }

    @Override
    public void close() throws Exception {
        if (Objects.nonNull(bufferedReader)) {
            bufferedReader.close();
        }
    }
}
