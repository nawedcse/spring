package com.example.spring.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring.filter.IdsFilter;
import com.example.spring.jpa.BasicRepositoryCustom;

public abstract class AbstractBasicService<ENTITY extends BasicTableDtoConverter<DTO>, DTO extends HasId, FILTER extends IdsFilter, REPOSITORY extends JpaRepository<ENTITY, Long> & BasicRepositoryCustom<ENTITY, FILTER>> {

//    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractBasicService.class);

    private final Class<ENTITY> entityClazz;
    private final Class<DTO> dtoClazz;
    private final Class<FILTER> filterClazz;

    public AbstractBasicService(Class<ENTITY> entityClazz, Class<DTO> dtoClazz, Class<FILTER> filterClazz) {
        this.entityClazz = entityClazz;
        this.dtoClazz = dtoClazz;
        this.filterClazz = filterClazz;
    }

    protected abstract REPOSITORY getRepository();

    protected Class<ENTITY> getEntityClazz() {
        return this.entityClazz;
    }

    protected Class<DTO> getDtoClazz() {
        return this.dtoClazz;
    }

    protected Class<FILTER> getFilterClazz() {
        return this.filterClazz;
    }

    public List<DTO> findAllDefault(FILTER filter) {
        final List<DTO> result = new ArrayList<>();

        for (final ENTITY entity : getRepository().findAll(filter)) {
            result.add(convertToDTO(entity));
        }
        return result;
    }

    public int countDefault(FILTER filter) {
        return getRepository().count(filter);
    }

    public DTO findByIdDefault(Long id) {
        ENTITY entity = getEntityById(id);

        return convertToDTO(entity);
    }

    public void deleteByIdDefault(Long id) {
        ENTITY entity = getEntityById(id);

        getRepository().deleteById(entity.getId());
    }

    public long createDefault(DTO dto) {
        if (dto.getId() != null) {
            throw new javax.ws.rs.BadRequestException("Can't create entity with id");
        }

        return getRepository().save(convertToEntity(dto))
                .getId();
    }

    public void updateDefault(Long id, DTO dto) {
        if (dto.getId() == null) {
            throw new javax.ws.rs.BadRequestException("Can't update entity without id");
        }
        if (!id.equals(dto.getId())) {
            throw new javax.ws.rs.BadRequestException("Can't update entity. IDs are not matching");
        }

        ENTITY entity = getEntityById(dto.getId());

        getRepository().save(convertToEntity(entity, dto));
    }

    protected ENTITY getEntityById(Long id) {
        FILTER filter = null;
        try {
            filter = getFilterClazz().newInstance();
        }
        catch (InstantiationException | IllegalAccessException e) {
//            AbstractBasicService.LOGGER.error("couldn't create filter for id: " + id, e);
            throw new InternalServerErrorException("couldn't create filter for id: " + id);
        }
        filter.setIds(Arrays.asList(id));
        ENTITY containerEntity = getRepository().findOne(filter);

        if (containerEntity == null) {
            throw new javax.ws.rs.NotFoundException();
        }

        return containerEntity;
    }

    protected ENTITY convertToEntity(DTO containerDto) {
        try {
            return convertToEntity(getEntityClazz().newInstance(), containerDto);
        }
        catch (InstantiationException | IllegalAccessException e) {
//            AbstractBasicService.LOGGER.error("couldn't create container entity for id: " + containerDto.getId(), e);
            throw new InternalServerErrorException("couldn't create container entity for id: " + containerDto.getId());
        }
    }

    protected ENTITY convertToEntity(ENTITY containerEntity, DTO containerDto) {
        return DtoEntityConverterUtils.convertToEntity(containerEntity, containerDto);
    }

    protected DTO convertToDTO(ENTITY entity) {
        return DtoEntityConverterUtils.convertToDto(entity, getDtoClazz());
    }

}