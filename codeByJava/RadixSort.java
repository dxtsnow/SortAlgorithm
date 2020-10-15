import java.util.Arrays;

/**
 * ʵ�ֻ�������Ͱ����
 * �ȶ��ԣ��ȶ�
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
		//1. ȷ���������������λ������ȷ�������ռ�������
		int times = 1;
		int p = 10;
		for(int i=0; i<arr.length; i++) {
			while(arr[i] >= p) {
				times++;
				p *= 10;
			}
		}
		//2. ���� ���� �ռ�����
		int[] temp = new int[arr.length];
		int[] buckets = new int[10];	//0-9ʮ��Ͱ
		int i, j, k;
		int radix = 1;	//�Ӹ�λ��ʼ
		for(i=0; i<times; i++) {	//�ܹ�����times��
			//2.1 ÿ�η���ǰ��Ͱ���,Ͱ�б������Ԫ�صĸ���
			for(j=0; j<buckets.length; j++) {
				buckets[j] = 0;
			}
			//2.2 �����ݷ��䵽Ͱ��
			//����ȡ�෽ʽ����ö�Ӧ��λ�ϵ���������ӦͰ�е����ݸ���+1
			for(j=0; j<arr.length; j++) {
				k = (arr[j] / radix) % 10;
				buckets[k]++;
			}
			//2.3 ȷ��ÿ��Ͱ��Ԫ�ض�Ӧ��λ��
			//���磺��2��Ͱ�е����ݶ��ȵ�1��Ͱ�е����ݴ����1��Ͱ�е�������a��,��2��ͬ����b��
			//��ô��2��Ͱ�еĵ����ݵĶ�Ӧλ�þ���(a, a+b], ����������Ӧ��a+b
			//������Ͱ��Ӧ��λ�þ���(a+b, a+b+c]
			for(j=1; j<10; j++) {
				buckets[j] = buckets[j] + buckets[j-1];
			}
			//2.4 �ռ�����,�ص�,ע�����
			for(j=arr.length-1; j>=0; j--) {
				k = (arr[j] / radix) % 10;
				temp[buckets[k] - 1] = arr[j];
				buckets[k]--;
			}
			//2.5 ����ʱ�����е����ݸ��Ƶ�arr[]��
			for(j=0; j<arr.length; j++) {
				arr[j] = temp[j];
			}
			//2.6 ȷ����һ�ֱȽϵ�λ��
			radix *= 10;
		}
	}
}
