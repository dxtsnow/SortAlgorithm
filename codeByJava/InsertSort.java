import java.util.Arrays;

/**
 * 实现插入排序算法
 * 时间复杂度：n^2
 * 稳定性：稳定
 * @author dxt
 *
 */
public class InsertSort {
	public static void main(String[] args) {
		int[] array = {9, -1, 3, 5, -5, 6, 4};
		insertSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static void insertSort(int[] array) {
		for(int i=1; i<array.length; i++) {
			//每次外循环为array[i]找一个合适的位置
			int insertVal = array[i];	//保存array[i]
			int j;
			for(j=i-1; j>=0 && insertVal<array[j]; j--) {
				//将数组前面大于insertVal的数据后移
				array[j+1] = array[j];
			}
			array[j+1] = insertVal; 
		}
	}
}
