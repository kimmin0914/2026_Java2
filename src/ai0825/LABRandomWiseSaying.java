package ai0825;

import java.util.Random;

public class LABRandomWiseSaying {
    static void main() {
        String[] wiseSay = {"바람이 불지 않으면 노를 저어라. - 윈스턴 처칠",
                "왕관을 쓰려는 자, 그 무게를 견뎌라. - 윌리엄 셰익스피어",
                "길이 없는 곳으로 가 발자국을 남겨라. - 랄프 왈도 에머슨",
                "괴물과 싸우는 자는 스스로 괴물이 되지 않도록 조심해야 한다. - 프리드리히 니체",
                "승리하면 조금 배울 수 있지만, 패배하면 모든 것을 배울 수 있다. - 크리스티 매튜슨",
                "스스로를 통제하지 못하는 자는 영원히 자유로울 수 없다. - 피타고라스",
                "바다를 건너려면 시야에서 해안선을 놓칠 용기가 필요하다. - 앙드레 지드",
                "당신이 원하는 모든 것은 두려움의 반대편에 있다. - 조지 아데어",
                "탁월함은 행동이 아니라 습관이다. - 아리스토텔레스",
                "모두에게 맞추려다가는 결국 아무것도 아닌 존재가 된다. - 스티브 잡스"};

        Random random = new Random();
        int randomIndex = random.nextInt(wiseSay.length);
        System.out.println("오늘의 명언: " + wiseSay[randomIndex]);


    }
}
