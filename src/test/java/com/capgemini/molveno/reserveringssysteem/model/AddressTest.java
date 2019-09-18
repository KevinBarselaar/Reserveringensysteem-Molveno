package com.capgemini.molveno.reserveringssysteem.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class AddressTest {

    private Address address;

    @BeforeEach
    void setup() {
        this.address = new Address();

        assertThat(address.getHouseNumber(), is(0));

        assertThat(address.getStreetName(), isEmptyOrNullString());
        assertThat(address.getHouseNumberAddition(), isEmptyOrNullString());
        assertThat(address.getPostalCode(), isEmptyOrNullString());
        assertThat(address.getCountry(), isEmptyOrNullString());
        assertThat(address.getCity(), isEmptyOrNullString());
    }

    @Test
    void setStreetName_inputStreet_returnStringStreet() {
        String expectedStreetname = "Street";

        address.setStreetName("Street");
        String actualStreetname = address.getStreetName();

        assertThat(actualStreetname, is(expectedStreetname));
    }

    @Test
    void setHouseNumber_input23_returnInt23() {
        int expectedHouseNumber = 23;

        address.setHouseNumber(23);
        int actualHouseNumber = address.getHouseNumber();

        assertThat(actualHouseNumber, is(expectedHouseNumber));
    }

    @Test
    void setHouseNumberAddition_inputC9_returnStringC9() {
        String expectedHouseNumberAddition = "C9";

        address.setHouseNumberAddition("C9");
        String actualHouseNumberAddition = address.getHouseNumberAddition();

        assertThat(actualHouseNumberAddition, is(expectedHouseNumberAddition));
    }

    @Test
    void setPostalCode_input1234_AB_returnString1234_AB() {
        String expectedPostalCode = "1234 AB";

        address.setPostalCode("1234 AB");
        String actualPostalCode = address.getPostalCode();

        assertThat(actualPostalCode, is(expectedPostalCode));
    }

    @Test
    void setCity_inputGotham_returnStringGotham() {
        String expectedCity = "Gotham";

        address.setCity("Gotham");
        String actualCity = address.getCity();

        assertThat(actualCity, is(expectedCity));
    }

    @Test
    void setCountry_inputAustralia_returnStringAustralia() {
        String expectedCountry = "Australia";

        address.setCountry("Australia");
        String actualCountry = address.getCountry();

        assertThat(actualCountry, is(expectedCountry));
    }

}
