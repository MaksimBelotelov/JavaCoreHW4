package org.example.Ex02;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Main {
    enum Holidays {
       WEEKDAY,
       NEWYEAR,
       INTWOMENSDAY,
       MANSDAY
    };

    private static Customer[] customers = {new Customer("Max", "+79998887766",
                                20, Customer.Genders.MALE),
            new Customer("Sam", "+79999998877", 25, Customer.Genders.FEMALE)};

    private static Product[] products = {new Product("Вода", 40), new Product("Шоколад", 55),
            new Product("Хлеб", 45), new Product("Молоко", 75),
            new Product("Яйца", 100)};

    private static Order[] orders = new Order[5];

    public static void main(String[] args) {
        int counter = 0;

        Order[] testOrdersData = { new Order(customers[0], products[0], 1),
                                new Order(customers[1], products[2], 2),
                                new Order(customers[0], new Product("Кашка", 25), 3), // Нет товара
                                new Order(customers[1], products[1], -2), // Некорректное количество
                                new Order(new Customer("Vasiliy", "+79876543214", 30,
                                        Customer.Genders.MALE), products[0], 1) }; // Некорректный пользователь

        congratulate(customers);

        for (Order currentOrder : testOrdersData) {
            try {
                orders[counter] = makeOrder(currentOrder.getCustomer(),
                        currentOrder.getProduct(), currentOrder.getAmount());
                counter++;
            } catch (ProductException ex) {
                System.out.println(ex.getMessage());
            } catch (AmountException ex) {
                System.out.println(ex.getMessage());
                orders[counter] = makeOrder(currentOrder.getCustomer(),
                        currentOrder.getProduct(), 1);
                counter++;
            } catch (CustomerException ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex);
            } finally {
                System.out.printf("Количество заказов: %d\n", counter);
            }
        }
    }

    public static <E> boolean contains(E[] array, E current) {
        for(var item: array) {
            if(item.equals(current))
                return true;
        }
        return false;
    }

    public static Order makeOrder(Customer customer, Product product, int amount) {
        if(!contains(customers, customer))
            throw new CustomerException("Ошибка! Покупатель не найден.");
        if(!contains(products, product))
            throw new ProductException("Ошибка! Товар не найден.");
        if(amount <= 0 || amount > 100)
            throw new AmountException("Ошибка! Некорректное количество товара.\n" +
                    "Заказ сформирован с одной единицей товара.");

        return new Order(customer, product, amount);
    }

    static class CustomerException extends RuntimeException {
        CustomerException(String message) {
            super(message);
        }
    }

    static class ProductException extends RuntimeException {
        ProductException(String message) { super(message); }
    }

    static class AmountException extends RuntimeException {
        AmountException(String message) { super(message); }
    }

    public static void congratulate(Customer[] customers) {
        Calendar currentDate = new GregorianCalendar();
        Holidays holidayToday = Holidays.WEEKDAY;

        if (currentDate.get(GregorianCalendar.MONTH) == Calendar.DECEMBER
                && currentDate.get(GregorianCalendar.DAY_OF_MONTH) == 31)
            holidayToday = Holidays.NEWYEAR;
        else if ((currentDate.get(GregorianCalendar.MONTH) == Calendar.MARCH)
                && currentDate.get(GregorianCalendar.DAY_OF_MONTH) == 8)
            holidayToday = Holidays.INTWOMENSDAY;
        else if ((currentDate.get(GregorianCalendar.MONTH) == Calendar.FEBRUARY)
                && currentDate.get(GregorianCalendar.DAY_OF_MONTH) == 23)
            holidayToday = Holidays.MANSDAY;

        if(!holidayToday.equals(Holidays.WEEKDAY)) {
            for (Customer customer : customers) {
                switch(holidayToday) {
                    case NEWYEAR:
                        System.out.println("С Новым годом, " + customer.getName());
                        break;
                    case INTWOMENSDAY:
                        if(customer.getGender().equals(Customer.Genders.FEMALE))
                            System.out.println("С 8 Марта, " + customer.getName());
                        break;
                    case MANSDAY:
                        if(customer.getGender().equals(Customer.Genders.MALE))
                            System.out.println("С 23 Февраля, " + customer.getName());
                        break;
                }
            }
        }
    }
}
