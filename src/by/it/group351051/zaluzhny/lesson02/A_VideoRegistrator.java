package by.it.group351051.zaluzhny.lesson02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
даны события events
реализуйте метод calcStartTimes, так, чтобы число включений регистратора на
заданный период времени (1) было минимальным, а все события events
были зарегистрированы.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class A_VideoRegistrator {

    public static void main(String[] args)  {
        A_VideoRegistrator instance=new A_VideoRegistrator();
        double[] events=new double[]{1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7};
        List<Double> starts=instance.calcStartTimes(events,1); //рассчитаем моменты старта, с длинной сеанса 1
        System.out.println(starts);                            //покажем моменты старта
    }
    //модификаторы доступа опущены для возможности тестирования
    List<Double> calcStartTimes(double[] events, double workDuration)  {
        //events - события которые нужно зарегистрировать
        //timeWorkDuration время работы видеокамеры после старта
        List<Double> result;
        result = new ArrayList<>();
        Arrays.sort(events);

        int i = 0; // Индекс текущего события

        // Пока есть незарегистрированные события
        while (i < events.length) {
            // Записываем время старта видеокамеры как текущее событие
            double start = events[i];
            result.add(start);
            double end = start + workDuration; // Вычисляем момент окончания работы видеокамеры

            // Пропускаем все события, которые происходят в пределах покрытия
            while (i < events.length && events[i] <= end) {
                i++; // Увеличиваем индекс, если событие покрыто
            }
        }

        return result; // Возвращаем итог
    }
}
