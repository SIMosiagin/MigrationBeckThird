package uplaodEmployee.dao.implementation;

import uplaodEmployee.dao.intefaces.MappingWithExcelIntDao;
import uplaodEmployee.entity.MappingWithExcel;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("MappingWithExcelIntDao")
public class MappingWithExcelDao extends AbstractDao implements MappingWithExcelIntDao {

    public void save(MappingWithExcel object) {
        persist(object);
    }

    public ArrayList<MappingWithExcel> findAll() {
        Criteria criteria = getSession().createCriteria(MappingWithExcel.class);
        return (ArrayList<MappingWithExcel>)criteria.list();
    }

    public MappingWithExcel findById(int id) {
        Criteria criteria = getSession().createCriteria(MappingWithExcel.class);
        criteria.add(Restrictions.eq("id", id));
        return (MappingWithExcel)criteria.uniqueResult();
    }

    public void deleteById(int id) {
        Query query = getSession().createQuery("delete from  MappingWithExcel   where id = :id");
        query.setInteger("id",id);
        query.executeUpdate();

    }

    public void saveOrUpdate(MappingWithExcel object) {
        saveOrUpdateIfExist(object);
    }

    public void deleteAll(){
        Query query = getSession().createQuery("delete from  MappingWithExcel");
        query.executeUpdate();
    }
}
