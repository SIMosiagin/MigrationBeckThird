package uplaodEmployee.service.intefaces;

import uplaodEmployee.entity.MappingWithExcel;

import java.util.ArrayList;

public interface MappingWithExcelService {

    public void save(MappingWithExcel object);

    public ArrayList<MappingWithExcel> findAll();

    public MappingWithExcel findById(int id);

    public void deleteById(int id);

    public void saveOrUpdate(MappingWithExcel object);

    public void deleteAll();

    public MappingWithExcel getMappingWithExcel (ArrayList<Object> arrayList);

    public void parseMappingTransit( String arrayMap[][]);

    public MappingWithExcel getByNameAndDisc(ArrayList<MappingWithExcel> mappingWithExcels, String name, String disc);
}
