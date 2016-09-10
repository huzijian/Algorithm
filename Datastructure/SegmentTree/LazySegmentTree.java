
public class LazySegmentTree {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] input = new int[]{-1, 2,4,0};
        int len = (int)Math.pow(2,Math.ceil(Math.log((double)input.length) / Math.log(2.0))) * 2 - 1;
        int[] tree = new int[len];
        construct(input, tree, 0, input.length - 1, 0);
        int[] lazy = new int[len];
        update(tree, lazy, 0, 3, 3, 0, 3, 0);
        for(int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + ",");
        }
        System.out.println();
        System.out.println(findmin(lazy, tree, 2, 3, 0, 3, 0));
        for(int i = 0; i < tree.length; i++) {
            System.out.print(tree[i] + ",");
        }        
        System.out.println();
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
    
    public static void update(int[] tree,int[] lazy,int ul,int ur,int value,int left,int right,int pos) {
        if(lazy[pos] != 0) {
            tree[pos] += lazy[pos];
            if(left != right) {
                lazy[2 * pos + 1] += lazy[pos];
                lazy[2 * pos + 2] += lazy[pos];
                lazy[pos] = 0;
            }
        }
        //no cover
        if(ul > right || ur < left) {
            return;
        }
        
        if(ul <= left && right >= ur) {
            tree[pos] += value;
            if(left != right) {
                lazy[2 * pos + 1] += value;
                lazy[2 * pos + 2] += value;
                lazy[pos] = 0;
            }
            return;
        }
        int mid = left + (right - left) / 2;
        update(tree, lazy, ul, ur, value, left, mid, 2 * pos + 1);
        update(tree, lazy, ul, ur, value, mid + 1, right, 2 * pos + 2);
        tree[pos] = Math.min(tree[2 * pos + 1], tree[2 * pos + 2]);
    }
    
    public static int findmin(int[] lazy,int[] tree,int qlow,int qhigh,int left,int right,int pos) {
        //total cover
        if(lazy[pos] != 0) {
            tree[pos] += lazy[pos];
            if(left != right) {
                tree[2 * pos + 1] += lazy[pos];
                tree[2 * pos + 2] += lazy[pos];
            }
        }
        if(qlow <= left && qhigh >= right) {
            return tree[pos];
        }
        //not cover
        if(qlow > right || qhigh < left) {
            return Integer.MAX_VALUE;
        }
        //partial cover
        int mid = left + (right - left) / 2;
        return Math.min(findmin(lazy, tree, qlow, qhigh, left, mid, 2 * pos + 1), 
                        findmin(lazy, tree, qlow, qhigh, mid + 1, right, 2 * pos + 2));
    }
    
}
