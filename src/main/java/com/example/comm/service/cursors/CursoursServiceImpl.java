package com.example.comm.service.cursors;

import com.example.comm.dao.cursors.CursorsMapper;
import com.example.comm.pojo.cursors.Cursors;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CursoursServiceImpl implements CursoursService {

    @Resource
    private CursorsMapper cursorsMapper;

    @Override
    public Cursors getCursorsBybarcode(String c_order) throws Exception {
        return cursorsMapper.getCursorsBybarcode(c_order);
    }
}
