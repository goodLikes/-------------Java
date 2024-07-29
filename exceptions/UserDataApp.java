import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Введите фамилию:");
            String surname = scanner.nextLine();
            System.out.println("Введите имя:");
            String name = scanner.nextLine();
            System.out.println("Введите отчество:");
            String patronymic = scanner.nextLine();
            System.out.println("Введите дату рождения (dd.mm.yyyy):");
            String birthDate = scanner.nextLine();
            System.out.println("Введите номер телефона:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Введите пол (f или m):");
            String gender = scanner.nextLine();

            // Проверка количества введенных данных
            if (surname.isEmpty() || name.isEmpty() || patronymic.isEmpty() || birthDate.isEmpty() || phoneNumber.isEmpty() || gender.isEmpty()) {
                throw new IllegalArgumentException("Все поля должны быть заполнены.");
            }

            // Проверка формата даты рождения
            if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
                throw new IllegalArgumentException("Неверный формат даты рождения. Ожидается dd.mm.yyyy");
            }

            // Проверка формата номера телефона
            if (!phoneNumber.matches("\\d+")) {
                throw new IllegalArgumentException("Неверный формат номера телефона. Ожидается целое число без форматирования");
            }

            // Проверка формата пола
            if (!gender.equals("f") && !gender.equals("m")) {
                throw new IllegalArgumentException("Неверный формат пола. Ожидается символ 'f' или 'm'");
            }

            String dataLine = surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender;
            writeToFile(surname, dataLine);
            System.out.println("Данные успешно записаны в файл.");

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлом:");
            e.printStackTrace();
        }
    }

    private static void writeToFile(String filename, String data) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename + ".txt", true))) {
            writer.write(data);
            writer.newLine();
        }
    }
}
