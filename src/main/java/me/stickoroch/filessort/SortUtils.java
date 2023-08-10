package me.stickoroch.filessort;

import me.stickoroch.filessort.element.SortableElement;
import me.stickoroch.filessort.exception.DifferentTypeException;

public class SortUtils {

    public static SortableElement[] mergeArray(SortableElement[] arrA, SortableElement[] arrB){
        if(arrA.length == 0) return arrB;
        if(arrB.length == 0) return arrA;

        SortableElement[] res = new SortableElement[arrA.length + arrB.length];
        int posA = 0, posB = 0;

        for (int i = 0; i < res.length; i++) {
            try{
                if(posA == arrA.length){
                    res[i] = arrB[posB];
                    posB++;
                }else if(posB == arrB.length){
                    res[i] = arrA[posA];
                    posA++;
                }else if(arrB[posB].compare(arrA[posA])){
                    res[i] = arrA[posA];
                    posA++;
                }else if(arrA[posA].compare(arrB[posB])){
                    res[i] = arrB[posB];
                    posB++;
                }else{
                    res[i] = res[i+1] = arrB[posB];
                    i++;
                    posA++;
                    posB++;
                }
            }catch (DifferentTypeException e){
                System.out.println(e.getMessage());
            }
        }
        return res;
    }
    
    public static void reverse(SortableElement[] arr){
        for (int i = 0; i < arr.length; i++) {
            SortableElement temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }
}
