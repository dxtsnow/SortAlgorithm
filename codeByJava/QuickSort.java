import java.util.Arrays;

/**
 * 快速排序算法
 * 时间复杂度：O（nlogn）
 * @author dxt
 *
 */
public class QuickSort {
	public static void main(String[] args) {
		int[] array = {9, -1, 3, 5, -5, 6, 4};
		quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
	}
	
	public static void quickSort(int[] arr, int low, int high) {
		if(low < high) {
			int i = low, j = high;
			int key = arr[i];	//将arr[i]保存到key中,arr[i]是一个坑
			while(i < j) {
				while(i < j && arr[j] >= key) {	//从后面找一个小于key的数
					j--;
				}
				if(i < j) {
					arr[i] = arr[j];	//将arr[j]填到arr[i]坑中，arr[j]又成了一个新坑
					i++;
				}
				while(i < j && arr[i] < key) {	//从前面找一个大于等于key的数
					i++;
				}
				if(i < j) {
					arr[j] = arr[i];	//将arr[i]填到arr[j]坑中,arr[i]又是一个新坑
					j--;
				}
			}
			arr[i] = key;	//将key填到坑中
			quickSort(arr, low, j-1);	//i == j, arr[j] = key
			quickSort(arr, j+1, high);
		}
	}
}
