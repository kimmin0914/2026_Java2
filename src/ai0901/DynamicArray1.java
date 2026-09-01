package ai0901;

public class DynamicArray1 {
    static void main() {
        int[][] arr = new int[3][];
        int [] colLength = {1, 2, 3};
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new int[colLength[i]];
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = ++count * 10;
            }
        }

        System.out.println("[동적배열에 저장된 초기값 출력]");
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%d", arr[i][j]);
            }
            System.out.println();
        }
    }
}
