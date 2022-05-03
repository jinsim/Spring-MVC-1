package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 안에 @Conponent가 들어있으므로 컴포넌트 스캔의 대상이 된다.
@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // static
    private static long sequence = 0L; // static
    // 스프링 컨테이너 안에서 사용하면 어차피 싱글톤이 보장되므로 사용하지 않아도 되지만,
    // 따로 new 해서 사용하는 경우에는 static을 붙이지 않으면 객체 생성할 때마다 따로 분리된다.

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        // store.values()를 그대로 반환해도 되지만, ArrayList로 한번 감싸서 반환하게 되면
        // ArrayList에 변화가 생겨도 store에는 영향을 주지 않는다.
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateParam) {
        // 원래는 ItemParamDto라는 별도의 객체를 만들어서 사용하는 필드만 넣어두는 것이 더 명확하다.
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() {
        // 테스트에서만 사용함.
        store.clear();
    }
}
