import java.util.Arrays;

/**
 * �鲢�����㷨
 * �ֶ���֮˼��
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
			mergeSort(arr, left, mid, temp);	//����ݹ���зֽ�
			mergeSort(arr, mid+1, right, temp);	//���ҵݹ���зֽ�
			merge(arr, left, mid, right, temp);	//�ϲ� �� �� ��
		}
	}
	/**
	 * �ϲ�������������
	 * ��������arr, arr[left]��arr[mid]������ģ�arr[mid+1]��arr[right]�������
	 * ����������ϲ���temp[left]��temp[right]�У���������������
	 * @param arr 	�����ԭʼ����
	 * @param left	��������������еĳ�ʼ����
	 * @param mid	�м�����
	 * @param right	�ұ����������ϲ���������������һ��Ԫ�ص�������
	 * @param temp	����ת������
	 */
	public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
		int i = left;	//iδarr�����������ĳ�ʼ����
		int j = mid + 1;	//j Ϊarr�ұ���������ĳ�ʼ����
		int t = 0;	//temp���������
		//1. �ϲ������������飬ֱ��һ������ϲ���Ϊֹ
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
		//2. ��ʣ�������ʣ��Ԫ����ӵ�temp��
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
		//3. ��temp�е��������� ���Ƶ� arr �ж�Ӧλ��
		for(int k=0; k<t; k++) {
			arr[left+k] = temp[k];
		}
	}
}
