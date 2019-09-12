package com.capgemini.molveno.reserveringssysteem.io;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import com.capgemini.molveno.reserveringssysteem.model.Kamer;
import com.capgemini.molveno.reserveringssysteem.model.KamerType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class KamerExcelDeserializer {

    public List<Kamer> deserialize(File excelFile) {
        List<Kamer> kamers = new ArrayList<>();
        int kamerId = 1;

        try (FileInputStream excelInputStream = new FileInputStream(excelFile);
             XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelInputStream)) {

            for (int sheetIndex = 0; sheetIndex < excelWorkbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = excelWorkbook.getSheetAt(sheetIndex);

                Iterator<Row> rowIterator = sheet.rowIterator();
                rowIterator.next(); //skip the first row (titles)

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    if (row.cellIterator().hasNext()) {
                        KamerType roomType = KamerType.from(row.getCell(0).toString());
                        String volwassenenCapaciteit = row.getCell(1).toString();
                        String kinderenCapaciteit = row.getCell(2).toString();
                        String bedType = row.getCell(3).toString();
                        boolean invalideVriendelijk = !row.getCell(4).toString().isEmpty();

                        Kamer kamerFromRow = new Kamer(kamerId, roomType,
                                (int) Double.parseDouble(volwassenenCapaciteit),
                                (int) Double.parseDouble(kinderenCapaciteit),
                                invalideVriendelijk,
                                sheetIndex,
                                this.getBedTypesFromString(bedType));

                        kamers.add(kamerFromRow);
                        kamerId += 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return kamers;
    }

    public BedType[] getBedTypesFromString(String bedTypes) {
        if (bedTypes == null || bedTypes.isEmpty()) {
            return null;
        }

        List<BedType> types = new ArrayList<>();
        String[] bedOptions = bedTypes.split(",");

        for (String option : bedOptions) {
            option = option.replace(" ", "").replace("BED", "").replace("x", "").toUpperCase();
            BedType bedType;

            Matcher numberInStringRegex = Pattern.compile("\\d[0-9]{0,}").matcher(option);
            if (numberInStringRegex.find()) { //The option contains a number

                int amountOfBeds = Integer.parseInt(numberInStringRegex.group(0));
                for (int bedIndex = 0; bedIndex < amountOfBeds; bedIndex++) {
                    bedType = BedType.valueOf(option.replace(String.valueOf(amountOfBeds), ""));

                    types.add(bedType);
                }
            } else {
                types.add(BedType.valueOf(option));
            }
        }

        return types.toArray(new BedType[0]);
    }
}
