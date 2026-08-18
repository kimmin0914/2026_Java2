public class Main {
    public static void main(String[] args) {
        //TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
        // 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP <shortcut actionId="Debug"/>을(를) 눌러 코드를 <b>디버그</b>하세요. 기본 설정으로 하나의 <icon src="AllIcons.Debugger.Db_set_breakpoint"/> 중단점이
            // 설정되어 있습니다. <shortcut actionId="ToggleLineBreakpoint"/>을(를) 눌러 언제든지 중단점을 더 추가할 수 있습니다.
            System.out.println("i = " + i);
        }
    }
}