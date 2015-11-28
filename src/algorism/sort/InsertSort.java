package algorism.sort;

/**
 * 插入排序  最简单的排序 时间复杂度 最坏情况下 O(n log(n)) 不需要额外的存储空间
 * 
 * @author Administrator
 * @see http://blog.csdn.net/kimylrong/article/details/17121519
 */
public class InsertSort {  
    public static void insertSort(int[] array) {  
        if (array == null || array.length < 2) {  
            return;  
        }  
  
        for (int i = 1; i < array.length; i++) {  
            int currentValue = array[i];  
            int position = i;  
            for (int j = i - 1; j >= 0; j--) {  
                if (array[j] > currentValue) {  
                    array[j + 1] = array[j];  
                    position -= 1;  
                } else {  
                    break;  
                }  
            }  
  
            array[position] = currentValue;  
        }  
    }  
  
    public static void main(String[] args) {  
        int[] array = { 3, -1, 0, -8, 2, 1 };  
        ArrayUtils.printArray(array);  
        insertSort(array);  
        ArrayUtils.printArray(array);  
    }  
}  