package lesson3;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static class Stack {
        private int maxSize; // размер
        private int[] stack; // место хранения
        private int head;    // вершина

        public Stack(int size) {
            this.maxSize = size;
            this.stack = new int[this.maxSize];
            this.head = -1;
        }

        public boolean isEmpty() { return this.head == -1; }
        public boolean isFull() { return this.head == this.maxSize - 1; }

        public void push(int i) {
            if (isFull()) {
                this.maxSize *= 2;
                int[] newStack = new int[this.maxSize];
                System.arraycopy(this.stack, 0, newStack, 0, this.stack.length);
                this.stack = newStack;
            }
            this.stack[++this.head] = i;
        }

        public int pop() {
            if (isEmpty()) throw new RuntimeException("Stack is empty"); //ret -1
            return this.stack[this.head--];
        }

        public int peek() {
            return this.stack[this.head];
        }
    }

    private static class Queue {
        private int maxSize; // размер
        private int[] queue; // место хранения
        private int head;    // отсюда уходят
        private int tail;    // сюда приходят
        private int items;   // текущее количество

        public Queue(int s) {
            maxSize = s;
            queue = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
        }

        public boolean isEmpty() { return (items == 0); }
        public boolean isFull() { return (items == maxSize); }
        public int size() { return items; }

        public void insert(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= head) {
                    System.arraycopy(queue, 0, tmpArr, 0, queue.length);
                } else {
                    System.arraycopy(queue, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(queue, head, tmpArr,
                            maxSize - (queue.length - head), queue.length - head);
                    head = maxSize - head - 1;
                }
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            queue[++tail] = i;
            ++items;
        }

        public int remove() {
            int temp =  queue[head++];
            head %= maxSize;
            items--;
            return temp;
        }

        public int peek(){
            return queue[head];
        }
    }

    private static class Deque {
        private int maxSize; // размер
        private int[] deque; // место хранения
        private int head;    // отсюда уходят
        private int tail;    // сюда приходят
        private int items;   // текущее количество

        public Deque(int s) {
            maxSize = s;
            deque = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
        }
        public void printTest(){
            System.out.println("deque " + Arrays.toString(deque));
            System.out.println("maxSize " + maxSize);
            System.out.println("head " + head);
            System.out.println("tail " + tail);
            System.out.println("items " + items);
            System.out.println("");
        }

        public boolean isEmpty() { return (items == 0); }
        public boolean isFull() { return (items == maxSize); }
        public int size() { return items; }

        /**
         * Вставляет число в дек справа циклически. При достижении максимального размера - увеличивает размерность в два
         * раза
         * @param i число
         */
        public void insertRight(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= head) {
                    System.arraycopy(deque, 0, tmpArr, 0, deque.length);
                } else {
                    System.arraycopy(deque, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(deque, head, tmpArr,
                            maxSize - (deque.length - head), deque.length - head);
                    head = maxSize/2 + head;
                }
                deque = tmpArr;
            }
            if (tail == maxSize - 1) {
                tail = -1;
            }
            deque[++tail] = i;
            ++items;
        }

        /**
         * Вставляет число в дек слева циклически. При достижении максимального размера - увеличивает размерность в два
         * раза
         * @param i число
         */
        public void insertLeft(int i) {
            if (isFull()) {
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= head) {
                    System.arraycopy(deque, 0, tmpArr, 0, deque.length);
                } else {
                    System.arraycopy(deque, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(deque, head, tmpArr,
                            maxSize - (deque.length - head), deque.length - head);
                    head = maxSize/2 + head;
                }
                deque = tmpArr;
            }
            if (head == 0) {
                head = maxSize;
            }
            deque[--head] = i;
            ++items;
        }

        /**
         * "Удаляет" путем смещения индексов элемент слева в деке. При попытке удалить из пустого дека - исключение
         * @return удаленное из дека число
         * @throws RuntimeException дек пуст
         */
        public int removeLeft() throws RuntimeException {
            if (items > 0){
                int temp =  deque[head++];
               // deque[head - 1] = 0; //test
                head %= maxSize;
                items--;
                return temp;
            }
            else {
                throw new RuntimeException("Deque is empty");
            }
        }

        /**
         * "Удаляет" путем смещения индексов элемент справа в деке. При попытке удалить из пустого дека - исключение
         * @return удаленное из дека число
         * @throws RuntimeException дек пуст
         */
        public int removeRight() throws RuntimeException {
            if (items > 0){
                int temp =  deque[tail--];
               // deque[tail + 1] = 0; //test
                if (tail == -1)
                    tail = maxSize - 1;
                items--;
                return temp;
            }
            else {
                throw new RuntimeException("Deque is empty");
            }

        }

    }

    private static class PriorityQueue {
        private int maxSize; // размер
        private int[] queuePrt; // место хранения
        private int head;    // отсюда уходят
        private int tail;    // сюда приходят
        private int items;   // текущее количество

        public PriorityQueue(int s) {
            maxSize = s;
            queuePrt = new int[maxSize];
            head = 0;
            tail = -1;
            items = 0;
        }
        public void printTest(){
            System.out.println("queuePrt " + Arrays.toString(queuePrt));
            System.out.println("maxSize " + maxSize);
            System.out.println("head " + head);
            System.out.println("tail " + tail);
            System.out.println("items " + items);
            System.out.println("");
        }

        public boolean isEmpty() { return (items == 0); }
        public boolean isFull() { return (items == maxSize); }
        public int size() { return items; }

        /**
         * Вставляет число в очередь справа циклически в соответствии с приоритетом.
         * При достижении максимального размера - увеличивает размерность в два
         * раза
         * @param i число
         */
        public void insert(int i) {
            if (isFull()) {//исли массив полный увеличиваем размер
                maxSize *= 2;
                int[] tmpArr = new int[maxSize];
                if (tail >= head) {
                    System.arraycopy(queuePrt, 0, tmpArr, 0, queuePrt.length);
                } else {
                    System.arraycopy(queuePrt, 0, tmpArr, 0, tail + 1);
                    System.arraycopy(queuePrt, head, tmpArr,
                            maxSize - (queuePrt.length - head), queuePrt.length - head);
                    head = maxSize/2 + head;
                }
                queuePrt = tmpArr;
            }
            if (tail == maxSize - 1) {//циклический переход
                tail = -1;
            }


            int j=0;

            if (items > 0) {//ищем место в очереди
                if (tail >= head){ //простой случай, цикл переход не случился
                    for (j = head; j < tail + 1 ; j++) {
                        if (queuePrt[j] < i){
                            break;
                        }
                    }
                    System.arraycopy(queuePrt,j,queuePrt,j+1,tail-j+1);//раздвигаем очередь
                    tail++;
                    queuePrt[j] = i;//вставляем элемент
                }
                else{ //сработал ранее циклический переход
                    boolean find = false;

                    for (j = head; j < maxSize ; j++) {//ищем в правой части
                        if (queuePrt[j] < i){
                            find = true;//если нашли раздвигаем правую часть со смещением влево
                            System.arraycopy(queuePrt,head,queuePrt,head-1,j-head+1);
                            head--;
                            queuePrt[j-1] = i;
                            break;
                        }
                    }
                    if (!find){ //ищем в левой части
                        if (tail == -1) //если хвост вначале, то присваем значение на его место+1
                            queuePrt[++tail] = i;
                        else{
                            for (j = 0; j < tail+1 ; j++) {//ищем место в левой части
                                if (queuePrt[j] < i){
                                    System.arraycopy(queuePrt,j,queuePrt,j+1,tail-j+1);
                                    tail++;
                                    find = true;
                                    queuePrt[j] = i;
                                    break;
                                }
                            }
                            if (!find){//если не нашли ставим в конец очереди
                                queuePrt[tail] = i;
                                tail++;
                            }
                        }
                    }
                }
            }
            else
                queuePrt[++tail] = i;//если очередь пустая

            ++items;
        }

        /**
         * "Удаляет" путем смещения индексов элемент слева в деке. При попытке удалить из пустого дека - исключение
         * @return удаленное из дека число
         * @throws RuntimeException дек пуст
         */
        public int remove() throws RuntimeException {
            if (items > 0){
                int temp =  queuePrt[head++];
                queuePrt[head - 1] = 0; //test
                head %= maxSize;
                items--;
                return temp;
            }
            else {
                throw new RuntimeException("Queue is empty");
            }
        }
    }

    public static void main(String[] args) {
        Deque deque = new Deque(3);
        PriorityQueue pq = new PriorityQueue(5);
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) {
            int tmp = rnd.nextInt(100);
            System.out.println(tmp);
            pq.insert(tmp);
            pq.printTest();
        }

        for (int i = 0; i < 2; i++) {
            System.out.println("Remove " + pq.remove());
            pq.printTest();
        }
        for (int i = 0; i < 30; i++) {
            int tmp = rnd.nextInt(100);
            System.out.println(tmp);
            pq.insert(tmp);
            pq.printTest();
        }
        System.out.println('3');
        pq.insert(3);
        pq.printTest();

        System.out.println('1');
        pq.insert(1);
        pq.printTest();

        System.out.println('2');
        pq.insert(2);
        pq.printTest();

//        for (int i = 1; i < 20; i+=2) {
//            deque.insertRight(i);
//            deque.insertLeft(i);
//            deque.printTest();
//        }
//
//        for (int i = 1; i < 21; i++) {
//            System.out.println(deque.removeRight());
//            deque.printTest();
//        }
//
//        for (int i = 1; i < 25; i+=2) {
//            deque.insertRight(i);
//            deque.insertLeft(i+1);
//            deque.printTest();
//        }



    }

    /**
     * Проверяет в цикле символы в строке поэлементно, если первой встречается закрывающая скобка - вывод на экран false
     * Символы открытия строки добавляются в стэк
     * При появлении символа закрытия - проверяется стэк на наличие символа открытия, если нет - выводится false
     * По итогам посимвольной проверки проверяется что осталось в стэке - если пустой - выводится true
     * @param input входная строка
     */
    public static void check(String input) {
        char open = '{';
        char close = '}';
        boolean fOk = true;// флаг проверки строки
        boolean fBegin  = false; // флаг проверки корректности первых символов
        Stack stack = new Stack(10);

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == open) {
                stack.push(open);
                fBegin = true;
            }
            if (c == close){
                if (!fBegin){ //вначале были скобки закрытия, до открытия
                    fOk = false;
                    break;
                }
                try {
                    stack.pop();
                }
                catch (RuntimeException e){
                    fOk = false;
                    break;
                }
            }
        }

        if (fOk)//проверяем что стэк пуст
            try {
                stack.pop();
                fOk = false;
            }
            catch (RuntimeException ignored){
            }

        System.out.println(fOk);
    }
}


