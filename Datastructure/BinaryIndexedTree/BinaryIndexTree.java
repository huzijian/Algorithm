public class BinaryIndexedTree {
    int[] nums;
    int[] record;
    public static void main(String[] args) {
        
    }
    public void build() {
        record = new int[nums.length+1];
        for(int i=0;i<nums.length;i++){
            int val = nums[i];
            int index = i+1;
            while(index<record.length){
                record[index] += val;
                index += (index & -index);
            }
        }
    }

    void update(int i, int val) {
        int value = val-nums[i];
        int index = i+1;
        while(index<record.length){
            record[index] += value;
            index += (index & -index);
        }
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        return get(j)-get(i-1);
    }
    
    public int get(int i){
        int sum = 0;
        int index = i+1;
        while(index>0){
            sum += record[index];
            index -= (index & -index);
        }
        return sum;
    }  
}
