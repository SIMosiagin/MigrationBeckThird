package uplaodEmployee.dao.intefaces;

import uplaodEmployee.entity.MappingWithExcel;

import java.util.ArrayList;


public interface MappingWithExcelIntDao {

    public void save(MappingWithExcel object);

    public ArrayList<MappingWithExcel> findAll();

    public MappingWithExcel findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(MappingWithExcel object);

    public void deleteAll();
}
