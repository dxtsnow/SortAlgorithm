import java.util.Arrays;

/**
 * ʵ�ֲ��������㷨
 * ʱ�临�Ӷȣ�n^2
 * �ȶ��ԣ��ȶ�
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
			//ÿ����ѭ��Ϊarray[i]��һ�����ʵ�λ��
			int insertVal = array[i];	//����array[i]
			int j;
			for(j=i-1; j>=0 && insertVal<array[j]; j--) {
				//������ǰ�����insertVal�����ݺ���
				array[j+1] = array[j];
			}
			array[j+1] = insertVal; 
		}
	}
}
