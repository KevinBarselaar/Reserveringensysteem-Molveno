package com.capgemini.molveno.reserveringssysteem.io;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.model.KamerType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelDeserializer {

    public static void deserlize(File excelFile) throws IOException { //TODO use a try with resources
        FileInputStream excelInputStream = new FileInputStream(excelFile);
        XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelInputStream);

        int floorNumber = 0;
        for (int sheetIndex = 0; sheetIndex < excelWorkbook.getNumberOfSheets(); sheetIndex++) {
            Sheet sheet = excelWorkbook.getSheetAt(sheetIndex);
            floorNumber = sheetIndex + 1;

            for (Row row : sheet) {
                if (row.getRowNum() == 0) { //skip the title row
                    break;
                }

                //TODO create a kamer object
                String roomType = row.getCell(0).toString();
                String volwassenenCapaciteit = row.getCell(1).toString();
                String kinderenCapaciteit = row.getCell(2).toString();
                String bedType = row.getCell(4).toString();
                boolean invalideVriendelijk = !row.getCell(5).toString().isEmpty();

                Kamer kamer = new Kamer(1, KamerType.SINGLE, Integer.parseInt(volwassenenCapaciteit),
                        Integer.parseInt(kinderenCapaciteit), invalideVriendelijk, floorNumber, BedType.SINGLE);

                System.out.println(kamer.toString());
            }

        }
    }
}
