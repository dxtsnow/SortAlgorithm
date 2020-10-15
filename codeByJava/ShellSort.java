import java.util.Arrays;

/**
 * ʵ��ϣ������
 * ʱ�临�Ӷȣ�����
 * �ȶ��ԣ����ȶ���һ�β����������ȶ��ģ����ڲ�ͬ�Ĳ�����������У���ͬ��Ԫ�ؿ����ڸ��ԵĲ����������ƶ���������ȶ��Ծͻᱻ����
 * @author dxt
 *
 */
public class ShellSort {
	public static void main(String[] args) {
		int[] array = {9, -1, 3, 5, -5, 6, 4};
		shellSort(array);
		System.out.println(Arrays.toString(array));
	}
	/**
	 * Ч�ʱȲ�������ߵ�ԭ��
	 * ��nֵ�ܴ�ʱ������ÿһ��������Ҫ�ƶ��ĸ������٣���������ľ���ܳ�;
	 * ��nֵ��Сʱÿһ����Ҫ�ƶ����������࣬��ʱ�Ѿ��ӽ�����������������λ�á�
	 * @param array
	 */
	public static void shellSort(int array[]) {
		//���ѭ��������������з��飬ͬһ���Ԫ�ؼ��Ϊgap
		for(int gap = array.length / 2; gap > 0; gap /= 2) {
			//�ڶ���ѭ����ÿ�����ݽ��в�������
			for(int i = gap; i < array.length; i++) {
				int temp = array[i];
				int j;
				for(j = i-gap; j>=0 && temp<array[j]; j-=gap) {
					array[j+gap] = array[j];
				}
				array[j+gap] = temp;
			}
		}
	}
}
