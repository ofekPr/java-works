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

        Queue<Integer> q4 = new Queue<>();
        for (int i = 0; i < 5; i++) {
            int x = (int) (Math.random() * 5);
            q4.insert(x);
        }
        sortQueue(q4);
        System.out.println("Sorted q4: " + q4);
        System.out.println("Merged sorted q3 q4: " + mergeQueue(q3, q4));
        q1.insert('$');
        q1.insert('a');
        q1.insert('*');
        System.out.println("q1 = " + q1);
        System.out.println("remove symbols in q1 = " + removeSymbols(q1));
        System.out.println("q2 = " + q2);
        System.out.println("sorted q2 = " + sortStringQueue(q2));
        q4 = new Queue<>();
        for (int i = 0; i < 20; i++) {
            int x = (int) (Math.random() * 100);
            q4.insert(x);
        }
        System.out.println("q4 = " + q4);
        System.out.println("Sum of longest even strike: " + sumOfLongestEvenStrike(q4));
        System.out.println("Distance of 40 from the end: " + distanceFromEnd(q4, 40));
        System.out.println("q4 only prime: " + removeNotPrime(q4));
    }

    // ex 4
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

    // ex 5
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

    // ex 6
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

    // ex 7
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

    // ex 9
    public static Queue<Integer> mergeQueue(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> sortedQ = new Queue<>();
        while(!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.head() < q2.head()) {
                sortedQ.insert(q1.remove());
            } else {
                sortedQ.insert(q2.remove());
            }
        }
        while (!q1.isEmpty()) {
            sortedQ.insert(q1.remove());
        }
        while(!q2.isEmpty()) {
            sortedQ.insert(q2.remove());
        }
        return sortedQ;
    }

    // ex 10
    public static Queue<Character> removeSymbols(Queue<Character> q) {
        q.insert(null);
        while (!q.isEmpty() && q.head() != null) {
            if ((q.head() >= 'A' && q.head() <= 'Z') || (q.head() >= 'a' && q.head() <= 'z') || (q.head() >= '1' && q.head() <= '9')) {
                q.insert(q.remove());
            } else {
                q.remove();
            }
        }
        q.remove();
        return q;
    }

    // ex 11
    public static Queue<String> sortStringQueue(Queue<String> q) {
        if (q == null || q.isEmpty()) {
            return null;
        }

        Queue<String> queue1 = new Queue<>();
        Queue<String> queue2 = new Queue<>();

        while (!q.isEmpty()) {
            String min = q.head();

            while (!q.isEmpty()) {
                String current = q.remove();
                if (current.compareTo(min) == -1) {
                    min = current;
                }
                queue1.insert(current);
            }

            while (!queue1.isEmpty()) {
                String current = queue1.remove();

                if (current.equals(min)) {
                    queue2.insert(current);
                } else {
                    q.insert(current);
                }
            }
        }
        return queue2;
    }

    // ex 12
    public static Queue<Integer> removeNotPrime(Queue<Integer> q) {
        q.insert(null);
        while (!q.isEmpty() && q.head() != null) {
            if (isPrime(q.head())) {
                q.insert(q.remove());
            } else {
                q.remove();
            }
        }
        q.remove();
        return q;
    }

    // ex 13
    public static int sumOfLongestEvenStrike(Queue<Integer> q) {
        q.insert(null);
        int sum = 0, maxSum = 0;
        while(!q.isEmpty() && q.head() != null) {
            if (q.head() % 2 == 0) {
                q.insert(q.head());
                sum += q.remove();
            } else {
                maxSum = Math.max(sum, maxSum);
                sum = 0;
                q.insert(q.remove());
            }
        }
        q.remove();
        maxSum = Math.max(sum, maxSum);
        return maxSum;
    }

    // ex 14
    public static int distanceFromEnd(Queue<Integer> q, int num) {
        q.insert(null);
        int distance = -1;
        boolean hasFound = false;
        while(!q.isEmpty() && q.head() != null) {
            if (hasFound) {
                distance ++;
            } else if (q.head() == num) {
                hasFound = true;
                if (distance == -1) {
                    distance = 0;
                }
            }
            q.insert(q.remove());
        }
        q.remove();
        return distance;
    }

    // ex 16
    public static Queue<Integer> sortQueuesInFirst(Queue<Integer> q1, Queue<Integer> q2) {
        Queue<Integer> sortedQ = new Queue<>();
        while(!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.head() < q2.head()) {
                sortedQ.insert(q1.remove());
            } else {
                sortedQ.insert(q2.remove());
            }
        }
        while (!q1.isEmpty()) {
            sortedQ.insert(q1.remove());
        }
        while(!q2.isEmpty()) {
            sortedQ.insert(q2.remove());
        }
        while (!sortedQ.isEmpty()) {
            q1.insert(sortedQ.remove());
        }
        return q1;
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
