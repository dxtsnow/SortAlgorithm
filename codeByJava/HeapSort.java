import java.util.Arrays;

/**
 * ������
 * �㷨ʱ�临�Ӷ�Ϊ nlogn
 * ���ȶ�
 * �ص㣺
 *  1) �ڱ���Ķ������ڵ�������У�һ���ڵ������Ϊn,��ô�������ӽڵ������Ϊ2n+1,���ӽڵ������Ϊ2n+2
 *  2) ����Ѻ�С���Ѷ�����ȫ��������������и��ڵ�����ݴ��ڵ������ӽڵ�����ݣ�С�����и��ڵ������С�ڵ�
 *  �����ӽڵ������
 *  3) ����������������У�С�������ڽ�������
 * @author dxt
 *
 */
public class HeapSort {
	public static void main(String[] args) {
		//���������ݣ������������������
		int[] arr = {4, 6, 0, 3, 99, 7, 64, 1024};
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	/**
	 * ʹ�ô���ѽ�������ķ���
	 * @param arr
	 */
	public static void heapSort(int[] arr) {
		//1. ��arrת��Ϊ�����
		for(int i=arr.length/2-1; i>=0; i--) {
			changeToHeap(arr, i, arr.length);
		}
		//2. ����
		int temp = 0;
		for(int j=arr.length-1; j>0; j--) {
			temp = arr[j];
			arr[j] = arr[0];	//�����ֵ�ŵ����
			arr[0] = temp;
			changeToHeap(arr, 0, j);
		}
	}
	/**
	 * ��һ������������������� ��i�ڵ�Ϊ�����Ӷ�����������һ������ѣ��˽ڵ���������������������Ǵ����
	 * ʹ�ô˷����������һ����Ҷ�ӽڵ�һֱ�����������ĸ��ڵ㣬�ͻὫ����������ת��Ϊ�����
	 * @param arr	���������������
	 * @param i		��ʾ��Ҷ�ӽڵ��������е�����
	 * @param length	��arr�ж��ٸ��ڵ���е��������������length���𽥱�С
	 */
	public static void changeToHeap(int arr[], int i, int length) {
		int temp = arr[i];
		//��ʼ����
		for(int k = i*2+1; k<length; k = k*2+1) {
			//�ж����ӽڵ� �� ���ӽڵ� �ĸ��ڵ�����ݸ���
			if(k+1 < length && arr[k] < arr[k+1]) {
				k++;
			}
			//����ӽ�����д��ڸ��ڵ���ӽڵ㣬�򽻻�
			if(arr[k] > temp) {
				arr[i] = arr[k];
				i = k;	//�ٴ����Դ��ӽڵ�Ϊ������
			}else {
				break;
			}
		}
		//��forѭ�������������Ѿ�����iΪ���ڵ���������ֵ�������˶���
		arr[i] = temp;	//��temp�ŵ��������λ��
	}
}
