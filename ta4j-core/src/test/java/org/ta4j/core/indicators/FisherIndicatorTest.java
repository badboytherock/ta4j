/*
  The MIT License (MIT)

  Copyright (c) 2014-2017 Marc de Verdelhan, Ta4j Organization & respective authors (see AUTHORS)

  Permission is hereby granted, free of charge, to any person obtaining a copy of
  this software and associated documentation files (the "Software"), to deal in
  the Software without restriction, including without limitation the rights to
  use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
  the Software, and to permit persons to whom the Software is furnished to do so,
  subject to the following conditions:

  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.ta4j.core.indicators;

import org.junit.Before;
import org.junit.Test;
import org.ta4j.core.BaseTimeSeries;
import org.ta4j.core.Num.Num;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.mocks.MockBar;

import java.time.ZonedDateTime;
import java.util.function.Function;

import static org.ta4j.core.TestUtils.assertNumEquals;


public class FisherIndicatorTest extends AbstractIndicatorTest{

protected TimeSeries series;

    public FisherIndicatorTest(Function<Number, Num> numFunction) {
        super(null, numFunction);
    }

    @Before
    public void setUp() {

        series = new BaseTimeSeries.SeriesBuilder()
                .withNumTypeOf(numFunction)
                .withName("NaN test")
                .build();
        int i = 20;
        // open, close, max, min
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),44.98, 45.05, 45.17, 44.96,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.05, 45.10, 45.15, 44.99,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.11, 45.19, 45.32, 45.11,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.19, 45.14, 45.25, 45.04,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.12, 45.15, 45.20, 45.10,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.15, 45.14, 45.20, 45.10,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.13, 45.10, 45.16, 45.07,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.12, 45.15, 45.22, 45.10,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.15, 45.22, 45.27, 45.14,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.24, 45.43, 45.45, 45.20,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.43, 45.44, 45.50, 45.39,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.43, 45.55, 45.60, 45.35,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.58, 45.55, 45.61, 45.39,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.45, 45.01, 45.55, 44.80,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),45.03, 44.23, 45.04, 44.17,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),44.23, 43.95, 44.29, 43.81,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),43.91, 43.08, 43.99, 43.08,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),43.07, 43.55, 43.65, 43.06,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i--),43.56, 43.95, 43.99, 43.53,0,1,0,numFunction));
        series.addBar(new MockBar(ZonedDateTime.now().minusSeconds(i),43.93, 44.47, 44.58, 43.93,0,1,0,numFunction));
    }

    @Test
    public void fisher() {
        FisherIndicator fisher = new FisherIndicator(series);

        assertNumEquals(fisher.getValue(10), 0.6448642008177138);
        assertNumEquals(fisher.getValue(11), 0.8361770425706673);
        assertNumEquals(fisher.getValue(12), 0.9936697984965788);
        assertNumEquals(fisher.getValue(13), 0.8324807235379169);
        assertNumEquals(fisher.getValue(14), 0.5026313552592737);
        assertNumEquals(fisher.getValue(15), 0.06492516204615063);
    }
}
