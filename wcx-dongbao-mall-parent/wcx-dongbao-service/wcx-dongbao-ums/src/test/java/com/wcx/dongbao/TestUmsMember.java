package com.wcx.dongbao;

import com.wcx.dongbao.dao.UmsMemberMapper;
import com.wcx.dongbao.ums.entity.UmsMember;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestUmsMember {
    @Resource
    UmsMemberMapper umsMemberMapper;
    @Test
    public void TestInsert(){
        UmsMember umsMember = new UmsMember();
        umsMember.setId(66L);
        umsMember.setNickName("wcx");
        umsMember.setPassword("1234569");
        umsMemberMapper.insert(umsMember);
    }

    @Test
    public void TestUpdate(){
        UmsMember umsMember = new UmsMember();
        umsMember.setId(64L);
        umsMember.setNickName("yyy");
        umsMember.setPassword("999999");
        umsMemberMapper.updateById(umsMember);
    }
}
