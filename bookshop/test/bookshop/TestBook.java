package bookshop;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lxhf.bean.Book;
import com.lxhf.mapper.BookMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:applicationContext.xml"})
public class TestBook {
	@SuppressWarnings("unused")
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入BookDao
    @Autowired
    private BookMapper bookMapper;
    //查询全部数据测试
    @Test
    public void queryAll() {
        List<Book> books = bookMapper.findBookAll();
        System.out.println(books);
    }

}
