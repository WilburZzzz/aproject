package com.zwb.aproject.es.dao;

import com.zwb.aproject.es.vo.ESUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ESDao extends ElasticsearchRepository<ESUser, Long> {

}