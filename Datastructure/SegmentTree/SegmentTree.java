
public class SegmentTree {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = new int[]{-1, 2,4,0};
        int len = (int)Math.pow(2,Math.ceil(Math.log((double)input.length) / Math.log(2.0))) * 2 - 1;
        int[] tree = new int[len];
        construct(input, tree, 0, input.length - 1, 0);
        //System.out.println(findmin(tree, 1, 3, 0, input.length - 1, 0));
        update(tree, 0, input.length - 1, 2, -4, 0);
        for(int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + ",");
        }
    }
    
    public static void construct(int[] input, int[] tree, int left, int right, int pos) {
        if(left == right) {
            tree[pos] = input[left];
            return;
        }
        int mid = left + (right - left) / 2;
        //left child
        construct(input, tree, left, mid, 2 * pos + 1);
        //right child
        construct(input, tree, mid + 1, right, 2 * pos + 2);
        tree[pos] = Math.min(tree[2 * pos + 1], tree[2 * pos + 2]);
    }
    
    public static int findmin(int[] tree,int qlow,int qhigh,int left,int right,int pos) {
        //total cover
        if(qlow <= left && qhigh >= right) {
            return tree[pos];
        }
        //not cover
        if(qlow > right || qhigh < left) {
            return Integer.MAX_VALUE;
        }
        //partial cover
        int mid = left + (right - left) / 2;
        return Math.min(findmin(tree, qlow, qhigh, left, mid, 2 * pos + 1), 
                        findmin(tree, qlow, qhigh, mid + 1, right, 2 * pos + 2));
    }
    
    
    public static void update(int[] tree,int left,int right,int index,int value,int pos) {
        if(left == right) {
            tree[pos] = value;
            return;
        }
        int mid = left + (right - left) / 2;
        if(index <= mid) {
            update(tree, left, mid, index, value, 2 * pos + 1);
        } else {
            update(tree, mid + 1, right, index, value, 2 * pos + 2);
        }
        tree[pos] = Math.min(tree[2 * pos + 1], tree[2 * pos + 2]);
    }
}
