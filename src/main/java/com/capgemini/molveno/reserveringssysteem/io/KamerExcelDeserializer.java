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

    public List<Kamer> deserlize(File excelFile) {
        List<Kamer> kamers = new ArrayList<>();
        int kamerId = 1;

        try (FileInputStream excelInputStream = new FileInputStream(excelFile);
             XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelInputStream)) {

            for (int sheetIndex = 0; sheetIndex < excelWorkbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = excelWorkbook.getSheetAt(sheetIndex);

                Iterator<Row> rowIterator = sheet.rowIterator();
                rowIterator.next();

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
                                sheetIndex + 1,
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
        List<BedType> types = new ArrayList<>();
        String[] bedOptions = bedTypes.split(",");

        for (String option : bedOptions) {
            option = option.replace(" ", "").toUpperCase();
            BedType bedType = null;

            Matcher numberInStringRegex = Pattern.compile("\\d[0-9]{0,}").matcher(option);
            if (numberInStringRegex.find()) { //The option contains a number

                String numberInString = numberInStringRegex.group(0);
                int amountOfBeds = Integer.parseInt(numberInString);
                for (int amount = 0; amount < amountOfBeds; amount++) {
                    option = option.replace(numberInString, "").replace("BED", "").replace("x", "");
                    bedType = BedType.valueOf(option);

                    types.add(bedType);
                }
            } else {
                bedType = BedType.valueOf(option.replace("BED", ""));
                types.add(bedType);
            }
        }

        return types.toArray(new BedType[types.size()]);
    }
}
