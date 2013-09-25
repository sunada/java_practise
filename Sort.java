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
        int len = dup.length;
        int key = 0;
        int tmp = 0;
        for (int i = 0; i < len; i++){
            key = 0;
            for(int j = i+1; j < len; j++){
                while (dup[j] <= dup[key] && key < j){
                    key++;
                }
                if (key == j){
                    break;
                }
                tmp = dup[j];
                while(j > key){
                    dup[j] = dup[j-1];
                    j--;
                }
                dup[key] = tmp;
            }
        }
        return dup;
    }

    public int[] shell(int[] arr, int step){
        int dup[] = this.dup_arr(arr);
        int len = dup.length;
        int i = 0;
        int j = 0;
        int key = 0;
        int tmp = 0;
        int flag = 0;
        while (step > 0){
            //insertion sorting
            for (i = 0; i < step; i++){
                for (j = i + step; j < len; j += step){
                    key = i;
                    while (dup[j] <= dup[key] && key < j){
                        key += step;
                    }
                    if (key == j){
                        if (dup[key - step] >= dup[j]){
                            continue;
                        }
                    }

                    tmp = dup[j];
                    flag = j;
                    while (j > key){
                        dup[j] = dup[j - step];
                        j -= step;
                    }
                    dup[key] = tmp;
                    j = flag;
                }
            }
            step = step / 2;
            this.shell(dup, step);
        }
        return dup;
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
    }
}
