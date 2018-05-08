package com.nytimes.subscription.platform.interview;

import com.nytimes.subscription.platform.interview.pojo.Result;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElevatorTest {

    @Test
    public void testModelA() {
        Model model = new ModelA();
        Result result = model.parse("7:11-6,10-5,6-8,7-4,12-7,8-9");
        assertEquals(13, result.getOrder().size());
        assertEquals(40, result.getSum());
    }

    @Test
    public void testModelB() {
        Model model = new ModelB();
        Result result = model.parse("7:11-6,10-5,6-8,7-4,12-7,8-9");
        assertEquals(12, result.getOrder().size());
        assertEquals(30, result.getSum());
    }

}
