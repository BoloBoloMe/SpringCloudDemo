import com.bolo.demo.ProviderApplication_8081_hystrix;
import com.bolo.demo.dao.entitys.Fruit;
import com.bolo.demo.dao.mapper.FruitMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * mapper单元测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProviderApplication_8081_hystrix.class)
public class MapperUnitTest {
    @Autowired
    private FruitMapper fruitMapper;

    /**
     * 测试新增水果方法
     */
    @Test
    public void testInsFruit() {
        Fruit fruit = new Fruit();
        fruit.setId(0);
        fruit.setSciName("菠萝");
        fruit.setType("热带水果");
        int row = fruitMapper.insert(fruit);
        System.out.println(row);
    }

    @Test
    public void testGetById() {
        Fruit fruit = fruitMapper.getById(0);
        System.out.println(fruit);
    }

    @Test
    public void testGetByExample() {
        Fruit example = new Fruit();
        example.setSciName("菠萝");
        example.setType("热带水果");
        List<Fruit> fruit = fruitMapper.getByExample(example);
        System.out.println(fruit);
    }
}
