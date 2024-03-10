package org.example.entity;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Students implements Serializable {
    private String name;
    private List<Integer> listGrade = new ArrayList<>();

    public Students(String name){
        this.name = name;
    }

    public void setGrade(int index, int newGrade) {
        if (index < 0 || index >= listGrade.size()) {
            System.out.println("Индекс выходжит за пределы списка оценок.");
            return;
        }
        if (newGrade < 1 || newGrade > 5) {
            System.out.println("Отметка должна быть в диапазоне от 1 (Не удовлетворительно) до 5 (Отлично).");
            return;
        }
        listGrade.set(index, newGrade);
    }

    public void addGrade(int newGrade) {
        if (newGrade < 1 || newGrade > 5) {
            System.out.println("Отметка должна быть в диапазоне от 1 (Не удовлетворительно) до 5 (Отлично).");
            return;
        }
        listGrade.add(newGrade);
    }

    @Override
    public String toString() {
        return "   студент: " + name + ", оценки: " + listGrade;
    }
}
