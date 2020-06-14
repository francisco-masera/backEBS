package ebs.back.service;

import org.springframework.stereotype.Service;

import ebs.back.entity.Config;
import ebs.back.repository.ConfigRepository;

@Service
public class ConfigService extends BaseService<Config, ConfigRepository> {

}
