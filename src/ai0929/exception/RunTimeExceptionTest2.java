package ai0929.exception;

import java.util.Random;

public class RunTimeExceptionTest2 {
    static void main() {
        int[] results = {10, 20, 30};

        Random random = null;
//      try: 예외 발생 가능성이 있는 문장
//      catch: 예외가 발생했을 때 처리할 문장
//      하위 클래스 => 상위 예외 클래스 순으로 catch절을 배치한다.


        try{
            System.out.println(results[2]);
            int randomNum = random.nextInt(5);
            results[1] = 600 / 0;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("배열의 인덱스 번호가 범위를 벗어났습니다.");
        } catch (ArithmeticException e) {
            System.out.println("나눗셈 연산식에서 나누는 수는 0이면 안 됩니다.");
        } catch (NullPointerException e){
            System.out.println("참조할 객체가 존재하지 않습니다.");
        } catch (Exception e){
            System.out.println("예외가 발생했습니다.");
        }
    }
}
