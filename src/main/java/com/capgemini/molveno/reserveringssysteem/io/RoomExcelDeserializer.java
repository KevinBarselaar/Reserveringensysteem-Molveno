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

    /**
     * Extracts all the room data from an excel sheet
     * @param excelFile Excel file containing room data
     * @return List of Room objects
     */
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

                    //Get room data from row
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

    /**
     * Gets all the different beds found in a room
     * @param bedTypes Raw string containing number and types of bed
     * @return Array containing the types of bed found in a room
     */
    public BedType[] getBedTypesFromString(String bedTypes) {
        if (bedTypes == null || bedTypes.isEmpty()) {
            return null;
        }

        List<BedType> types = new ArrayList<>();
        //Remove unnecessary data so that only the number of beds and types remain
        bedTypes = bedTypes.toUpperCase().replace("BEDS", "").replace("BED", "")
                .replace("X", "").replace(",", "");
        String[] bedOptions = bedTypes.split(" ");
        bedOptions = deleteEmpty(bedOptions);

        for (int i = 0; i < bedOptions.length; i++) {
                BedType bedType;

                Matcher numberInStringRegex = Pattern.compile("\\d[0-9]{0,}").matcher(bedOptions[i]);
                if (numberInStringRegex.find()) { //The option contains a number
                    int amountOfBeds = Integer.parseInt(numberInStringRegex.group(0));
                    //Type of bed found in next value in array
                    bedType = getType(bedOptions[i+1]);

                    for (int bedIndex = 0; bedIndex < amountOfBeds; bedIndex++) {
                        types.add(bedType);
                    }

                    i++;
                } else { //Option doesn't contain a number, which means a single bed of the type
                    types.add(getType(bedOptions[i]));
                }
            }
        return types.toArray(new BedType[0]);
    }

    /**
     * Gets the BedType contained in a String
     * @param bedType String containing info about BedType
     * @return BedType found in String
     */
    public BedType getType(String bedType) {
        String[] split = bedType.replaceAll("[0-9]", "").split("");
        //Due to BedType naming we only have to check the first letter of the string to find the type of bed
        switch (split[0].toLowerCase()) {
            case "s":
                return BedType.SINGLE;
            case "d":
                return BedType.DOUBLE;
            case "b":
                return BedType.BABY;
            default:
                return BedType.SINGLE;
        }

    }

    /**
     * Removes empty entries in an array
     * @param array Array containing strings
     * @return Array that was given, with the empty values removed
     */
    public String[] deleteEmpty(String[] array) {
        ArrayList<String> result = new ArrayList<String>();
        //Adds only the strings that are not empty into the ArrayList
        for(String string : array) {
            if(!string.equals("")) {
                result.add(string);
            }
        }

        return result.toArray(new String[0]);
    }
}
