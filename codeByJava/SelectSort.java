import java.util.Arrays;

/**
 * ʵ��ѡ������
 * ʱ�临�Ӷȣ�n^2
 * ��ã�O(n) ���O(n^2)
 * �ȶ��ԣ����ȶ���ע����⣩
 * @author dxt
 *
 */
public class SelectSort {
	public static void main(String[] args) {
		int[] arr = {2, 4, 1, 3, 0, -5, 6};
		selectSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	public static void selectSort(int[] array) {
		int temp = 0;
		boolean sorted = false;	//��ǰ�����Ƿ��������
		//ÿ����ѭ��ȷ����i��λ�ã�����Ϊi-1����������
		for(int i=array.length; !sorted && i>0; i--) {
			int indexOfMax = 0;
			//ÿ����ѭ���ҳ�ǰi�����������е��������ݵ�����
			for(int j=1; j<i; j++) {
				if(array[indexOfMax] < array[j]) {
					indexOfMax = j;
				}else {
					sorted = false;
				}
			}
			//�������ݣ����ܻ��ƻ��ȶ���
			temp = array[i-1];
			array[i-1] = array[indexOfMax];
			array[indexOfMax] = temp;
		}
	}
}
