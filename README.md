# FilesSort
Сортирует строки указанных файлов в один файл

сделал: stickoroch
## Инструкция

### ВСЕ СТРОКИ В ФАЙЛАХ ДОЛЖНЫ БЫТЬ ПРЕДВАРИТЕЛЬНО ОТСОРТИРОВАНЫ ПО ВОЗРАСТАНИЮ

### Запуск приложения (только windows):
Есть 2 варианта запуска приложния:

Первый:
- Cкачать файл [FilesSort.jar](https://github.com/stickoroch/FilesSort/blob/master/test/FilesSort.jar) из этого репозитория, находящийся в [FilesSort
/test/](https://github.com/stickoroch/FilesSort/tree/master/test)
- Открыть консоль в месте расположения файла (До папки можно добраться командой `cd`)
- В консоли написать команду по запуску `java -jar FilesSort.jar <args>`

Второй (запуск демо версии):
- Cкачать директорию [test](https://github.com/stickoroch/FilesSort/tree/master/test) из этого репозитория
- Запустить в этой папке run.bat

### Применяемые аргументы запуска (* - обязателен)
- *Тип входных данных `-i` (число) или `-s` (строка)
- Режим сортировки `-a` (возрастающий) или `-d` (убывающий)
- *После всех аргументов указывается выходной файл. например out.txt
- *После выходного файла указываются входные(минимум один) файлы. Например in1.txt in2.txt...

Пример строки запуска `java -jar FilesSort.jar -i -a out.txt in1.txt in2.txt in3.txt`

## Особенности
- Проверенная версия java: 17.0.2
- Не использует никаких зависимостей
- Не использует никаких сборщиков проектов
