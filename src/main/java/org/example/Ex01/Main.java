package org.example.Ex01;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите логин: ");
        String login = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();
        System.out.print("Подтвердите пароль: ");
        String confirmation = scanner.nextLine();
        boolean loggedIn = false;

        try {
            loggedIn = checkLoginAndPassword(login, password, confirmation);
        }
        catch (WrongLoginException exception) {
            exception.printStackTrace();
        }
        catch (WrongPasswordException exception) {
            System.out.println(exception.getMessage());
        } finally {
            if(loggedIn) System.out.println("Вход выполнен");
            else System.out.println("Вход не выполен");
        }
    }

    public static boolean checkLoginAndPassword(String login, String password, String confirmation)
                            throws WrongLoginException, WrongPasswordException {
        if (login.length() >= 20)
            throw new WrongLoginException(String.format("Ошибка: Длина логина: %d, ожидалось: < 20",
                    login.length()));
        if (password.length() < 20)
            throw new WrongPasswordException(String.format("Ошибка: Длина введенного пароля: %d, ожидалось: >= 20",
                    password.length()));
        if(!password.equals(confirmation))
            throw new WrongPasswordException("Ошибка: Введенные пароли не совпадают");

        return true;
    }
}
