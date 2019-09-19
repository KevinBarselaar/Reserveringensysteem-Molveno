package com.capgemini.molveno.reserveringssysteem.io;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class RoomExcelDeserializerTest {

    private RoomExcelDeserializer excelDeserializer;

    @BeforeEach
    void setup() {
        this.excelDeserializer = new RoomExcelDeserializer();
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
        BedType[] expectedBedTypes = null;

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void getBedTypesFromString_2_x_Double_bed_4_Singles_2_Baby_Beds_returnsBedTypes() {
        String inputString = "2 x Double bed, 4 singles, 2 baby beds";
        BedType[] expectedBedTypes = {BedType.DOUBLE, BedType.DOUBLE, BedType.SINGLE, BedType.SINGLE, BedType.SINGLE,
                BedType.SINGLE, BedType.BABY, BedType.BABY};

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void getBedTypesFromStringMissingComma() {
        String inputString = "2 Double bed 1 single";
        BedType[] expectedBedTypes = {BedType.DOUBLE, BedType.DOUBLE, BedType.SINGLE};

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void getBedTypesFromStringMissingCommaAndX() {
        String inputString = "2 Double bed 1 single, 2 x baby";
        BedType[] expectedBedTypes = {BedType.DOUBLE, BedType.DOUBLE, BedType.SINGLE, BedType.BABY, BedType.BABY};

        BedType[] actualBedTypes = this.excelDeserializer.getBedTypesFromString(inputString);

        assertThat(actualBedTypes, is(expectedBedTypes));
    }

    @Test
    void deserialize_mockedExcelFile_returnsKamerList() {
//        createMockedExcelFile();
    }

    private void createMockedExcelFile() {
        XSSFWorkbook workbook = new XSSFWorkbook();

        for (int sheetIndex = 0; sheetIndex < 4; sheetIndex++) {
            workbook.createSheet("Sheet " + (sheetIndex + 1));
            XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

            for (int rowIndex = 0; rowIndex < 3; rowIndex++) {
                XSSFRow row = sheet.createRow(rowIndex);

                for (int cellIndex = 0; cellIndex < 3; cellIndex++) {
                    XSSFCell cell = row.createCell(0, CellType.STRING);
//                    cell.setCellValue();
                }
//                sheet.row
            } //TODO create mock excel file and use threading (callable)
        }
    }

}
