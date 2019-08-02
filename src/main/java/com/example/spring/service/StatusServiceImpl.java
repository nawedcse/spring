package com.example.spring.service;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.transaction.annotation.Transactional;

import com.example.spring.dto.Status;
import com.example.spring.entity.StatusEntity;
import com.example.spring.filter.StatusFilter;
import com.example.spring.jpa.StatusRepository;
import com.example.spring.utils.AbstractBasicService;

@RestService
@Transactional
public class StatusServiceImpl extends AbstractBasicService<StatusEntity, Status, StatusFilter, StatusRepository> implements StatusService {

	public StatusServiceImpl(Class<StatusEntity> entityClazz, Class<Status> dtoClazz, Class<StatusFilter> filterClazz) {
		super(StatusEntity.class, Status.class, StatusFilter.class);
		// TODO Auto-generated constructor stub
	}

	@Resource
	private StatusRepository statusRepository;

	@Override
    protected StatusRepository getRepository() {
        return this.statusRepository;
    }

	@Override
	public List<Status> findAll(@Valid StatusFilter filter) {
		// TODO Auto-generated method stub
		return super.findAllDefault(filter);
	}

	@Override
	public Status findById(@NotNull Long id) {
		// TODO Auto-generated method stub
		return super.findByIdDefault(id);
	}

	@Override
	public void deleteById(@NotNull Long id) {
		// TODO Auto-generated method stub
		super.deleteByIdDefault(id);
	}

	@Override
	public Long create(Status status) {
		// TODO Auto-generated method stub
		return super.createDefault(status);
	}

	@Override
	public void update(@NotNull Long id, Status status) {
		// TODO Auto-generated method stub
		 super.updateDefault(id, status);
	}

	@Override
	public int count(@Valid StatusFilter filter) {
		// TODO Auto-generated method stub
		return super.countDefault(filter);
	}

	@Override
	public void activate(@NotNull Long id) {
		// TODO Auto-generated method stub
		Status dto = super.findByIdDefault(id);
//      dto.setIsDeactived(Boolean.FALSE);
      super.updateDefault(id, dto);
	}

	@Override
	public void deactivate(@NotNull Long id) {
		// TODO Auto-generated method stub
		Status dto = super.findByIdDefault(id);
//        dto.setIsDeactived(Boolean.TRUE);
        super.updateDefault(id, dto);
	}
	
	
	

}
