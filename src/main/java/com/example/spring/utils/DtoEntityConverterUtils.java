package com.example.spring.utils;

public class DtoEntityConverterUtils {

	public static <ENTITY extends BasicTableDtoConverter<DTO>, DTO extends HasId> ENTITY convertToEntity(final ENTITY containerEntity, final DTO containerDto) {
        final ENTITY convertedContainerEntity = DtoEntityConverterUtils.convertToEntityDefaults(containerEntity, containerDto);
        containerEntity.updateEntity(containerDto);
        return convertedContainerEntity;
    }
	
	
	public static <ENTITY extends BasicTableDtoConverter<DTO>, DTO extends HasId> DTO convertToDto(final ENTITY entity, final Class<DTO> dtoClazz) {
        final DTO dto = DtoEntityConverterUtils.convertToDtoDefaults(entity, dtoClazz);
        if (dto != null) {
            entity.updateDto(dto);
        }
        return dto;
    }
	
	private static <ENTITY extends BasicTableDtoConverter<DTO>, DTO extends HasId> DTO convertToDtoDefaults(final ENTITY entity, final Class<DTO> dtoClazz) {
        if (entity == null) {
            return null;
        }

        try {
            final DTO dto = dtoClazz.newInstance();
            dto.setId(entity.getId());
            if ((dto instanceof HasOptlock && !(entity instanceof HasOptlock)) || (!(dto instanceof HasOptlock) && entity instanceof HasOptlock)) {
                throw new HasOptlockException(dto.getClass(), entity.getClass());
            }
            if (dto instanceof HasOptlock && entity instanceof HasOptlock) {
                ((HasOptlock) dto).setOptlock(((HasOptlock) entity).getOptlock());
            }
            return dto;
        }
        catch (InstantiationException | IllegalAccessException e) {
//            DtoEntityConverterUtils.LOGGER.error("couldn't create dto for entity id: " + entity.getId(), e);
        }
        catch (final HasOptlockException e) {
//            DtoEntityConverterUtils.LOGGER.error(e.getLocalizedMessage(), e);
        }
        return null;
    }


	private static <ENTITY extends BasicTableDtoConverter<DTO>, DTO extends HasId> ENTITY convertToEntityDefaults(final ENTITY containerEntity,
            final DTO containerDto) {
        containerEntity.setId(containerDto.getId());
        if ((containerDto instanceof HasOptlock && !(containerEntity instanceof HasOptlock))
                || (!(containerDto instanceof HasOptlock) && containerEntity instanceof HasOptlock)) {
            try {
                throw new HasOptlockException(containerDto.getClass(), containerEntity.getClass());
            }
            catch (final HasOptlockException e) {
//                DtoEntityConverterUtils.LOGGER.error(e.getLocalizedMessage(), e);
            }
        }
        if (containerDto instanceof HasOptlock && containerEntity instanceof HasOptlock) {
            ((HasOptlock) containerEntity).setOptlock(((HasOptlock) containerDto).getOptlock());
        }
        return containerEntity;
    }


	 public static <ENTITY extends FetchTableDtoConverter<DTO, FETCHOPTIONS>, DTO extends HasId, FETCHOPTIONS> DTO convertToDto(final ENTITY entity,
	            final FETCHOPTIONS fetchOptions, final Class<DTO> dtoClazz) {
	        final DTO dto = DtoEntityConverterUtils.convertToDtoDefaults(entity, dtoClazz);
	        if (dto != null) {
	            entity.updateDto(dto, fetchOptions);
	        }
	        return dto;
	    }

}
