/*
以面向对象的思维实现一个排序算法的总结 
排序算法：
1、选择排序
2、冒泡排序
3、插入排序
4、基数排序
5、快速排序
6、归并排序 
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
		void selectionSort();	//选择排序 
		void bubbleSort();		//冒泡排序 
		void insertionSort();	//插入排序 
		void radixSort();		//基数排序 
		void quickSort(int low, int high);		//快速排序 
		void mergeSort();		//归并排序
		void mergeSort_merge(int first, int mid, int last, T temp[]);
		void mergeSort_sort(int first, int last, T temp[]);
		
		void selectionSortB();	//及时终止的选择排序
		void bubbleSortB(); 	//及时终止的冒泡排序
		 
		void output();
		 
	private:
		T *arr;		//存储元素的数组 
		int size; 	//元素个数 
};
//构造函数 
template <class T>
sort<T>::sort(int num){
	if(num <= 0){
		cout <<"the num of array must > 0"<<endl;
		throw "the num of array must > 0";
	}
	size = num;
	arr = new T[size];
}
//构造函数
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
//复制构造函数 
template <class T>
sort<T>::sort(const sort<T> & theArr){
	size = theArr.size;
	arr = new T[size];
	for(int i=0; i<size; i++){
		arr[i] = theArr[i];
	}
}
//获取元素个数
template <class T>
int sort<T>::getSize(){
	return size;
} 

//选择排序
/*
1. 每次选择一个最大的数放到最后 
2. 内层循环找出在前i个元素[0::i-1]中最大的那个数的索引
   外层循环 每次 将内层循环找出的数 放到第i-1个位置,然后 i-- 
*/
template <class T>
void sort<T>::selectionSort(){ 			
	for(int i=size; i>1; i--){
		int max_index = 0;
		for(int j=1; j<i; j++){ 	//前i个数字是无序的 
			if(arr[max_index] < arr[j]){
				max_index = j;
			}
		}
		swapAB(arr[i-1], arr[max_index]); //将最大数 与 最后一个位置的数字互换 
	}
} 

//冒泡排序
/*
1. 比较相邻两个元素，如果前一个比后一个大，则swap
2. 设有n个元素，则索引为[0::n-1],
    则需进行n-1轮比较，每轮都确定一个最大的放到最后 
	则第i轮时，有i个元素以确定即arr[n-1-i,n-1]
	有n-1-i个元素还没排序，即arr[0, n-1-i-1] 
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

//插入排序
/*
1. 将元素从头开始不断的向自己所在的数组空间插入
2. 当只有一个元素时，不用排序
    多于1个元素时 ，从第2个元素开始向已有数组里面插入，
	插入之前，要将大于第i个元素之前，且数值大于arr[i]的元素向后移 
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

//基数排序
/*
1. 利用了桶的原理，从低位开始比起，不断放入桶再从从中依次拿出，比至最高位，拿出的是有序的 
2.  首先要确定最高位对应的数字，即需要比几轮 
*/
template <class T>
void sort<T>::radixSort(){
	//1 确定最大的位数  d 
	int d = 1; 			//保存最大的位数 
	int p = 10;
	for(int i=0; i<size; i++){
		while(arr[i] >= p){
			d++;
			p *= 10; 
		}
	} 
	//2 分配 收集 
	T *tmp = new T[size];
	int *count = new int[10];	//计数器 , 0-9 10个桶 
	int i, j, k;
	int radix = 1;
	for(i = 1; i <= d; i++){	//共分配收集 d 次 
		for(j=0; j<10; j++){
			count[j] = 0;		//每次分配前清空计数器（桶） 
		}
		for(j=0; j<size; j++){
			k = (arr[j] / radix) % 10;	//将每个数分配到不同的桶中
			count[k]++;					//对应桶中数字的个数+1 
		}
		for(j=1; j<10; j++){
			count[j] = count[j-1] + count[j]; //a, a+b, a+b+c, ...., size 
		} 
		for(j = size-1; j>=0; j--){	 //收集 从后向前是为了 将每个桶 上端的数 保持在相对的后面 
			k = (arr[j] / radix) % 10;		//确定arr[j]属于那个桶 
            tmp[count[k] - 1] = arr[j];		//上端的位于后面 
            count[k]--;						
		}
		for(j = 0; j < size; j++) //将临时数组的内容复制到data中
            arr[j] = tmp[j];
        radix = radix * 10;		//进行下一轮分配收集 
	} 
	delete[] tmp;
	delete[] count;
} 

//快速排序
/*
1．先从数列中取出一个数作为基准数。
2．分区过程，将比这个数大的数全放到它的右边，小于或等于它的数全放到它的左边。
3．再对左右区间重复第二步，直到各区间只有一个数。  
*/
template <class T>
void sort<T>::quickSort(int low, int high){
	if(low < high){
		int i = low, j = high;
		T key = arr[low];
		while(i < j){
			while(i<j && arr[j] >= key){	//从右向左， 找小于key的 
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

//归并排序
//将有序数列a[first...mid]和a[mid...last]合并。
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
	//重新写回
	for(i = 0; i<k; i++){
		arr[first+i] = temp[i];
	}	
}
//当每块数据都只有一个数据时，可用mergeSort_merge()合并 
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

//及时终止的选择排序
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
 
//及时终止的冒泡排序
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
//输出
template <class T>
void sort<T>::output(){
	for(int i=0; i<size; i++){
		cout <<arr[i]<<" ";
	}
	cout <<endl;
} 

//测试
int main(){
	int array[4] = {4, 3, 2, 1};
	int len = sizeof(array) / sizeof(array[0]);
	
	bool finish = false;
	while(!finish){
		sort<int> *s1 = new sort<int>(array, len);
		s1->output();
		
		int function = 0;
		cout <<"*************************************"<<endl;
		cout <<"请选择排序方式："<<endl;
		cout <<"\t 1: 选择排序"<<endl;
		cout <<"\t 2: 冒泡排序"<<endl;
		cout <<"\t 3: 插入排序"<<endl;
		cout <<"\t 4: 基数排序"<<endl;
		cout <<"\t 5: 快速排序"<<endl;
		cout <<"\t 6: 归并排序"<<endl;
		cout <<"\t 7: 及时终止的选择排序"<<endl;
		cout <<"\t 8: 及时终止的冒泡排序"<<endl; 
		cout <<"\t 9: 退出"<<endl; 
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
				cout <<"输入错误，请重新输入"<<endl; 
		}
		if(!finish){
			cout <<"排序后输出："; 
			s1->output();
			cout <<endl;
		}
		delete s1;
	}
	return 0;
} 

