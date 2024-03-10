package org.example.service;

import org.example.entity.Students;
import org.example.repository.repositoryStudents;

import java.util.Scanner;

public class Menu {
    public  char menuChoice() {
        Scanner scanner = new Scanner(System.in);
        char choice = 'r';
        System.out.println("" +
                "a. Добавьте нового ученика\n" +
                "b. Удалите ученика\n" +
                "c. Обновите оценку ученика\n" +
                "d. Просмотр оценок всех учащихся\n" +
                "e. Просмотр оценок конкретного учащегося\n"+
                "f. Выход из программы\n");
        System.out.print(" ВВЕДИТЕ ВЫБРАННЫЙ ПУНКТ МЕНЮ: ");
        String str = scanner.nextLine();
        if (!str.isEmpty()){
            choice = ( char) str.toCharArray()[0];
        }
        return choice;
    }

    public  void handlingMenu(ReadWriteFile rwFile) {
        Scanner scanner = new Scanner(System.in);
        Students student = null;
        boolean prolong = true;
        while (prolong) {
            switch (menuChoice()) {
                case ('a'): //Добавьте нового ученика
                    System.out.print("Имя нового ученика ученика: ");
                    String name = scanner.nextLine();
                    repositoryStudents.studentsList.add(new Students(name));
                    break;
                case ('b'): //Удалите ученика
                    student = getStudentByID();
                    assert student != null;
                    repositoryStudents.studentsList.remove(student);
                    System.out.println("УДАЛЯЕТСЯ: "+student.getName());
                    break;
                case ('c'): //Обновите оценку ученика
                    student = getStudentByID();
                    if (student != null) {
                        System.out.printf("Введите новую оценку для " + student.getName() + ": ");
                        int mark = scanner.nextInt();
                        student.addGrade(mark);
                    }
                    break;
                case ('d'): //Просмотр оценок всех учащихся
                    System.out.println("Оценки учащихся: ");
                    int i = 0;
                    for (Students students : repositoryStudents.studentsList){
                        System.out.println(i + ". " + students);
                        i++;
                    }
                    break;
                case ('e'): //Просмотр оценок конкретного учащегося
                    System.out.println(getStudentByID());
                    break;
                case ('f'): //Выход из программы
                    System.out.println("ДО НОВЫХ ВСТРЕЧЬ!!!");
                    new ReadWriteFile().saveFile();
                    prolong = false;
                    break;
                default:
                    System.out.println("НЕПРАВИЛЬНОЕ ЗНАЧЕНИЕ. ПОПРОБУЙТЕ ЕЩЕ РАЗ.");
            }
        }
    }

    private  Students getStudentByID () {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введите индекс ученика: ");
        int index = scanner.nextInt();
        try{
            return repositoryStudents.studentsList.get(index);
        }catch (IndexOutOfBoundsException e){
            System.out.println("Индекс выходит за пределы списка студентов. Ожидается от 0 до "+(repositoryStudents.studentsList.size()-1));
        }
        return null;
    }
}
