package kz.gamma.my.project.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

public class AllRandomMethods {

    //RANDOM boolean START
    public static boolean makeRandomBoolean() {
        int n = (int) (10 * Math.random());
        if (n % 2 == 0) {
            return true;
        } else
            return false;
    }
    //RANDOM boolean END

    //RANDOM BOOLEAN START
    public static Boolean makeRandomBOOLEAN() {
        int n = (int) (10 * Math.random());
        if (n % 2 == 0) {
            return true;
        } else
            return false;
    }
    //RANDOM BOOLEAN END

    //RANDOM STRINGS START
    public static String makeRandomString(int length) {
        String password = "";

        for (int i = 0; i < length - 2; i++) {
            password = password + randomCharacter("AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz");
        }
        String randomDigit = randomCharacter("0123456789");
        password = insertAtRandom(password, randomDigit);
        String randomCharacter = randomCharacter("!@#$%&_");
        password = insertAtRandom(password, randomCharacter);
        return password;
    }

    private static String randomCharacter(String characters) {
        int n = characters.length();
        int r = (int) (n * Math.random());
        return characters.substring(r, r + 1);
    }

    private static String insertAtRandom(String str, String toInsert) {
        int n = str.length();
        int r = (int) ((n + 1) * Math.random());
        return str.substring(0, r) + toInsert + str.substring(r);
    }
    //RANDOM STRINGS END


    //RANDOM Long START
    public static Long makeRandomLong(int num) {

        String number = "";
        String numbers = "1234567890";
        for (int i = 0; i < num; i++) {
            number = number + randomCharacters(numbers);
        }
        long numberLong = Long.parseLong(number);
        return numberLong;
    }

    private static String randomCharacters(String characters) {
        int n = characters.length();
        int r = (int) (n * Math.random());
        return characters.substring(r, r + 1);
    }
    //RANDOM Long END

    //RANDOM int START
    public static int makeRandomInt(int num) {

        String number = "";
        String numbers = "1234567890";
        for (int i = 0; i < num; i++) {
            number = number + randomCharactersInt(numbers);
        }
        int numberInt = Integer.parseInt(number);
        return numberInt;
    }

    private static String randomCharactersInt(String characters) {
        int n = characters.length();
        int r = (int) (n * Math.random());
        return characters.substring(r, r + 1);
    }
    //RANDOM int END


    //RANDOM DATE START

    public static Date makeRandomDate() {
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Random random = new Random();
        int minDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        Date date = Date.from(randomDate.atStartOfDay(defaultZoneId).toInstant());

        return date;

    }

    //END

    //RANDOM LocalDate START

    public static LocalDate makeRandomLocalDate() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2020, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);

        LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

        return randomDate;

    }

    //END












//    //RANDOM ORGANIZATION NAME START
//    public static String makeRandomOrgName() {
//
//        String[] orgNames = {"Microsoft ", "Apple", "Amazon ", "Alphabet ", "Berkshire Hathaway", "Facebook", "Alibaba", "Tencent"};
//        String orgName = orgNames[(int) (orgNames.length * Math.random())];
//        return orgName;
//    }
//
//
//    //RANDOM ORGANIZATION NAME END
//
//    //RANDOM FULL NAME START
//    public static String makeRandomFirstName() {
//        String[] firstNames = {"Анатолий", "Евгений", "Геннадий", "Игорь", "Богдан", "Ростислав", "Фёдор", "Ярослав"};
//        String firstName = firstNames[(int) (firstNames.length * Math.random())];
//        return firstName;
//    }
//
//    public static String makeRandomMiddleName() {
//        String[] middleNames = {"Павлович", "Борисович", "Александрович", "Михайлович", "Аркадьевич", "Ярославович", "Кириллович", "Егорович"};
//        String middleName = middleNames[(int) (middleNames.length * Math.random())];
//
//        return middleName;
//    }
//
//    public static String makeRandomLastName() {
//        String[] lastNames = {"Кузнецов", "Богданов", "Мельников", "Евсеев", "Блинов", "Бобылёв", "Захаров", "Лыткин"};
//        String lastName = lastNames[(int) (lastNames.length * Math.random())];
//
//        return lastName;
//    }
//    //RANDOM FULL NAME END








}
