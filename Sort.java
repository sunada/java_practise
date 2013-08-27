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
    }
}
