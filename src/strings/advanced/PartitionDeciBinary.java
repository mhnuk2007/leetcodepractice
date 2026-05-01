public class PartitionDeciBinary {
    public int minPartitions(String n) {
        int max = 0;
        for (int i = 0; i < n.length(); i++) {
            int digit = n.charAt(i) - '0';
            if (digit > max) {
                max = digit;
            }
            if (max == 9) {
                return max;
            }

        }

        return max;
    }
    public static void main(String[] args) {
        PartitionDeciBinary obj = new PartitionDeciBinary();
        System.out.println(obj.minPartitions("47"));
        System.out.println(obj.minPartitions("82734"));
        System.out.println(obj.minPartitions("27346209830709182346"));

    }
}
