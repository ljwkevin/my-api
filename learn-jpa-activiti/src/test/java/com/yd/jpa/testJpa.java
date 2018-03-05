package com.yd.jpa;

import com.yd.jpa.dao.CategoryDao;
import com.yd.jpa.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = "classpath:spring.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class testJpa {

    int id=5;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void testSave(){
        Category category=new Category();
//        category.setId(id);
        category.setName("test12345");
        categoryDao.save(category);
        System.out.println("----------\n"+category.getId());
    }

    @Test
    public void testUpdateName(){
        String name="test111";
        categoryDao.updateName(id,name);
    }

    @Test
    public void testFindById(){
        Category category=categoryDao.findOne(id);
        System.out.println(category);
    }

    @Test
    public void count(){
        long count=categoryDao.count();
        System.out.println(count);
    }

    @Test
    public void testFindAll(){
        Iterable<Category> categories=categoryDao.findAll();
        for(Category category:categories){
            System.out.println(category);
        }
    }

    @Test
    public void delete(){
        categoryDao.delete(id);
    }

}