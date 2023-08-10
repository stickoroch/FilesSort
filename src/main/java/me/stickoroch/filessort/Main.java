package me.stickoroch.filessort;

import me.stickoroch.filessort.element.IntegerSortableElement;
import me.stickoroch.filessort.element.SortableElement;
import me.stickoroch.filessort.element.StringSortableElement;
import me.stickoroch.filessort.exception.DifferentTypeException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Boolean isUpSort;
    private static Boolean isStringFiles;
    private static String outFileName;
    private static List<File> inputFiles;

    public static void main(String[] args) {
        if(!readArgs(args)) return;
        SortableElement[] resultArray = new SortableElement[0];

        for (File file : inputFiles) {
            List<SortableElement> fileListArray = new ArrayList<>();
            try{
                Scanner reader = new Scanner(file);

                while(reader.hasNextLine()){
                    SortableElement newElement = null;
                    String newLine = reader.nextLine();
                    if(!isStringFiles){
                        try{
                            int value = Integer.parseInt(newLine);
                            newElement = new IntegerSortableElement(value);
                        }catch (NumberFormatException e){
                            System.out.println("При чтении файла "+file.getName() +" произошла ошибка!");
                            System.out.println("Ожидаемый тип данных INTEGER, подается тип STRING");
                            if(getErrorFeedBack())
                                return;
                            else
                                continue;
                        }
                    }
                    else{
                        try{
                            Integer.parseInt(newLine);
                            System.out.println("При чтении файла "+file.getName() +" произошла ошибка!");
                            System.out.println("Ожидаемый тип данных STRING, подается тип INTEGER");
                            if(getErrorFeedBack())
                                return;
                            else
                                continue;
                        }catch (NumberFormatException e){
                            newElement = new StringSortableElement(newLine);
                        }

                    }

                    if(fileListArray.size() > 0){
                        if(newElement.compare(fileListArray.get(fileListArray.size() - 1))
                                || newElement.isEquals(fileListArray.get(fileListArray.size() - 1)))
                            fileListArray.add(newElement);
                        else
                        {
                            System.out.printf("В файле %s нарушен порядок сортировки!\n", file.getName());
                            if(getErrorFeedBack())
                                return;
                        }
                    }
                    else fileListArray.add(newElement);
                }

                if(fileListArray.size() > 0){
                    resultArray = SortUtils.mergeArray(resultArray, fileListArray.toArray(new SortableElement[0]));
                }else{
                    System.out.println("Файл "+file.getName()+" оказался пуст");
                }
            }catch (IOException | DifferentTypeException e){
                System.out.println("При чтении файла "+file.getName() +" произошла ошибка!");
                if(getErrorFeedBack())
                    return;
            }

            System.out.printf("Из файла %s загружено %d строк \n", file.getName(), fileListArray.size());
        }

        if(!isUpSort){
            SortUtils.reverse(resultArray);
            System.out.println("Массив инвертирован");
        }

        saveToFile(resultArray);

    }

    public static boolean saveToFile(SortableElement[] arr){
        File outFile = new File(outFileName);

        try {
            if(!outFile.exists())
                outFile.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(outFile));

            for (SortableElement sortableElement : arr) {
                out.write(sortableElement.toString());
                out.newLine();
            }

            out.close();
        } catch (IOException e) {
            System.out.println("Не удалось создать выходной файл!");
            return false;
        }

        System.out.println("Файл записан и сохранен!");
        return true;
    }

    public static boolean readArgs(String[] args){
        int baseArgsIndex = 0;

        for (int i = 0; i < args.length; i++) {
            if(args[i].startsWith("-")){
                if(args[i].equals("-s")){
                    isStringFiles = true;
                }
                else if(args[i].equals("-i")){
                    isStringFiles = false;
                }
                else if(args[i].equals("-d")){
                    isUpSort = false;
                }
                else if(args[i].equals("-a")){
                    isUpSort = true;
                }else{
                    System.out.printf("Неизвестный аргумент %s \n", args[i]);
                    if(getErrorFeedBack())
                        return false;
                }
            }else{
                baseArgsIndex = i;
                break;
            }
        }

        if(baseArgsIndex < 2){
            System.out.println("Недостаточно аргументов!");
            return false;
        }

        if(isUpSort == null){
            isUpSort = true;
        }

        if(isStringFiles == null){
            System.out.println("Не указан тип данных в аргументах команды( -i = Integer | -s = String)");
            return false;
        }


        outFileName = args[baseArgsIndex];

        inputFiles = new ArrayList<>();
        for (int i = baseArgsIndex+1; i < args.length; i++) {
            File file = new File(args[i]);

            if(!file.exists()){
                System.out.println("Файл "+file.getName()+" не найден!");

                if(getErrorFeedBack()){
                    return false;
                }
            }

            inputFiles.add(file);
        }
        return true;
    }

    public static boolean getErrorFeedBack(){
        String res;
        do{
            System.out.println("Продолжить работу и пропустить ошибку? (y/n)");
            Scanner scanner = new Scanner(System.in);
            res = scanner.nextLine();
        }while (!res.equalsIgnoreCase("y") && !res.equalsIgnoreCase("n"));


        return res.equalsIgnoreCase("n");
    }
}
