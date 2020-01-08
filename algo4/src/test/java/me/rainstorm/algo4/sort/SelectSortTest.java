package me.rainstorm.algo4.sort;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author baochen1.zhang
 * @date 2020.01.06
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SelectSortTest extends BaseIntegerSortTest {
    protected final int REPEATED_TIME = 10;

    public SelectSortTest() {
        super(Selection::new);
    }

    @Order(1)
    @RepeatedTest(REPEATED_TIME)
    public void ints1KTest() throws Exception {
        sort(ints1K());
    }

    @Order(2)
    @RepeatedTest(REPEATED_TIME)
    public void ints2KTest() throws Exception {
        sort(ints2K());
    }

    @Order(3)
    @RepeatedTest(REPEATED_TIME)
    public void ints4KTest() throws Exception {
        sort(ints4K());
    }

    @Order(4)
    @RepeatedTest(REPEATED_TIME)
    public void ints8KTest() throws Exception {
        sort(ints8K());
    }

    @Order(5)
    @RepeatedTest(REPEATED_TIME)
    public void ints32KTest() throws Exception {
        sort(ints32K());
    }

    @Order(6)
    @RepeatedTest(REPEATED_TIME)
    public void ints1MTest() throws Exception {
        sort(ints1M());
    }
}
