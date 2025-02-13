package by.it.group351051.a_hancharyk.lesson02;

import java.util.ArrayList;
import java.util.List;
/*
даны интервальные события events
реализуйте метод calcStartTimes, так, чтобы число принятых к выполнению
непересекающихся событий было максимально.
Алгоритм жадный. Для реализации обдумайте надежный шаг.
*/

public class B_Sheduler {
    //событие у аудитории(два поля: начало и конец)
    static class Event {
        int start;
        int stop;

        Event(int start, int stop) {
            this.start = start;
            this.stop = stop;
        }

        @Override
        public String toString() {
            return "("+ start +":" + stop + ")";
        }
    }


    public static void main(String[] args) {
        B_Sheduler instance = new B_Sheduler();
        Event[] events = {  new Event(0, 3),  new Event(0, 1), new Event(1, 2), new Event(3, 5),
                new Event(1, 3),  new Event(1, 3), new Event(1, 3), new Event(3, 6),
                new Event(2, 7),  new Event(2, 3), new Event(2, 7), new Event(7, 9),
                new Event(3, 5),  new Event(2, 4), new Event(2, 3), new Event(3, 7),
                new Event(4, 5),  new Event(6, 7), new Event(6, 9), new Event(7, 9),
                new Event(8, 9),  new Event(4, 6), new Event(8, 10), new Event(7, 10)
        };

        List<Event> starts = instance.calcStartTimes(events,0,10);  //рассчитаем оптимальное заполнение аудитории
        System.out.println(starts);                                 //покажем рассчитанный график занятий
    }

    List<Event> calcStartTimes(Event[] events, int from, int to) {
        //events - события которые нужно распределить в аудитории
        //в период [from, int] (включительно).
        //оптимизация проводится по наибольшему числу непересекающихся событий.
        //начало и конец событий могут совпадать.
        List<Event> result;
        result = new ArrayList<>();
        //ваше решение.

        int current=from;//начинаем с нуля
        while (current<=to) { //пока не дойдем до конца интервала
            boolean isCorrectEvent =false;//в начале событие не входит(мы думаем)

            //начинаем проверять
            int last = to;//последнее
            for (int counter = 0; counter < events.length; counter++) {//цикл по всем события
                if (current == events[counter].start && ((events[counter].stop - events[counter].start) < (last-current))) {
                    isCorrectEvent = true; //событие входит в текущую группу
                    last = events[counter].stop;//запоминаем последнее время
                }
            }
            if (isCorrectEvent == true) {

                Event currentEvent = new Event(current,last);//создали событие с указанным интервалом
                result.add(currentEvent);//добавили событие
                current = last;//текущее стало последним
            }
            else current++;//переходим к след событию
        }




        return result;                        //вернем итог
    }
}