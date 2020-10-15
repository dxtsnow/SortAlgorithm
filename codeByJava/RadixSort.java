import java.util.Arrays;

/**
 * 实现基数排序（桶排序）
 * 稳定性：稳定
 * @author Lenovo
 *
 */
public class RadixSort {
	public static void main(String[] args) {
		int[] array = {4, 3, 2, 1, 100, 30, 0, 22};
		radixSort(array);
		System.out.println(Arrays.toString(array));
	}
	
	public static void radixSort(int[] arr) {
		//1. 确定数组中最大数的位数，即确定分配收集的轮数
		int times = 1;
		int p = 10;
		for(int i=0; i<arr.length; i++) {
			while(arr[i] >= p) {
				times++;
				p *= 10;
			}
		}
		//2. 进行 分配 收集操作
		int[] temp = new int[arr.length];
		int[] buckets = new int[10];	//0-9十个桶
		int i, j, k;
		int radix = 1;	//从个位开始
		for(i=0; i<times; i++) {	//总共进行times轮
			//2.1 每次分配前将桶清空,桶中保存的是元素的个数
			for(j=0; j<buckets.length; j++) {
				buckets[j] = 0;
			}
			//2.2 将数据分配到桶中
			//依据取余方式，获得对应数位上的数，将对应桶中的数据个数+1
			for(j=0; j<arr.length; j++) {
				k = (arr[j] / radix) % 10;
				buckets[k]++;
			}
			//2.3 确定每个桶中元素对应的位置
			//比如：第2个桶中的数据都比第1个桶中的数据大，设第1个桶中的数据有a个,第2个同中有b个
			//那么第2个桶中的的数据的对应位置就是(a, a+b], 在这里计算对应的a+b
			//第三个桶对应的位置就是(a+b, a+b+c]
			for(j=1; j<10; j++) {
				buckets[j] = buckets[j] + buckets[j-1];
			}
			//2.4 收集数据,重点,注意理解
			for(j=arr.length-1; j>=0; j--) {
				k = (arr[j] / radix) % 10;
				temp[buckets[k] - 1] = arr[j];
				buckets[k]--;
			}
			//2.5 将临时数组中的内容复制到arr[]中
			for(j=0; j<arr.length; j++) {
				arr[j] = temp[j];
			}
			//2.6 确定下一轮比较的位数
			radix *= 10;
		}
	}
}
