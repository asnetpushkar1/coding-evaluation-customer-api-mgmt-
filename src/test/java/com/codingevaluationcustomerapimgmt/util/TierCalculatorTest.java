package com.codingevaluationcustomerapimgmt.util;


import com.codingevaluationcustomerapimgmt.model.enttites.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TierCalculatorTest {

    private TierCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new TierCalculator();
    }


    @ParameterizedTest
    @MethodSource("customerTierProvider")
    void testCalculateTier(Customer customer, String expectedTier) {
        String actual = calculator.calculateTier(customer);
        assertEquals(expectedTier, actual);
    }

    static Stream<Arguments> customerTierProvider() {

        LocalDateTime now = LocalDateTime.now();

        return Stream.of(
                Arguments.of(null, null),
                Arguments.of(customerBuilder(null, null), "SILVER"),
                Arguments.of(customerBuilder(null, now), "SILVER"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(15000), now.minusMonths(3)), "PLATINUM"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(10000), now.minusMonths(6).plusDays(1)), "PLATINUM"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(5000), now.minusMonths(10)), "GOLD"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(1000), now.minusMonths(12).plusDays(1)), "GOLD"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(10000), now.minusMonths(6).minusDays(1)), "GOLD"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(1000), now.minusMonths(12).minusDays(1)), "SILVER"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(999), now), "SILVER"),
                Arguments.of(customerBuilder(BigDecimal.ZERO, now), "SILVER"),
                Arguments.of(customerBuilder(BigDecimal.valueOf(15000), null), "SILVER")
        );
    }
    private static Customer customerBuilder(BigDecimal spend, LocalDateTime lastPurchase) {
        Customer c = new Customer();
        c.setAnnualSpend(spend);
        c.setLastPurchaseDate(lastPurchase);
        return c;
    }

}