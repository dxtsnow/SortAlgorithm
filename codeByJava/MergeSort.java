import java.util.Arrays;

/**
 * 归并排序算法
 * 分而治之思想
 * @author dxt
 *
 */
public class MergeSort {
	public static void main(String[] args) {
		int[] array = {9, -1, 3, 5, -5, 6, 4};
		int[] temp = new int[array.length];
		mergeSort(array, 0, array.length-1, temp);
		System.out.println(Arrays.toString(array));
	}
	
	public static void mergeSort(int[] arr, int left, int right, int[] temp) {
		if(left < right) {
			int mid = (left + right) / 2;
			mergeSort(arr, left, mid, temp);	//向左递归进行分解
			mergeSort(arr, mid+1, right, temp);	//向右递归进行分解
			merge(arr, left, mid, right, temp);	//合并 左 和 右
		}
	}
	/**
	 * 合并两个有序数组
	 * 对于数组arr, arr[left]到arr[mid]是有序的，arr[mid+1]到arr[right]是有序的
	 * 将两段数组合并到temp[left]到temp[right]中，并保持数据有序
	 * @param arr 	排序的原始数组
	 * @param left	数组左边有序序列的初始索引
	 * @param mid	中间索引
	 * @param right	右边索引（待合并的两段数组的最后一个元素的索引）
	 * @param temp	做中转的数组
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;	//i未arr左边有序数组的初始索引
		int j = mid + 1;	//j 为arr右边有序数组的初始索引
		int t = 0;	//temp数组的索引
		//1. 合并两段有序数组，直到一段数组合并完为止
		while(i <= mid && j <= right) {
			if(arr[i] <= arr[j]) {
				temp[t] = arr[i];
				i++;
				t++;
			}else {
				temp[t] = arr[j];
				j++;
				t++;
			}
		}
		//2. 将剩余数组的剩余元素添加到temp中
		while(i <= mid) {
			temp[t] = arr[i];
			i++;
			t++;
		}
		while(j <= right) {
			temp[t] = arr[j];
			j++;
			t++;
		}
		//3. 将temp中的有序数据 复制到 arr 中对应位置
		for(int k=0; k<t; k++) {
			arr[left+k] = temp[k];
		}
	}
}
