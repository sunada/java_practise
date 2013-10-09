import java.util.*;

public class Sort{
    public void print_arr(int[] arr){
        int len = arr.length;
        for (int i = 0; i < len; i++){
            System.out.print("" + arr[i] + " ");
        }
        System.out.println("");
    }


    public int[] dup_arr(int[] arr){
        int len = arr.length;
        int re[] = new int[len];
        for (int i = 0; i < len; i++){
            re[i] = arr[i];
        }
        return re;
    }


    public int[] bubble(int[] arr){
        int dup[] = this.dup_arr(arr);
        int len = dup.length;
        int tmp = 0;
        for (int i = 0; i < len; i++){
            for (int j = 0; j < len - 1 - i; j++){
                if (dup[j] < dup[j+1]){
                    tmp = dup[j];
                    dup[j] = dup[j+1];
                    dup[j+1] = tmp;
                }
            }
        }
        return dup;
    }


    public int[] selection(int[] arr){
        int dup[] = this.dup_arr(arr);
        int len = dup.length;
        int tmp = 0;
        int max = 0;
        for (int i = 0; i < len; i++){
            max = i;
            for (int j = i + 1; j < len; j++){
                if (dup[j] > dup[max]){
                    max = j;
                }
            }
            tmp = dup[i];
            dup[i] = dup[max];
            dup[max] = tmp;
        }
        
        return dup;
    }


    public int[] insertion(int[] arr){
        int dup[] = this.dup_arr(arr);
        for (int i = 1; i < dup.length; i++){
            int key = dup[i];
            int j = i - 1;
            while (j >= 0 && dup[j] < key){
                dup[j + 1] = dup[j];
                j--;
            }
            dup[j + 1] = key;
        }
        return dup;
    }


    public int[] shell(int[] arr, int step){
        int dup[] = this.dup_arr(arr);
        while (step > 0){
            //insertion sorting
            for (int i = step; i < dup.length; i += step){
                int j = i - step;
                int key = dup[i];
                while (j >= 0 && dup[j] < key){
                    dup[j + step] = dup[j];
                    j -= step;
                }
                dup[j + step] = key;
            }
            step = step / 2;
        }
        return dup;
    }


    public void quick(int[] dup, int leftpoint, int rightpoint){
        if (leftpoint >= rightpoint){
            return;
        }
        int key = dup[rightpoint];
        int left = leftpoint;
        int right = rightpoint;
        while (true){
            while (left < right && dup[left] >= key){
                left++;
            }
            while (left < right && dup[right] <= key){
                right--;
            }
            int tmp = 0;
            if (left >= right){
                tmp = dup[right];
                dup[right] = key;
                dup[rightpoint] = tmp;
                break;
            }
            tmp = dup[right];
            dup[right] = dup[left];
            dup[left] = tmp;
        }
        this.quick(dup, leftpoint, left - 1);
        this.quick(dup, left + 1, rightpoint);
    }
   

    //min heap
    public int micro_adjust(int[] arr, int i, int last){
        if (2 * i + 1 > last){
            return i;
        }

        int max = arr[i] < arr[2 * i + 1] ? i : (2 * i + 1);
        if (2 * i + 2 > last){
            return max;
        }

        max = arr[max] < arr[2 * i + 2] ? max : (2 * i + 2);
        if (max != i){
            int tmp = arr[i];
            arr[i] = arr[max];
            arr[max] = tmp;
        }

        return max;
    }
    //part of heap sorting. build a min heap
    public void build_heap(int[] arr, int last){
        int mid = (last + 1) / 2 - 1;
        for (int i = mid; i >= 0; i--){
            int tmp = micro_adjust(arr, i, last);
            //System.out.print("i: " + i + " tmp: " + tmp + "arr: ");
            //this.print_arr(arr);
            int flag = i;
            while (tmp <= mid && tmp != flag){
                flag = tmp;
                tmp = micro_adjust(arr, tmp, last);
            }
        }
        return;
    }
    //part of heap sorting. adjust the left arr
    public void adjust_heap(int[] arr, int last){
        int tmp = arr[0];
        arr[0] = arr[last];
        arr[last] = tmp;
        this.build_heap(arr, last - 1);
        return;
    }
    //use min heap to sort arr from big to small
    public int[] heap(int[] arr){
        int dup[] = this.dup_arr(arr);
        this.build_heap(dup, dup.length - 1);
        for (int i = 0; i < dup.length; i++){
            //System.out.print("i: " + i + " arr: ");
            //this.print_arr(dup);
            adjust_heap(dup, dup.length - 1 - i);
        }
        return dup;
    }        


    public int[] counting(int[] arr){
        int min = arr[0];
        int max = arr[0];
        for (int i = 1; i < arr.length; i++){
            min = min < arr[i] ? min : arr[i];
            max = max > arr[i] ? max : arr[i];
        }

        int buckets[] = new int[max - min + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++){
            index = arr[i] - min;
            buckets[index]++;
        }

        int res[] = new int[arr.length];
        for (int i = 0; i < res.length; i++){
            res[i] = -1;
        }

        for (int i = 0; i < arr.length; i++){
            index = arr[i] - min;
            int cnt = 0;
            for (int j = 0; j < index; j++){
                cnt += buckets[j];
            }
            while (res[res.length - 1 - cnt] != -1){
                cnt++;
            }
            res[res.length - 1 - cnt] = arr[i];
        }
        return res;
    }

    
    public int[] radix(int[] arr){
        int dup[] = this.dup_arr(arr);

        LinkedList[] buckets = new LinkedList[10];
        for (int i = 0; i < 10; i++){
            buckets[i] = new LinkedList();
        }

        int max = dup[0];
        for (int i = 1; i < dup.length; i++){
            max = max > dup[i] ? max : dup[i];
        }
        
        int n = 10;
        while (max > 0){
            for (int i = 0; i < dup.length; i++){
                buckets[dup[i] % n].add(dup[i]);
            }
            int j = 0;
            for (int i = 9; i >= 0; i--){ 
                while (!buckets[i].isEmpty()){
                    dup[j++] = (int)buckets[i].getFirst();
                    buckets[i].removeFirst();
                }
            }
            max /= 10;
            n *= 10;
        }
        return dup;
    }
    

    //part of merge sorting: merge two arr together
    public int[] merge(int[] arr1, int[] arr2){
        int res[] = new int[arr1.length + arr2.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr1.length && j < arr2.length){
            if (arr1[i] > arr2[j]){
                res[k++] = arr1[i++];
            }else{
                res[k++] = arr2[j++];
            }
        }
        
        while (i < arr1.length){
            res[k++] = arr1[i++];
        }
        while (j < arr2.length){
            res[k++] = arr2[j++];
        }
        return res;
    }
    //the main part of merge_sort
    public int[] merge_sort(int[] arr, int start, int end){
        //System.out.println("start: " + start + " end: " + end);
        if (end - start < 1){
            //System.out.println("end == start");
            int tmp[] = {arr[start]};
            return tmp;
        }
        
        int flag = (end - start) / 2;
        int res1[] = merge_sort(arr, start, start + flag);
        //System.out.println("res1:");
        //this.print_arr(res1);
        int res2[] = merge_sort(arr, start + flag + 1, end);
        //System.out.println("res2:");
        //this.print_arr(res2);
        int res[] = merge(res1, res2);
        //this.print_arr(res);
        return res;
    }


    public static void main(String[] args){
        int arr[] = {6, 6, 7, 5, 3, 2, 4, 1, 8, 9, 0};
        Sort s = new Sort();
        System.out.print("The original array:        ");
        s.print_arr(arr);

        System.out.print("after bubble sorting:      ");
        s.print_arr(s.bubble(arr));

        System.out.print("after selection sorting:   ");
        s.print_arr(s.selection(arr));

        System.out.print("after insertion sorting:   ");
        s.print_arr(s.insertion(arr));

        System.out.print("after shell sorting:       ");
        s.print_arr(s.shell(arr, arr.length/2));

        System.out.print("after quick sorting:       ");
        int dup[] = s.dup_arr(arr);
        s.quick(dup, 0, dup.length - 1);
        s.print_arr(dup);

        System.out.print("after heap sorting:        ");
        s.print_arr(s.heap(arr));

        System.out.print("after counting sorting:    ");
        s.print_arr(s.counting(arr));
        
        System.out.print("after radix sorting:       ");
        s.print_arr(s.radix(arr));
        
        //System.out.print("after buckets sorting:       ");
        //s.print_arr(s.buckets(arr));

        System.out.print("after merge sorting:       ");
        dup = s.dup_arr(arr);
        s.print_arr(s.merge_sort(dup, 0, dup.length - 1));
    }
}
