//this is for range update and single query
public class BinaryIndexTreeRangeUpdate {
    static int[] record;
    static int[] num;
    public static void main(String[] args) {
        record = new int[11];
        num = new int[]{1,2,3,4,5,6,7,8,9,10};
        update(0, 1, 1);
        //update(3, 4, 1);
        for(int i = 0; i <= 9; i++) {
            System.out.print(get(i) + ",");
        }
    }
    
    public static void update(int i, int val) {
        int index = i+1;
        while(index<record.length){
            record[index] += val;
            index += (index & -index);
        }
    }
    
    public static void update(int i, int j, int val) {
        update(i, val);
        update(j + 1, -val);
    }
    
    public static int get(int i){
        int sum = 0;
        int index = i+1;
        while(index>0){
            sum += record[index];
            index -= (index & -index);
        }
        return sum + num[i];
    }
}
