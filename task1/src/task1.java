import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class task1 {

    public static void main(String[] args) {
        String path = args[0];
        File file = new File(path);

        ArrayList<Long> list = new ArrayList();
        String buffer;

        try (BufferedReader fileReader = new BufferedReader(new FileReader(file));){

            while((buffer = fileReader.readLine()) != null ) {
                list.add(Long.parseLong(buffer));
            }

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        Long[] numbers = list.toArray(new Long[0]);

        mergeSort(numbers,0,numbers.length-1);

        float averageValue = 0;
        int percentile = (int) (numbers.length*0.9);
        for (int i = 0; i < numbers.length; i++) {
            averageValue  =  averageValue + (float) numbers[i]/numbers.length;
        }
        long sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] > averageValue && numbers[i] < numbers[percentile]){
                long num = numbers[i];
                while (num != 0) {
                    sum = sum + num % 10;
                    num = num / 10;
                }
            }
        }

        System.out.println(sum);


    }

    public static void mergeSort(Long[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left+right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, mid, right);
    }

    public static void merge(Long [] array, int left, int mid, int right) {

        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;


        Long leftArray[] = new Long[lengthLeft];
        Long rightArray[] = new Long[lengthRight];


        for (int i = 0; i < lengthLeft; i++)
            leftArray[i] = array[left+i];
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid+i+1];


        int leftIndex = 0;
        int rightIndex = 0;


        for (int i = left; i < right + 1; i++) {

            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                }
                else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }

            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }

            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }

}
