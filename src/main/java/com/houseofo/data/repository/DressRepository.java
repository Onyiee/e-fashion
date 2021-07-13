package com.houseofo.data.repository;

import com.houseofo.data.dtos.DressDto;
import com.houseofo.data.model.Dress;
import com.houseofo.data.model.Size;
import com.houseofo.data.model.Type;
import com.houseofo.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DressRepository extends MongoRepository<Dress,String> {
    List<Dress> findDressesByDesigner(User designer);
    List<Dress> findDressesBySize(Size size);
    List<Dress> findDressesByType(Type type);
    List<Dress> findAll();
}
