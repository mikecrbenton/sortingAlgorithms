package sortingalgorithms;

import java.util.Scanner;

public class SortingAlgorithms {

    public static void main(String[] args) {
       
        //VARIABLES
        int myArray[] = new int[8];
        String userInput[] = new String[]{"first","second","third","fourth",
                                          "fifth","sixth","seventh","eigth"};
        int sortingChoice;
        Scanner getInput = new Scanner(System.in);
        
        //GET USER INPUT
        System.out.println("Please enter 8 numbers to be sorted:");        
         
        for(int i = 0 ; i < 8 ; i++){
            System.out.print("Please enter the " + userInput[i] + " number: ");
            myArray[i] = getInput.nextInt();
        }
        
        //PRINT UNSORTED LIST
        System.out.print("Unsorted Array is: "); 
        printArray(myArray);
        
        //PRINT MENU CHOICES
        printChoice();
        //GET USER INPUT
        sortingChoice = getInput.nextInt();
        //CHECK FOR CORRECT INPUT
        while(sortingChoice > 6 || sortingChoice < 1){
            System.out.println(sortingChoice + " is invalid input");
            printChoice();
            sortingChoice = getInput.nextInt();
        }
        //CALL SORTING METHODS
        switch (sortingChoice) {
            case 1:
                bubbleSort(myArray);
                System.out.print("Sorted Array is: "); 
                printArray(myArray);
                break;
            case 2:
                selectionSort(myArray);
                System.out.print("Sorted Array is: "); 
                printArray(myArray);
                break;
            case 3:
                insertionSort(myArray);
                System.out.print("Sorted Array is: "); 
                printArray(myArray);
                break;
            case 4:
                quickSort_for(myArray,0,7);
                System.out.print("Sorted Array is: "); 
                printArray(myArray);
                break;
            case 5:
                quickSort_while(myArray,0,7);
                System.out.print("Sorted Array is: "); 
                printArray(myArray);
                break;
            case 6:
                mergeSort(myArray,0,7);
                System.out.print("Sorted Array is: "); 
                printArray(myArray);
                break;
            default:
                break;
        }
        
    }//END MAIN
 
//HELPER METHODS
    
    public static void printArray(int[] arr) {
         for(int i = 0 ; i < 8 ; i++){
            System.out.print(arr[i]);
            if( i < 7)
               System.out.print(" , ");  
            if ( i == 7 )
               System.out.println(); 
        }
    }
    
    public static void printChoice(){
        System.out.println("Please Enter a Number for a Sorting Method: ");
        System.out.println("1) Bubble Sort" ); 
        System.out.println("2) Selection Sort" ); 
        System.out.println("3) Insertion Sort" ); 
        System.out.println("4) Quicksort (w/for() loop)" ); 
        System.out.println("5) Quicksort (w/while() loop)" ); 
        System.out.println("6) Mergesort" ); 
    } 
    
    public static int[] swapNums(int[] arr, int a, int b ){
        
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        
        return arr;
    }

//SORTING METHODS
 
    //BUBBLESORT-------------------------------------
    public static void bubbleSort(int[] arr){
        
        for( int i=0 ; i<arr.length ; i++){       
          boolean isSwapped = false;        
            for(int j = arr.length-1 ; j > i ; j-- ){            
                if( arr[j] <= arr[j-1] ){
                    swapNums(arr,j,j-1);
                    isSwapped = true;
                }
            }
            if(!isSwapped){
                break;
            }
        }
    }
    
    //SELECTION SORT---------------------------------
    public static void selectionSort(int[] arr){
        
        int i,j;
        for( i=0 ; i < arr.length - 1 ; i++){
          int min = i;
           for( j=i+1 ; j<arr.length ; j++ ){
               if(arr[j] < arr[min]){
                   min = j;
               }
           }
           swapNums(arr,i,min);
        }
    }
    
    //INSERTION SORT----------------------------------
    public static void insertionSort(int[] arr){
        
        for( int i=1 ; i<arr.length ; i++ ){
          int key = arr[i];
          int j = i-1;
              while( j>=0 && key <= arr[j]){
                  swapNums(arr,j,j+1);
                  j--;
              }
        }
    }
    
    //QUICKSORT W/ FOR() LOOP---------------------------
    public static void quickSort_for(int[] arr, int low, int high){
        
        if( low < high ){
            int part = partition_for(arr, low, high);
            quickSort_for(arr, low, part-1);
            quickSort_for(arr, part+1, high);
        }
    }
    public static int partition_for(int[] arr, int low, int high){
        
        int pivot = arr[high];
        int i = low-1 ;
        
        for( int j=low ; j<=high-1 ; j++){
            if( arr[j] <= pivot){
                i++;
                swapNums(arr, i, j);
            }
        }
        swapNums(arr, i+1, high);
        return i + 1;
    }
    
    //QUICKSORT W/ WHILE() LOOP-------------------------------
    public static void quickSort_while(int[] arr, int low, int high){
        
        if( low < high ){
            int part = partition_while(arr, low, high);
            quickSort_while(arr, low, part-1);
            quickSort_while(arr, part+1, high);
        }
    }   
    public static int partition_while(int[] arr, int low, int high){
        
        int pivot = arr[low];
        int indexL = low;
        int indexH = high;
        
        while( indexL < indexH ){
            
            while( arr[indexL] <= pivot && indexL < indexH){
                indexL++;
            }
            while( arr[indexH] > pivot){
                indexH--;
            }
            if( indexL < indexH ){
                swapNums(arr, indexL, indexH);
            }
        }
        
        swapNums(arr, low, indexH);
        return indexH;
    }
    
    //MERGESORT----------------------------------------------
    public static void mergeSort(int[] arr, int low, int high){
        
        if( low < high ){
            
            int mid = (low+high) / 2;
            
            mergeSort(arr, low, mid);
            mergeSort(arr, mid+1, high);
            merge(arr, low, mid, high);
        }
    }
    public static void merge(int[] arr, int low, int mid, int high){
        
        int tempArr[] = new int[high+1];
        int j = mid+1;
        int i = low;
        int k = low;
        
        while( i <= mid && j <= high ){
            
            if( arr[i] <= arr[j] ){
                tempArr[k] = arr[i];
                i++;
                k++;              
            }
            if( arr[j] < arr[i]){
                tempArr[k] = arr[j];
                j++;
                k++; 
            }
        }
        while(i <= mid){
            tempArr[k] = arr[i];
            i++;
            k++;
        }
        while(j <= high){
            tempArr[k] = arr[j];
            j++;
            k++;
        }
        for( i=low ; i<k ; i++){
            arr[i] = tempArr[i];
        }       
    }
    
}//END CLASS


