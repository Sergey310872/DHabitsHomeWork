package org.example.service;

import org.example.DTO.ListStudentsDTO;

import java.io.*;

public class InputOutputFile {
    public static void serialize(ListStudentsDTO students, String path) throws IOException {
        try (FileOutputStream outputStream = new FileOutputStream(path); ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            //создаем 2 потока для сериализации объекта и сохранения его в файл

            // сохраняем объект в файл
            objectOutputStream.writeObject(students);
        }
        // Закроем потоки в блоке finally
    }


    public static ListStudentsDTO deserialize(String path) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {

            //создаем 2 потока для десериализации объекта из файла
            fileInputStream = new FileInputStream(path);
            objectInputStream = new ObjectInputStream(fileInputStream);

            //загружаем объект из файла
            return  (ListStudentsDTO) objectInputStream.readObject();
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (objectInputStream != null) {
                objectInputStream.close();
            }
        }
    }
}
