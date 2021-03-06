/**
 * algorithm 5.4: trie symbol table
 *
 * each node has an array of references to R other 
 * nodes that indirectly correspond to each character
 * of the alphabet; in these nodes we also keep a 
 * value, which represents the value associated with
 * the key corresponding to that node.
 *
 * doesn't use the Key generic because the keys are
 * going to be Strings
 *
 * @author bleart
 */
public class TrieSymbolTable<Value> {

    private static final int R = 256;
    private Node root;

    private static class Node {
        private Object val; 
        private Node[] next = new Node[R];
    }
    
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = val;
            return x;
        }

        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d+1);
        return x;
    }

    public Value get(String key) {
        Node x = get(root, key, 0);
        if (x == null) return null;
        return (Value) x.val;
    }

    public Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d); 
        return get(x.next[c], key, d+1); 
    }

    public static void main(String[] args) {
        TrieSymbolTable<Integer> st = new TrieSymbolTable<>();
        st.put("unix", 2);
        st.put("sun", 2);
        System.out.println(st.get("sun"));
    }
}
