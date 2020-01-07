package me.rainstorm.algo4.sort;

import me.rainstorm.algo4.sort.converter.IntegerElementConverter;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;

/**
 * @author baochen1.zhang
 * @date 2020.01.06
 */
public class SelectTest {

    @RepeatedTest(5)
    @ParameterizedTest
    @TxtFileSource(resources = {"data/1Kints.txt", "data/8ints.txt"},
            elementConverter = IntegerElementConverter.class)
    public void Selection(Integer[] ints) {
        Sort<Integer> selection = new Selection<>();

        selection.sort(ints, selection::less);
        assert selection.isSorted(ints, selection::less);
        selection.print(ints);
    }
}
