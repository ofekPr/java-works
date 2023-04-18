class Main {
    public static void main(String[] args) {
        Queue<Character> q1 = new Queue<>();
        System.out.println("q1 = " + q1);
        for (int i = 0; i < 5; i++) {
            char x = (char) (Math.random() * 10 + 97);
            q1.insert(x);
        }
        System.out.println("q1 = " + q1);
        System.out.println("Sequences lengths: " + countSequence(q1));
        Queue<String> q2 = new Queue<>();
        q2.insert("a");
        q2.insert("b");
        q2.insert("c");
        q2.insert("a");
        System.out.println("q2 = " + q2);
        System.out.println("Are there multiplications: " + areThereMulitple(q2));
        Queue<Integer> q3 = new Queue<>();
        System.out.println("q1 = " + q1);
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 5);
            q3.insert(x);
        }
        System.out.println("q3 = " + q3);
        System.out.println("Remove multiplications: " + removeMultiple(q3));
        sortQueue(q3);
        System.out.println("Sorted q3: " + q3);
    }

    public static Queue<Integer> countSequence(Queue<Character> q) {
        Queue<Integer> sequenceLengths = new Queue<>();
        int count = 1;
        char prev = q.remove();

        while (!q.isEmpty()) {
            char curr = q.remove();

            if (curr == prev) {
                count++;
            } else {
                sequenceLengths.insert(count);
                count = 1;
            }

            prev = curr;
        }

        sequenceLengths.insert(count);
        return sequenceLengths;
    }

    public static boolean areThereMulitple(Queue<String> q) {
        Queue<String> q2 = q;
        while(!q2.isEmpty()) {
            String head = q2.head();
            q2.remove();
            if (isIn(q2, head)) {
                return true;
            }
        }
        return false;
    }

    public static Queue<Integer> removeMultiple(Queue<Integer> q) {
        Queue<Integer> q2 = q, returnQ = new Queue<>();
        while(!q2.isEmpty()) {
            Integer head = q2.head();
            q2.remove();
            if (!isIn(q2, head)) {
                returnQ.insert(head);
            }
        }

        while(!returnQ.isEmpty()) {
            q.insert(returnQ.remove());
        }

        return q;
    }

    public static void sortQueue(Queue<Integer> q) {
        if (q == null || q.isEmpty()) {
            return;
        }

        Queue<Integer> queue1 = new Queue<>();
        Queue<Integer> queue2 = new Queue<>();

        while (!q.isEmpty()) {
            int min = q.head();

            while (!q.isEmpty()) {
                int current = q.remove();
                min = Math.min(current, min);
                queue1.insert(current);
            }

            while (!queue1.isEmpty()) {
                int current = queue1.remove();

                if (current == min) {
                    queue2.insert(current);
                } else {
                    q.insert(current);
                }
            }
        }

        while (!queue2.isEmpty()) {
            q.insert(queue2.remove());
        }
    }

    // האם זה ראשוני
    public static boolean isPrime(int num) {
        for (int i=2; i < num/2; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    public static boolean firstIsEven(Queue<Integer> q) {
        return (q.head() % 2 == 0);
    }

    public static int howMany(Queue<Integer> q) {

        int count = 0;
    /*
    אורך הקלט הוא מספר האיברים בתור, שנסמן באות 
    n
    */

        // create queue for backup
        Queue<Integer> q2 = new Queue<Integer>();

        while (!q.isEmpty()) {
            int a = q.remove();
            q2.insert(a);  // save in backup
            count++;
        }
        // restore q from backup
        while (!q2.isEmpty()) {
            q.insert(q2.remove());
        }
        return count;
    }


    public static boolean isIn(Queue<String> q, String str) {

        boolean ret = false;

        // create queue for backup
        Queue<String> q2 = new Queue<>();

        while (!q.isEmpty()) {
            String a = q.remove();
            q2.insert(a);  // save in backup
            if (a.equals(str)) {
                ret = true;
            }
        }
        // restore q from backup
        while (!q2.isEmpty()) {
            q.insert(q2.remove());
        }
        return ret;
    }

    public static boolean isIn(Queue<Integer> q, int num) {

        boolean ret = false;

        // create queue for backup
        Queue<Integer> q2 = new Queue<>();

        while (!q.isEmpty()) {
            int a = q.remove();
            q2.insert(a);  // save in backup
            if (a == num) {
                ret = true;
            }
        }
        // restore q from backup
        while (!q2.isEmpty()) {
            q.insert(q2.remove());
        }
        return ret;
    }
}
