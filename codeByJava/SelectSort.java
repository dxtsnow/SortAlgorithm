import java.util.Arrays;

/**
 * 实现选择排序
 * 时间复杂度：n^2
 * 最好：O(n) 最坏：O(n^2)
 * 稳定性：不稳定（注意理解）
 * @author dxt
 *
 */
public class SelectSort {
	public static void main(String[] args) {
		int[] arr = {2, 4, 1, 3, 0, -5, 6};
		selectSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void selectSort(int[] array) {
		int temp = 0;
		boolean sorted = false;	//当前数组是否是无序的
		//每次外循环确定第i个位置（索引为i-1）处的数据
		for(int i=array.length; !sorted && i>0; i--) {
			int indexOfMax = 0;
			//每次内循环找出前i个无序数据中的最大的数据的索引
			for(int j=1; j<i; j++) {
				if(array[indexOfMax] < array[j]) {
					indexOfMax = j;
				}else {
					sorted = false;
				}
			}
			//交换数据，可能会破坏稳定性
			temp = array[i-1];
			array[i-1] = array[indexOfMax];
			array[indexOfMax] = temp;
		}
	}
}
