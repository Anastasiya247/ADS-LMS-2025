package by.it.group351051.nekhviadovich.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Рассчитать число инверсий одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо посчитать число пар индексов 1<=i<j<n, для которых A[i]>A[j].

    (Такая пара элементов называется инверсией массива.
    Количество инверсий в массиве является в некотором смысле
    его мерой неупорядоченности: например, в упорядоченном по неубыванию
    массиве инверсий нет вообще, а в массиве, упорядоченном по убыванию,
    инверсию образуют каждые (т.е. любые) два элемента.
    )

Sample Input:
5
2 3 9 2 9
Sample Output:
2

Головоломка (т.е. не обязательно).
Попробуйте обеспечить скорость лучше, чем O(n log n) за счет многопоточности.
Докажите рост производительности замерами времени.
Большой тестовый массив можно прочитать свой или сгенерировать его программно.
*/


public class C_GetInversions {

    int calc(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!
        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int result = 0;
        //!!!!!!!!!!!!!!!!!!!!!!!!     тут ваше решение   !!!!!!!!!!!!!!!!!!!!!!!!

        //для маленького массива мне кажется максимально простое решение, но тут сложность O n^2
        /*for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (a[i] > a[j]) {
                    result++;
                }
            }
        }*/

        int[]tempArr = new int[n];//временный массив

        result = mergeSortAndCount(a, tempArr, 0, n - 1);


        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }

    private int mergeSortAndCount(int[] array, int[] temp, int left, int right) {
        int invCount = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            // считаем инверсии в левой и правой половинах
            invCount += mergeSortAndCount(array, temp, left, mid);
            invCount += mergeSortAndCount(array, temp, mid + 1, right);

            // считаем инверсии при слиянии двух половин
            invCount += mergeAndCount(array, temp, left, mid, right);
        }
        return invCount;
    }

    private int mergeAndCount(int[] array, int[] temp, int left, int mid, int right) {
        //индексы
        int i = left;
        int j = mid + 1;
        int k = left;
        int invCount = 0;


        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
                invCount += (mid + 1 - i);
            }
        }

        //копируем оставшиеся слева
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        //копируем оставшиеся справа
        while (j <= right) {
            temp[k++] = array[j++];
        }

        // копируем временный массив обратно в исходный
        for (i = left; i <= right; i++) {
            array[i] = temp[i];
        }

        return invCount;
    }


    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson04/dataC.txt");
        C_GetInversions instance = new C_GetInversions();
        //long startTime = System.currentTimeMillis();
        int result = instance.calc(stream);
        //long finishTime = System.currentTimeMillis();
        System.out.print(result);
    }
}
