package com.example.comm.service.cursors;

import com.example.comm.dao.cursors.CursorsMapper;
import com.example.comm.pojo.cursors.Cursors;
import com.example.comm.pojo.customer.Customer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CursoursServiceImpl implements CursoursService {

    @Resource
    private CursorsMapper cursorsMapper;

    @Override
    public Cursors getCursorsBybarcode(String c_order) throws Exception {
        return cursorsMapper.getCursorsBybarcode(c_order);
    }

    @Override
    public int updateCursors(Cursors cursors) throws Exception {
        return cursorsMapper.updateCursors(cursors);
    }

    @Override
    public int addCursors(Cursors cursors) throws Exception {
        return cursorsMapper.addCursors(cursors);
    }

    @Override
    public List<Cursors> getCursors() throws Exception {
        return cursorsMapper.getCursors();
    }
}
