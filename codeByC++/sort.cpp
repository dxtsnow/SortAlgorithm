/*
����������˼άʵ��һ�������㷨���ܽ� 
�����㷨��
1��ѡ������
2��ð������
3����������
4����������
5����������
6���鲢���� 
*/

#include <iostream>
using namespace std;

template <class T>
void swapAB(T & a, T & b){
	T temp = a;
	a = b;
	b = temp;
}

template <class T>
class sort{
	public:
		sort(int num = 3);
		sort(T *array, int len);
		sort(const sort<T> &); 
		~sort(){
			delete [] arr;
		} 
		int getSize();
		void selectionSort();	//ѡ������ 
		void bubbleSort();		//ð������ 
		void insertionSort();	//�������� 
		void radixSort();		//�������� 
		void quickSort(int low, int high);		//�������� 
		void mergeSort();		//�鲢����
		void mergeSort_merge(int first, int mid, int last, T temp[]);
		void mergeSort_sort(int first, int last, T temp[]);
		
		void selectionSortB();	//��ʱ��ֹ��ѡ������
		void bubbleSortB(); 	//��ʱ��ֹ��ð������
		 
		void output();
		 
	private:
		T *arr;		//�洢Ԫ�ص����� 
		int size; 	//Ԫ�ظ��� 
};
//���캯�� 
template <class T>
sort<T>::sort(int num){
	if(num <= 0){
		cout <<"the num of array must > 0"<<endl;
		throw "the num of array must > 0";
	}
	size = num;
	arr = new T[size];
}
//���캯��
template <class T>
sort<T>::sort(T *array, int len){
	if(len <= 0){
		cout <<"the length of array must > 0"<<endl;
		throw "the length of array must > 0";
	}
	size = len;
	arr = new T[size];
	for(int i=0; i<size; i++){
		arr[i] = array[i];
	} 
} 
//���ƹ��캯�� 
template <class T>
sort<T>::sort(const sort<T> & theArr){
	size = theArr.size;
	arr = new T[size];
	for(int i=0; i<size; i++){
		arr[i] = theArr[i];
	}
}
//��ȡԪ�ظ���
template <class T>
int sort<T>::getSize(){
	return size;
} 

//ѡ������
/*
1. ÿ��ѡ��һ���������ŵ���� 
2. �ڲ�ѭ���ҳ���ǰi��Ԫ��[0::i-1]�������Ǹ���������
   ���ѭ�� ÿ�� ���ڲ�ѭ���ҳ����� �ŵ���i-1��λ��,Ȼ�� i-- 
*/
template <class T>
void sort<T>::selectionSort(){ 			
	for(int i=size; i>1; i--){
		int max_index = 0;
		for(int j=1; j<i; j++){ 	//ǰi������������� 
			if(arr[max_index] < arr[j]){
				max_index = j;
			}
		}
		swapAB(arr[i-1], arr[max_index]); //������� �� ���һ��λ�õ����ֻ��� 
	}
} 

//ð������
/*
1. �Ƚ���������Ԫ�أ����ǰһ���Ⱥ�һ������swap
2. ����n��Ԫ�أ�������Ϊ[0::n-1],
    �������n-1�ֱȽϣ�ÿ�ֶ�ȷ��һ�����ķŵ���� 
	���i��ʱ����i��Ԫ����ȷ����arr[n-1-i,n-1]
	��n-1-i��Ԫ�ػ�û���򣬼�arr[0, n-1-i-1] 
*/
template <class T>
void sort<T>::bubbleSort(){
	for(int i=size; i>1; i--){
		for(int j=0; j<i-1; j++){
			if(arr[j] > arr[j+1]){
				swapAB(arr[j], arr[j+1]);
			}
		}
	}	
} 

//��������
/*
1. ��Ԫ�ش�ͷ��ʼ���ϵ����Լ����ڵ�����ռ����
2. ��ֻ��һ��Ԫ��ʱ����������
    ����1��Ԫ��ʱ ���ӵ�2��Ԫ�ؿ�ʼ����������������룬
	����֮ǰ��Ҫ�����ڵ�i��Ԫ��֮ǰ������ֵ����arr[i]��Ԫ������� 
*/
template <class T>
void sort<T>::insertionSort(){
	for(int i=1; i<size; i++){
		T temp = arr[i];
		int j;
		for(j=i-1; j>=0 && temp < arr[j]; j--){
			arr[j+1] = arr[j];
		}
		arr[j+1] = temp;
	} 
}

//��������
/*
1. ������Ͱ��ԭ���ӵ�λ��ʼ���𣬲��Ϸ���Ͱ�ٴӴ��������ó����������λ���ó���������� 
2.  ����Ҫȷ�����λ��Ӧ�����֣�����Ҫ�ȼ��� 
*/
template <class T>
void sort<T>::radixSort(){
	//1 ȷ������λ��  d 
	int d = 1; 			//��������λ�� 
	int p = 10;
	for(int i=0; i<size; i++){
		while(arr[i] >= p){
			d++;
			p *= 10; 
		}
	} 
	//2 ���� �ռ� 
	T *tmp = new T[size];
	int *count = new int[10];	//������ , 0-9 10��Ͱ 
	int i, j, k;
	int radix = 1;
	for(i = 1; i <= d; i++){	//�������ռ� d �� 
		for(j=0; j<10; j++){
			count[j] = 0;		//ÿ�η���ǰ��ռ�������Ͱ�� 
		}
		for(j=0; j<size; j++){
			k = (arr[j] / radix) % 10;	//��ÿ�������䵽��ͬ��Ͱ��
			count[k]++;					//��ӦͰ�����ֵĸ���+1 
		}
		for(j=1; j<10; j++){
			count[j] = count[j-1] + count[j]; //a, a+b, a+b+c, ...., size 
		} 
		for(j = size-1; j>=0; j--){	 //�ռ� �Ӻ���ǰ��Ϊ�� ��ÿ��Ͱ �϶˵��� ��������Եĺ��� 
			k = (arr[j] / radix) % 10;		//ȷ��arr[j]�����Ǹ�Ͱ 
            tmp[count[k] - 1] = arr[j];		//�϶˵�λ�ں��� 
            count[k]--;						
		}
		for(j = 0; j < size; j++) //����ʱ��������ݸ��Ƶ�data��
            arr[j] = tmp[j];
        radix = radix * 10;		//������һ�ַ����ռ� 
	} 
	delete[] tmp;
	delete[] count;
} 

//��������
/*
1���ȴ�������ȡ��һ������Ϊ��׼����
2���������̣���������������ȫ�ŵ������ұߣ�С�ڻ����������ȫ�ŵ�������ߡ�
3���ٶ����������ظ��ڶ�����ֱ��������ֻ��һ������  
*/
template <class T>
void sort<T>::quickSort(int low, int high){
	if(low < high){
		int i = low, j = high;
		T key = arr[low];
		while(i < j){
			while(i<j && arr[j] >= key){	//�������� ��С��key�� 
				j--;
			}
			if(i < j){
				arr[i] = arr[j];
				i++; 
			}
			while(i<j && arr[i] < key){
				i++;
			}
			if(i < j){
				arr[j] = arr[i];
				j--;
			}
		}
		arr[i] = key;
		quickSort(low, j-1);
		quickSort(j+1, high); 	
	}
}

//�鲢����
//����������a[first...mid]��a[mid...last]�ϲ���
template <class T>
void sort<T>::mergeSort_merge(int first, int mid, int last, T temp[]){
	int i = first, j = mid+1;
	int m = mid, n = last;
	int k = 0;
	
	while(i <= m && j <= n){
		if(arr[i] <= arr[j]){
			temp[k] = arr[i];
			k++;
			i++; 
		}
		else{
			temp[k] = arr[j];
			k++;
			j++;
		}
	}
	while(i <= m){
		temp[k] = arr[i];
		k++;
		i++;
	}
	while(j <= n){
		temp[k] = arr[j];
		k++;
		j++;
	}
	//����д��
	for(i = 0; i<k; i++){
		arr[first+i] = temp[i];
	}	
}
//��ÿ�����ݶ�ֻ��һ������ʱ������mergeSort_merge()�ϲ� 
template <class T>
void sort<T>::mergeSort_sort(int first, int last, T temp[]){
	if(first < last){
		int mid = (first+last) / 2;
		mergeSort_sort(first, mid, temp);
		mergeSort_sort(mid+1, last, temp);
		mergeSort_merge(first, mid, last, temp);
	}
}
template <class T>
void sort<T>::mergeSort(){
	T *p = new T[size];
	mergeSort_sort(0, size-1, p);
	delete [] p;
} 

//��ʱ��ֹ��ѡ������
template <class T>
void sort<T>::selectionSortB(){
	bool sorted = false;
	
	for(int i=size; !sorted && i>0; i--){
		int max_index = 0;
		sorted = true;
		for(int j=1; j<i; j++){
			if(arr[max_index] <= arr[j]){
				max_index = j;
			}
			else{
				sorted = false;
			}
		}
		swapAB(arr[max_index], arr[i-1]);
	}
}
 
//��ʱ��ֹ��ð������
template <class T>
void sort<T>::bubbleSortB(){
	bool sorted = false;
	for(int i=size; !sorted && i>1; i--){
		sorted = true;
		for(int j=0; j<i-1; j++){
			if(arr[j] > arr[j+1]){
				swapAB(arr[j], arr[j+1]);
				sorted = false;
			}
		}
	}
} 
//���
template <class T>
void sort<T>::output(){
	for(int i=0; i<size; i++){
		cout <<arr[i]<<" ";
	}
	cout <<endl;
} 

//����
int main(){
	int array[4] = {4, 3, 2, 1};
	int len = sizeof(array) / sizeof(array[0]);
	
	bool finish = false;
	while(!finish){
		sort<int> *s1 = new sort<int>(array, len);
		s1->output();
		
		int function = 0;
		cout <<"*************************************"<<endl;
		cout <<"��ѡ������ʽ��"<<endl;
		cout <<"\t 1: ѡ������"<<endl;
		cout <<"\t 2: ð������"<<endl;
		cout <<"\t 3: ��������"<<endl;
		cout <<"\t 4: ��������"<<endl;
		cout <<"\t 5: ��������"<<endl;
		cout <<"\t 6: �鲢����"<<endl;
		cout <<"\t 7: ��ʱ��ֹ��ѡ������"<<endl;
		cout <<"\t 8: ��ʱ��ֹ��ð������"<<endl; 
		cout <<"\t 9: �˳�"<<endl; 
		cin >>function;
		switch(function){
			case 1:
				s1->selectionSort();
				break;
			case 2:
				s1->bubbleSort();
				break;
			case 3:
				s1->insertionSort();
				break;
			case 4:
				s1->radixSort();
				break; 
			case 5:
				s1->quickSort(0, s1->getSize()-1);
				break; 
			case 6: 
				s1->mergeSort();
				break; 
			case 7:
				s1->selectionSortB();
				break;
			case 8:
				s1->bubbleSortB();
				break;
			case 9:
				finish = true;
				break;
			default:
				cout <<"�����������������"<<endl; 
		}
		if(!finish){
			cout <<"����������"; 
			s1->output();
			cout <<endl;
		}
		delete s1;
	}
	return 0;
} 

