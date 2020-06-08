package ebs.back.service;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;

import ebs.back.entity.Stock;
import ebs.back.repository.StockRepository;

@Service
public class StockService extends BaseService<Stock, StockRepository> {

}
