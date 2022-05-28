package com.example.laboratory_work;

import org.springframework.data.mongodb.repository.MongoRepository;

interface Repository extends MongoRepository<DataClass, Integer> {

}
