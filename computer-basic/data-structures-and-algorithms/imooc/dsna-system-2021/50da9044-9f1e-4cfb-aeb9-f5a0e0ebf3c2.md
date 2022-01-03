# 【映射】映射总结

<br/>

## 1、时间复杂度

<br/>

- 参见 [【数据结构—集合】集合总结](https://yyscyber.github.io/computer-basic/data-structures-and-algorithms/imooc/dsna-system-2021/5b8df638-84ad-4c6a-9829-a12f975c56c7)

---

## 2、其他的问题

<br/>

### 2.1、Java 类库中的集合与映射

- Java 类库中常用的集合类有`java.util.HashSet`、`java.util.TreeSet`，常用的映射类有`java.util.HashMap`、`java.util.TreeMap`。
- `java.util.HashSet`、`java.util.HashMap`的底层实现使用的是哈希表。`java.util.TreeSet`、`java.util.TreeMap`的底层实现使用的是平衡二分搜索树。

---

### 2.2、集合与映射的关系

- 映射是“键值对”的集合。
- 既可以通过集合来实现映射，也可以通过映射来实现集合。
- 理论上，完全可以通过集合来实现映射。更多的时候，是通过映射来实现集合，因为只需保留每个“键值对”中的“键”就能把映射变为集合。



```java
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    V get(K key);

    void set(K key, V value);

    boolean contains(K key);

    int size();

    boolean isEmpty();

}
```



```java
public class BinarySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        K key;

        V value;

        Node leftChild;

        Node rightChild;

        public Node() {
            this(null, null, null, null);
        }

        public Node(K key, V value) {
            this(key, value, null, null);
        }

        public Node(K key, V value, Node leftChild, Node rightChild) {
            this.key = key;
            this.value = value;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }
    }

    private Node root;

    private int size;

    public BinarySearchTreeMap() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        if (root == null) {
            root = new Node(key, value);
        } else {
            Node pre = null;
            Node cur = root;

            while (cur != null) {
                pre = cur;
                if (key.compareTo(cur.key) < 0) {
                    cur = cur.leftChild;
                } else if (key.compareTo(cur.key) > 0) {
                    cur = cur.rightChild;
                } else {
                    break;
                }
            }

            if (cur == null) {
                Node newNode = new Node(key, value);
                if (key.compareTo(pre.key) < 0) {
                    pre.leftChild = newNode;
                } else {
                    pre.rightChild = newNode;
                }
                size++;
            }
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        if (root == null) {
            return null;
        }

        Node cur1 = root;
        Node pre1 = null;
        Node cur2 = null;
        Node pre2 = null;

        while (cur1 != null) {
            if (key.compareTo(cur1.key) < 0) {
                pre1 = cur1;
                cur1 = cur1.leftChild;
            } else if (key.compareTo(cur1.key) > 0) {
                pre1 = cur1;
                cur1 = cur1.rightChild;
            } else {
                break;
            }
        }

        // 1、Key 不存在
        if (cur1 == null) {
            return null;
        }

        // 2、Key 存在

        // 2.1、pre1 == null：要删的节点为整棵树的根节点
        if (pre1 == null) {
            // 2.1.1、根节点的左右子树为空，即整棵树只有根节点
            V val = root.value;

            if (root.leftChild == null && root.rightChild == null) {
                root = null;
                size--;
                return val;
            } else {
                // 2.1.2、根节点的左子树为空，右子树不为空
                if (root.leftChild == null) {
                    Node temp = root.rightChild;
                    root.rightChild = null;
                    root = temp;
                    size--;
                    return val;
                }

                // 2.1.3、根节点的右子树为空，左子树不为空
                if (root.rightChild == null) {
                    Node temp = root.leftChild;
                    root.leftChild = null;
                    root = temp;
                    size--;
                    return val;
                }

                // 2.1.4、根节点左、右子树均不为空
                cur2 = root.leftChild;
                pre2 = root;
                while (cur2.rightChild != null) {
                    pre2 = cur2;
                    cur2 = cur2.rightChild;
                }

                root.key = cur2.key;
                root.value = cur2.value;

                Node temp = cur2.leftChild;
                cur2.leftChild = null;

                // 2.1.4.1、所使用替换值（左子树中最大值）所在的节点的父节点 为 待删除根节点
                if (pre2 == root) {
                    pre2.leftChild = temp;
                } else {
                    // 2.1.4.2、所使用替换值（左子树中最大值）所在的节点的父节点 不是 待删除根节点
                    pre2.rightChild = temp;
                }
                size--;
                return val;
            }
        } else {
            // 2.2、pre1 != null：要删的节点为非根节点

            V val = cur1.value;

            // 2.2.1、待删节点的左右子树为空，即叶子节点
            if (cur1.leftChild == null && cur1.rightChild == null) {
                // 2.2.1.1、待删节点比父节点小
                if (cur1.key.compareTo(pre1.key) < 0) {
                    pre1.leftChild = null;
                } else {
                    // 2.2.1.2、待删节点比父节点大
                    pre1.rightChild = null;
                }
                size--;
                return val;
            } else {
                // 2.2.2、待删节点的左子树为空，右子树不为空
                if (cur1.leftChild == null) {
                    Node temp = cur1.rightChild;
                    cur1.rightChild = null;
                    // 2.2.2.1、待删节点比父节点小
                    if (cur1.key.compareTo(pre1.key) < 0) {
                        pre1.leftChild = temp;
                    } else {
                        // 2.2.2.2、待删节点比父节点大
                        pre1.rightChild = temp;
                    }
                    size--;
                    return val;
                }

                // 2.2.3、待删节点的右子树为空，左子树不为空
                if (cur1.rightChild == null) {
                    Node temp = cur1.leftChild;
                    cur1.leftChild = null;
                    // 2.2.3.1、待删节点比父节点小
                    if (cur1.key.compareTo(pre1.key) < 0) {
                        pre1.leftChild = temp;
                    } else {
                        // 2.2.3.2、待删节点比父节点大
                        pre1.rightChild = temp;
                    }
                    size--;
                    return val;
                }

                // 2.2.4、待删节点的左、右子树均不为空
                cur2 = cur1.leftChild;
                pre2 = cur1;
                while (cur2.rightChild != null) {
                    pre2 = cur2;
                    cur2 = cur2.rightChild;
                }

                cur1.key = cur2.key;
                cur1.value = cur2.value;

                Node temp = cur2.leftChild;
                cur2.leftChild = null;
                // 2.2.4.1、所使用替换值（左子树中最大值）所在的节点的父节点 是 待删除节点
                if (pre2 == cur1) {
                    pre2.leftChild = temp;
                } else {
                    // 2.2.4.2、所使用替换值（左子树中最大值）所在的节点的父节点 不是 待删除节点
                    pre2.rightChild = temp;
                }
                size--;
                return val;
            }
        }

    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        Node node = getNode(key);

        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        Node node = getNode(key);
        if (node == null) {
            throw new IllegalArgumentException(key + ", Key not exist!");
        }
        node.value = newValue;
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key is null!");
        }

        return getNode(key) != null;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    private Node getNode(K key) {
        Node cur = root;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                return cur;
            } else if (key.compareTo(cur.key) < 0) {
                cur = cur.leftChild;
            } else {
                cur = cur.rightChild;
            }
        }
        return null;
    }

}
```



```java
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int size();

    boolean isEmpty();

}
```



```java
public class BinarySearchTreeSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTreeMap<E, Object> map;

    public BinarySearchTreeSet() {
        map = new BinarySearchTreeMap<>();
    }

    @Override
    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Value is null!");
        }

        map.add(e, null);
    }

    @Override
    public void remove(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Value is null!");
        }

        map.remove(e);
    }

    @Override
    public boolean contains(E e) {
        if (e == null) {
            throw new IllegalArgumentException("Value is null!");
        }

        return map.contains(e);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

}
```



---

### 2.3、关于 Null

- 一般情况下，底层使用二分搜索树或者在其基础上的数据结构实现的集合、映射，集合中的元素、映射中的`Key`，不允许`null`值。

---

### 2.4、有序映射与无序映射

- 见  [【数据结构—集合】集合总结](https://yyscyber.github.io/computer-basic/data-structures-and-algorithms/imooc/dsna-system-2021/5b8df638-84ad-4c6a-9829-a12f975c56c7) 中的 “2.1、有序集合、无序集合”。
- 有序集合、映射的常见底层实现是搜索树（二分搜索树等）等；无序集合、映射的常见底层实现是哈希表、链表等。