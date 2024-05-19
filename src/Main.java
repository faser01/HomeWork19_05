//задача:
//создать программу,
//которая будет считать сумму всех элементов в двумерном массиве
//с использованием потоков.
//Каждый поток будет отвечать за подсчет суммы определенного диапазона строк в массиве.

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[][] array = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };


        System.out.println("Исходный массив:");
        for (int[] row : array) {
            System.out.println(Arrays.toString(row));
        }

        int numThreads = 4;
        SumThread[] threads = new SumThread[numThreads];
        int rowsPerThread = array.length / numThreads;

        for (int i = 0; i < numThreads; i++) {
            int startRow = i * rowsPerThread;
            int endRow = (i == numThreads - 1) ? array.length : startRow + rowsPerThread;
            threads[i] = new SumThread(array, startRow, endRow);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int totalSum = 0;
        for (int i = 0; i < numThreads; i++) {
            totalSum += threads[i].getSum();
        }

        System.out.println("Итоговая сумма: " + totalSum);
    }
}
//В данном примере создается двумерный массив array и определяется количество потоков numThreads.
//Затем создается массив потоков threads, каждый из которых отвечает за подсчет суммы
//определенного диапазона строк в массиве.
//После завершения работы потоков, основной поток суммирует результаты
//всех потоков и выводит общую сумму.
//Таким образом, с использованием потоков можно реализовать
//параллельные вычисления и ускорить обработку массива данных.
