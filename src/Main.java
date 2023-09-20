import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("Dell", "XPS 13", 16, 512, "Windows 10", "Silver", 1299.99));
        notebooks.add(new Notebook("Apple", "MacBook Air", 8, 256, "macOS", "Gold", 999.99));
        notebooks.add(new Notebook("HP", "Envy 13", 8, 256, "Windows 10", "Silver", 899.99));
        notebooks.add(new Notebook("Asus", "ZenBook 14", 16, 512, "Windows 10", "Blue", 1099.99));

        //Запрос у пользователя критериев фильтрации и фильтрация ноутбуков
        Map<String, Object> filters = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        System.out.println("5 - Цена");
        System.out.println("0 - Завершить ввод критериев");

        while (true) {
            int choice = scanner.nextInt();
            if (choice == 0) {
                break;
            }

            switch (choice) {
                case 1:
                    System.out.println("Введите минимальное значение ОЗУ (в GB):");
                    filters.put("RAM", scanner.nextInt());
                    break;
                case 2:
                    System.out.println("Введите минимальный объем жесткого диска (в GB):");
                    filters.put("Storage", scanner.nextInt());
                    break;
                case 3:
                    System.out.println("Введите название операционной системы:");
                    filters.put("OperatingSystem", scanner.next());
                    break;
                case 4:
                    System.out.println("Введите желаемый цвет:");
                    filters.put("Color", scanner.next());
                    break;
                case 5:
                    System.out.println("Введите минимальную цену:");
                    filters.put("Price", scanner.nextDouble());
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
                    break;
            }
        }

        // Фильтрация ноутбуков на основе выбранных критериев
        for (Notebook notebook : notebooks) {
            if (matchesFilters(notebook, filters)) {
                System.out.println(notebook);
            }
        }

        scanner.close();
    }

    public static boolean matchesFilters(Notebook notebook, Map<String, Object> filters) {
        for (Map.Entry<String, Object> entry : filters.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            switch (key) {
                case "RAM":
                    if (notebook.getRAM() < (int) value) {
                        return false;
                    }
                    break;
                case "Storage":
                    if (notebook.getStorage() < (int) value) {
                        return false;
                    }
                    break;
                case "OperatingSystem":
                    if (!notebook.getOperatingSystem().equalsIgnoreCase((String) value)) {
                        return false;
                    }
                    break;
                case "Color":
                    if (!notebook.getColor().equalsIgnoreCase((String) value)) {
                        return false;
                    }
                    break;
                case "Price":
                    if (notebook.getPrice() > (double) value) {
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }
}

class Notebook {
    private String brand;
    private String model;
    private int RAM;
    private int storage;
    private String operatingSystem;
    private String color;
    private double price;

    public Notebook(String brand, String model, int RAM, int storage, String operatingSystem, String color, double price) {
        this.brand = brand;
        this.model = model;
        this.RAM = RAM;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getRAM() {
        return RAM;
    }

    public int getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Notebook{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", RAM=" + RAM +
                ", storage=" + storage +
                ", operatingSystem='" + operatingSystem + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}