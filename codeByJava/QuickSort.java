import java.util.Arrays;

/**
 * ���������㷨
 * ʱ�临�Ӷȣ�O��nlogn��
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
			int key = arr[i];	//��arr[i]���浽key��,arr[i]��һ����
			while(i < j) {
				while(i < j && arr[j] >= key) {	//�Ӻ�����һ��С��key����
					j--;
				}
				if(i < j) {
					arr[i] = arr[j];	//��arr[j]�arr[i]���У�arr[j]�ֳ���һ���¿�
					i++;
				}
				while(i < j && arr[i] < key) {	//��ǰ����һ�����ڵ���key����
					i++;
				}
				if(i < j) {
					arr[j] = arr[i];	//��arr[i]�arr[j]����,arr[i]����һ���¿�
					j--;
				}
			}
			arr[i] = key;	//��key�����
			quickSort(arr, low, j-1);	//i == j, arr[j] = key
			quickSort(arr, j+1, high);
		}
	}
}
