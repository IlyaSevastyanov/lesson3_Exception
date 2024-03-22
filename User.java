
import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import exceptionsForProject.IncorrectEnter;
import exceptionsForProject.IncorrectLength;

public class User {

    private String firstname;
    private String lastName;
    private String patronymic;
    private String birthday;
    private long numberPhone;
    private char sex;

    public User() throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите данные форматом: <Фамилия> <Имя> <Отчество> <дата_рождения> <номер_телефона> <пол>");
            String registration = scanner.nextLine();
            String[] data = registration.split(" ");
            if (data.length != 6) throw new IncorrectLength();

            SetBirthday(data[3]);
            SetNumber(data[4]);
            SetSex(data[5]);
            this.firstname = data[1];
            this.lastName = data[0];
            this.patronymic = data[2];

            CreateAndWriteOfLog(registration);
        }
    }

    private void SetBirthday(String data) {
        String[] birthdayString = data.split(Pattern.quote("."));
        if (birthdayString.length != 3) {
            throw new IncorrectEnter("Некорректная дата рождения!");
        }
        try {
            int day = Integer.parseInt(birthdayString[0]);
            int month = Integer.parseInt(birthdayString[1]);
            int year = Integer.parseInt(birthdayString[2]);
            if (day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900 && year < 2025) {
                this.birthday = String.format("%d.%d.%d", day, month, year);
            } else {
                throw new IncorrectEnter("Нереальная дата рождения!");
            }
        } catch (NumberFormatException e) {
            throw new IncorrectEnter("В дате рождения должны быть только цифры!");
        }
    }

    public String GetBirthday() {
        return this.birthday;
    }

    public void SetNumber(String data) {
        long number = Long.parseLong(data);
        if(data.length() != 10) throw new IncorrectEnter("Нужно ввести номер длиной 10 цифр, без лишних символов!");
        if (number / 1000000000 == 7) throw new IncorrectEnter("Нужно ввести номер без форматирования");
        if (number / 1000000000 != 9 ) throw new IncorrectEnter("Первая цифра номера должна начинаться на 9!");
        this.numberPhone = number;
    }

    public long GetNumber() {
        return this.numberPhone;
    }

    public void SetSex(String data) {
        if (data.length() != 1) throw new IncorrectEnter("Введите первую букву своего пола m/f.");
        char sex = data.charAt(0);
        if (sex == 'm' || sex == 'f') this.sex = sex;
        else throw new IncorrectEnter("Введите m или f.");
    }

    public char GetSex() {
        return this.sex;
    }

    public String GetName() {
        return this.firstname;
    }

    public String GetLastName() {
        return this.lastName;
    }

    public String GetPatronymic() {
        return this.patronymic;
    }

    public void CreateAndWriteOfLog(String registration) throws IOException {

        File file = new File(lastName + ".txt");
        if (!file.exists()) {
            file.createNewFile();
            try(FileWriter writer = new FileWriter(file)) {
                writer.write(registration + "\n");
            }
        } else {
            try(FileWriter writer = new FileWriter(file, true)) {
                writer.write(registration + "\n");
            }
        }
    }

}