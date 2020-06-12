package ebs.back.service;

import org.springframework.stereotype.Service;

import ebs.back.entity.Stock;
import ebs.back.repository.StockRepository;

@Service
public class StockService extends BaseService<Stock, StockRepository> {

}
