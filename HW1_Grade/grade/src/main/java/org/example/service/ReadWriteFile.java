package org.example.service;

import lombok.Getter;
import org.example.DTO.ListStudentsDTO;
import org.example.entity.Students;
import org.example.repository.repositoryStudents;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReadWriteFile {
    @Getter
    private ListStudentsDTO students;
    final String path = "students.ser";

    public void makeFile() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Создать новый файл? y/n ");
        String strIn = scanner.nextLine();
        if (strIn.equals("y")) {
            createNewFail();
        } else if (strIn.equals("n")) {
            readFile();
        }
    }

    public void createNewFail() throws IOException {
        File tmp = new File(path);
        System.out.println("Удален старый файл students.ser: "+tmp.delete());
        tmp.createNewFile();

        //Начальная инициализация репозитория и нового файла
        repositoryStudents.studentsList.clear();
        repositoryStudents.studentsList.addAll(List.of( new Students("Sergey"),
                new Students("Yuriy")));
        students = new ListStudentsDTO(repositoryStudents.studentsList);
        InputOutputFile.serialize(students, path);
    }

    public void readFile() throws IOException, ClassNotFoundException {
        students = InputOutputFile.deserialize(path);
        if (!repositoryStudents.studentsList.isEmpty()) {
            repositoryStudents.studentsList.clear();
        }
        repositoryStudents.studentsList.addAll(students.getStudentsList());
    }

    public void saveFile() {
        students = new ListStudentsDTO(repositoryStudents.studentsList);
        try {
            InputOutputFile.serialize(students, path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
