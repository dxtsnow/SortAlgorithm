import java.util.Arrays;

/**
 * 实现冒泡排序算法
 * 时间复杂度：n^2
 * 最好：O(n)	最坏：O(n^2)
 * 稳定性：稳定 
 * @author dxt
 *
 */
public class BubbleSort {
	public static void main(String[] args) {
		int[] arr = {2, 4, 1, 3, 0, -5, 6};
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void bubbleSort(int[] array) {
		int temp = 0;
		boolean flag = false;	//表示数组是否进行过交换
		//外层循环确定第i大的数
		for(int i=0; i<array.length-1; i++) {
			//j+1<array.length-i ==> j<array.length-i-1
			for(int j=0; j<array.length-i-1; j++) {
				if(array[j] > array[j+1]) {
					flag = true;	//进行了数据交换
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp; 
				}
			}
			if(flag) {
				//如果进行过交换，重置flag
				flag = false;
			}else {
				break;
			}
		}
	}
}
