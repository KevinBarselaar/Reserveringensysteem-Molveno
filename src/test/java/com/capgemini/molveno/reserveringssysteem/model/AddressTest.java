package com.capgemini.molveno.reserveringssysteem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddressTest {

    private Address address;

    @BeforeEach
    void setup() {
        this.address = new Address();
    }

    @Test
    void getStreetname_void_returnsNull() {
        String expectedStreetname = null;
        String actualStreetname = address.getStreetName();

        assertThat(actualStreetname, is(expectedStreetname));
    }

    @Test
    void setStreetname_Void_returnsStreetname() {
        String expectedStreetname = "Street";

        address.setStreetName("Street");
        String actualStreetname = address.getStreetName();

        assertThat(actualStreetname, is(expectedStreetname));
    }

    @Test
    void getHouseNumber_void_returnsZero() {
        int expectedHouseNumber = 0;
        int actualHouseNumber = address.getHouseNumber();

        assertThat(actualHouseNumber, is(expectedHouseNumber));
    }

    @Test
    void setHouseNumber_void_returnsHouseNumber() {
        int expectedHouseNumber = 23;

        address.setHouseNumber(23);
        int actualHouseNumber = address.getHouseNumber();

        assertThat(actualHouseNumber, is(expectedHouseNumber));
    }

    @Test
    void getHouseNumberAddition_void_returnsNull() {
        String expectedHouseNumberAddition = null;
        String actualHouseNumberAddition = address.getHouseNumberAddition();

        assertThat(actualHouseNumberAddition, is(expectedHouseNumberAddition));
    }

    @Test
    void setHouseNumberAddition_void_returnsHouseNumberAddition() {
        String expectedHouseNumberAddition = "C9";

        address.setHouseNumberAddition("C9");
        String actualHouseNumberAddition = address.getHouseNumberAddition();

        assertThat(actualHouseNumberAddition, is(expectedHouseNumberAddition));
    }

    @Test
    void getPostalCode_void_returnsNull() {
        String expectedPostalCode = null;
        String actualPostalCode = address.getPostalCode();

        assertThat(actualPostalCode, is(expectedPostalCode));
    }

    @Test
    void setPostalCode_void_returnsPostalCode() {
        String expectedPostalCode = "1234 AB";

        address.setPostalCode("1234 AB");
        String actualPostalCode = address.getPostalCode();

        assertThat(actualPostalCode, is(expectedPostalCode));
    }

    @Test
    void getCity_void_returnsNull() {
        String expectedCity = null;
        String actualCity = address.getCity();

        assertThat(actualCity, is(expectedCity));
    }

    @Test
    void setCity_void_returnsCity() {
        String expectedCity = "Gotham";

        address.setCity("Gotham");
        String actualCity = address.getCity();

        assertThat(actualCity, is(expectedCity));
    }

    @Test
    void getCountry_void_returnsNull() {
        String expectedCountry = null;
        String actualCountry = address.getCountry();

        assertThat(actualCountry, is(expectedCountry));
    }

    @Test
    void setCountry_void_returnsCountry() {
        String expectedCountry = "Australia";

        address.setCountry("Australia");
        String actualCountry = address.getCountry();

        assertThat(actualCountry, is(expectedCountry));
    }

}
