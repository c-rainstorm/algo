package me.rainstorm.algo4.sort;

import me.rainstorm.algo4.tools.FileIn;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

/**
 * @author baochen1.zhang
 * @date 2020.01.06
 */
public class SelectTest {

    @Test
    public void Ints8() throws Exception {
        try (FileIn fileIn = new FileIn(Paths.get("data/8ints.txt").toFile())) {
            Integer[] ints = fileIn.readAllInts();
            Sort<Integer> selection = new Selection<>();

            selection.sort(ints, selection::less);
            assert selection.isSorted(ints, selection::less);
            selection.print(ints);
        }
    }

    @Test
    public void Ints1K() throws Exception {
        try (FileIn fileIn = new FileIn(Paths.get("data/1Kints.txt").toFile())) {
            Integer[] ints = fileIn.readAllInts();
            Sort<Integer> selection = new Selection<>();

            selection.sort(ints, selection::less);
            assert selection.isSorted(ints, selection::less);
            selection.print(ints);
        }
    }
}
