import java.util.Arrays;

/**
 * 实现希尔排序
 * 时间复杂度：？？
 * 稳定性：不稳定，一次插入排序是稳定的，但在不同的插入排序过程中，相同的元素可能在各自的插入排序中移动，最后其稳定性就会被打乱
 * @author dxt
 *
 */
public class ShellSort {
	public static void main(String[] args) {
		int[] array = {9, -1, 3, 5, -5, 6, 4};
		shellSort(array);
		System.out.println(Arrays.toString(array));
	}
	/**
	 * 效率比插入排序高的原因
	 * 当n值很大时数据项每一趟排序需要移动的个数很少，但数据项的距离很长;
	 * 当n值减小时每一趟需要移动的数据增多，此时已经接近于它们排序后的最终位置。
	 * @param array
	 */
	public static void shellSort(int array[]) {
		//外层循环对整个数组进行分组，同一组的元素间隔为gap
		for(int gap = array.length / 2; gap > 0; gap /= 2) {
			//第二层循环对每组数据进行插入排序
			for(int i = gap; i < array.length; i++) {
				int temp = array[i];
				int j;
				for(j = i-gap; j>=0 && temp<array[j]; j-=gap) {
					array[j+gap] = array[j];
				}
				array[j+gap] = temp;
			}
		}
	}
}
