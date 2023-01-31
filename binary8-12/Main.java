import java.util.*;

class Main {
    public static BinNode<Integer> fromString(StringTokenizer tokenizer) {
        if (!tokenizer.hasMoreElements())
            return null;
        String s = tokenizer.nextToken();
        if (s.equals("null"))
            return null;
        if (s.equals("(")) {
            BinNode<Integer> left = fromString(tokenizer);
            s = tokenizer.nextToken();
            Integer value = Integer.valueOf(s);
            BinNode<Integer> right = fromString(tokenizer);
            s = tokenizer.nextToken();
            if (!s.equals(")"))
                System.out.println("Note: missing ')'");
            return new BinNode<Integer>(left, value, right);
        }
        return new BinNode<Integer>(Integer.valueOf(s));
    }

    // ex 8 a
    public static void printNeg(BinNode<Integer> b) {
        if(b != null) {
            printNeg(b.getLeft());
            printNeg(b.getRight());
            if (b.getValue() < 0) {
                System.out.println(b.getValue());
            }
        }
    }

    // ex 8 b
    public static void printLeftOnly(BinNode<Integer> b, boolean isLeft) {
        if(b != null) {
            printLeftOnly(b.getLeft(), true);
            printLeftOnly(b.getRight(), false);
            if (isLeft) {
                System.out.println(b.getValue());
            }
        }
    }

    public static void printLeft(BinNode<Integer> b) {
        if(b != null) {
            printLeftOnly(b.getLeft(), true);
            printLeftOnly(b.getRight(), false);
        }
    }

    // ex 8 c

    public static void printPos(BinNode<Integer> b) {
        if(b != null) {
            if (b.getValue() > 0) {
                System.out.println(b.getValue());
            }
            printPos(b.getLeft());
            printPos(b.getRight());
        }
    }
    public static void printNegLeft(BinNode<Integer> b) {
        if (b != null) {
            if (b.getValue() < 0) {
                printLeft(b);
            } else {
                printPos(b);
            }
        }

    }

    // ex 9
    public static BinNode<Character> incCharTree(BinNode<Character> b) {
        if (b!= null && (b.hasLeft() || b.hasRight())) {
            char val = b.getValue();
            if (val == 'z') {
                val = 'a';
            } else {
                val++;
            }
            b.setValue(val);
            incCharTree(b.getRight());
            incCharTree(b.getLeft());
        }
        return b;
    }

    // ex 10
    public static <T>void printLeaves(BinNode<T> b) {
        if(b != null) {
            printLeaves(b.getLeft());
            printLeaves(b.getRight());
            if (!b.hasLeft() && !b.hasRight()) {
                System.out.println(b.getValue());
            }
        }
    }

    // ex 11
    public static void printEvenWithNoOddSon(BinNode<Integer> b) {
        if(b != null) {
            printEvenWithNoOddSon(b.getLeft());
            printEvenWithNoOddSon(b.getRight());
            if (b.getValue() % 2 == 0) {
                if ((!b.hasLeft() || Math.abs(b.getLeft().getValue() % 2) != 1) && (!b.hasRight() || Math.abs(b.getRight().getValue() % 2) != 1)) {
                    System.out.println(b.getValue());
                }
            }
        }
    }

    // ex 12
    public static int countNotTooBig(BinNode<Integer> b) {
        if(b != null) {
            int count = 0;
            count += countNotTooBig(b.getLeft());
            count += countNotTooBig(b.getRight());
            if (b.getValue() >= 10 && b.getValue() < 100) {
                count += 1;
            }
            return count;
        } else {
            return 0;
        }
    }

    // ex 13
    public static void similarToFather(BinNode<Integer> b) {
        if(b != null && (b.hasLeft() || b.hasRight())) {
            similarToFather(b.getLeft());
            similarToFather(b.getRight());
            if (!b.hasRight() || Math.abs(b.getValue() - b.getRight().getValue()) > Math.abs(b.getValue() - b.getLeft().getValue())) {
                System.out.println(b.getLeft().getValue());
            } else {
                System.out.println(b.getRight().getValue());
            }
        }
    }

    // ex 14
    public static <T>int countLeaves(BinNode<T> b) {
        if(b != null) {
            int count = 0;
            count += countLeaves(b.getLeft());
            count += countLeaves(b.getRight());
            if (!b.hasRight() && !b.hasLeft()) {
                count += 1;
            }
            return count;
        } else {
            return 0;
        }
    }

    // ex 15
    public static int countInRange(BinNode<Integer> b, int max, int min) {
        if(b != null && (b.hasLeft() || b.hasRight())) {
            int count = 0;
            count += countInRange(b.getLeft(), max, min);
            count += countInRange(b.getRight(), max, min);
            if (b.getValue() > min && b.getValue() < max) {
                count += 1;
            }
            return count;
        } else {
            return 0;
        }
    }

    // ex 16
    public static int sumOfFathers(BinNode<Integer> b) {
        int sum=0;
        if(b != null){
            sum+=sumOfFathers(b.getLeft());
            sum+=sumOfFathers(b.getRight());
            if(b.hasLeft()&&b.hasRight()){
                sum+=b.getValue();
            }
        }
        return sum;
    }

    // ex 17
    public static <T>int countGrandFathers(BinNode<T> b) {
        if(b != null && b.hasLeft() && b.hasRight()) {
            int count = 0;
            count += countGrandFathers(b.getLeft());
            count += countGrandFathers(b.getRight());
            BinNode<T> rightSon = b.getRight();
            BinNode<T> leftSon = b.getLeft();
            if ((rightSon.hasRight() || rightSon.hasLeft()) && (leftSon.hasRight() || leftSon.hasLeft())) {
                count += 1;
            }
            return count;
        } else {
            return 0;
        }
    }

    public static <T>boolean findNum(BinNode<T> b, T val) {
        if(b != null){
            if(b.getValue() == val){
                return true;
            }
            if(findNum(b.getLeft(), val)) {
                return true;
            }
            if (findNum(b.getRight(), val)) {
                return true;
            }
            return false;
        }
        return false;
    }

    // ex 18
    public static <T>boolean isContained(BinNode<T> b1, BinNode<T> b2) {
        if(b2 != null){
            if(findNum(b1, b2.getValue())){
                return isContained(b1, b2.getLeft()) && isContained(b1, b2.getRight());
            }
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        BinNode<Integer> t15 = new BinNode<Integer>(15);
        BinNode<Integer> t14 = new BinNode<Integer>(14);
        BinNode<Integer> tree = new BinNode<Integer>(t15, 3, t14);
        BinNode<Integer> t9 = new BinNode<Integer>(9);
        tree.getLeft().setLeft(new BinNode<Integer>(t9, 6, new BinNode<Integer>(7)));
        tree.getLeft().setRight(new BinNode<Integer>(new BinNode<Integer>(34), 56, new BinNode<Integer>(12)));
//        System.out.println(tree);

        StringTokenizer str = new StringTokenizer("( ( ( -9 15 -7 ) 1 null ) -3 ( 6 34 -4 ) )");
        BinNode<Integer> t = fromString(str);
        System.out.println(t);
        printTree(t);

        System.out.println("\n\nex8a: print neg");
        printNeg(t);

        System.out.println("\n\nex8b: print left");
        printLeft(t);

        System.out.println("\n\nex8c: print left if neg pos if pos");
        printNegLeft(t);

        BinNode<Character> ta = new BinNode<Character>('a');
        BinNode<Character> tb = new BinNode<Character>('b');
        BinNode<Character> treeChar = new BinNode<Character>(ta, 'c', tb);
        BinNode<Character> td = new BinNode<Character>('d');
        treeChar.getLeft().setLeft(new BinNode<Character>(td, 'e', new BinNode<Character>('f')));
        treeChar.getLeft().setRight(new BinNode<Character>(new BinNode<Character>('g'), 'h', new BinNode<Character>('i')));
        System.out.println("\n\nex9: increase tree characters");
        System.out.println("Original tree: " + treeChar);
        System.out.println("Increased tree: " + incCharTree(treeChar));

        System.out.println("\n\nex10: print leaves");
        printLeaves(t);

        System.out.println("\n\nex11: print even with no odd child");
        printEvenWithNoOddSon(t);

        System.out.println("\n\nex12: print numbers between 10 and 100");
        System.out.println(countNotTooBig(t));

        System.out.println("\n\nex13: print numbers most similar to father");
        similarToFather(t);

        System.out.println("\n\nex14: count leaves");
        System.out.println(countLeaves(t));

        System.out.println("\n\nex15: count in range");
        System.out.println(countInRange(t, 10, 0));

        System.out.println("\n\nex16: sum of fathers");
        System.out.println(sumOfFathers(t));

        System.out.println("\n\nex17: count grandfathers");
        System.out.println(countGrandFathers(t));

        System.out.println("\n\nex18: count grandfathers");
        StringTokenizer str2 = new StringTokenizer("( -9 15 -7 )");
        BinNode<Integer> t2 = fromString(str2);
        System.out.println("New tree: " + t2);
        System.out.println(isContained(t, t2));
    }
}
