/*
 * Copyright (c) 2017 AcadiaSoft, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.acadiasoft.simm.engine.test;

import com.acadiasoft.simm.model.addon.AddOnNotionalFactor;
import com.acadiasoft.simm.engine.SIMM;
import com.acadiasoft.simm.model.addon.AddOnNotional;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by alec.stewart on 9/6/17.
 */
public class AcadiaAddOnUnitTestV2_0 extends AbstractAcadiaUnitTestV2_0 {

  private final SIMM simm = new SIMM();

  @Test // tested: notional
  public void testAN1() {
    Map<String, AddOnNotionalFactor> factors = new HashMap<>();
    factors.put(AN1.getProduct(), AN1);
    Map<String, List<AddOnNotional>> notional = new HashMap<>();
    notional.put(AN3.getProduct(), Arrays.asList(AN3));
    Assert.assertEquals(new BigDecimal("610500"), simm.calculateAdditional(EMPTY_LIST, EMPTY_MULT, factors, notional, ZERO_FIXED).setScale(0, RoundingMode.HALF_UP));
  }

  @Test // tested: notional (netting)
  public void testAN2() {
    Map<String, AddOnNotionalFactor> factors = new HashMap<>();
    factors.put(AN1.getProduct(), AN1);
    Map<String, List<AddOnNotional>> notional = new HashMap<>();
    notional.put(AN3.getProduct(), Arrays.asList(AN3, AN5));
    Assert.assertEquals(new BigDecimal("1526250"), simm.calculateAdditional(EMPTY_LIST, EMPTY_MULT, factors, notional, ZERO_FIXED).setScale(0, RoundingMode.HALF_UP));
  }

  @Test // tested: notional (netting with negative (absolute sum))
  public void testAN3() {
    Map<String, AddOnNotionalFactor> factors = new HashMap<>();
    factors.put(AN1.getProduct(), AN1);
    Map<String, List<AddOnNotional>> notional = new HashMap<>();
    notional.put(AN3.getProduct(), Arrays.asList(AN3, AN4, AN5));
    Assert.assertEquals(new BigDecimal("2747250"), simm.calculateAdditional(EMPTY_LIST, EMPTY_MULT, factors, notional, ZERO_FIXED).setScale(0, RoundingMode.HALF_UP));
  }

  @Test // tested: notional (two products)
  public void testAN4() {
    Map<String, AddOnNotionalFactor> factors = new HashMap<>();
    factors.put(AN1.getProduct(), AN1);
    factors.put(AN2.getProduct(), AN2);
    Map<String, List<AddOnNotional>> notional = new HashMap<>();
    notional.put(AN3.getProduct(), Arrays.asList(AN3, AN4, AN5));
    notional.put(AN6.getProduct(), Arrays.asList(AN6, AN7));
    Assert.assertEquals(new BigDecimal("3363750"), simm.calculateAdditional(EMPTY_LIST, EMPTY_MULT, factors, notional, ZERO_FIXED).setScale(0, RoundingMode.HALF_UP));
  }

  @Test // tested: notional (non-existent product)
  public void testAN5() {
    Map<String, AddOnNotionalFactor> factors = new HashMap<>();
    factors.put(AN1.getProduct(), AN1);
    factors.put(AN2.getProduct(), AN2);
    Map<String, List<AddOnNotional>> notional = new HashMap<>();
    notional.put(AN3.getProduct(), Arrays.asList(AN3, AN4, AN5));
    notional.put(AN6.getProduct(), Arrays.asList(AN6, AN7));
    notional.put(AN8.getProduct(), Arrays.asList(AN8));
    Assert.assertEquals(new BigDecimal("3363750"), simm.calculateAdditional(EMPTY_LIST, EMPTY_MULT, factors, notional, ZERO_FIXED).setScale(0, RoundingMode.HALF_UP));
  }

  @Test // tested: notional (two products)
  public void testAN6() {
    Assert.assertEquals(new BigDecimal("16500000"), simm.calculateAdditional(EMPTY_LIST, EMPTY_MULT, EMPTY_FACTORS, EMPTY_NOTIONALS, AN10).setScale(0, RoundingMode.HALF_UP));
  }

  @Test // tested: notional (non-existent product)
  public void testAN7() {
    Map<String, AddOnNotionalFactor> factors = new HashMap<>();
    factors.put(AN1.getProduct(), AN1);
    factors.put(AN2.getProduct(), AN2);
    Map<String, List<AddOnNotional>> notional = new HashMap<>();
    notional.put(AN3.getProduct(), Arrays.asList(AN3, AN4, AN5));
    notional.put(AN6.getProduct(), Arrays.asList(AN6, AN7));
    notional.put(AN8.getProduct(), Arrays.asList(AN8));
    notional.put(AN9.getProduct(), Arrays.asList(AN9));
    Assert.assertEquals(new BigDecimal("19863750"), simm.calculateAdditional(EMPTY_LIST, EMPTY_MULT, factors, notional, AN10).setScale(0, RoundingMode.HALF_UP));
  }

}
