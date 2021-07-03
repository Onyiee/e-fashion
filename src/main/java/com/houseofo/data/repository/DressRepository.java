package com.houseofo.data.repository;

import com.houseofo.data.model.Dress;
import com.houseofo.data.model.Size;
import com.houseofo.data.model.Type;
import com.houseofo.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DressRepository extends MongoRepository<Dress,String> {
//    List<Dress> findDressesByName(String name);
    List<Dress> findDressesByDesigner(User designer);
    List<Dress> findDressesBySize(Size size);
    List<Dress> findDressesByType(Type type);
}
