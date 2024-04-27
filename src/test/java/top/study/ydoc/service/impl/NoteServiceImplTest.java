package top.study.ydoc.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import top.study.ydoc.mapper.NoteMapper;
import top.study.ydoc.mapper.UserMapper;
import top.study.ydoc.pojo.entity.Note;
import top.study.ydoc.pojo.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tjy
 * @date 2024/4/27
 * @apiNote
 */
@SpringBootTest
class NoteServiceImplTest {

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private UserMapper userMapper;

    @Test
    void queryPage() {
        List<Note> notes = noteMapper.selectList(null);
        System.out.println(notes);

        List<User> users = userMapper.selectList(null);
        System.out.println(users);


    }
}