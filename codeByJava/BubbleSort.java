import java.util.Arrays;

/**
 * ʵ��ð�������㷨
 * ʱ�临�Ӷȣ�n^2
 * ��ã�O(n)	���O(n^2)
 * �ȶ��ԣ��ȶ� 
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
		boolean flag = false;	//��ʾ�����Ƿ���й�����
		//���ѭ��ȷ����i�����
		for(int i=0; i<array.length-1; i++) {
			//j+1<array.length-i ==> j<array.length-i-1
			for(int j=0; j<array.length-i-1; j++) {
				if(array[j] > array[j+1]) {
					flag = true;	//���������ݽ���
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp; 
				}
			}
			if(flag) {
				//������й�����������flag
				flag = false;
			}else {
				break;
			}
		}
	}
}
