# Домашняя работа #2 (Опциональная)

## Задание
Реализовать многопоточную сортировку массива,используя `ForkJoinPool`

## Цель
Изучить алгоритм [Разделяй и властвуй ](https://ru.wikipedia.org/wiki/%D0%A0%D0%B0%D0%B7%D0%B4%D0%B5%D0%BB%D1%8F%D0%B9_%D0%B8_%D0%B2%D0%BB%D0%B0%D1%81%D1%82%D0%B2%D1%83%D0%B9_(%D0%B8%D0%BD%D1%84%D0%BE%D1%80%D0%BC%D0%B0%D1%82%D0%B8%D0%BA%D0%B0)) <br/>
Научиться использовать один из `java.util.concurrent.Executor`

## Требования
1. Реализация интерфейса `ParallelSorter`
2. Реализация показывает лучший результат на бенчмарках, чем `DefaultJavaSorter`
3. Проходит `ParallelSorterTest` тест

##  Комментарии, Подсказки
1. `ParallelSorter` может быть оберткой для предоставления тасок в `ForkJoinPool`
2. В качестве тасок можно использовать `RecursiveAction`

## Сборка приложения
```shell script
# сборка приложения + тесты
mvn clean package
# запуск бенчмарков
java -jar target/homework-2.1-multithreading--1.0-SNAPSHOT-jar-with-dependencies.jar
```

##  Как сдавать?
* Fork этого репозитория
* Pull request вашей реализации в этот репозиторий
* Отправляем ссылку на Pull request в задании на платформе
* Обсуждаем результаты с ментором
