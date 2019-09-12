package com.capgemini.molveno.reserveringssysteem.io;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class KamerExcelDeserializerTest {

    private KamerExcelDeserializer excelDeserializer;

    @BeforeEach
    void setup() {
        this.excelDeserializer = new KamerExcelDeserializer();
    }

    @Test
    void getBedTypesFromString_single_returnsBedTypes() {
        String inputString = "Single";
        BedType[] expectedBedTypes = {BedType.SINGLE};

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void getBedTypesFromString_2Double1Baby_returnsBedTypes() {
        String inputString = "2 Double, 1 baby";
        BedType[] expectedBedTypes = {BedType.DOUBLE, BedType.DOUBLE, BedType.BABY};

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void getBedTypesFromString_2DoubleBaby2Single_returnsBedTypes() {
        String inputString = "2 double, baby, 2 Single";
        BedType[] expectedBedTypes = {BedType.DOUBLE, BedType.DOUBLE, BedType.BABY, BedType.SINGLE, BedType.SINGLE};

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void getBedTypesFromString_nullString_returnsNull() {
        String inputString = null;
        BedType[] expectedBedTypes = null;

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void getBedTypesFromString_emptyString_returnsNull() {
        String inputString = "";
        BedType[] expctedBedTypes = null;

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expctedBedTypes));
    }

    private void excelStub() {
        XSSFWorkbook workbook = new XSSFWorkbook();
    }

}
