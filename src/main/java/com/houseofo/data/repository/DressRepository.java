package com.houseofo.data.repository;

import com.houseofo.data.model.Dress;
import com.houseofo.data.model.Size;
import com.houseofo.data.model.Type;
import com.houseofo.data.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface DressRepository extends MongoRepository<Dress,String> {
    List<Dress> findDressesByDesigner(User designer);
    List<Dress> findDressesBySize(Size size);
    List<Dress> findDressesByType(Type type);
    List<Dress> findAll();
    Optional<Dress> findDressByDressName(String dressName);
//    Dress findDressByDressName(String dressName);
//    List<Dress> findDressesByPriceBetween(BigDecimal startRange, BigDecimal endRange);
}
