import java.util.Arrays;

/**
 * 堆排序
 * 算法时间复杂度为 nlogn
 * 不稳定
 * 重点：
 *  1) 在保存的二叉树节点的数组中，一个节点的索引为n,那么他的左子节点的索引为2n+1,右子节点的索引为2n+2
 *  2) 大根堆和小根堆都是完全二叉树，大根堆中父节点的数据大于等于其子节点的数据，小根堆中父节点的数据小于等
 *  于其子节点的数据
 *  3) 大根堆用于升序排列，小根堆用于降序排列
 * @author dxt
 *
 */
public class HeapSort {
	public static void main(String[] args) {
		//待排序数据，对数组进行升序排列
		int[] arr = {4, 6, 0, 3, 99, 7, 64, 1024};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	/**
	 * 使用大根堆进行排序的方法
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		//1. 将arr转换为大根堆
		for(int i=arr.length/2-1; i>=0; i--) {
			changeToHeap(arr, i, arr.length);
		}
		//2. 排序
		int temp = 0;
		for(int j=arr.length-1; j>0; j--) {
			temp = arr[j];
			arr[j] = arr[0];	//将最大值放到最后
			arr[0] = temp;
			changeToHeap(arr, 0, j);
		}
	}
	/**
	 * 将一个保存二叉树的数组中 以i节点为根的子二叉树调整成一个大根堆，此节点的左子树和右子树必须是大根堆
	 * 使用此方法，从最后一个非叶子节点一直处理到二叉树的根节点，就会将整个二叉树转换为大根堆
	 * @param arr	保存二叉树的数组
	 * @param i		表示非叶子节点在数组中的索引
	 * @param length	对arr中多少个节点进行调整，排序过程中length会逐渐变小
	 */
	public static void changeToHeap(int arr[], int i, int length) {
		int temp = arr[i];
		//开始调整
		for(int k = i*2+1; k<length; k = k*2+1) {
			//判断左子节点 和 右子节点 哪个节点的数据更大
			if(k+1 < length && arr[k] < arr[k+1]) {
				k++;
			}
			//如果子结点中有大于父节点的子节点，则交换
			if(arr[k] > temp) {
				arr[i] = arr[k];
				i = k;	//再处理以此子节点为根的树
			}else {
				break;
			}
		}
		//当for循环结束后，我们已经将以i为父节点的树的最大值，放在了顶部
		arr[i] = temp;	//将temp放到调整后的位置
	}
}
