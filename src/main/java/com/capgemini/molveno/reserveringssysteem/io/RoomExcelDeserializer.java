package com.capgemini.molveno.reserveringssysteem.io;

import com.capgemini.molveno.reserveringssysteem.model.BedType;
import com.capgemini.molveno.reserveringssysteem.model.Room;
import com.capgemini.molveno.reserveringssysteem.model.RoomType;
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
public class RoomExcelDeserializer {

    public List<Room> deserialize(File excelFile) {
        List<Room> rooms = new ArrayList<>();
        long roomId = 1;

        try (FileInputStream excelInputStream = new FileInputStream(excelFile);
             XSSFWorkbook excelWorkbook = new XSSFWorkbook(excelInputStream)) {

            for (int sheetIndex = 0; sheetIndex < excelWorkbook.getNumberOfSheets(); sheetIndex++) {
                Sheet sheet = excelWorkbook.getSheetAt(sheetIndex);

                Iterator<Row> rowIterator = sheet.rowIterator();
                rowIterator.next(); //skip the first row (titles)

                while (rowIterator.hasNext()) {
                    Row row = rowIterator.next();

                    if (row.cellIterator().hasNext()) {
                        RoomType roomType = RoomType.from(row.getCell(0).toString());
                        String adultCapacity = row.getCell(1).toString();
                        String minorCapacity = row.getCell(2).toString();
                        String bedType = row.getCell(3).toString();
                        boolean disabledFriendly = !row.getCell(4).toString().isEmpty();
                        String roomPrice = row.getCell(5).toString();

                        Room roomFromRow = new Room(roomType,
                                (int) Double.parseDouble(adultCapacity),
                                (int) Double.parseDouble(minorCapacity),
                                this.getBedTypesFromString(bedType),
                                disabledFriendly,
                                sheetIndex + 1,
                                Double.parseDouble(roomPrice));

                        rooms.add(roomFromRow);
                        roomId += 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    public BedType[] getBedTypesFromString(String bedTypes) {
        if (bedTypes == null || bedTypes.isEmpty()) {
            return null;
        }

        List<BedType> types = new ArrayList<>();
        String[] bedOptions = bedTypes.split(",");

        for (String option : bedOptions) {
            option = option.toUpperCase().replace(" ", "").replace("BED", "").replace("x", "");
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
