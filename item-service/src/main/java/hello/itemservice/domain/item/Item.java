package hello.itemservice.domain.item;

import lombok.Data;

// 핵심 데이터 모델에서 사용하기에는 위험함! @Getter나 @Setter 정도가 적당하다. 우리는 예제니까 괜찮다.
@Data
public class Item {

    private Long id;
    private String itemName;
    // Integer를 사용하는 이유는, null이 들어갈 가능성이 있기 때문이다. int를 사용하면 0이라도 넣어야 한다.
    private Integer price;
    private Integer quantity;

    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
