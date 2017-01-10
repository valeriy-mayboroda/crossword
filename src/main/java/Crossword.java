import java.util.ArrayList;
import java.util.List;
/* Кроссворд
1. Дан двумерный массив, который содержит буквы английского алфавита в нижнем регистре.
2. Метод detectAllWords должен найти все слова из words в массиве crossword.
3. Элемент(startX, startY) должен соответствовать первой букве слова, элемент(endX, endY) - последней.
text - это само слово, располагается между начальным и конечным элементами
4. Все слова есть в массиве.
5. Слова могут быть расположены горизонтально, вертикально и по диагонали как в нормальном, так и в обратном порядке.
*/
public class Crossword {
    public static List<Word> result;
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'p', 'r', 'r', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };
        result = detectAllWords(crossword, "home", "same", "rrj");
        for (Word a : result) {
            System.out.println(a);
        }
        /*Ожидаемый результат
        home - (5, 3) - (2, 0) X - столбец (горизонталь) Y - строка (вертикаль)
        same - (1, 1) - (4, 1)*/
    }
    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        List<Word> list = new ArrayList<Word>();
        for (String name : words){
            int wordLength = name.length();//Длина слова
            char word[] = name.toCharArray();
            int firstLetter = (int)word[0];//Первая буква слова

            int blockChars[] = new int[word.length];

            for (int i = 0; i < word.length; i ++) {
                blockChars[i] = word[i];
            }

            int verticalLength = crossword.length;//Длина массива по вертикали (кол-во элементов в столбце, т.е. кол-во строк)
            int horizontalLength = crossword[0].length;//Длина массива по горизонтали (кол-во элементов в строке, т.е. кол-во столбцов)
            for (int i = 0; i < verticalLength; i ++) {
                for (int j = 0; j < horizontalLength; j ++) {
                    int letter = crossword[i][j];

                    boolean wright = j + wordLength <= horizontalLength;
                    boolean left = j - wordLength >= -1;
                    boolean down = i + wordLength <= verticalLength;
                    boolean up = i - wordLength >= -1;

                    if (letter == firstLetter){
                        if (wright){//Проверка вправо
                            int startX = j;
                            int endX = (j + wordLength) - 1;
                            int startY = i;
                            int endY = i;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                        if (left){//Проверка влево
                            int startX = j;
                            int endX = (j - wordLength) + 1;
                            int startY = i;
                            int endY = i;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                        if (down){//Проверка вниз
                            int startX = j;
                            int endX = j;
                            int startY = i;
                            int endY = (i + wordLength) - 1;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                        if (up){//Проверка вверх
                            int startX = j;
                            int endX = j;
                            int startY = i;
                            int endY = (i - wordLength) + 1;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                        if (wright && down){//Проверка вправо вниз по диагонали
                            int startX = j;
                            int endX = (j + wordLength) - 1;
                            int startY = i;
                            int endY = (i + wordLength) - 1;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                        if (left && down){//Проверка влево вниз по диагонали
                            int startX = j;
                            int endX = (j - wordLength) + 1;
                            int startY = i;
                            int endY = (i + wordLength) - 1;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                        if (wright && up){//Проверка вправо вверх по диагонали
                            int startX = j;
                            int endX = (j + wordLength) - 1;
                            int startY = i;
                            int endY = (i - wordLength) + 1;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                        if (left && up){//Проверка влево вверх по диагонали
                            int startX = j;
                            int endX = (j - wordLength) + 1;
                            int startY = i;
                            int endY = (i - wordLength) + 1;
                            boolean okey = isEqual(blockChars, crossword, startX, startY, endX, endY);
                            if (okey) {
                                list.add(addToList(name, startX, startY, endX, endY));
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    public static int isValue(int value, int startValue, int endValue){
        int result;
        if (startValue == endValue){
            result = value;
        }
        else if (endValue > startValue) {
            result = value + 1;
        }
        else{
            result = value - 1;
        }
        return result;
    }

    public static boolean isEqual(int word[], int crossword[][], int startX, int startY, int endX, int endY){
        int x = startX;
        int y = startY;
        boolean equal = true;
        for (int i = 0; i < word.length & equal; i ++){
            if (crossword[y][x] == word[i]){
                equal = true;
                x = isValue(x, startX, endX);
                y = isValue(y, startY, endY);
            }
            else equal = false;
        }
        return equal;
    }

    public static Word addToList(String name, int startX, int startY, int endX, int endY){
        Word newWord = new Word(name);
        newWord.setStartPoint(startX, startY);
        newWord.setEndPoint(endX, endY);
        return newWord;
    }

    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}